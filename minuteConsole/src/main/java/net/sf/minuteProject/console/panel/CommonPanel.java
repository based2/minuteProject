package net.sf.minuteProject.console.panel;

import static net.sf.minuteProject.console.utils.UIUtils.createLabel;
import static net.sf.minuteProject.console.utils.UIUtils.createTextAreaScroll;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class CommonPanel extends JPanel {

	private JPanel panel;
	private JLabel limitationsL, conventionsL;
	private JScrollPane limitationsJSP, conventionsJSP;
	public static final String limitations = "limitations";
	public static final String conventions = "conventions";
	
	
	public CommonPanel(JPanel panel) {
		super();
		this.panel = panel;
	}


	public void fillCommonPanel () {
		limitationsL = createLabel(limitations);
		panel.add(limitationsL, "skip");
		limitationsJSP = createTextAreaScroll(getLimitations(), 10, 40, true, false);
		panel.add(limitationsJSP, "span, growx");		
		
		conventionsL = createLabel(conventions);
		panel.add(conventionsL, "skip");
		conventionsJSP = createTextAreaScroll(getConventions(), 10, 40, true, false);
		panel.add(conventionsJSP, "span, growx");				
	}


	private String getConventions() {
		return "When generating for ORM framework the following conventions are applied:\n"+
		       "- Tables without primary key are excluded from the generation;\n"+
		       "- Views are enriched with a virtual primary key: the first field serves as primary key.\n\n"
//		       +"When using sequence the sequence name is the concatenation of the table name and _SEQ."
		       ;
	}


	private String getLimitations() {
		return "The console application has no enrichment of the data model capabilities such as provided by the classical 'configuration' way.\n\n"+
		       "When generating for ORM framework the following limitations are applied:\n"+
		       "- All entities (tables/views) are on the same schema;\n"+
		       "- Every table must have a primary key;\n"+
		       "- Composite primary keys are supported only for many-to-many tables;\n"+
		       "- Composite foreign keys are not supported.\n"+
		       ""
		 ;
	}
}
