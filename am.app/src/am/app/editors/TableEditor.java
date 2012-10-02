package am.app.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import am.app.Row;
import am.app.Table;
import am.app.rcp.Activator;
import am.app.viewers.TableViewerWithHeaders;


public class TableEditor extends EditorPart {
	
	public static final String ID = "am.app.editors.Table";
	
	private Table table;
	private TableEditorInput input; 
	private TableViewerWithHeaders viewer;
	
	
					
	
	class LabelProvider extends ColumnLabelProvider {
		
		private String columnId;
		public LabelProvider(String columnId){
			this.columnId = columnId;
		}
		
		@Override
		public String getText(Object element){
			Row row = (Row)element;
			return row.get(columnId).toString();
		}
				
	}
	
	
	
	public void createColumns(){
						
		for(int i = 0; i < table.getColumns().size(); i++){						
			if(i == 0){
				TableViewerColumn viewerColumn = new TableViewerColumn(viewer.getRowsViewer(), SWT.NONE);
				viewerColumn.setLabelProvider(new LabelProvider(table.getColumns().get(i)));				
				TableColumn col = viewerColumn.getColumn();
				
				col.setText(table.getColumns().get(i));
				col.setWidth(100);
				col.setResizable(false);
			}
			else{
				TableViewerColumn viewerColumn = new TableViewerColumn(viewer.getTableViewer(), SWT.NONE);
				viewerColumn.setLabelProvider(new LabelProvider(table.getColumns().get(i)));
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
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		if(!(input instanceof TableEditorInput)){
			throw new RuntimeException("Wrong input type");
		}
		
		this.input = (TableEditorInput)input;
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

}
