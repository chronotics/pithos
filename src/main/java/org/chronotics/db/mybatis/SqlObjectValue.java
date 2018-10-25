package org.chronotics.db.mybatis;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
    private Object value = null;

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

    private static class Factory {

        public static Object createBoolean(Object _o) {
            Constructor<Boolean> c = null;
            try {
                c = Boolean.class.getConstructor(
                        new Class[]{boolean.class}
                );
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            Object ret = null;
            try {
                ret = c.newInstance(new Object[]{new Boolean((Boolean) _o)});
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return ret;
        }

        public static Object createByte(Object _o) {
            Constructor<Byte> c = null;
            try {
                c = Byte.class.getConstructor(
                        new Class[]{byte.class}
                );
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            Object ret = null;
            try {
                ret = c.newInstance(new Object[]{new Byte((Byte) _o)});
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return ret;
        }

        public static Object createCharacter(Object _o) {
            Constructor<Character> c = null;
            try {
                c = Character.class.getConstructor(
                        new Class[]{char.class}
                );
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            Object ret = null;
            try {
                ret = c.newInstance(new Object[]{new Character((Character) _o)});
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return ret;
        }

        public static Object createShort(Object _o) {
            Constructor<Short> c = null;
            try {
                c = Short.class.getConstructor(
                        new Class[]{short.class}
                );
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            Object ret = null;
            try {
                ret = c.newInstance(new Object[]{new Short((Short) _o)});
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return ret;
        }

        public static Object createInteger(Object _o) {
            Constructor<Integer> c = null;
            try {
                c = Integer.class.getConstructor(
                        new Class[]{int.class}
                );
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            Object ret = null;
            try {
                ret = c.newInstance(new Object[]{new Integer((Integer) _o)});
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return ret;
        }

        public static Object createLong(Object _o) {
            Constructor<Long> c = null;
            try {
                c = Long.class.getConstructor(
                        new Class[]{long.class}
                );
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            Object ret = null;
            try {
                ret = c.newInstance(new Object[]{new Long((Long) _o)});
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return ret;
        }

        public static Object createFloat(Object _o) {
            Constructor<Float> c = null;
            try {
                c = Float.class.getConstructor(
                        new Class[]{float.class}
                );
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            Object ret = null;
            try {
                ret = c.newInstance(new Object[]{new Float((Float) _o)});
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return ret;
        }

        public static Object createDouble(Object _o) {
            Constructor<Double> c = null;
            try {
                c = Double.class.getConstructor(
                        new Class[]{double.class}
                );
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            Object ret = null;
            try {
                ret = c.newInstance(new Object[]{new Double((Double) _o)});
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return ret;
        }
    }

    public SqlObjectValue(Object _v) {
//        Integer i = new Integer(1);
//        Integer x = null;
//        Object o = null;
//        o = Factory.createInteger((Object) i);
//
//        try {
//            try {
//                Constructor<Integer> c = Integer.class.getConstructor(
//                        new Class[]{int.class}
//                );
//                x = c.newInstance(new Object[]{new Integer(i)});
//                o = x;
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//
//            x = i.getClass().newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        x = 1;

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
        if (_v instanceof String[]) {
            StringBuilder builder = new StringBuilder();
            String[] temp = (String[]) _v;
            for (String s : temp) {
                builder.append(s);
            }
            value = builder.toString();
        } else if (_v instanceof Boolean) {
            value = Factory.createBoolean(_v);
        } else if (_v instanceof Byte) {
            value = Factory.createByte(_v);
        } else if (_v instanceof Character) {
            value = Factory.createCharacter(_v);
        } else if (_v instanceof Short) {
            value = Factory.createShort(_v);
        } else if (_v instanceof Integer) {
            value = Factory.createInteger(_v);
        } else if (_v instanceof Long) {
            value = Factory.createLong(_v);
        } else if (_v instanceof Float) {
            value = Factory.createFloat(_v);
        } else if (_v instanceof Double) {
            value = Factory.createDouble(_v);
        } else {
                value = _v;
//            try {
//                value = _v.getClass().newInstance();
//                value = _v;
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//            try {
//                try {
//                    value = _v.getClass()
//                            .getDeclaredConstructor()
//                            .newInstance(_v);
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                } catch (NoSuchMethodException e) {
//                    e.printStackTrace();
//                }
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
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

//    @Override
//    public void addChildObject(SqlObject _obj) {
//
//    }

    @Override
    public void build(List<Object> _statement) {
        if (value instanceof String) {
            System.out.println("value is String tyep");
        } else if (value instanceof String[]) {
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
