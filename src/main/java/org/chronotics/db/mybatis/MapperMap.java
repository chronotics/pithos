package org.chronotics.db.mybatis;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
//import org.springframework.stereotype.Repository;

//@Repository("mapperMap")
public class MapperMap implements IMapper {
	
	private Map<String,IMapper> mapperMap = new ConcurrentHashMap<>();
	
	public void setMapperMap(Map<String,IMapper> object) {
		mapperMap.putAll(object);
	}
	
	public Map<String,IMapper> getMapeerMap() {
		return mapperMap;
	}
	
	public int size() {
		return mapperMap.size();
	}
	
	public Set<Entry<String,IMapper>> entrySet() {
		return mapperMap.entrySet();
	}
	
	public IMapper get(String key) {
		return mapperMap.get(key);
	}
	
	public Map<String, Object> selectOne(
			String key, Map<Object,Object> _statementMap) {
		if(get(key) == null) throw new NullPointerException();
		return get(key).selectOne(_statementMap);
	}
	
	public List<Map<String,Object>> selectList(
			String key, Map<Object,Object> _statementMap) {
		if(get(key) == null) throw new NullPointerException();
		return get(key).selectList(_statementMap);
	}

	public int insert(
			String key, Map<Object,Object> _statementMap) {
		if(get(key) == null) throw new NullPointerException();
		return get(key).insert(_statementMap);
	}

	public int update(
			String key, Map<Object,Object> _statementMap) {
		if(get(key) == null) throw new NullPointerException();
		return get(key).update(_statementMap);
	}

	public int delete(
			String key, Map<Object,Object> _statementMap) {
		if(get(key) == null) throw new NullPointerException();
		return get(key).delete(_statementMap);
	}

	public int doStatement(
			String key, Map<Object,Object> _statementMap) {
		if(get(key) == null) throw new NullPointerException();
		return get(key).doStatement(_statementMap);
	}

	@Override
	public Map<String, Object> selectOne(Map<Object, Object> _statementMap) {
		return null;
	}

	@Override
	public List<Map<String, Object>> selectList(Map<Object, Object> _statementMap) {
		return null;
	}

	@Override
	public int insert(Map<Object, Object> _statementMap) {
		return 0;
	}

	@Override
	public int update(Map<Object, Object> _statementMap) {
		return 0;
	}

	@Override
	public int delete(Map<Object, Object> _statementMap) {
		return 0;
	}

	@Override
	public int doStatement(Map<Object, Object> _statementMap) {
		return 0;
	}
}
