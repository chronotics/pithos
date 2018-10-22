package org.chronotics.db.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Mapper implemented from IMapper
 * @author sglee
 * @since 2015
 * @description
 * SqlSessionTemplate of Mybatis-Spring will be injected
 * according to Beans that are defined in context_mapperBean.xml
 * SqlSessionTemplate has two types.
 * One is simple, the other is batch
 */
@Repository("MapperMySqlN")
public class MapperMySqlN implements IMapperN {

	private String className = this.getClass().getName();
	public String getClassName() {
		return className;
	}
	
	@Resource(name = "sqlSessionSimpleMySql")
	private SqlSession sqlSession;
	
	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession _sqlSession) {
		sqlSession = _sqlSession;
	}
	
	@Override
	public Map<String, Object> selectOne(Map<Object, Object> parameter) {
		return getSqlSession().selectOne(
				getClassName() + ".selectOne", 
				parameter);
	}

	@Override
	public Map<String, Object> selectOne(StatementProvider provider) throws Exception {
		return getSqlSession().selectOne(
				getClassName() + ".selectOne",
				provider.getParameter());
	}

	@Override
	public List<Map<String, Object>> selectList(Map<Object, Object> parameter) {
		return getSqlSession().selectList(
				getClassName() + ".selectList",
				parameter);
	}

	@Override
	public List<Map<String,Object>> selectList(StatementProvider provider) throws Exception {
		return getSqlSession().selectList(
				getClassName() + ".selectList",
				provider.getParameter());
	}
	
	@Override
	public List<Map<String,Object>> selectWithStatement(String statement) {
		return getSqlSession().selectList(statement);
	}

	@Override
	public int insert(Map<Object, Object> parameter) {
		return getSqlSession().insert(
				getClassName() + ".insert",
				parameter);
	}

	@Override
	public int insert(StatementProvider provider) throws Exception {
		return getSqlSession().insert(
				getClassName() + ".insert",
				provider.getParameter());
	}
	
	@Override
	public int insertWithStatement(String statement) {
		return getSqlSession().insert(statement);
	}
	
	@Override
	public int update(Map<Object, Object> parameter) {
		return getSqlSession().update(
				getClassName() + ".update", 
				parameter);
	}

	@Override
	public int update(StatementProvider provider) throws Exception {
		return getSqlSession().update(
				getClassName() + ".update",
				provider.getParameter());
	}
	
	@Override
	public int updateWithStatement(String statement) {
		return getSqlSession().update(statement);
	}

	@Override
	public int delete(Map<Object, Object> parameter) {
		return getSqlSession().delete(
				getClassName() + ".delete", 
				parameter);
	}

	@Override
	public int delete(StatementProvider provider) throws Exception {
		return getSqlSession().delete(
				getClassName() + ".delete", 
				provider.getParameter());
	}
		
	@Override
	public int deleteWithStatement(String statement) {
		return this.deleteWithStatement(statement);
	}

	@Override
	public int insertMultipleItems(Map<Object,Object> parameters) {
		return getSqlSession().insert(
				getClassName() + ".insertMultipleItems", 
				parameters);
	}

	@Override
	public int insertMultipleItems(StatementProvider provider) throws Exception {
		return getSqlSession().insert(
				getClassName() + ".insertMultipleItems", //sqlstatement.getMapperStatement(),
				provider.getParameter());
	}
	
	@Override
	public int doStatement(Map<Object,Object> parameters) {
		return getSqlSession().update(
				getClassName() + ".doStatement", 
				parameters);
	}

	@Override
	public int doStatement(StatementProvider provider) throws Exception {
		return getSqlSession().update(
				getClassName() + ".doStatement", 
				provider.getParameter());
	}
	
}
