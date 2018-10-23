package org.chronotics.db.mybatis;

import java.util.List;

public class SqlObjectValue<T> extends SqlObject {
//    SqlObjectValue(SqlObject.VALUETYPE _type) {
//        super(_type);
//    }
//
    // super.childObject

    // String
    // Variable (Number, SqlObject...)
    private T value;

//    private SqlObjectValue(OBJECTTYPE _type) {
//        super(_type);
//    }

    SqlObjectValue(T _object) {
        if(_object instanceof String) {
            setType(OBJECTTYPE.STATEMENT);
        } else {
            setType(OBJECTTYPE.VARIABLE);
        }
        value = (T)_object;
    }

    @Override
    public void addChildObject(SqlObject _obj) {

    }

    @Override
    public void build(List<Object> _statement) {
        _statement.add(value);
//        if(value instanceof String) {
//            _statement.add(new String(value));
//        } else {
//            _statement.add(value);
//        }
    }
}
