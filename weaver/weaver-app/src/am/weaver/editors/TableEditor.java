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
import am.weaver.viewers.TableViewerEditableColumn;
import am.weaver.viewers.TableViewerWithHeaders;

public class TableEditor extends EditorPart {

	public static final String ID = "am.weaver.editors.Table";

	private Table table;
	private TableEditorInput input;
	private TableViewerWithHeaders viewer;
	

	private void createColumns() {

		for (int i = 0; i < table.getColumns().size(); i++) {
			if (i == 0) {
				TableViewerEditableColumn viewerColumn = new TableViewerEditableColumn(viewer.getRowsViewer(),
						SWT.NONE, table.getColumns().get(i));												
				
				TableColumn col = viewerColumn.getColumn();
				col.setText(table.getColumns().get(i));
				col.setWidth(100);
				col.setResizable(false);

			} else {				
				TableViewerEditableColumn viewerColumn = new TableViewerEditableColumn(viewer.getTableViewer(),
						SWT.NONE, table.getColumns().get(i));				
				
				viewerColumn.setEditable(true);
				
				TableColumn col = viewerColumn.getColumn();
				col.setText(table.getColumns().get(i));
				col.setWidth(100);
				col.setResizable(true);
			}

		}
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
		table = Activator.getDefault().getDataSource().getTable(this.input.getTableName());
		this.setPartName(table.getTableName());
	}

	@Override
	public void createPartControl(Composite parent) {
		viewer = new TableViewerWithHeaders(parent);

		viewer.getTableViewer().setContentProvider(new ArrayContentProvider());
		viewer.getRowsViewer().setContentProvider(new ArrayContentProvider());

		createColumns();

		viewer.setInput(table.getRows());
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
		return this.table;
	}
	
	/*
	 * Updates the whole row as a row is the only element of the table.
	 * The table widget will ask each column to update the content of its cell
	 * in the given row.  
	 */
	public void cellUpdated(int row){
		viewer.updateElement(table.getRows().get(row));
	}
	
	public boolean isVisible(){
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().isPartVisible(this);
	}
	
	public boolean isCellVisible(int row, int col){
		return this.isVisible() && viewer.isCellVisible(row, col);				
	}
}
