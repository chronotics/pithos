package org.chronotics.db.mybatis;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlObjectOperator extends SqlObject {

    public static String EQ = "=";
    public static String LT = "<";
    public static String LE = "<=";
    public static String NE = "<>";
    public static String GE = ">=";
    public static String GT = ">";
    public static String BETWEEN = "BETWEEDN";
    public static String LIKE = "LIKE";
    public static String IN = "IN";

//    public class EQ {
//        public EQ() {
//            objectType = OBJECTTYPE.STATEMENT;
//            name = EQ;
//        }
//    }
//
//    public class BETWEEN {
//        public BETWEEN() {
//            objectType = OBJECTTYPE.STATEMENT;
//            name = BETWEEN;
//        }
//    }

    public SqlObjectOperator(String _name) {
        setType(OBJECTTYPE.STATEMENT);
        setName(_name);
    }
//    private SqlObjectOperator(OBJECTTYPE _type) {
//        super(_type);
//    }

//    @Override
//    public void addChildObject(SqlObject _obj) {
//
//    }

    @Override
    public void build() {
        if(getName().equals(EQ) ||
                getName().equals(LT) ||
                getName().equals(LE) ||
                getName().equals(NE) ||
                getName().equals(GE) ||
                getName().equals(GT) ||
                getName().equals(LIKE)
        ) {
            assert(childObjects.size() == 2); // LEFT and RIGHT operand
        }
        if(getName().equals(BETWEEN)
        ) {
            assert(childObjects.size() == 3); // LEFT and RIGHT from and RIGHT to operand
        }
        if(getName().equals(IN)
        ) {
            assert(childObjects.size() >= 2); // LEFT and RIGHT(many)
        }

//        Map<String, SqlObject> newChild = new LinkedHashMap<>();
//
//        int index = 0;
//        for(Map.Entry<String, SqlObject> entry: childObjects.entrySet()) {
//            newChild.put(entry.getKey(), entry.getValue());
//            if(index == 0) {
//            }
//            if (index == childObjects.size()-1) {
//                break;
//            }
//
//            index++;
//        }
//
//
//        childObjects.clear();
//        childObjects.putAll(newChild);

        List<SqlObject> newChildren = new ArrayList<>();

        if(name.equals(IN)) {
            newChildren.add(new SqlObjectValue<String>(getName()));
            newChildren.add(new SqlObjectValue<String>("("));
            for(int i = 0; i < childObjects.size(); i++) {
                newChildren.add(childObjects.get(i));
                if(i == childObjects.size() - 1) {
                    break;
                }
                newChildren.add(new SqlObjectValue<String>(KEYWORD.COMMA));
            }
            newChildren.add(new SqlObjectValue<String>(")"));
        } else {
            for(int i = 0; i < childObjects.size(); i++) {
                newChildren.add(childObjects.get(i));
                if(i == 0) {
                    newChildren.add(new SqlObjectValue<String>(getName()));
                }
                if(i == childObjects.size() - 1) {
                    break;
                }
                if((i == childObjects.size()-2) && name.equals(BETWEEN) ) {
                   newChildren.add(new SqlObjectValue<String>("AND"));
                }
            }
        }
        childObjects.clear();
        childObjects.addAll(newChildren);
    }
}
