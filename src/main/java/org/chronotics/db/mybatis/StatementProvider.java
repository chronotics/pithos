package org.chronotics.db.mybatis;

import org.springframework.jdbc.core.SqlProvider;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatementProvider {

    public enum BUILDTYPE {
        MYSQL("MYSQL"),
        ORACLE("ORACLE");
        private String str;
        BUILDTYPE(String _arg) {
            str = _arg;
        }
        public String toString() {
            return str;
        }
    }

    public static String STATEMENTMAP = "STATEMENTMAP";

    private Map<Object, Object> statementMap = new LinkedHashMap();
    public Map<Object,Object> getStatementMap() {
        return statementMap;
    }

    private List<SqlObject> sqlObjectList = new ArrayList<>();
    public void addObject(SqlObject _obj) {
        sqlObjectList.add(_obj);
    }

    private void build(BUILDTYPE _type) {
        List<Object> buildList = new ArrayList<>();
        sqlObjectList.forEach(object -> {
           object.build(buildList, _type);
        });

        statementMap.clear();
        statementMap.put(STATEMENTMAP, new ArrayList<>(buildList));
    }

    public static class Builder {
        // for final return value
        private List<SqlObject> sqlObjects = new ArrayList<>();

        public Builder() {
        }

        public StatementProvider build(BUILDTYPE _type) {
            StatementProvider provider = new StatementProvider();
            // for(SqlObject object: this.sqlObjects) {
            // can add the algorithm to check sequence
            // }
            for(SqlObject object: this.sqlObjects) {
                provider.addObject(object);
            }
            provider.build(_type);
            return provider;
        }

        private Builder addChildObject(SqlObject _sqlObject, Object ..._objects) {
            if(_objects != null) {
                for (Object object : _objects) {
                    _sqlObject.addChild(object, false);
                }
            }
            this.sqlObjects.add(_sqlObject);
            return this;
        }

        private Builder addValues(SqlObject _sqlObject, List<Object> _columns, List<Object> _values) {
            _sqlObject.addChild(_columns, false);
            _sqlObject.addChild(_values, true);
            sqlObjects.add(_sqlObject);
            return this;
        }

        private Builder addInsertMulti(
                SqlObject _sqlObject,
                String _tableName,
                List<Object> _columns,
                List<List<Object>> _valuesList) {
            _sqlObject.addChild(_tableName, false);
            _sqlObject.addChild(_columns, false);
            _sqlObject.addChild(_valuesList, true);
            sqlObjects.add(_sqlObject);
            return this;
        }

        public Builder select(Object ..._objects) {
            assert(_objects!=null);
            SqlObject sqlObject = new SqlObjectCommand(SqlObjectCommand.SELECT);
            return addChildObject(sqlObject, _objects);
        }

        public Builder insertInto(String _tableName) {
            assert(_tableName!=null);
            SqlObject sqlObject = new SqlObjectCommand(SqlObjectCommand.INSERTINTO);
            return addChildObject(sqlObject, _tableName);
        }

        public Builder values(List<Object> _columns, List<Object> _values) {
            assert(_columns!=null);
            assert(_values!=null);
            SqlObject sqlObject = new SqlObjectCommand(SqlObjectCommand.VALUES);
            return addValues(sqlObject, _columns, _values);
        }

        public Builder insertMulti(String _tableName, List<Object> _columns, List<List<Object>> _valuesList) {
            SqlObject sqlObject = new SqlObjectCommand(SqlObjectCommand.INSERTMULTI);
            return addInsertMulti(sqlObject, _tableName, _columns, _valuesList);
        }

        public Builder update(Object ..._objects) {
            assert(_objects!=null);
            SqlObject sqlObject = new SqlObjectCommand(SqlObjectCommand.UPDATE);
            return addChildObject(sqlObject, _objects);
        }

        public Builder deleteFrom(Object _object) {
            assert(_object!=null);
            SqlObject sqlObject = new SqlObjectCommand(SqlObjectCommand.DELETE);
            return addChildObject(sqlObject, _object);
        }

        public Builder from(Object ..._objects) {
            assert(_objects!=null);
            SqlObject sqlObject = new SqlObjectCommand(SqlObjectCommand.FROM);
            return addChildObject(sqlObject, _objects);
        }

        public Builder where(Object ..._objects) {
            assert(_objects!=null);
            SqlObject sqlObject = new SqlObjectCommand(SqlObjectCommand.WHERE);
            return addChildObject(sqlObject, _objects);
        }

        public Builder wherenot(Object ..._objects) {
            assert(_objects!=null);
            SqlObject sqlObject = new SqlObjectCommand(SqlObjectCommand.WHERENOT);
            return addChildObject(sqlObject, _objects);
        }

        public Builder and(Object ..._objects) {
            assert(_objects!=null);
            SqlObject sqlObject = new SqlObjectCommand(SqlObjectCommand.AND);
            return addChildObject(sqlObject, _objects);
        }

        public Builder not(Object ..._objects) {
            assert(_objects!=null);
            SqlObject sqlObject = new SqlObjectCommand(SqlObjectCommand.NOT);
            return addChildObject(sqlObject, _objects);
        }

        public Builder andnot(Object ..._objects) {
            assert(_objects!=null);
            SqlObject sqlObject = new SqlObjectCommand(SqlObjectCommand.ANDNOT);
            return addChildObject(sqlObject, _objects);
        }

        public Builder or(Object ..._objects) {
            assert(_objects!=null);
            SqlObject sqlObject = new SqlObjectCommand(SqlObjectCommand.ANDNOT);
            return addChildObject(sqlObject, _objects);
        }

        public Builder ornot(Object ..._objects) {
            assert(_objects!=null);
            SqlObject sqlObject = new SqlObjectCommand(SqlObjectCommand.ORNOT);
            return addChildObject(sqlObject, _objects);
        }

        public Builder orderby(Object ..._objects) {
            assert(_objects!=null);
            SqlObject sqlObject = new SqlObjectCommand(SqlObjectCommand.ORDERBY);
            return addChildObject(sqlObject, _objects);
        }

        public Builder asc() {
            SqlObject sqlObject = new SqlObjectValue(SqlObjectCommand.ASC, false);
            return addChildObject(sqlObject, null);
        }

        public Builder desc() {
            SqlObject sqlObject = new SqlObjectValue(SqlObjectCommand.DESC, false);
            return addChildObject(sqlObject, null);
        }

        public Builder set(SqlObjectOperator ..._objects) {
            assert(_objects!=null);
            SqlObject sqlObject = new SqlObjectCommand(SqlObjectCommand.SET);
            return addChildObject(sqlObject, _objects);
        }

        public Builder innerjoin(Object ..._objects) {
            assert(_objects!=null);
            SqlObject sqlObject = new SqlObjectCommand(SqlObjectCommand.INNERJOIN);
            return addChildObject(sqlObject, _objects);
        }

        public Builder leftjoin(Object ..._objects) {
            assert(_objects!=null);
            SqlObject sqlObject = new SqlObjectCommand(SqlObjectCommand.LEFTJOIN);
            return addChildObject(sqlObject, _objects);
        }

        public Builder rightjoin(Object ..._objects) {
            assert(_objects!=null);
            SqlObject sqlObject = new SqlObjectCommand(SqlObjectCommand.RIGHTJOIN);
            return addChildObject(sqlObject, _objects);
        }

        public Builder fullouterjoin(Object ..._objects) {
            assert(_objects!=null);
            SqlObject sqlObject = new SqlObjectCommand(SqlObjectCommand.FULLOUTERJOIN);
            return addChildObject(sqlObject, _objects);
        }

        public Builder union() {
            SqlObject sqlObject = new SqlObjectCommand(SqlObjectCommand.UNION);
            return addChildObject(sqlObject, null);
        }

        public Builder on(Object ..._objects) {
            assert(_objects!=null);
            SqlObject sqlObject = new SqlObjectCommand(SqlObjectCommand.ON);
            return addChildObject(sqlObject, _objects);
        }

        public Builder limit(Object ..._objects) {
            assert(_objects!=null);
            SqlObject sqlObject = new SqlObjectCommand(SqlObjectCommand.LIMIT);
            return addChildObject(sqlObject, _objects);
        }

        public Builder doStatement(String _str) {
            assert(_str!=null);
            SqlObject sqlObject = new SqlObjectValue(_str, false);
            return addChildObject(sqlObject, null);
        }
    }
}
