package am.app.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class TableEditorInput implements IEditorInput{
	
	private String tableName;
	
	public TableEditorInput(String tableName){
		this.tableName = tableName;
	}
	
	public String getTableName(){
		return this.tableName;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

	@Override
	public boolean exists() {		
		return true;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {		
		return null;
	}

	@Override
	public String getName() {		
		return this.tableName;
	}

	@Override
	public IPersistableElement getPersistable() {		
		return null;
	}

	@Override
	public String getToolTipText() {		
		return this.tableName;
	}
	
	@Override
	public boolean equals(Object input){
		if(!(input instanceof TableEditorInput)){
			return false;
		}
		
		return ((TableEditorInput)input).getTableName().equals(tableName);
	}

}
