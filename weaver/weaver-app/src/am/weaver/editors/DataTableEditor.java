package am.weaver.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;

import am.weaver.datasource.Table;
import am.weaver.rcp.Activator;
import am.weaver.viewers.DataTableViewerColumn;
import am.weaver.viewers.TableViewerWithHeaders;

public class DataTableEditor extends EditorPart {
	public static final String ID = "am.weaver.editors.DataTable";
	
	protected TableEditorInput input;
	protected TableViewerWithHeaders viewer;
	

	private void createColumns() {
		for (int i = 0; i < getTable().getColumns().size(); i++) {
			if (i == 0) {																				
				String id = getTable().getColumns().get(i);				
				TableColumn col = createHeaderColumn(id);				
				col.setText(id);
				col.setWidth(100);
				col.setResizable(false);

			} else {
				String id = getTable().getColumns().get(i);																				
				TableColumn col = createColumn(id);
				col.setText(id);
				col.setWidth(100);
				col.setResizable(false);
			}

		}
	}
	
	
	protected TableColumn createColumn(String id){
		DataTableViewerColumn viewerColumn = new DataTableViewerColumn(viewer.getTableViewer(),
				SWT.NONE, id);
		
		viewerColumn.setEditable(true);
		return viewerColumn.getColumn();
		
	}

	protected TableColumn createHeaderColumn(String id){
		DataTableViewerColumn viewerColumn = new DataTableViewerColumn(viewer.getRowsViewer(),
				SWT.NONE, id);
		
		viewerColumn.setEditable(true);
		return viewerColumn.getColumn();
	}
		
	
	@Override
	public void doSave(IProgressMonitor monitor) {
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		if (!(input instanceof TableEditorInput)) {
			throw new RuntimeException("Wrong input type");
		}

		this.input = (TableEditorInput) input;
		
		this.setSite(site);
		this.setInput(input);		
		this.setPartName(this.input.getTableName());
	}

	@Override
	public void createPartControl(Composite parent) {							
		viewer = new TableViewerWithHeaders(parent);

		viewer.getTableViewer().setContentProvider(new ArrayContentProvider());
		viewer.getRowsViewer().setContentProvider(new ArrayContentProvider());

		createColumns();
		
		viewer.setInput(getTable().getRows());						
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void setFocus() {
		viewer.getTableViewer().getControl().setFocus();
	}
	
	
	public TableViewerWithHeaders getViewer(){
		return viewer;
	}
	
	public Table getTable(){
		return Activator.getDefault().getDataSource().getTable(this.input.getTableName());
	}
	
	/*
	 * Updates the whole row as a row is the only element of the table.
	 * The table widget will ask each column to update the content of its cell
	 * in the given row.  
	 */
	public void cellUpdated(int row){
		viewer.updateElement(getTable().getRows().get(row));
	}
	
	public boolean isVisible(){
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().isPartVisible(this);
	}
	
	public boolean isCellVisible(int row, int col){
		return this.isVisible() && viewer.isCellVisible(row, col);				
	}
	
	public void updateViewer(){
		viewer.removeColumns();		
		createColumns();
		viewer.refresh();
	}
}
