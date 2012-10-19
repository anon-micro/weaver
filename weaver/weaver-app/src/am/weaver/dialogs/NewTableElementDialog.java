package am.weaver.dialogs;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class NewTableElementDialog extends TitleAreaDialog {
	
	private String firstColumnName;
	private String cellValue;
	
	public NewTableElementDialog(Shell parentShell, String firstColumnName) {
		super(parentShell);
		this.firstColumnName = firstColumnName;
	}
	
	@Override
	protected Control createContents(Composite parent) {
	    Control contents = super.createContents(parent);
	    setTitle("Add new element to the table");
	    setMessage("Please enter the data for the new element",
	        IMessageProvider.INFORMATION);
	    
	    return contents;
	}
	
	@Override
	  protected Control createDialogArea(Composite parent) {
	    GridLayout layout = new GridLayout();
	    layout.numColumns = 2;
	    parent.setLayout(layout);
	    
	    Label columnLabel = new Label(parent, SWT.NONE);
	    columnLabel.setText(firstColumnName);	    
	    final Text cellText = new Text(parent, SWT.BORDER | SWT.SINGLE);
	    
	    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
	    cellText.setLayoutData(gd);
	    
	    cellText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				cellValue = cellText.getText();
			}
		});
	    
	    Label spacer = new Label(parent, SWT.NONE);
	    spacer.setVisible(false);
	    
	    return parent;

	  }
	
	public String getCellValue(){
		return cellValue;
	}
}
