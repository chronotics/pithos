package org.chronotics.db.mybatis;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.chronotics.db.mybatis.SqlObject.FUNCTIONTYPE.UNARY;

public class SqlObjectFunction extends SqlObject {

    private String functionName="";

    public class SUM {
        public SUM() {
            functionType = UNARY;
            functionName = "SUM";
        }
    }

    public class COUNT {
        public COUNT() {
            functionType = UNARY;
            functionName = "COUNT";
        }
    }

    public class Parentheses {
        public Parentheses() {
            functionType = UNARY;
            functionName = "(";
        }
    }

    public SqlObjectFunction(SqlObject.FUNCTIONTYPE _type) {
        super(_type);
    }

    @Override
    public void addChildObject(SqlObject _obj) {

    }

    @Override
    public void build() {
        Map<String, SqlObject> newChild = new LinkedHashMap<>();
        newChild.put(
                SqlObjectValue.class.getName(),
                new SqlObjectValue<String>(this.functionName));

        int index = 0;
        for(Map.Entry<String, SqlObject> entry: childObjects.entrySet()) {
            newChild.put(entry.getKey(), entry.getValue());
            if (index != childObjects.size()-1) {
                switch (functionName) {
                    case "(":
                        newChild.put(
                                SqlObjectValue.class.getName(),
                                new SqlObjectValue(KEYWORD.COMMA));
                        break;
                }
            }
            index++;
        }

        newChild.put(
                SqlObjectValue.class.getName(),
                new SqlObjectValue<String>(")"));

        childObjects.clear();
        childObjects.putAll(newChild);
    }
}
