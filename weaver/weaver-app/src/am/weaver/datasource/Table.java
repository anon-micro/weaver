package am.weaver.datasource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Table {

	private final String tableName;
	
	private final DefinitionTable definitionTale;
	
	private final List<Row> rows;
	
	public Table(String tableName, ColumnDefinition[] columns, Object[] data) {
		this(tableName, new DefinitionTable(tableName + "-definition", columns));
		for(int i = 0; i < data.length; i += columns.length) {
			Object[] tmp = new Object[columns.length];
			System.arraycopy(data, i, tmp, 0, columns.length);
			addRow(tmp);
		}
	}
	
	protected Table(final String tableName, final DefinitionTable definitionTable) {
		this.tableName = tableName;
		this.definitionTale = definitionTable;
		this.rows = new ArrayList<Row>();
	}
	
	public DefinitionTable getDefinitionTable(){ return definitionTale; }
	
	public String getTableName() { return tableName; }

	public List<String> getColumns() { return definitionTale.getColumnNames(); }

	public List<Row> getRows() { return Collections.unmodifiableList(rows); }
	
	public void addRow(Row row) { 
		rows.add(row);
		row.setDefinitionTable(definitionTale);
	}
	
	public void addRow(Object[] data) {
		addRow(new Row(definitionTale, data));
	}

	public void deleteRows(Collection<Row> rows) { this.rows.removeAll(rows); }
}
