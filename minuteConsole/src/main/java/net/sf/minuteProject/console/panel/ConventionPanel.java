package net.sf.minuteProject.console.panel;

import static net.sf.minuteProject.console.utils.UIUtils.createLabel;
import static net.sf.minuteProject.console.utils.UIUtils.createTextField;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolTip;

import net.sf.minuteProject.console.face.FillBasicConfiguration;
import net.sf.minuteProject.integration.bean.BasicIntegrationConfiguration;

public class ConventionPanel extends JPanel implements FillBasicConfiguration{

	private JCheckBox virtualPkForViewsConvention;
	private JTextField virtualPkForViewsConventionListTf;
	
	private JCheckBox enableUpdatableAreaConvention;
//	private JTextField enableUpdatableAreaConventionListTf;
	
	public void fill(BasicIntegrationConfiguration bic) {
		if (virtualPkForViewsConvention.isSelected())
			bic.setVirtualPrimaryKey(virtualPkForViewsConvention.getText());
		bic.setEnableUpdatableAreaConvention(enableUpdatableAreaConvention.isSelected());		
	}

	public void fillPanel (JPanel panel) {
		virtualPkForViewsConvention = new JCheckBox("add 'virtual' primary key columns");
		virtualPkForViewsConvention.setToolTipText("When no primary key is provided for an entity, a default one is provided from the ones retrieved in the list");
		virtualPkForViewsConvention.setSelected(true);
		virtualPkForViewsConvention.setOpaque(true);
		panel.add(createLabel(""));
		panel.add(virtualPkForViewsConvention, "skip");

		virtualPkForViewsConventionListTf = createTextField("");
		panel.add(virtualPkForViewsConventionListTf,"growx, span, wrap para");

		enableUpdatableAreaConvention = new JCheckBox("enable updatable areas");
		enableUpdatableAreaConvention.setToolTipText("Add and Modify generated code in specific areas without losing benefit of consecutive generations");
		enableUpdatableAreaConvention.setSelected(true);
		enableUpdatableAreaConvention.setOpaque(true);
		panel.add(createLabel(""));
		panel.add(enableUpdatableAreaConvention, "skip");
		
//		fillConvention(panel, 
//			 virtualPkForViewsConvention, 
//			 true,				
//			 new JTextField(),
//	       "add 'virtual' primary key columns",
//	       "When no primary key is provided for an entity, a default one is provided from the ones retrieved in the list");
//
//		fillConvention(panel, 
//	       enableUpdatableAreaConvention, 
//	       false,
//	       "enable updatable areas",
//	       "Add and Modify generated code in specific areas without losing benefit of consecutive generations");
	}

	private void fillConvention (JPanel panel, JCheckBox jcb, boolean selected, String label, String tip) {
		fillConvention(panel, jcb, selected, null, label, tip);	
	}
	
	private void fillConvention (JPanel panel, JCheckBox jcb, boolean selected, JTextField jtf, String label, String tip) {
		jcb = new JCheckBox(label);
		jcb.setToolTipText(tip);
		jcb.setSelected(selected);
		jcb.setOpaque(true);
		panel.add(createLabel(""));
		panel.add(jcb, "skip");
		if (jtf!=null) {
			jtf = createTextField("");
			panel.add(jtf,"growx, span, wrap para");
		} 
	}
}
