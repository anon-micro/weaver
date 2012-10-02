package am.app.perspectives;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;



import am.app.views.TableListView;

public class DefaultPerspective implements IPerspectiveFactory {
	
	public static final String ID = "am.app.perspectives.Default";
	
	public void createInitialLayout(IPageLayout layout) {				
		String editorArea = layout.getEditorArea();
		
		IFolderLayout bottom = layout.createFolder(
        		"bottom", IPageLayout.BOTTOM, (float) 0.65, editorArea);
     		
        bottom.addView(IConsoleConstants.ID_CONSOLE_VIEW);
		
		IFolderLayout topLeft = layout.createFolder(
                "topLeft", IPageLayout.LEFT, (float) 0.28, editorArea);		
        topLeft.addView(TableListView.ID);
                             
	}		
}