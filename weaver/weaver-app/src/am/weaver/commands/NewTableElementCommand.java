package am.weaver.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import am.weaver.datasource.Row;
import am.weaver.datasource.Table;
import am.weaver.dialogs.NewTableElementDialog;
import am.weaver.editors.TableEditor;

public class NewTableElementCommand extends AbstractHandler{
	
	public static final String ID = "am.weaver.commands.NewTableElement";
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
		
		if(part instanceof TableEditor){
			TableEditor editor = (TableEditor)part;
			Table table = editor.getTable();
			
			NewTableElementDialog dialog = new NewTableElementDialog(part.getSite().getShell(),
					table.getColumns().get(0));
			
			/*
			 * The input of the dialog is not validated
			 */
			
			if(dialog.open() == Window.OK){				
				String[] columns = new String[table.getColumns().size()];
				Object[] data = new Object[table.getColumns().size()];
				data[0] = dialog.getCellValue();
				table.addRow(new Row(table.getColumns().toArray(columns), data));
				
				editor.getViewer().refresh();
			}
						
		}
		
		
		return null;
	}

}