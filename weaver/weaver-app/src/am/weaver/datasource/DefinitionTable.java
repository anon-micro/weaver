package am.weaver.datasource;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DefinitionTable extends Table {
			
	static final DefinitionTable DEF_DEF_TABLE;			
	
	static {
		DEF_DEF_TABLE = new DefinitionTable("DefinitionTable-definition", null,
				new ColumnDefinition[] {
				new ColumnDefinition(ColumnDefinition.NAME, "", ColumnType.String),
				new ColumnDefinition(ColumnDefinition.DEFINITION, "", ColumnType.Script),
				new ColumnDefinition(ColumnDefinition.TYPE, "am.weaver.datasource.ColumnType", ColumnType.Enum)
		});
		
		for(Row r: DEF_DEF_TABLE.getRows()){
			r.setDefinitionTable(DEF_DEF_TABLE);
		}
	}
	
	private final List<String> columnNames = new ArrayList<String>();
		
	DefinitionTable(String tableName, DefinitionTable defTable, ColumnDefinition[] columns) {
		super(tableName, defTable);
		for(ColumnDefinition col : columns)
			addRow(col);				
	}

	public DefinitionTable(String tableName, ColumnDefinition[] columns) {
		this(tableName, DEF_DEF_TABLE, columns);
	}

	public List<String> getColumnNames() { return Collections.unmodifiableList(columnNames); }
			
	public String getColumnDefinition(String columnName) {
		for(Row row : getRows())
			if(columnName.equals(row.get(ColumnDefinition.NAME)))
				return (String) row.get(ColumnDefinition.DEFINITION);
		return "";
	}
	
	public ColumnType getColumnType(String columnName) {
		for(Row row : getRows())
			if(columnName.equals(row.get(ColumnDefinition.NAME)))
				return (ColumnType) row.get(ColumnDefinition.TYPE);
		return ColumnType.String;
	}
	
	public Object parse(String colName, String s) {
		try{
			ColumnType colType = getColumnType(colName);
			switch(colType) {
			case String:
			case Script:
				return s;
			case Number:
				return NumberFormat.getInstance().parse(s);
			case Date:
				return new SimpleDateFormat("ddMMMyyyy").parse(s);
			case Enum:
				String enumClassName = getColumnDefinition(colName);
				return parseEnum(enumClassName, s);
			default:
				return null;	
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private <E extends Enum<E>> E parseEnum(String enumClassName, String s) throws ClassNotFoundException {
			@SuppressWarnings("unchecked")
			Class<E> cl = (Class<E>) Class.forName(enumClassName);
			return Enum.valueOf(cl, s);
	}
	
	
	
	@Override
	public void addRow(Row row) {
		super.addRow(row);
		String name = (String) row.get(ColumnDefinition.NAME);
		this.columnNames.add(name);
	}

	@Override
	public void deleteRows(Collection<Row> rows) {
		super.deleteRows(rows);
		for(Row row : rows)
			this.columnNames.remove(row.get(ColumnDefinition.NAME));
	}
}
