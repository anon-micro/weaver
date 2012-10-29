package am.weaver.editors;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;

public class DateTimeCellEditor extends CellEditor{
	
	private DateTime control;
	
	public DateTimeCellEditor(Composite parent) {
		super(parent, SWT.DATE | SWT.SHORT | SWT.DROP_DOWN);
	}
	
	@Override
	protected Control createControl(Composite parent) {			
		control = new DateTime(parent, this.getStyle());
		return control;
	}

	@Override
	protected Object doGetValue() {						
		Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.DAY_OF_MONTH, control.getDay());
		cal.set(Calendar.MONTH, control.getMonth());
		cal.set(Calendar.YEAR, control.getYear());
		
		return cal.getTime();
	}

	@Override
	protected void doSetFocus() {
		control.setFocus();		
	}

	@Override
	protected void doSetValue(Object value) {				
		Calendar cal = Calendar.getInstance();
		if(value != null){
			cal.setTime((Date)value);
		}		
		control.setDay(cal.get(Calendar.DAY_OF_MONTH));
		control.setMonth(cal.get(Calendar.MONTH));
		control.setYear(cal.get(Calendar.YEAR));
				
	}

}
