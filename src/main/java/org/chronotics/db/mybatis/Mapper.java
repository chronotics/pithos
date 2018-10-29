package org.chronotics.db.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Mapper implemented from IMapper
 * @author sglee
 * @since 2013
 * @description
 * SqlSessionTemplate of Mybatis-Spring will be injected to sqlSession
 * according to Beans that are defined in context_mapperBean.xml
 * SqlSessionTemplate has two types.
 * One is simple, the other is batch
 */
//@Repository("mapperSimpleMySql")
public class Mapper implements IMapper {

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
				IMapper.class.getName() + ".selectOne",
				_statementMap);
	}

	@Override
	public List<Map<String, Object>> selectList(Map<Object, Object> _statementMap) {
		return getSqlSession().selectList(
				IMapper.class.getName() + ".selectList",
				_statementMap);
	}

	@Override
	public int insert(Map<Object, Object> _statementMap) {
		return getSqlSession().insert(
				IMapper.class.getName() + ".insert",
				_statementMap);
	}

	@Override
	public int update(Map<Object, Object> _statementMap) {
		return getSqlSession().update(
				IMapper.class.getName() + ".update",
				_statementMap);
	}

	@Override
	public int delete(Map<Object, Object> _statementMap) {
		return getSqlSession().delete(
				IMapper.class.getName() + ".delete",
				_statementMap);
	}

	@Override
	public int doStatement(Map<Object, Object> _statementMap) {
		return getSqlSession().update(
				IMapper.class.getName() + ".doStatement",
				_statementMap);
	}
}
