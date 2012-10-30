package am.weaver.views;


import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;

import am.weaver.commands.OpenTableEditorCommand;
import am.weaver.datasource.DataSource;


public class TableListView extends ViewPart {
	public static final String ID = "am.weaver.views.TableList";

	private ListViewer viewer;
				
	public void createPartControl(Composite parent) {		
		viewer = new ListViewer(parent, SWT.V_SCROLL);		
		viewer.setContentProvider(new ArrayContentProvider());		
				
		this.getSite().setSelectionProvider(viewer);
		
		viewer.addDoubleClickListener(new IDoubleClickListener(){			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				IHandlerService handlerService = 
						(IHandlerService) getSite().getService(IHandlerService.class);
				
				try {					
					handlerService.executeCommand(OpenTableEditorCommand.ID, null);
				} catch (Exception e) {
					throw new RuntimeException("OpenEditorCommand not found ");					
				}
			}
			
		});
	}

	
	public void setFocus() {
		viewer.getControl().setFocus();		
	}
	
	public void setInput(DataSource input){		
		viewer.setInput(input.getTableNames().toArray());		
	}
}