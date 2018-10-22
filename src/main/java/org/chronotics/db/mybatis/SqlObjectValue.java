package org.chronotics.db.mybatis;

public class SqlObjectValue<T> extends SqlObject {
    SqlObjectValue(SqlObject.VALUETYPE _type) {
        super(_type);
    }

    SqlObjectValue(String _str) {
        super(VALUETYPE.STRING);
        value = (T) _str;
    }

    // super.childObject

    // String
    // Variable (Number, SqlObject...)
    private T value;

    @Override
    public void addChildObject(SqlObject _obj) {

    }

    @Override
    public void build() {

    }
}
