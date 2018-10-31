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


    public SqlObjectOperator(String _name) {
        setName(_name);
    }

    private SqlObject leftOperand = null;

    public SqlObjectOperator setLeftOperand(Object _object) {
        if(_object instanceof SqlObject) {
            leftOperand = (SqlObject)_object;
        } else {
            leftOperand = SqlObjectValue.create(_object, false);
        }
        return this;
    }

    public SqlObjectOperator addRightOperand(Object ..._objects) {
        for(Object object: _objects) {
            addChild(object, true);
        }
        return this;
    }

    public SqlObjectOperator setRightOperand(Object _object, boolean _isParameter) {
        addChild(_object, _isParameter);
        return this;
    }

    @Override
    public void build(List<Object> _statement, StatementProvider.BUILDTYPE _type) {
        assert(leftOperand != null);

        if(getName().equals(EQ) ||
                getName().equals(LT) ||
                getName().equals(LE) ||
                getName().equals(NE) ||
                getName().equals(GE) ||
                getName().equals(GT) ||
                getName().equals(LIKE)
        ) {
            assert(childObjects.size() == 1); // LEFT and RIGHT operand
        }
        if(getName().equals(BETWEEN)
        ) {
            assert(childObjects.size() == 2); // LEFT and RIGHT from and RIGHT to operand
        }
        if(getName().equals(IN)
        ) {
            assert(childObjects.size() >= 1); // LEFT and RIGHT(many)
        }

        // set left operand and operator
        // ex) COL LIKE
        leftOperand.build(_statement, _type);
//        _statement.add(getName());
        SqlObjectValue.buildString(_statement,getName());

        if(name.equals(IN)) {
//            _statement.add(LPARENTHESIS);
            SqlObjectValue.buildString(_statement,LPARENTHESIS);
        }

        for(int i = 0; i < childObjects.size(); i++) {
            childObjects.get(i).build(_statement, _type);
            if((i == childObjects.size()-2) && name.equals(BETWEEN) ) {
//                _statement.add(AND);
                SqlObjectValue.buildString(_statement,AND);
            }
            if(i == childObjects.size() - 1) {
                break;
            }
            if(name.equals(IN)) {
//                _statement.add(COMMA);
                SqlObjectValue.buildString(_statement,COMMA);
            }
        }

        if(name.equals(IN)) {
//            _statement.add(RPARENTHESIS);
            SqlObjectValue.buildString(_statement,RPARENTHESIS);
        }
    }
}
