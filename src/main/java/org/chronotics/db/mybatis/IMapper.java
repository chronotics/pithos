package org.chronotics.db.mybatis;

import java.util.List;
import java.util.Map;

/**
 * @author SG Lee
 * @since 2013
 * @description
 * Thanks Ladybug members, Jinho and Nate.
 * This is mapper interface for the use of Mybatis.
 * You have to implement a derived Class from this interface and
 * a mapper file(.xml) that is mapped into the derived Class.
 * Prepared statement is defined in the mapper file and
 * parameters will complete the statement by Mabatis
 * Hash tag # is used to set a parameter in the prepared statement.
 * Dollar sign $ is used to represent a simple string.
 * ex)
 * SELECT * FROM ${table} WHERE id = #{id} ORDER BY ${orderBy}
 * SELECT ${statement}
 */

public interface IMapper {

    /**
     * selectOne
     * this is mapped to the query function "selectOne" in a mapper file.
     * @param _statementMap
     * _statementMap is used to complete the statement.
     * Some are to define String of a statement and
     * the others are for parameters in a prepared statement
     * @return
     * single object with the type of key, value
     * key = property of a returned object, value = value of a returned object
     */
    Map<String,Object> selectOne(Map<Object, Object> _statementMap);

    /**
     * selectList
     * this is mapped to the query function "selectList" in a mapper file.
     * @param _statementMap
     * _statementMap is used to complete the statement.
     * Some are to define String of a statement and
     * the others are for parameters in a prepared statement
     * @return
     * multiple objects with the type of key, value
     * key = property of a returned object, value = value of a returned object
     */
	List<Map<String,Object>> selectList(Map<Object, Object> _statementMap);

    /**
     * insert
     * this is mapped to the query function "insert" in a mapper file.
     * @param _statementMap
     * _statementMap is used to complete the statement.
     * Some are to define String of a statement and
     * the others are for parameters in a prepared statement
     * @return
     * the number of inserted elements
     */
	int insert(Map<Object,Object> _statementMap);

    /**
     * update
     * this is mapped to the query function "update" in a mapper file.
     * @param _statementMap
     * _statementMap is used to complete the statement.
     * Some are to define String of a statement and
     * the others are for parameters in a prepared statement
     * @return
     * the number of updated elements
     */
	int update(Map<Object,Object> _statementMap);

    /**
     * delete
     * this is mapped to the query function "delete" in a mapper file.
     * @param _statementMap
     * _statementMap is used to complete the statement.
     * Some are to define String of a statement and
     * the others are for parameters in a prepared statement
     * @return
     * the number of deleted elements
     */
	int delete(Map<Object,Object> _statementMap);

    /**
     * doStatement
     * @param _statementMap
     * _statementMap is for the completely prepared statement.
     * @return
     * no meaning
     */
	int doStatement(Map<Object,Object> _statementMap);
}
