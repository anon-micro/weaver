package am.weaver.viewers;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;


public class TableViewerWithHeaders {

	private Composite parent;
	private TableViewer rowHeaderViewer;
	private TableViewer tableBodyViewer;
	
	public TableViewerWithHeaders(Composite parent) {
		this.parent = parent;
		createViewers(parent);
	}

	public Table getTable() {
		return tableBodyViewer.getTable();
	}

	public Table getRows() {
		return rowHeaderViewer.getTable();
	}

	public TableViewer getTableViewer() {
		return tableBodyViewer;
	}

	public TableViewer getRowsViewer() {
		return rowHeaderViewer;
	}

	public void setInput(Object input) {
		rowHeaderViewer.setInput(input);
		tableBodyViewer.setInput(input);
	}
	
	
	public int[] getSelection(){		
		return tableBodyViewer.getTable().getSelectionIndices();
	}
	
	
	public void refresh(){
		rowHeaderViewer.refresh();
		tableBodyViewer.refresh();
	}
	
	public void removeColumns(){
		TableColumn[] columns = tableBodyViewer.getTable().getColumns();
		int count = columns.length;
		for(int i = 0; i< count; i++){
			columns[i].dispose();
		}
		
		columns = rowHeaderViewer.getTable().getColumns();
		count = columns.length;
		for(int i = 0; i< count; i++){
			columns[i].dispose();
		}
	}
	
	public void updateElement(Object element) {
		rowHeaderViewer.update(element, null);
		tableBodyViewer.update(element, null);
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
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = layout.marginHeight = layout.verticalSpacing = 0;
		layout.horizontalSpacing = 1;
		parent.setLayout(layout);

		final Table rowHeader = new Table(parent, SWT.FULL_SELECTION | SWT.NO_SCROLL);
		rowHeader.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true));
		rowHeader.setHeaderVisible(true);
		rowHeader.setLinesVisible(true);

		final Table tableBody = new Table(parent, SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
		tableBody.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));		
		tableBody.setHeaderVisible(true);
		tableBody.setLinesVisible(true);
		
//		rowHeader.addMouseWheelListener(new MouseWheelListener() {
//			public void mouseScrolled(MouseEvent e) {
//				rowHeader.setTopIndex(rowHeader.getTopIndex() - e.count);
//			}
//		});
		
		rowHeaderViewer = new TableViewer(rowHeader);
		tableBodyViewer = new TableViewer(tableBody);
		
		// Horizontal bar on second table takes up a little extra space.
		// To keep vertical scroll bars in sink, force table1 to end above
		// horizontal scrollbar
		final Label spacer = new Label(parent, SWT.NONE);
		final ScrollBar hBarRight = tableBody.getHorizontalBar();
		updateSpacer(spacer, hBarRight);
		
		tableBody.addListener(SWT.Paint, new Listener() {
			@Override
			public void handleEvent(Event event) {
				if(hBarRight.isVisible() != spacer.isVisible()) {
					updateSpacer(spacer, hBarRight);
				}
			}
		});		
		
//		new TableScrollAndSelectionSync(rowHeader, tableBody);
		new TableScrollAndSelectionSync(tableBody, rowHeader);

		parent.setBackground(rowHeader.getBackground());
	}
	
	private void updateSpacer(final Label spacer, final ScrollBar hBarRight) {
		GridData spacerData = new GridData(SWT.LEFT, SWT.FILL, false, false);
		spacerData.heightHint = hBarRight.isVisible() ? hBarRight.getSize().y : 0;
		spacer.setLayoutData(spacerData);
		spacer.setVisible(hBarRight.isVisible());
	}
}
