package am.weaver.datasource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Row {

	private final Map<String, Object> map = new HashMap<String, Object>();

	private DefinitionTable definitionTable;
	
	public Row(DefinitionTable definitionTable, final Object[] data) {
		setDefinitionTable(definitionTable);
		List<String> columnNames = definitionTable.getColumnNames();
		for(int i = 0; i < columnNames.size(); i++) {
			String colName = columnNames.get(i);
			this.set(colName, i < data.length ? data[i] : null);
		}
	}

	protected Row() {}
	
	public Object get(String key) { return map.get(key); }
	
	public void set(String colName, Object value) {
		if(definitionTable != null && value instanceof String)
			map.put(colName,  definitionTable.parse(colName, (String) value));
		else
			map.put(colName, value); 
	}
	
	public void setDefinitionTable(DefinitionTable definitionTable) {
		this.definitionTable = definitionTable;
	}
	
	public DefinitionTable getDefinitionTable() {
		return definitionTable;
	}
}
