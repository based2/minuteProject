package net.sf.minuteProject.console.panel;

import static net.sf.minuteProject.console.utils.UIUtils.createLabel;
import static net.sf.minuteProject.console.utils.UIUtils.createTextAreaScroll;
import static net.sf.minuteProject.console.utils.UIUtils.createTextField;

import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.sf.minuteProject.console.ConsoleSample;
import net.sf.minuteProject.console.face.FillBasicConfiguration;
import net.sf.minuteProject.integration.bean.BasicIntegrationConfiguration;
import net.sf.minuteProject.loader.catalog.technologycatalog.node.Convention;
import net.sf.minuteProject.loader.catalog.technologycatalog.node.Conventions;
import net.sf.minuteProject.loader.catalog.technologycatalog.node.Limitation;
import net.sf.minuteProject.loader.catalog.technologycatalog.node.Limitations;
import net.sf.minuteProject.loader.catalog.technologycatalog.node.Technology;

public class TechnologyLimitationPanel extends JPanel implements FillBasicConfiguration{

//	private JPanel panel;
	private JLabel limitationsL, conventionsL;
	private JScrollPane limitationsJSP;
	public static final String limitations = "limitations";
	public static final String conventions = "conventions";
	private ConsoleSample consoleSample;
	
	public TechnologyLimitationPanel (ConsoleSample consoleSample) {
		this.consoleSample = consoleSample;
	}
	
	public void fill(BasicIntegrationConfiguration bic) {
	}

	private List<Limitation> getTechLimitations () {
		Technology technology = consoleSample.getTargetPanel().getChoosenTechnology();
		return getTechLimitations (technology);
	}
	
	public List<Limitation> getTechLimitations (Technology technology) {
		Limitations limitations = technology.getLimitations();
		return limitations.getLimitations();
	}
	
	public void fillPanel (JPanel panel) {
		limitationsL = createLabel(limitations);
		panel.add(limitationsL, "skip");
		limitationsJSP = createTextAreaScroll(getLimitations(), 10, 40, true, false);
		panel.add(limitationsJSP, "span, growx");	

//		conventionsL = createLabel(limitations);
//		panel.add(conventionsL, "skip");
		
	}

	public String getLimitations() {
		StringBuffer sb = new StringBuffer();
		for (Limitation limitation : getTechLimitations()) {
			sb.append(limitation.getName()+"\n");
			sb.append(limitation.getDescription()+"\n\n");
		}
		return sb.toString();
	}

	public JScrollPane getLimitationsJSP() {
		return limitationsJSP;
	}

	public void setLimitationsJSP(JScrollPane limitationsJSP) {
		this.limitationsJSP = limitationsJSP;
	}
	
	
}
