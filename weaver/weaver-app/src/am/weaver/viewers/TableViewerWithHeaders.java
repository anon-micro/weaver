package am.weaver.viewers;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.PlatformUI;



public class TableViewerWithHeaders {
	
	private Composite parent;
	private TableViewer rows;
	private TableViewer table;
	
	
			
	public TableViewerWithHeaders(Composite parent){
		this.parent = parent;
		createViewers(parent);
	}
		
	
	public Table getTable(){
		return table.getTable();
	}
	
	public Table getRows(){
		return rows.getTable();
	}
	
			
	
	public TableViewer getTableViewer(){
		return table;
	}
	
	public TableViewer getRowsViewer(){
		return rows;
	}
	
	
	public void setInput(Object input){
		rows.setInput(input);
		table.setInput(input);
		
		createRowHeaders();
//		createSpacer();
	}
	
	
	private void createViewers(Composite parent){
		parent.setLayout(new FillLayout());
				
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = layout.marginHeight = layout.horizontalSpacing = 0;
		parent.setLayout(layout);
		
		
		rows = new TableViewer(parent, SWT.FULL_SELECTION | SWT.NO_SCROLL);
		rows.getTable().setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true));
		rows.getTable().setHeaderVisible(true);
		
		table = new TableViewer(parent, SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
		table.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		table.getTable().setHeaderVisible(true);


		
		
		// Make selection the same in both tables
		rows.getTable().addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				table.getTable().setSelection(rows.getTable().getSelectionIndices());
			}
		});
		
		table.getTable().addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				rows.getTable().setSelection(table.getTable().getSelectionIndices());
			}
		});
		
		
												
		
		
		ScrollBar vBarRight = table.getTable().getVerticalBar();
		vBarRight.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				rows.getTable().setTopIndex(table.getTable().getTopIndex());
			}
		});
		
									
		parent.setBackground(rows.getTable().getBackground());
	}
	
	private void createRowHeaders(){
//		for (TableItem item : rows.getTable().getItems()) {
//			
//			TableEditor editor = new TableEditor (rows.getTable());						
//			Button button = new Button (rows.getTable(), SWT.PUSH | SWT.FLAT);
//			button.setText(item.getText());
//			button.pack ();
//			//editor.minimumWidth = button.getSize ().x;
//			//editor.horizontalAlignment = SWT.LEFT;
//			editor.grabHorizontal = true;
//			editor.setEditor (button, item, 0);
//			
//		}
	}
	
	private void createSpacer(){															
		Label spacer = new Label(parent, SWT.NONE);
		GridData spacerData = new GridData();
		spacerData.heightHint =table.getTable().getHorizontalBar().getSize().y;
		spacer.setVisible(false);
		
	}
}
