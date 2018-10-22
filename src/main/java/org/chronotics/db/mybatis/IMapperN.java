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

public interface IMapperN {

	/**
	 * getSqlSession
	 * this function is for polymorphism of different DB connections
	 * @return
	 */

	/**
 	 * @param provider
	 * query statement defined in a mapper file.
	 * you should make two paths defined in .java and *mapper*.xml be equal.
	 */

	/**
	 * select
	 * this function is mapped to the query statement in a mapper file.
	 * @param parameter
	 * parameter to complete query.
	 * parameter can be used for statement or simple string variable.
	 * key = property of a variable, value = value of a variable
	 * @return
	 * single object with the type of key, value
	 * key = property of a returned object, value = value of a returned object
	 */
	public Map<String,Object> selectOne(Map<Object, Object> parameter);

	public Map<String,Object> selectOne(StatementProvider provider) throws Exception;

	/**
	 * selectList
	 * this function is mapped to the query statement in a mapper file.
	 * @param parameter
	 * parameter to complete query.
	 * parameter can be used for provider or simple string variable.
	 * key = property of a variable, value = value of a variable
	 * @return
	 * multiple objects with the type of key, value
	 * key = property of a returned object, value = value of a returned object
	 */
	public List<Map<String,Object>> selectList(Map<Object, Object> parameter);

	public List<Map<String,Object>> selectList(StatementProvider provider) throws Exception;

	/**
	 * selectWithStatement
	 * this function is mapped to the query statement in a mapper file.
	 * @param provider
	 * query provider
	 * ex) SELECT ${provider}
	 * @return
	 * multiple objects with the type of key, value
	 * key = property of a returned object, value = value of a returned object
	 */
	public List<Map<String,Object>> selectWithStatement(String provider);


	/**
	 * insert
	 * this function is mapped to the query statement in a mapper file.
	 * @param parameter
	 * parameter to complete query.
	 * parameter can be used for provider or simple string variable.
	 * key = property of a variable, value = value of a variable
	 * @return
	 * the number of inserted elements
	 */
	public int insert(Map<Object, Object> parameter);

	public int insert(StatementProvider provider) throws Exception;

	/**
	 * insertWithStatement
	 * this function is mapped to the query definition in mapper.xml
	 * @param provider
	 * query provider
	 * ex) insert ${provider}
	 * @return
	 * the number of inserted elements
	 */
	public int insertWithStatement(String provider);

	/**
	 * update
	 * this function is mapped to the query statement in a mapper file.
	 * @param parameter
	 * parameter to complete query.
	 * parameter can be used for provider or simple string variable.
	 * key = property of a variable, value = value of a variable
	 * @return
	 * the number of updated elements
	 */
	public int update(Map<Object, Object> parameter);

	public int update(StatementProvider provider) throws Exception;

	/**
	 * insertWithStatement
	 * this function is mapped to the query statement in a mapper file.
	 * @param provider
	 * query provider
	 * ex) update ${provider}
	 * @return
	 * the number of updateded elements
	 */
	public int updateWithStatement(String provider);

	/**
	 * delete
	 * this function is mapped to the query statement in a mapper file.
	 * @param parameter
	 * parameter to complete query.
	 * parameter can be used for provider or simple string variable.
	 * key = property of a variable, value = value of a variable
	 * @return
	 * the number of deleted elements
	 */
	public int delete(Map<Object, Object> parameter);

	public int delete(StatementProvider provider) throws Exception;

	/**
	 * deletedWithStatement
	 * this function is mapped to the query statement in a mapper file.
	 * @param provider
	 * query provider
	 * ex) delete ${provider}
	 * @return
	 * the number of deleted elements
	 */
	public int deleteWithStatement(String provider);

	/**
	 * insertMultipleItems
	 * this function is mapped to the query statement in a mapper file.
	 * @param parameters
	 * parameter to complete query.
	 * parameter can be used for provider or simple string variable.
	 * key = property of a variable, value = value of a variable
	 * @return
	 * the number of inserted elements
	 */
	public int insertMultipleItems(Map<Object, Object> parameters);

	/**
	 * insertMultipleItems
	 * @param provider
	 * StatementProvider generated from SqlStatement.Builder()
	 * @return
	 * the number of inserted elements
	 */
	public int insertMultipleItems(StatementProvider provider) throws Exception;

	/**
	 * doStatement
	 * @param parameters
	 * parameter to complete query.
	 * parameter can be used for provider or simple string variable.
	 * key = property of a variable, value = value of a variable
	 * @return
	 */
	public int doStatement(Map<Object, Object> parameters);
	
	public int doStatement(StatementProvider provider) throws Exception;

}
