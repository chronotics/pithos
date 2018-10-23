package org.chronotics.db.mybatis;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SqlObjectCommand extends SqlObject {
    //        private String className = this.getClass().getName();
//        public String getClassName() {
//            return className;
//        }

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
//    public static String ASC = "ASC";
//    public static String DESC= "DESC";
    public static String SET = "SET";
    public static String INNERJOIN = "INNER JOIN";
    public static String LEFTJOIN = "LEFT JOIN";
    public static String RIGHTJOIN = "RIGHT JOIN";
    public static String FULLOUTERJOIN = "FULL OUTER JOIN";
    public static String ON = "ON";

//    public static class Select {
//        Select() {
//            objectType = OBJECTTYPE.STATEMENT;
//            name = SELECT;
//        }
//    }
//    public static class Where {
//       Where() {
//           objectType = OBJECTTYPE.STATEMENT;
//           name = WHERE;
//       }
//    }
//
//    public static class From {
//        From() {
//            objectType = OBJECTTYPE.STATEMENT;
//            name = FROM;
//        }
//    }

    public SqlObjectCommand(String _name) {
//        super(OBJECTTYPE.STATEMENT);
        setType(OBJECTTYPE.STATEMENT);
        setName(_name);
    }
//    private SqlObjectCommand(OBJECTTYPE _type) {
//        super(_type);
//    }

//    @Override
//    public void addChildObject(SqlObject _obj) {
//    }

    @Override
    public void build(List<Object> _statement) {
        // insert Command
//        _statement.add(new SqlObjectValue<>(getName()));
        _statement.add(new String(getName()));

        for(int i = 0; i < childObjects.size(); i++) {
            SqlObject object = childObjects.get(i);
            object.build(_statement);
            if(i == childObjects.size() - 1) {
                break;
            }
            // insert "," in the case of SELECT or FROM
            if(name.equals(SELECT) ||
                    name.equals(FROM)) {
//                _statement.add(new SqlObjectValue<>(COMMA));
                _statement.add(new String(COMMA));
            } else {
            }
        }
    }
}
