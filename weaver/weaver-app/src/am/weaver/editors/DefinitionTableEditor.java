package am.weaver.editors;

import am.weaver.datasource.Table;

public class DefinitionTableEditor extends DataTableEditor{
	public static final String ID = "am.weaver.editors.DefinitionTable";
	
	public Table getTable(){		
		return super.getTable().getDefinitionTable();
	}
	
}
