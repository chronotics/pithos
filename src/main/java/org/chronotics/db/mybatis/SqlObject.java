package org.chronotics.db.mybatis;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class SqlObject {

    public static String COMMA = ",";
    public static String LPARENTHESIS = "(";
    public static String RPARENTHESIS = ")";
    public static String AND = "AND";

    public static enum OBJECTTYPE {
//        COMMAND("COMMAND"),
//        FUNCTION("FUNCTION"),
//        OPERATOR("OPERATOR"),
//        LOPERAND("LOPERAND"),
//        ROPERAND("ROPERAND");
        STATEMENT("STATEMENT"),
        VARIABLE("VARIABLE");
        private String str;
        OBJECTTYPE(String _arg) {
            str = _arg;
        }
        public String toString() {
            return str;
        }
    }

//    public static enum COMMANDTYPE {
//        SELECT("SELECT"),
//        INSERT("INSERT INTO"),
//        UPDATE("UPDATE"),
//        DELETE("DELETE FROM"),
//        FROM("FROM"),
//        WHERE("WHERE"),
//        WHERENOT("WHERE NOT"),
//        NOT("NOT"),
//        AND("AND"),
//        ANDNOT("AND NOT"),
//        OR("OR"),
//        ORNOT("OR NOT"),
//        GROUPBY("GROUP BY"),
//        ORDERBY("ORDER BY"),
//        SET("SET"),
//        INNERJOIN("INNER JOIN"),
//        FULLOUTERJOIN("FULLOUTER JOIN"),
//        LEFTJOIN("LEFT JOIN"),
//        RIGHTJOIN("RIGHT JOIN"),
//        //SELFJOIN("WHERE"),
//        ON("ON");
//        private String str;
//        COMMANDTYPE(String _arg) {
//            str = _arg;
//        }
//        public String toString() {
//            return str;
//        }
//    }
//
//    public static enum FUNCTIONTYPE {
////        PARENTHESES("PARENTHESES"),
//////        PARENTHESES(")"),
//////        EQ("="),
//////        LT("<"),
//////        LE("<="),
//////        NE("<>"),
//////        GE(">="),
//////        GT(">"),
//////        BETWEEN("BETWEEN"),
//////        LIKE("LIKE"),
//////        IN("IN"),
////        COUNT("COUNT"),
////        AVG("AVG"),
////        SUM("SUM");
//        UNARY("UNARY"),
//        BINARY("BINARY");
//        private String str;
//        FUNCTIONTYPE(String _arg) {
//            str = _arg;
//        }
//        public String toString() {
//            return str;
//        }
//    }
//
//    public static enum OPERATORTYPE {
//        EQ("="),
//        LT("<"),
//        LE("<="),
//        NE("<>"),
//        GE(">="),
//        GT(">"),
//        BETWEEN("BETWEEN"),
//        LIKE("LIKE"),
//        IN("IN");
//        private String str;
//        OPERATORTYPE(String _arg) {
//            str = _arg;
//        }
//        public String toString() {
//            return str;
//        }
//    }
//
//    public static enum VALUETYPE {
//        VARIABLE("VARIABLE"),
//        STRING("STRING");
//        private String str;
//        VALUETYPE(String _arg) {
//            str = _arg;
//        }
//        public String toString() {
//            return str;
//        }
//    }

//    public static enum OPERANDTYPE {
//        LP("LP"),
//        RP("RP");
//        private String str;
//        OPERANDTYPE(String _arg) {
//            str = _arg;
//        }
//        public String toString() {
//            return str;
//        }
//    }

    protected String name = "";

    public String getName() {
        return name;
    }

    protected void setName(String _name) {
        name = _name;
    }

    protected OBJECTTYPE objectType;

//    public SqlObject(OBJECTTYPE _type) {
//        objectType = _type;
//    }

    public OBJECTTYPE getType() {
        return objectType;
    }

    protected void setType(OBJECTTYPE _type) {
        objectType = _type;
    }

//    protected COMMANDTYPE commandType;
//    protected FUNCTIONTYPE functionType;
////    protected OPERATORTYPE operatorType;
//    protected VALUETYPE valueType;
////    protected OPERANDTYPE operandType;
//
//    public SqlObject(COMMANDTYPE _commandType) {
//        commandType = _commandType;
//    }
//
//    public SqlObject(FUNCTIONTYPE _functionType) {
//        functionType = _functionType;
//    }
//
////    public SqlObject(OPERATORTYPE _operatorType) {
////        operatorType = _operatorType;
////    }
//
//    public SqlObject(VALUETYPE _valueType) {
//        valueType = _valueType;
//    }
////    public SqlObject(OPERANDTYPE _operandType) {
////        operandType = _operandType;
////    }

    protected List<SqlObject> childObjects = new ArrayList<>();

//        public void addObject(SqlObject _obj) {
//            StatementProvider.this.addObject(_obj);
//        }

    public void addChildObject(SqlObject _obj) {
        childObjects.add(_obj); //.put(_obj.getClass().getName(), _obj);
    }

    public abstract void build(List<Object> _list);
}
