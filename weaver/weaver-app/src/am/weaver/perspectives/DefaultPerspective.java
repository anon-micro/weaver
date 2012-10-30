package am.weaver.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IPlaceholderFolderLayout;
import org.eclipse.ui.console.IConsoleConstants;



import am.weaver.views.TableListView;

public class DefaultPerspective implements IPerspectiveFactory {
	
	public static final String ID = "am.weaver.perspectives.Default";
	
	public void createInitialLayout(IPageLayout layout) {				
		String editorArea = layout.getEditorArea();
		
		IPlaceholderFolderLayout bottom = layout.createFolder(
        		"bottom", IPageLayout.BOTTOM, (float) 0.65, editorArea);
     		
        bottom.addPlaceholder(IConsoleConstants.ID_CONSOLE_VIEW);
		
        IPlaceholderFolderLayout topLeft = layout.createFolder(
                "topLeft", IPageLayout.LEFT, (float) 0.28, editorArea);
		topLeft.addPlaceholder(TableListView.ID);		                              
	}		
}
