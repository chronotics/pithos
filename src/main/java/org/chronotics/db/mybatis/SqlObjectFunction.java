package org.chronotics.db.mybatis;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlObjectFunction extends SqlObject {

    public static String SUM = "SUM";
    public static String COUNT = "COUNT";
    public static String PARENTHESES = "(";
//    public class SUM {
//        public SUM() {
//            objectType = OBJECTTYPE.STATEMENT;
//            name = SUM;
//        }
//    }
//
//    public class COUNT {
//        public COUNT() {
//            objectType = OBJECTTYPE.STATEMENT;
//            name = COUNT;
////            addChildObject(new SqlObjectValue<String>("ss"));
//        }
//    }
//
//    public class Parentheses {
//        public Parentheses() {
//            objectType = OBJECTTYPE.STATEMENT;
//            name = PARENTHESES;
//        }
//    }

    public SqlObjectFunction(String _name) {
//        super(OBJECTTYPE.STATEMENT);
        setType(OBJECTTYPE.STATEMENT);
        setName(_name);
    }

//    private SqlObjectFunction(OBJECTTYPE _type) {
//        super(_type);
//    }

//    @Override
//    public void addChildObject(SqlObject _obj) {
//
//    }

    @Override
    public void build() {
//        Map<String, SqlObject> newChild = new LinkedHashMap<>();
//
//        newChild.put(SqlObjectCommand.class.getName(), new SqlObjectValue<String>(getName()));
//
//        int index = 0;
//        for(Map.Entry<String, SqlObject> entry: childObjects.entrySet()) {
//            newChild.put(entry.getKey(), entry.getValue());
//
//            if(index == 0) {
//                newChild.put(
//                        SqlObjectValue.class.getName(),
//                        new SqlObjectValue<String>(PARENTHESES));
//            }
//            if (index == childObjects.size()-1) {
//                break;
//            }
//
//            if (name.equals(PARENTHESES)) {
//                newChild.put(
//                        SqlObjectValue.class.getName(),
//                        new SqlObjectValue(KEYWORD.COMMA));
//            } else {
//
//            }
//            index++;
//        }
//
//        newChild.put(
//                SqlObjectValue.class.getName(),
//                new SqlObjectValue<String>(")"));
//
//        childObjects.clear();
//        childObjects.putAll(newChild);

        List<SqlObject> newChildren = new ArrayList<>();
        newChildren.add(new SqlObjectValue<String>(getName()));

        for(int i = 0; i < childObjects.size(); i++) {
            newChildren.add(childObjects.get(i));
            if(i == 0 && !name.equals((PARENTHESES))) {
                newChildren.add(new SqlObjectValue<String>(PARENTHESES));
            }
            if(i == childObjects.size() - 1) {
                break;
            }
            if(name.equals(PARENTHESES)) {
                newChildren.add(new SqlObjectValue<String>(SqlObject.KEYWORD.COMMA));
            } else {
            }
        }
        newChildren.add(new SqlObjectValue<String>(")"));
        childObjects.clear();
        childObjects.addAll(newChildren);
    }
}
