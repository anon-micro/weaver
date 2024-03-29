package am.weaver.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import am.weaver.datasource.ColumnDefinition;
import am.weaver.datasource.ColumnType;
import am.weaver.datasource.DefinitionTable;
import am.weaver.datasource.Row;
import am.weaver.datasource.Table;
import am.weaver.dialogs.NewTableElementDialog;
import am.weaver.editors.DataTableEditor;
import am.weaver.editors.TableEditor;

public class NewTableElementCommand extends AbstractHandler{
	
	public static final String ID = "am.weaver.commands.NewTableElement";
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
		
		if(part instanceof TableEditor){
			TableEditor editor = (TableEditor)part;
			Table table = editor.getActiveTable();
			
			NewTableElementDialog dialog = new NewTableElementDialog(part.getSite().getShell(),
					table.getColumns().get(0));
			
			/*
			 * The input of the dialog is not validated
			 */
			int index = -1;
			if(editor.getActiveViewer().getSelection().length > 0){
				index = editor.getActiveViewer().getSelection().length - 1; 
			}
			
			if(dialog.open() == Window.OK){																
				if(table instanceof DefinitionTable){
					ColumnDefinition def = new ColumnDefinition(dialog.getCellValue(), "", ColumnType.String);
					if(index < 0){
						table.addRow(def);
					}
					else{
						table.insertRow(def, index);
					}
					editor.getDefinitionTableEditor().getViewer().refresh();
					editor.getDataTableEditor().updateViewer();					
				}
				else {
					Object[] data = new Object[table.getColumns().size()];
					data[0] = dialog.getCellValue();
					
					if(index < 0){
						table.addRow(data);
					}
					else{
						table.insertRow(data, index);
					}
										
					editor.getDataTableEditor().getViewer().refresh();
				}
			}
		}
		
		
		return null;
	}

}
