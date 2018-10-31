package org.chronotics.db.mybatis;

import java.util.*;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SqlStatement {
//	static Object createObject(String _className)
//			throws ClassNotFoundException,
//			NoSuchMethodException,
//			SecurityException,
//			InstantiationException,
//			IllegalAccessException,
//			IllegalArgumentException,
//			InvocationTargetException {
//		Class<?> c = Class.forName(_className);
//		Constructor<?> cons = c.getConstructor(c);
//		Object object = cons.newInstance();
//		return object;
//	}
	
	public static String getObject(String _json, String _keyword) throws JSONException {
		JSONObject jsonObject = new JSONObject(_json);
		return (String)jsonObject.get(_keyword);
	}
	
	public static List<Object> getList(String _json,String _keyword) throws JSONException {
		List<Object> rtObject = null;
		JSONObject jsonObject = new JSONObject(_json);
		Object obj = jsonObject.get(_keyword);
		if(obj instanceof JSONArray == false) {
			throw new JSONException("There is no object corresponding to the given keyword");
		}
		rtObject = new ArrayList<>();
		JSONArray array = (JSONArray)obj;
		for(int i=0; i<array.length(); i++) {
			rtObject.add(array.get(i));
		}
		return rtObject;
	}

	public static List<List<Object>> getListOfList(String _json,String _keyword) throws JSONException {
		List<List<Object>> rtObject = null;
		JSONObject jsonObject = new JSONObject(_json);
		Object obj = jsonObject.get(_keyword);
		if(obj instanceof JSONArray == false) {
            throw new JSONException("There is no object corresponding to the given keyword");
		}
		JSONArray jsonArrayRecords = (JSONArray)obj;
		rtObject = new ArrayList<>();
		for(int i=0; i<jsonArrayRecords.length(); i++) {
			JSONArray jsonArrayRecord = jsonArrayRecords.getJSONArray(i);
			if(jsonArrayRecord == null) {
				throw new JSONException("There is no array in records");
			}
			List<Object> record = new ArrayList<>();
			for(int j=0; j<jsonArrayRecord.length(); j++) {
				record.add(jsonArrayRecord.get(j));
			}
			rtObject.add(record);
		}
		return rtObject;
	}
	
	/**
	 * 
	 * @param _resultSet
	 * @param count_from
	 * if(count_from <= i && i < count_to)
	 * @param count_to
	 * @return
	 */
	public static JSONObject getJSonObject(
			List<Map<String,Object>> _resultSet, 
			int count_from, 
			int count_to) {
		JSONObject object = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		int i = 0;
		for(Map<String,Object> element: _resultSet) {
			if(count_from <= i && i < count_to) {
				JSONObject jsonChild = new JSONObject();
				for(Entry<String,Object> entry: element.entrySet()) {
					Object entryObj = entry.getValue();
					try {
						jsonChild.put(entry.getKey(), entryObj);
					} catch (JSONException e) {
						e.printStackTrace();
						return null;
					}
				}
				jsonArray.put(jsonChild);
			}
			i++;
		}
		try {
			object.put("resultSet", jsonArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}

	public static JSONObject getJSonObject(
			List<Map<String,Object>> _resultSet,
			String _resultType,
			int count_from,
			int count_to) {
		JSONObject object = new JSONObject();
		JSONArray columns = new JSONArray();
        Set<String> columnSet = new HashSet<>();
		JSONArray dataSource = new JSONArray();
		int i = 0;
		boolean columnSetAdded = false;
		for (Map<String, Object> element : _resultSet) {
			if (count_from <= i && i < count_to) {
				JSONObject jsonChild = new JSONObject();
				for (Entry<String, Object> entry : element.entrySet()) {
					Object entryObj = entry.getValue();
					try {
						jsonChild.put(entry.getKey(), entryObj);
					} catch (JSONException e) {
						e.printStackTrace();
						return null;
					}
					if(columnSetAdded == false) {
					    columnSet.add(entry.getKey());
                    }
				}
				dataSource.put(jsonChild);
				if(columnSetAdded == false && !columnSet.isEmpty()) {
                    for(String v : columnSet) {
                        JSONObject jsonColumn = new JSONObject();
                        try {
                            jsonColumn.put("title", v);
                            jsonColumn.put("dataIndex", v);
                            jsonColumn.put("key", v);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        columns.put(jsonColumn);
                    }
                    columnSetAdded = true;
                }
			}
			i++;
		}
		switch(_resultType) {
            case "antd":
                try {
                    object.put("dataSource", dataSource);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                try {
                    object.put("columns", columns);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
		return object;
	}
}
