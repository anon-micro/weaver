package am.weaver.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import am.weaver.datasource.Table;
import am.weaver.editors.TableEditor;

public class DeleteTableElementCommand extends AbstractHandler{
	
	public static final String ID = "am.weaver.commands.DeleteTableElement";
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
		
		if(part instanceof TableEditor){
			TableEditor editor = (TableEditor)part;
			Table table = editor.getTable();
			
			table.deleteRows(editor.getViewer().getSelection());
			editor.getViewer().refresh();
		}
		
		return null;
	}

}
