package am.weaver.datasource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Table {

	private final String tableName;
	
	private final List<String> columns;
	
	private final List<Row> rows;
	
	public Table(String tableName, String[] columns, Object[] data) {
		this.tableName = tableName;
		this.columns = Arrays.asList(columns);
		this.rows = new ArrayList<Row>();
		for(int i = 0; i < data.length; i += columns.length) {
			Object[] tmp = new Object[columns.length];
			System.arraycopy(data, i, tmp, 0, columns.length);
			this.rows.add(new Row(columns, tmp));
		}
	}

	public String getTableName() { return tableName; }

	public List<String> getColumns() { return Collections.unmodifiableList(columns); }

	public List<Row> getRows() { return Collections.unmodifiableList(rows); }
}
