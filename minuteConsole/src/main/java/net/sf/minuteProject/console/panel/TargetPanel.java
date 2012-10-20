package net.sf.minuteProject.console.panel;

import static net.sf.minuteProject.console.utils.UIUtils.createCombo;
import static net.sf.minuteProject.console.utils.UIUtils.createLabel;
import static net.sf.minuteProject.console.utils.UIUtils.createTextAreaScroll;
import static net.sf.minuteProject.console.utils.UIUtils.updateTableScroll;
import static net.sf.minuteProject.console.utils.UIUtils.updateTextAreaScroll;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.sf.minuteProject.application.ModelViewGenerator;
import net.sf.minuteProject.console.ConsoleSample;
import net.sf.minuteProject.console.face.FillBasicConfiguration;
import net.sf.minuteProject.exception.MinuteProjectException;
import net.sf.minuteProject.integration.bean.BasicIntegrationConfiguration;
import net.sf.minuteProject.loader.catalog.technologycatalog.node.Technology;
import net.sf.minuteProject.utils.FormatUtils;
import net.sf.minuteProject.utils.catalog.TechnologyCatalogUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class TargetPanel extends JPanel implements FillBasicConfiguration{

	private static Logger logger = Logger.getLogger(TargetPanel.class);
	private JButton generateButton, detailButton;
	public static String targetL = "choose target";
	public static String statusL = "status";
	public static String dependencyL = "dependency";
	public static String descriptionL = "description";
	public static String showDetailL = "Show details";
	public static String hideDetailL = "Hide details";
	private ConsoleSample consoleSample;
	private JComboBox targetCb;
	private JLabel statusJL, statusDetailJL, descriptionJL, dependencyJL;
	private JScrollPane descriptionJSP;
	private JPanel panel;
	private String[][] dependentFrameworks;
	private JScrollPane dependentFrameworksJSP;
	private enum ModelType {rdbms, webservice} 
	
	public TargetPanel(ConsoleSample consoleSample) {
		this(consoleSample, "rdbms");
	}	

	public TargetPanel(ConsoleSample consoleSample, String type) {
		this.consoleSample = consoleSample;
		statusJL = createLabel(statusL);
		dependencyJL = createLabel(dependencyL);
		descriptionJL = createLabel(descriptionL);
	}
	
	private JButton getGenerateButton() {
		generateButton = new JButton("Generate");
		generateButton.addActionListener(new ClickListener());
		return generateButton;
	}

	private JButton getDetailButton() { 
		detailButton = new JButton(showDetailL);
		detailButton.addActionListener(new ClickListener());
		return detailButton;
	}
	
	private class ClickListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == generateButton) {
				BasicIntegrationConfiguration bic = new BasicIntegrationConfiguration();
				consoleSample.fill(bic);				
				ModelViewGenerator mvg = new ModelViewGenerator(bic);
				mvg.resetTemplatePath();
				TechnologyCatalogUtils.resetTechnologies();
				try {
					mvg.generate();
				} catch (MinuteProjectException mpe) {
					logger.info("error generating : "+mpe.getError());
				}
			}
			else if (e.getSource() == detailButton) {
				updateTechnologyDetails();
//				if (detailButton.getText().equals(showDetailL)) {
//					detailButton.setText(hideDetailL);
//					showDetails(panel);
//				} else {
//					detailButton.setText(showDetailL);
//					hideDetails(panel);
//				}
			}
		}
	}

	private class TargetNameListener implements ItemListener {

		public void itemStateChanged(ItemEvent e) {
			consoleSample.getModelCommonPanel().rebuildDefaultTargetDir();
			updateTechnologyDetails();
		}
	}
	
	public void fill(BasicIntegrationConfiguration bic) {
		bic.setTargetTechnology(getTargetTechnology());
	}

	public String getTargetTechnology() {
		if (targetCb==null || targetCb.getSelectedItem()==null)
			return getTechnologyNames()[0];
		return targetCb.getSelectedItem().toString();
//		return getTechnology(technologyName).getTemplateConfigFileName();
	}

	public void fillPanel (JPanel panel) {
		this.panel = panel;
		panel.add(createLabel(targetL), "skip");
		targetCb = createCombo(getTechnologyNames(), new TargetNameListener());
		panel.add(targetCb);	
		panel.add(getGenerateButton(), "wrap");
		showDetails(panel);
	}
	
	private void showDetails (JPanel panel) {
		Technology technology = getChoosenTechnology();
		panel.add(statusJL, "skip");
		statusDetailJL = createLabel(technology.getStatus());
		panel.add(statusDetailJL, "wrap");
		panel.add(descriptionJL, "skip");
		descriptionJSP = createTextAreaScroll(getTechnologyDescription(technology), 5, 40, true, false);
		panel.add(descriptionJSP, "span, growx");	

		panel.add(dependencyJL,"skip");
		dependentFrameworksJSP = getDependentFrameworksJSP ();
		panel.add(dependentFrameworksJSP, "span, growx");
	}	
	
	private String getTechnologyDescription(Technology technology) {
		return FormatUtils.convertAttributeText(technology.getDescription());//StringUtils.replace(technology.getDescription(), "\\", "\n");
//		return technology.getDescription();
	}

	private JScrollPane getDependentFrameworksJSP() {
		dependentFrameworks = TechnologyCatalogUtils.getFrameworkDependency(getChoosenTechnology(), consoleSample.getCatalogDir());
		TableModel dataModel = new DefaultTableModel(dependentFrameworks, getDependentFrameworksTitle());
		JTable table = new JTable(dataModel);
		Dimension size = table.getPreferredScrollableViewportSize();
		table.setPreferredScrollableViewportSize
		    (new Dimension(Math.min(getPreferredSize().width, size.width), 50));
		
		return new JScrollPane(table);
	}
	
	private String [] getDependentFrameworksTitle () {
		return new String[] {"Framework","Version"};
	}

	private void updateTechnologyDetails () {
		Technology technology = getChoosenTechnology();
		statusDetailJL.setText(technology.getStatus());
		updateTextAreaScroll(descriptionJSP,getTechnologyDescription(technology));
		updateTableScroll(dependentFrameworksJSP, TechnologyCatalogUtils.getFrameworkDependency(technology, consoleSample.getCatalogDir()), getDependentFrameworksTitle());
		updateTextAreaScroll(consoleSample.getTechnologyLimitationPanel().getLimitationsJSP(), consoleSample.getTechnologyLimitationPanel().getLimitations());
		consoleSample.getTechnologyConventionPanel().rebuildPanel(consoleSample.getTechnologyInfoTab());
//		consoleSample.rebuildTechnologyPanelTab();
	}

	public Technology getChoosenTechnology() {
		return TechnologyCatalogUtils.getPublishedTechnology(targetCb.getSelectedItem().toString(), consoleSample.getCatalogDir());
	}	

	private String[] getTechnologyNames() {
		return TechnologyCatalogUtils.getPublishedTechnologyNames(consoleSample.getCatalogDir());
	}
	
}
