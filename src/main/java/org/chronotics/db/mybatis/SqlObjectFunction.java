package org.chronotics.db.mybatis;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlObjectFunction extends SqlObject {

    public static String SUM = "SUM";
    public static String COUNT = "COUNT";
    public static String PARENTHESIS = "(";
//    public class SUM

    public SqlObjectFunction(String _name) {
        setName(_name);
    }

    @Override
    public void build(List<Object> _statement, StatementProvider.BUILDTYPE _type) {
        // insert Function
        _statement.add(getName());

        for(int i = 0; i < childObjects.size(); i++) {
            SqlObject object = childObjects.get(i);
            object.build(_statement, _type);
            // insert ( for general Function, ex) COUNT"("
            if(i == 0 && !name.equals((PARENTHESIS))) {
                _statement.add(LPARENTHESIS);
            }
            if(i == childObjects.size() - 1) {
                break;
            }
            if(name.equals(PARENTHESIS)) {
                _statement.add(COMMA);
            } else {
            }
        }
        // insert ) for all Function
        _statement.add(RPARENTHESIS);
    }
}
