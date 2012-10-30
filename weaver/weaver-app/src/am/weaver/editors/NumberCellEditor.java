package am.weaver.editors;

import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public class NumberCellEditor extends TextCellEditor{
			
	public NumberCellEditor(Composite parent){
		super(parent);
	}
	
	@Override
	protected Control createControl(Composite parent) {
		Text text = (Text)super.createControl(parent);
		
		text.addVerifyListener(new VerifyListener() {
	        @Override
	        public void verifyText(VerifyEvent e) {

	            Text text = (Text)e.getSource();
	            
	            final String curStr = text.getText();
	            String newStr = curStr.substring(0, e.start) + e.text + curStr.substring(e.end);

	            boolean isNumber = true;
	            try
	            {
	            	Integer.parseInt(newStr);	                
	            }
	            catch(NumberFormatException ex)
	            {
	                isNumber = false;
	            }
	            
	            if(!isNumber)
	                e.doit = false;
	        }
	    });
		
		return text;
	}
}
