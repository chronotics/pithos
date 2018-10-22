package org.chronotics.db.mybatis;

import org.springframework.jdbc.core.SqlProvider;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatementProvider {

    public static class KEYWORD {
        public static String TABLENAME = "tableName";
        public static String RESULTSET = "resultSet";
        public static String COLNAMES = "colNames";
        public static String COLVALUES = "colValues";
        public static String COLVARIABLES = "colVariables";
        public static String RECORDS = "records";
        public static String WHERECLAUSE = "whereClause";
        public static String STATEMENT = "statement";
    }

    public static class NUMERIC_FUNCTION {
        public static String AVG = "avg";
        public static String COUNT = "count";
        public static String COS = "cos";
        public static String SIN = "sin";
        public static String SQRT = "sqrt";
        public static String SUM = "sum";
        public static String TAN = "tan";
    }

    private Map<Object, Object> objectMap = new LinkedHashMap();
    public void addObject(SqlObject _obj) {
        objectMap.put(_obj.getClass().getName(), _obj);
    }

    public Map<Object,Object> getParameter() {
        return objectMap;
    }

    private void build() {
        for(Map.Entry<Object,Object> entry: objectMap.entrySet()) {
            SqlObject sqlObject = (SqlObject)entry.getValue();
            sqlObject.build();
        }
    }

    public static class Builder {
        // for final return value
        private List<SqlObject> sqlObjects = new ArrayList<>();

        public Builder() {
        }

        public StatementProvider build() {
            StatementProvider provider = new StatementProvider();
            // for(SqlObject object: this.sqlObjects) {
            // can add the algorithm to check sequence
            // }
            for(SqlObject object: this.sqlObjects) {
                provider.addObject(object);
            }

//            provider.objectMap = this.objectMap
//                    .entrySet()
//                    .stream()
//                    .collect(Collectors.toMap(
//                            Map.Entry::getKey,
//                            Map.Entry::getValue));
            provider.build();

            return provider;
        }

        private Builder addChildren(SqlObject _sqlObject, SqlObject ..._objects) {
            for(SqlObject object: _objects) {
                _sqlObject.addChildObject(object);
            }
            this.sqlObjects.add(_sqlObject);
            return this;
        }

        public Builder select(SqlObject ..._objects) {
            SqlObject sqlObject = new SqlObjectCommand(SqlObject.COMMANDTYPE.SELECT);
            return addChildren(sqlObject, _objects);
        }

        public Builder from(SqlObject ..._objects) {
            SqlObject sqlObject = new SqlObjectCommand(SqlObject.COMMANDTYPE.FROM);
            return addChildren(sqlObject, _objects);
        }

        public Builder where(SqlObject ..._objects) {
            SqlObject sqlObject = new SqlObjectCommand(SqlObject.COMMANDTYPE.WHERE);
            return addChildren(sqlObject, _objects);
        }
    }


    @FunctionalInterface
    interface BinaryFunction {
        public void function(Object _lo, Object _ro);
    }

    @FunctionalInterface
    interface UnaryFunction {
        public void function(Object _o);
    }

}
