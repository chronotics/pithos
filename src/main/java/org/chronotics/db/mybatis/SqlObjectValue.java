package org.chronotics.db.mybatis;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class SqlObjectValue extends SqlObject {
//    private static class Creator<T> {
//         public T create(Class<T> _class) {
//             try {
//                 return _class.newInstance();
//             } catch (InstantiationException e) {
//                 e.printStackTrace();
//             } catch (IllegalAccessException e) {
//                 e.printStackTrace();
//             }
//         }
//    }
//    SqlObjectValue(SqlObject.VALUETYPE _type) {
//        super(_type);
//    }
//
    // super.childObject

    // String
    // Variable (Number, SqlObject...)
    private Object value;

//    private SqlObjectValue(OBJECTTYPE _type) {
//        super(_type);
//    }

//    SqlObjectValue(T _v) {
//        try {
//            this.setValue((Class<T>) String.class, _v);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }
//    }

//    public void setValue(Class<T> _class, T _v) throws IllegalAccessException, InstantiationException {
//        if(_object instanceof String) {
//            setType(OBJECTTYPE.STATEMENT);
//        } else {
//            setType(OBJECTTYPE.VARIABLE);
//        }

    public SqlObjectValue(Object _v) {
//        if(_v instanceof String) {
//            try {
//                value = (T) String.class.newInstance();
//                value = _v;
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//
//        } else if(_v instanceof String[]) {
        if(_v instanceof String[]) {
            StringBuilder builder = new StringBuilder();
            String[] temp = (String[])_v;
            for(String s: temp) {
                builder.append(s);
            }
            value = builder.toString();
        } else {
            try {
                value = _v.getClass().newInstance();
                value = _v;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println(value);
    }

    public static SqlObjectValue create(Object _object) {
        return new SqlObjectValue(_object);
    }

//    public T getValue() {
//        return value;
//    }
//
//    public void setValue(T _v) {
//        value = _v;
//    }

    @Override
    public void addChildObject(SqlObject _obj) {

    }

    @Override
    public void build(List<Object> _statement) {
        if(value instanceof String) {
            System.out.println("value is String tyep");
        } else if(value instanceof String[]) {
            System.out.println("value is String array");
        } else {
            System.out.println("is not String type");
//            _statement.add((Object)value);
//            _statement.add(new String("\'" + value.toString() + "\'"));
        }

        System.out.println(value.toString());
        _statement.add(value.toString());
//        _statement.add(new String("\'" + value.getClass().getDeclaredField("value").get(value) + "\'"));
//            _statement.add(new String("\'" + value + "\'"));
//        _statement.add(value);
//        if(value instanceof String) {
//            _statement.add(new String("\'" + value + "\'"));
//        } else {
////            _statement.add(value);
//            _statement.add(new String("\'" + value + "\'"));
//        }
    }
}
