package am.weaver.viewers;


import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;

import org.eclipse.jface.viewers.ViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;

import am.weaver.datasource.ColumnDefinition;
import am.weaver.datasource.ColumnType;
import am.weaver.datasource.Row;
import am.weaver.editors.DateTimeCellEditor;
import am.weaver.editors.NumberCellEditor;

public class DataTableViewerColumn extends ViewerColumn{
	
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
				if(value instanceof String){
					return (String)value;
				}
				else{
					return value.toString();
				}
			}
			
			return "";
		}

	}
	
	
	private class ColumnEditingSupport extends EditingSupport{
		
		private CellEditor cellEditor;
		
		protected DataTableViewerColumn column;
		protected TableViewer viewer;
		protected String columnId;
				
		public ColumnEditingSupport(TableViewer viewer, DataTableViewerColumn column, String columnId) {
			super(viewer);
			
			this.column = column;
			this.viewer = viewer;
			this.columnId = columnId;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {			
			Row row = (Row)element;															
			ColumnType type = row.getDefinitionTable().getColumnType(columnId);
						
			switch(type){
			case String:
			case Script:				
				cellEditor = new TextCellEditor(viewer.getTable(), SWT.MULTI);								
				break;
			case Number:
				cellEditor = new NumberCellEditor(viewer.getTable());								
				break;
			case Date:
				cellEditor = new DateTimeCellEditor(viewer.getTable());
				break; 
			case Enum:								
				Object value = row.get(columnId);
				Object[] enums = value.getClass().getEnumConstants();
																	 
				String[] enumStrings = new String[enums.length];		
				for(int i = 0; i < enums.length; i++ ){
					enumStrings[i] = enums[i].toString();
				}
				
				cellEditor = new ComboBoxCellEditor(viewer.getTable(), enumStrings);
				break;
			}
			
			return cellEditor;
		}

		@Override
		protected boolean canEdit(Object element) {			
			return column.isEditable();
		}

		@Override
		protected Object getValue(Object element) {
			Row row = (Row)element;			
			ColumnType type = row.getDefinitionTable().getColumnType(columnId);
			Object value = row.get(columnId);						
			
			switch(type){
			case String:
			case Script:
			case Number:
				if(value == null){
					return "";
				}
				else{
					return value.toString();
				}			
			case Date:
				return value;			
			case Enum:
				if(value == null){
					return 0;
				}
				else{
					ComboBoxCellEditor editor = (ComboBoxCellEditor)cellEditor;
					for(int i = 0; i < editor.getItems().length; i++){
						if(editor.getItems()[i].equals(value.toString())){
							return i;
						}
					}
					
					return 0;
				}
			}
		
			return "";			
		}

		@Override
		protected void setValue(Object element, Object value) {
			Row row = (Row)element;						
			ColumnType type = row.getDefinitionTable().getColumnType(columnId);
						
			switch(type){
			case String:
			case Script:
			case Number:
			case Date:
				row.set(columnId, value);
				break;							
			case Enum:				
				row.set(columnId, ((ComboBoxCellEditor)cellEditor).getItems()[(Integer)value]);
				break;
			}
						
			viewer.update(element, null);
		}
		
	}
	
	
	public DataTableViewerColumn(TableViewer viewer, int style, String columnId) {
		this(viewer, new TableColumn(viewer.getTable(), style), columnId);		
	}
	
	public DataTableViewerColumn(TableViewer viewer, TableColumn column, String columnId) {
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
