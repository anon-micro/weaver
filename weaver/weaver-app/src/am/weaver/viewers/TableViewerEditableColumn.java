package am.weaver.viewers;


import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;

import am.weaver.datasource.Row;

public class TableViewerEditableColumn extends ViewerColumn{
	
	private TableColumn column;
	private boolean editable;	
	
	
	private class LabelProvider extends ColumnLabelProvider {
		private String columnId;

		public LabelProvider(String columnId) {
			this.columnId = columnId;
		}

		@Override
		public String getText(Object element) {
			Row row = (Row) element;
			Object value = row.get(columnId);			
			if(value != null){
					return value.toString();
			}			
			return "";
		}

	}
	
	
	private class ColumnEditingSupport extends EditingSupport{
		
		private TableViewerEditableColumn column;
		private TableViewer viewer;
		private String columnId;
		
		public ColumnEditingSupport(TableViewer viewer, TableViewerEditableColumn column, String columnId) {
			super(viewer);
			
			this.column = column;
			this.viewer = viewer;
			this.columnId = columnId;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			TextCellEditor cellEditor = new TextCellEditor(viewer.getTable());
			cellEditor.setStyle(SWT.MULTI);
			
			return cellEditor;
		}

		@Override
		protected boolean canEdit(Object element) {			
			return column.isEditable();
		}

		@Override
		protected Object getValue(Object element) {
			Row row = (Row)element;
			Object value = row.get(columnId);
			if(value != null){
				return value.toString();
			}
		
			return "";			
		}

		@Override
		protected void setValue(Object element, Object value) {
			Row row = (Row)element;
			row.set(columnId, value);
			
			viewer.update(element, null);
		}
		
	}
	
	
	public TableViewerEditableColumn(TableViewer viewer, int style, String columnId) {
		this(viewer, new TableColumn(viewer.getTable(), style), columnId);		
	}
	
	public TableViewerEditableColumn(TableViewer viewer, TableColumn column, String columnId) {
		super(viewer, column);
				
		this.column = column;
		
		this.setEditingSupport(new ColumnEditingSupport(viewer, this, columnId));
		this.setLabelProvider(new LabelProvider(columnId));
	}
	
	public TableColumn getColumn(){
		return this.column;
	}
	
	public boolean isEditable(){
		return editable;
	}
	
	public void setEditable(boolean editable){
		this.editable = editable;
	}
}
