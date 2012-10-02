package am.app;

public class DataSourceTest {

	public static final void main(String[] args) {
		DataSource ds = new DataSource();
		
		System.out.println("Tables: " + ds.getTableNames());
		System.out.println();
		
		for(String tableName : ds.getTableNames()) {
			System.out.println("================================================================");
			Table table = ds.getTable(tableName);
			System.out.println(tableName + " has " + table.getRows().size() + " rows.");
			System.out.println(table.getColumns());
			for(Row row : table.getRows()) {
				for(String col : table.getColumns())
					System.out.print(" " + row.get(col));
				System.out.println();
			}
			System.out.println("================================================================");
			System.out.println();
		}
	}
}
