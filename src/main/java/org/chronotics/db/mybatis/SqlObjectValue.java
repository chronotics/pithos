package org.chronotics.db.mybatis;

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
//        super(OBJECTTYPE.STATEMENT);

        if(_object instanceof String) {
            setType(OBJECTTYPE.STATEMENT);
        } else {
            setType(OBJECTTYPE.VARIABLE);
        }
        value = (T) _object;
    }

    @Override
    public void addChildObject(SqlObject _obj) {

    }

    @Override
    public void build() {
    }
}
