package org.chronotics.db.mybatis;

import java.util.List;

public class SqlObjectCommand extends SqlObject {
    public static String SELECT = "SELECT";
    public static String INSERT = "INSERT INTO";
    public static String UPDATE = "UPDATE";
    public static String DELETE = "DELETE";
    public static String FROM = "FROM";
    public static String WHERE = "WHERE";
    public static String WHERENOT = "WHERE NOT";
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
    public void build(List<Object> _statement) {
        // insert Command
        _statement.add(getName());

        for(int i = 0; i < childObjects.size(); i++) {
            SqlObject object = childObjects.get(i);
            object.build(_statement);
            if(i == childObjects.size() - 1) {
                break;
            }
            // insert "," in the case of SELECT or FROM
            if(name.equals(SELECT) ||
                    name.equals(FROM) ||
                    name.equals(ORDERBY)
                    ) {
                _statement.add(COMMA);
            } else {
            }
        }
    }
}
