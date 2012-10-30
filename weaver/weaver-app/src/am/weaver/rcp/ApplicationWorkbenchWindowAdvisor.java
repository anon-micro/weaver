package am.weaver.rcp;


import java.io.PrintStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import am.weaver.views.TableListView;



public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	public ActionBarAdvisor createActionBarAdvisor(
			IActionBarConfigurer configurer) {
		
		return new ApplicationActionBarAdvisor(configurer);
	}

	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(400, 300));
		configurer.setShowCoolBar(false);
		configurer.setShowStatusLine(false);
		configurer.setTitle("Greenfield RCP Application");
	}
	
	public void postWindowOpen(){
		super.postWindowOpen();
		
		MessageConsole console = new MessageConsole("Colored Console", null);
		MessageConsoleStream out = console.newMessageStream();
		MessageConsoleStream err = console.newMessageStream();
						
		out.setColor(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
		err.setColor(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
		
				
		System.setOut(new PrintStream(out));
		System.setErr(new PrintStream(err));
		
		ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] { console });
		
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
									
		IConsoleView consoleView;
		try {
			consoleView = (IConsoleView) page.showView(IConsoleConstants.ID_CONSOLE_VIEW);
			consoleView.display(console);
			
			TableListView tableListView = (TableListView) page.showView((TableListView.ID));
			tableListView.setInput(Activator.getDefault().getDataSource());
			
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
