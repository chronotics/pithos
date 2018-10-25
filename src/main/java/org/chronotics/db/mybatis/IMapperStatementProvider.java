package org.chronotics.db.mybatis;

import java.util.List;
import java.util.Map;

/**
 * @author SG Lee
 * @since 2013
 * @description
 * Thanks Ladybug members, Jinho and Nate.
 * This Class is mybatis mapper class.
 * Values of VO are defined as prepared statement variables (key,value) and 
 * the set of them is used as an argument of below functions.
 * Additionally, qeury condition can be represented as simple string.
 * Hash tag # is used for prepared statement variable.
 * Dollar sign $ is used for query condition that is represented as simple string.
 * ex)
 * SELECT * FROM ${table} WHERE id = #{id} ORDER BY ${orderBy}
 * SELECT ${statement}
 */

public interface IMapperStatementProvider {

	Map<String,Object> selectOne(Map<Object, Object> _statementMap);

	List<Map<String,Object>> selectList(Map<Object, Object> _statementMap);

	int insert(Map<Object, Object> _statementMap);

	int update(Map<Object, Object> _statementMap);

	int delete(Map<Object, Object> _statementMap);

	int insertMultipleItems(Map<Object, Object> _statementMaps);

	int doStatement(Map<Object, Object> _statementMap);
}
