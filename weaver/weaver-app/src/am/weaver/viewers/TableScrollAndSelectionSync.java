package am.weaver.viewers;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;

public class TableScrollAndSelectionSync implements PaintListener, SelectionListener, Listener {
	private static Field NSPoint_x, NSPoint_y;
	private static Constructor<?> NSPoint_new;
	private static Method NSView_scrollPoint;
	private static boolean MacOS = false;

	// This is a total hack to provide platform specific support for table scrolling
	// Ideally this logic would be placed in a separate package that is only included
	// in the MacOs build.
	static {
		try {
			Class<?> viewClass = Class.forName("org.eclipse.swt.internal.cocoa.NSView");
			Class<?> pointClass = Class.forName("org.eclipse.swt.internal.cocoa.NSPoint");
			NSPoint_new = pointClass.getConstructor();
			NSPoint_x = pointClass.getField("x");
			NSPoint_y = pointClass.getField("y");
			NSView_scrollPoint = viewClass.getMethod("scrollPoint", pointClass);
			MacOS = true;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private final Table master, slave;
	private int oldVerticalPos;
	
	public TableScrollAndSelectionSync(Table master, Table slave) 
	{
		this.master = master;
		this.slave = slave;
		oldVerticalPos = master.getClientArea().y;
		if(MacOS)
			master.addPaintListener(this);
		else
			master.getVerticalBar().addSelectionListener(this);
		
		master.addListener(SWT.Selection, this);
	}
	
	@Override
	public void paintControl(PaintEvent e) {
		int newVerticalPos = master.getClientArea().y;
		if(newVerticalPos == oldVerticalPos)
			return;

		oldVerticalPos = newVerticalPos;
		
		Rectangle slaveClientArea = slave.getClientArea();
		if(slaveClientArea.y == newVerticalPos)
			return;
		
		if(MacOS) // MacOS hack continues  
			try {
				Field viewField = Table.class.getField("view");
				Object slaveView = viewField.get(slave);
				Object nsPoint = NSPoint_new.newInstance();
				int x = slaveClientArea.x;
				int y = newVerticalPos + master.getHeaderHeight();
				NSPoint_x.set(nsPoint, (double) x);
				NSPoint_y.set(nsPoint, (double) y);
				NSView_scrollPoint.invoke(slaveView, nsPoint);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		slave.setTopIndex(master.getTopIndex());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		widgetSelected(e);
	}

	@Override
	public void handleEvent(Event event) {
		slave.setSelection(master.getSelectionIndices());		
	}
}