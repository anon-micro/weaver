package am.weaver.viewers;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import am.weaver.datasource.Row;


public class TableViewerWithHeaders {

	private Composite parent;
	private TableViewer rows;
	private TableViewer table;
	
	private int cellHeight  = 0;
	private TextLayout textLayout;
	
	public TableViewerWithHeaders(Composite parent) {
		this.parent = parent;
		createViewers(parent);
	}

	public Table getTable() {
		return table.getTable();
	}

	public Table getRows() {
		return rows.getTable();
	}

	public TableViewer getTableViewer() {
		return table;
	}

	public TableViewer getRowsViewer() {
		return rows;
	}

	public void setInput(Object input) {
		rows.setInput(input);
		table.setInput(input);

		//createRowHeaders();
		// createSpacer();
	}
	
	
	public Collection<Row> getSelection(){		
		ArrayList<Row> selection = new ArrayList<Row>();
		for(TableItem item: table.getTable().getSelection()){			
			selection.add((Row)item.getData());
		}		
		return selection;
	}
	
	
	public void refresh(){
		rows.refresh();
		table.refresh();
	}
	
	public void removeColumns(){
		TableColumn[] columns = table.getTable().getColumns();
		int count = columns.length;
		for(int i = 0; i< count; i++){
			columns[i].dispose();
		}
		
		columns = rows.getTable().getColumns();
		count = columns.length;
		for(int i = 0; i< count; i++){
			columns[i].dispose();
		}
	}
	
	public void updateElement(Object element) {
		rows.update(element, null);
		table.update(element, null);
	}
	
	
	public boolean isCellVisible(int row, int col){
		Table tableWidget = null;
		
		if(col < getRows().getColumnCount()){
			tableWidget = getRows();
		}
		else{
			tableWidget = getTable();
			col = col - getRows().getColumnCount();
		}
		
		Rectangle tableArea = tableWidget.getClientArea();
		Rectangle columnArea = tableWidget.getItem(row).getBounds(col);
		
		return tableArea.intersects(columnArea);
	}
	
	
	private void createViewers(Composite parent) {
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
		
		textLayout = new TextLayout(Display.getCurrent());
		textLayout.setFont(table.getTable().getFont());
		
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
		
		rows.getTable().addListener(SWT.MeasureItem, new Listener() {
			   public void handleEvent(Event event) {				  
				   textLayout.setText(((TableItem)event.item).getText());
				   textLayout.setWidth(((TableItem)event.item).getBounds().width);
					  
				   int h = textLayout.getBounds().height;
					  
				   if(h > event.height){
					   cellHeight = h;
				   }
				   
				   event.height = cellHeight;
				  
				  
			   }
			});
		
		table.getTable().addListener(SWT.MeasureItem, new Listener() {
			   public void handleEvent(Event event) {
				  textLayout.setText(((TableItem)event.item).getText());
				  textLayout.setWidth(((TableItem)event.item).getBounds().width);
				  
				  int h = textLayout.getBounds().height;
				  
				  if(h > event.height){
					  cellHeight = h;
				  }
				  
				  event.height = cellHeight;				  				  
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
	

	@SuppressWarnings("unused")
	private void createSpacer() {
		Label spacer = new Label(parent, SWT.NONE);
		GridData spacerData = new GridData();
		spacerData.heightHint = table.getTable().getHorizontalBar().getSize().y;
		spacer.setVisible(false);

	}
}
