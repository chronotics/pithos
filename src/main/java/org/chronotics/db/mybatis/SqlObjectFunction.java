package org.chronotics.db.mybatis;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlObjectFunction extends SqlObject {

    public static String SUM = "SUM";
    public static String COUNT = "COUNT";
    public static String MIN = "MIN";
    public static String MAX = "MAX";
    public static String AVG = "AVG";
    public static String PARENTHESIS = "(";

    private boolean isCommaInserted = false;

    public SqlObjectFunction(String _name, boolean _isCommaInserted) {
        setName(_name);
        isCommaInserted = _isCommaInserted;
    }

    @Override
    public void build(List<Object> _statement, StatementProvider.BUILDTYPE _type) {
        // insert Function
        String name = getName();
        if(!name.equals(PARENTHESIS)) {
           name += LPARENTHESIS;
        }
        SqlObjectValue.buildString(_statement,name);

        for(int i = 0; i < childObjects.size(); i++) {

            childObjects.get(i).build(_statement, _type);

            if(i == childObjects.size() - 1) {
                break;
            }
            if(name.equals(PARENTHESIS) && isCommaInserted) {
                SqlObjectValue.buildString(_statement, COMMA);
            }
        }
        // insert ) for all Function
        SqlObjectValue.buildString(_statement,RPARENTHESIS);
    }
}
