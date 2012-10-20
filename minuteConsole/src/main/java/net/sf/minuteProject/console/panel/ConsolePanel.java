package net.sf.minuteProject.console.panel;

import static net.sf.minuteProject.console.utils.UIUtils.addSeparator;
import static net.sf.minuteProject.console.utils.UIUtils.createTabPanel;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import net.sf.minuteProject.console.face.FillBasicConfiguration;
import net.sf.minuteProject.integration.bean.BasicIntegrationConfiguration;

public class ConsolePanel extends JPanel implements FillBasicConfiguration{

	private ModelAccessPanel modelAccessPanel;
	private ModelCommonPanel modelCommonPanel;
	private TargetPanel targetPanel;
	
	public ConsolePanel() {
		setBorder(BorderFactory.createTitledBorder("Minute Project Basic Console"));
	    BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
	    setLayout(layout);
	    JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
	    JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
	    JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
//		modelAccessPanel = new ModelAccessPanel();
//		modelCommonPanel = new ModelCommonPanel();
//		targetPanel = new TargetPanel();

		p1.add(modelAccessPanel);
		p2.add(modelCommonPanel);
//		p3.add(targetPanel);
		
		add(p1);
		add(p2);
		add(p3);
	}
	
//	public ConsolePanel() {
//		MigLayout lm = new MigLayout("ins 20", "[para]0[][100lp, fill][60lp][95lp, fill]", "");
//		JPanel panel = createTabPanel(lm);
//	
//		addSeparator(panel, "Model Access");
//	
//		fillModelAccessPanel(panel);
//	
//	
//		addSeparator(panel, "Common configuration");
//	
//		fillModelCommonPanel (panel);
//	
//		addSeparator(panel, "Ship");
//	
//		TargetPanel tp = new TargetPanel(this);
//	}
	
	public void fill(BasicIntegrationConfiguration bic) {
		modelAccessPanel.fill(bic);
		modelCommonPanel.fill(bic);
		targetPanel.fill(bic);
	}
}
