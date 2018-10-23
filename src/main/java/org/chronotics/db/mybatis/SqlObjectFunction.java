package org.chronotics.db.mybatis;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlObjectFunction extends SqlObject {

    public static String SUM = "SUM";
    public static String COUNT = "COUNT";
    public static String PARENTHESIS = "(";
//    public class SUM {
//        public SUM() {
//            objectType = OBJECTTYPE.STATEMENT;
//            name = SUM;
//        }
//    }
//
//    public class COUNT {
//        public COUNT() {
//            objectType = OBJECTTYPE.STATEMENT;
//            name = COUNT;
////            addChildObject(new SqlObjectValue<String>("ss"));
//        }
//    }
//
//    public class Parentheses {
//        public Parentheses() {
//            objectType = OBJECTTYPE.STATEMENT;
//            name = PARENTHESES;
//        }
//    }

    public SqlObjectFunction(String _name) {
//        super(OBJECTTYPE.STATEMENT);
        setType(OBJECTTYPE.STATEMENT);
        setName(_name);
    }

//    private SqlObjectFunction(OBJECTTYPE _type) {
//        super(_type);
//    }

//    @Override
//    public void addChildObject(SqlObject _obj) {
//
//    }

    @Override
    public void build(List<Object> _statement) {
        // insert Function
//        _statement.add(new SqlObjectValue<>(getName()));
        _statement.add(new String(getName()));

        for(int i = 0; i < childObjects.size(); i++) {
            SqlObject object = childObjects.get(i);
            object.build(_statement);
            // insert ( for general Function, ex) COUNT"("
            if(i == 0 && !name.equals((PARENTHESIS))) {
//                _statement.add(new SqlObjectValue<>(LPARENTHESIS));
                _statement.add(new String(LPARENTHESIS));
            }
            if(i == childObjects.size() - 1) {
                break;
            }
            if(name.equals(PARENTHESIS)) {
//                _statement.add(new SqlObjectValue<>(COMMA));
                _statement.add(new String(COMMA));
            } else {
            }
        }
        // insert ) for all Function
//        _statement.add(new SqlObjectValue<>(RPARENTHESIS));
        _statement.add(new String(RPARENTHESIS));
    }
}
