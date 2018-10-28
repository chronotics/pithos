package org.chronotics.db.mybatis;

import java.util.ArrayList;
import java.util.List;

public abstract class SqlObject {

    public static String COMMA = ",";
    public static String LPARENTHESIS = "(";
    public static String RPARENTHESIS = ")";
    public static String AND = "AND";

    protected String name = "";

    public String getName() {
        return name;
    }

    protected void setName(String _name) {
        name = _name;
    }

    protected List<SqlObject> childObjects = new ArrayList<>();

    public SqlObject addChild(Object _object, boolean _isParameter) {
        if(_object instanceof SqlObject) {
            childObjects.add((SqlObject) _object);
        } else {
            childObjects.add(SqlObjectValue.create(_object,_isParameter));
        }
        return this;
    }

    public abstract void build(List<Object> _list, StatementProvider.BUILDTYPE _type);
}
