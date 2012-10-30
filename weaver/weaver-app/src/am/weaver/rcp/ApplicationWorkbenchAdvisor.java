package am.weaver.rcp;

import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import am.weaver.perspectives.DefaultPerspective;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {
	
	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(
			IWorkbenchWindowConfigurer configurer) {
		return new ApplicationWorkbenchWindowAdvisor(configurer);
	}

	public String getInitialWindowPerspectiveId() {		
		return DefaultPerspective.ID;
				
	}

	public void postStartup(){
		super.postStartup();								
	}
}
