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
    public static String BETWEEN = "BETWEEN";
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
    public void build(List<Object> _statement) {
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

        if(name.equals(IN)) {
            _statement.add(new SqlObjectValue(getName()));
            _statement.add(new SqlObjectValue(LPARENTHESIS));
            for(int i = 0; i < childObjects.size(); i++) {
                SqlObject object = childObjects.get(i);
                object.build(_statement);
                if(i == childObjects.size() - 1) {
                    break;
                }
                _statement.add(new SqlObjectValue(COMMA));
            }
            _statement.add(new SqlObjectValue(RPARENTHESIS));
        } else {
            for(int i = 0; i < childObjects.size(); i++) {
                SqlObject object = childObjects.get(i);
                // i = 0 : LEFT OPERAND
                // i = 1 : OPERATOR
                // i >= 2 : RIGHT OPERAND
                if(i == 0) {
                    // insert LEFT OPERAND
                    object.build(_statement);
                    // insert OPERATOR
                    _statement.add(new SqlObjectValue(getName()));
                } else {
                    object.build(_statement);
                }
                if(i == childObjects.size() - 1) {
                    break;
                }
                if((i == childObjects.size()-2) && name.equals(BETWEEN) ) {
                    _statement.add(new SqlObjectValue(AND));
                }
            }
        }
    }
}
