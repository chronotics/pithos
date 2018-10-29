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
@Repository("mapperSimpleStatementProviderMySql")
public class MapperStatementProviderMySql implements IMapperStatementProvider {

	private String className = this.getClass().getName();
	public String getClassName() {
		return className;
	}

//	@Resource(name = "sqlSessionSimpleMySql")
	private SqlSession sqlSession = null;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession _sqlSession) {
		sqlSession = _sqlSession;
	}

	@Override
	public Map<String, Object> selectOne(Map<Object, Object> _statementMap) {
		return getSqlSession().selectOne(
				getClassName() + ".selectOne",
				_statementMap);
	}

	@Override
	public List<Map<String, Object>> selectList(Map<Object, Object> _statementMap) {
		return getSqlSession().selectList(
				getClassName() + ".selectList",
				_statementMap);
	}

	@Override
	public int insert(Map<Object, Object> _statementMap) {
		return getSqlSession().insert(
				getClassName() + ".insert",
				_statementMap);
	}

	@Override
	public int update(Map<Object, Object> _statementMap) {
		return getSqlSession().update(
				getClassName() + ".update",
				_statementMap);
	}

	@Override
	public int delete(Map<Object, Object> _statementMap) {
		return getSqlSession().delete(
				getClassName() + ".delete",
				_statementMap);
	}

	@Override
	public int doStatement(Map<Object, Object> _statementMap) {
		return getSqlSession().update(
				getClassName() + ".doStatement",
				_statementMap);
	}
}
