package org.chronotics.db.mybatis;

import java.util.List;

import static org.chronotics.db.mybatis.StatementProvider.BUILDTYPE.ORACLE;

public class SqlObjectCommand extends SqlObject {
    public static String SELECT = "SELECT";
    public static String INSERTINTO = "INSERT INTO";
//    public static String INSERTCOLUMNS = "INSERTCOLUMNS";
//    public static String VALUESLIST = "VALUESLIST";
    public static String INSERTMULTI = "INSERT";
    public static String UPDATE = "UPDATE";
    public static String DELETE = "DELETE FROM";
    public static String FROM = "FROM";
    public static String WHERE = "WHERE";
    public static String WHERENOT = "WHERE NOT";
    public static String VALUES = "VALUES";
    public static String AND = "AND";
    public static String NOT = "NOT";
    public static String ANDNOT = "AND NOT";
    public static String OR = "OR";
    public static String ORNOT = "OR NOT";
    public static String ORDERBY = "ORDER BY";
    public static String ASC = "ASC";
    public static String DESC= "DESC";
    public static String SET = "SET";
    public static String INNERJOIN = "INNER JOIN";
    public static String LEFTJOIN = "LEFT JOIN";
    public static String RIGHTJOIN = "RIGHT JOIN";
    public static String FULLOUTERJOIN = "FULL OUTER JOIN";
    public static String UNION = "UNION";
    public static String ON = "ON";
    public static String LIMIT = "LIMIT";

    public SqlObjectCommand(String _name) {
        setName(_name);
    }

    @Override
    public void build(List<Object> _statement, StatementProvider.BUILDTYPE _type) {
        // insert Command
        if(!name.equals(VALUES)) {
            SqlObjectValue.buildString(_statement, getName());
        }

        // multiple row insertion in the case of Oracle
        // INSERT ALL
        // INTO t
        // (col1, col2, ... coln)
        // VALUES
        // ('v1', 'v2', ... vn)
        // INTO t
        // (col1, col2, ... coln)
        // VALUES
        // ('v1', 'v2', ... vn)
        // SELECT 1 FROM DUAL

        // multiple row insertion in the case of Mysql
        // INSERT
        // INTO t
        // (col1, col2, ... coln)
        // VALUES
        // ('v1', 'v2', ... vn)
        // ,
        // ('v1', 'v2', ... vn)
        // ,
        // ('v1', 'v2', ... vn)

        String tableNameForOracle = "";
        SqlObjectValue columnsForOracle = null;
        for(int i = 0; i < childObjects.size(); i++) {
            if(name.equals(INSERTMULTI)) {
                if(_type == StatementProvider.BUILDTYPE.MYSQL) {
                    // Table Name
                    if(i == 0) {
                        SqlObjectValue.buildString(_statement, "INTO");
                        childObjects.get(i).build(_statement, _type);
                    }
                    // Columns
                    else if(i == 1) {
                        // i == 1 => List<Object>
                        childObjects.get(i).build(_statement, _type);
                    }
                    // Parameters
                    else {
                        assert( i < 3);
                        SqlObjectValue.buildString(_statement, VALUES);
                        // i == 2 => List<List<Object>>
                        SqlObjectValue sqlObject = (SqlObjectValue)childObjects.get(i);
                        boolean isParameter = sqlObject.isParameter();
                        int count = 0;
                        List<List<Object>> valuesList =
                                (List<List<Object>>)(sqlObject.getObject());
                        for(List<Object> e: valuesList) {
                            SqlObjectValue values =
                                    SqlObjectValue.create(e, isParameter);
                            values.build(_statement, _type);
                            if(count == valuesList.size() -1) {
                                break;
                            }
                            SqlObjectValue.buildString(_statement, COMMA);
                            count++;
                        }
                    }
                } else if (_type == ORACLE) {
                    if(i == 0) {
                        SqlObjectValue.buildString(_statement, "ALL");
                        tableNameForOracle = (String)(((SqlObjectValue)childObjects.get(i)).getObject());
                    }
                    else if(i==1) {
                        columnsForOracle = (SqlObjectValue)childObjects.get(i);
                    }
                    else {
                        assert( i < 3);
                        // i == 2 => List<List<Object>>
                        SqlObjectValue sqlObject = (SqlObjectValue)childObjects.get(i);
                        boolean isParameter = sqlObject.isParameter();
                        int count = 0;
                        List<List<Object>> valuesList =
                                (List<List<Object>>)(sqlObject.getObject());
                        for(List<Object> e: valuesList) {
                            // INTO t
                            // (c1, c2, ... cn)
                            SqlObjectValue.buildString(_statement, "INTO");
                            SqlObjectValue.buildString(_statement, tableNameForOracle);
                            columnsForOracle.build(_statement, _type);
                            // VALUES
                            SqlObjectValue.buildString(_statement, VALUES);
                            SqlObjectValue values =
                                    SqlObjectValue.create(e, isParameter);
                            values.build(_statement, _type);
                            if(count == valuesList.size() -1) {
                                break;
                            }
                            count++;
                        }
                    }
                } else {
                    // unsupported type
                    assert(false);
                }
            } else {
                childObjects.get(i).build(_statement, _type);
            }

            if(name.equals(VALUES)) {
                if(i == 0) {
                    SqlObjectValue.buildString(_statement, SqlObjectCommand.VALUES);
                }
            }
            if(i == childObjects.size() - 1) {
                break;
            }
            // insert "," in the case of SELECT or FROM
            if(name.equals(SELECT) ||
                    name.equals(FROM) ||
                    name.equals(ORDERBY) ||
                    name.equals(SET)
                    ) {
                SqlObjectValue.buildString(_statement,COMMA);
            } else {
            }
        }
    }
}
