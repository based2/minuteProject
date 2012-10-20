package net.sf.minuteProject.console.panel;

import static net.sf.minuteProject.console.utils.UIUtils.createLabel;
import static net.sf.minuteProject.console.utils.UIUtils.createTextField;

import java.awt.Component;
import java.awt.TextField;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.sf.minuteProject.configuration.bean.enrichment.convention.VersionConvention;
import net.sf.minuteProject.console.ConsoleSample;
import net.sf.minuteProject.console.component.CheckBoxField;
import net.sf.minuteProject.console.face.FillBasicConfiguration;
import net.sf.minuteProject.integration.bean.BasicIntegrationConfiguration;
import net.sf.minuteProject.loader.catalog.technologycatalog.node.Convention;
import net.sf.minuteProject.loader.catalog.technologycatalog.node.Conventions;
import net.sf.minuteProject.loader.catalog.technologycatalog.node.Technology;

public class TechnologyConventionPanel extends JPanel implements FillBasicConfiguration{

	public static final String limitations = "limitations";
	public static final String conventions = "conventions";
	public List<Component> componentList = new ArrayList<Component>() ;
	public List<CheckBoxField> conventionList = new ArrayList<CheckBoxField>() ;
	private ConsoleSample consoleSample;
	
	public TechnologyConventionPanel (ConsoleSample consoleSample) {
		this.consoleSample = consoleSample;
	}
	
	public void fill(BasicIntegrationConfiguration bic) {
		for (CheckBoxField cbf : conventionList) {
				fillConvention (cbf, bic);
		}		
	}

	private void fillConvention(CheckBoxField cbf, BasicIntegrationConfiguration bic) {
		if (cbf.getBox().isSelected())
			bic.addConvention(getConvention(cbf));
	}

	private net.sf.minuteProject.configuration.bean.enrichment.convention.Convention getConvention(CheckBoxField cbf) {
		net.sf.minuteProject.configuration.bean.enrichment.convention.Convention convention = null;
		if ("version-convention".equals(cbf.getName())) {
			convention=new VersionConvention();
		}
		if (convention!=null) {
			convention.setDefaultValue(cbf.getTf().getText());
		}
		
		return convention;
	}

	private List<Convention> getTechConventions () {
		Technology technology = consoleSample.getTargetPanel().getChoosenTechnology();
		return getTechConventions (technology);
	}

	public List<Convention> getTechConventions (Technology technology) {
		Conventions conventions = technology.getConventions();
		return conventions.getConventions();
	}
	
	public void fillPanel (JPanel panel) {
		for (Convention convention : getTechConventions()) {
			addConventionToPanel (convention, panel);
		}
	}
	
	private void addConventionToPanel(Convention convention, JPanel panel) {
		CheckBoxField cbf = new CheckBoxField(convention.getName(), convention.getDescription(), "", true, null);
		JLabel l = createLabel("");
		panel.add(l);
		panel.add(cbf.getBox(), "skip");	
		panel.add(cbf.getTf(),"growx, span, wrap para");	
		componentList.add(cbf.getBox());
		componentList.add(cbf.getTf());
		componentList.add(l);
		conventionList.add(cbf);
	}

	public void rebuildPanel(JPanel panel) {
//		panel.remov
		removeConventions(panel);
		this.removeAll();
		fillPanel(panel);
//		this.updateUI();
		this.repaint();
		panel.repaint();
	}

	private void removeConventions(JPanel panel) {
		for (Component component : componentList) {
			panel.remove(component);
		}
		conventionList = new ArrayList<CheckBoxField>();	
	}


}
