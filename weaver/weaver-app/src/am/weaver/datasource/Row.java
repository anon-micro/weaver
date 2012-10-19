package am.weaver.datasource;

import java.util.HashMap;
import java.util.Map;

public class Row {

	private final Map<String, Object> map = new HashMap<String, Object>();
	
	public Row(final String[] columns, final Object[] data) {
		if(columns.length != data.length) throw new IllegalArgumentException();
		for(int i = 0; i < columns.length; i++)
			map.put(columns[i], data[i]);
	}
	
	public Object get(String key) { return map.get(key); }	
	public void set(String key, Object value) {map.put(key, value); }
}
