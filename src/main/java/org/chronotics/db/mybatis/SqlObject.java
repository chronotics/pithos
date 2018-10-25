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

    public SqlObject put(Object ..._objects) {
        for(Object object: _objects) {
            if(object instanceof SqlObject) {
                childObjects.add((SqlObject) object);
            } else {
                childObjects.add(SqlObjectValue.create(object));
            }
        }
        return this;
    }

    public abstract void build(List<Object> _list);
}
