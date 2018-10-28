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
    public static String DELETE = "DELETE";
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
        List<Object> columnsForOracle = null;
        for(int i = 0; i < childObjects.size(); i++) {
            if(name.equals(INSERTMULTI)) {
                if(_type == StatementProvider.BUILDTYPE.MYSQL) {
                    if(i == 0) {
                        SqlObjectValue.buildString(_statement, "INTO");
                        childObjects.get(i).build(_statement, _type);
                    }
                    else if(i == 1) {
                        childObjects.get(i).build(_statement, _type);
                        SqlObjectValue.buildString(_statement, VALUES);
                    }
                    else {
                        // i == 2 => List<List<>>
                        List<List<Object>> valueslist =
                                (List<List<Object>>)(((SqlObjectValue)childObjects.get(i)).getObject());
                        for(List<Object> values: valueslist) {

                        }
                        childObjects.get(i).build(_statement, _type);
                    }
                } else if (_type == ORACLE) {
                    if(i == 0) {
                        SqlObjectValue.buildString(_statement, "ALL");
                        tableNameForOracle = (String)(((SqlObjectValue)childObjects.get(i)).getObject());
                    }
                    else if(i==1) {
                        columnsForOracle = (List<Object>)(((SqlObjectValue)childObjects.get(i)).getObject());
                    }
                    else {
                        SqlObjectValue.buildString(_statement, "INTO");
                        SqlObjectValue.buildString(_statement, tableNameForOracle);
                    }
                    if(i%2 == 1) {
                        SqlObjectValue.buildString(_statement, tableNameForOracle);
                    } else {

                    }
                } else {
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
                    name.equals(SET) ||
                    (name.equals(INSERTMULTI) && _type == StatementProvider.BUILDTYPE.MYSQL)
                    ) {
                SqlObjectValue.buildString(_statement,COMMA);
            } else {
            }
        }
    }
}
