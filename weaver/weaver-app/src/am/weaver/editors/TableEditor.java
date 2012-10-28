package am.weaver.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;

import am.weaver.datasource.Table;
import am.weaver.viewers.TableViewerWithHeaders;

public class TableEditor extends MultiPageEditorPart{	
	public static final String ID = "am.weaver.editors.Table";
	
	private DataTableEditor dataEditor;
	private DefinitionTableEditor defEditor;
	
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		if (!(input instanceof TableEditorInput)) {
			throw new RuntimeException("Wrong input type");
		}
		
		super.init(site, input);
	}
	
	@Override
	protected void createPages() {
		dataEditor = new DataTableEditor();
		defEditor = new DefinitionTableEditor();
		
		try {
			int index = addPage(dataEditor, getEditorInput());			
			setPageText(index, "Data");
			
			index = addPage(defEditor, getEditorInput());
			setPageText(index, "Definition");						
			
		} catch (PartInitException e) {			
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public TableViewerWithHeaders getActiveViewer(){
		return ((DataTableEditor)this.getActiveEditor()).getViewer();
	}
	
	public Table getActiveTable(){
		return ((DataTableEditor)this.getActiveEditor()).getTable();
	}
	
	public DataTableEditor getDataTableEditor(){
		return dataEditor;
	}
	
	public DefinitionTableEditor getDefinitionTableEditor(){
		return defEditor;
	}				
}
