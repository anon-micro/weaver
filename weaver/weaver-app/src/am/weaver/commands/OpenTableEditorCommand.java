package am.weaver.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import am.weaver.editors.TableEditor;
import am.weaver.editors.TableEditorInput;
import am.weaver.views.TableListView;


public class OpenTableEditorCommand extends AbstractHandler{
	public static final String ID = "am.app.commands.OpenTableEditor";
			
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
				
	    IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
	    IWorkbenchPage page = window.getActivePage();
	    TableListView view = (TableListView) page.findView(TableListView.ID);
	    	    
	    ISelection selection = view.getSite().getSelectionProvider().getSelection();
	    
	    if (selection != null && selection instanceof IStructuredSelection) {
	      Object tableName = ((IStructuredSelection) selection).getFirstElement();
	      	      
	      if (tableName != null) {	    	  
	        TableEditorInput input = new TableEditorInput((String)tableName);	        
	        try {
	        	page.openEditor(input, TableEditor.ID, true);	        	
	        } catch (PartInitException e) {
	        	throw new RuntimeException(e);
	        }
	      }
	    }
	    
		return null;
	}		

}
