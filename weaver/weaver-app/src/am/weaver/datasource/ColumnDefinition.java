package am.weaver.datasource;

public class ColumnDefinition extends Row{

	public static final String NAME = "Name", DEFINITION = "Definition", TYPE = "Type";
	
	
	public ColumnDefinition(String name, String definition, ColumnType type) {
		super();
		setName(name);
		setDefinition(definition);
		setType(type);
	}

	public ColumnDefinition(String name) { this(name, "", ColumnType.String); }
	
	public String getName() { return(String) get(NAME); }
	public void setName(String name) { set(NAME, name); }
	
	public String getDefinition() { return (String) get(DEFINITION); }
	public void setDefinition(String definition) { set(DEFINITION, definition); }
	
	public ColumnType getType() { return (ColumnType) get(TYPE); }
	public void setType(ColumnType type) { set(TYPE, type); }
}
