package org.chronotics.db.mybatis;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlObjectFunction extends SqlObject {

    public static String SUM = "SUM";
    public static String COUNT = "COUNT";
    public static String PARENTHESIS = "(";

    public SqlObjectFunction(String _name) {
        setName(_name);
    }

    @Override
    public void build(List<Object> _statement, StatementProvider.BUILDTYPE _type) {
        // insert Function
        SqlObjectValue.buildString(_statement,getName());

        for(int i = 0; i < childObjects.size(); i++) {
            // insert ( for general Function, ex) COUNT"("
            if(i == 0 && !name.equals((PARENTHESIS))) {
                SqlObjectValue.buildString(_statement,LPARENTHESIS);
            }

            childObjects.get(i).build(_statement, _type);

            if(i == childObjects.size() - 1) {
                break;
            }
            if(name.equals(PARENTHESIS)) {
                SqlObjectValue.buildString(_statement,COMMA);
            } else {
            }
        }
        // insert ) for all Function
        SqlObjectValue.buildString(_statement,RPARENTHESIS);
    }
}
