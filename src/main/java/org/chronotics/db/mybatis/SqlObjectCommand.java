package org.chronotics.db.mybatis;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SqlObjectCommand extends SqlObject {
    //        private String className = this.getClass().getName();
//        public String getClassName() {
//            return className;
//        }
    public SqlObjectCommand(SqlObject.COMMANDTYPE _type) {
        super(_type);
    }

    @Override
    public void addChildObject(SqlObject _obj) {
        childObjects.put(_obj.getClass().getName(), _obj);
    }

    @Override
    public void build() {
        Map<String, SqlObject> newChild = new LinkedHashMap<>();
//                childObjects.entrySet()
//                .stream()
//                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));

        int index = 0;
        for(Map.Entry<String, SqlObject> entry: childObjects.entrySet()) {
            newChild.put(entry.getKey(), entry.getValue());
            if (index != childObjects.size()-1) {
                switch (this.commandType) {
                    case SELECT:
                    case FROM:
                        newChild.put(
                                SqlObjectValue.class.getName(),
                                new SqlObjectValue(KEYWORD.COMMA));
                        break;
                }
            }
            index++;
        }

        childObjects.clear();
        childObjects.putAll(newChild);
    }
}
