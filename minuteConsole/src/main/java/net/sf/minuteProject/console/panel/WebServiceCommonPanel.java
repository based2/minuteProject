package net.sf.minuteProject.console.panel;

import static net.sf.minuteProject.console.utils.UIUtils.createCombo;
import static net.sf.minuteProject.console.utils.UIUtils.createLabel;
import static net.sf.minuteProject.console.utils.UIUtils.createTextField;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.sf.minuteProject.configuration.bean.strategy.datamodel.PrimaryKeyPolicyPatternEnum;
import net.sf.minuteProject.console.ConsoleSample;
import net.sf.minuteProject.console.face.FillBasicConfiguration;
import net.sf.minuteProject.integration.bean.BasicIntegrationConfiguration;
import net.sf.minuteProject.loader.catalog.databasecatalog.node.Database;
import net.sf.minuteProject.utils.code.RestrictedCodeUtils;

@SuppressWarnings("serial")
public class WebServiceCommonPanel extends JPanel implements FillBasicConfiguration {

//	private Form f;
	public static final String root_package = "root package";
	public static final String model_name = "model name";
	public static final String version_name = "version";
	public static final String primary_key_policy = "primary key policy";
	public static final String sequence_pattern = "pattern";
	public static final String global_sequence_name = "name";
	public static final String entity_attached_sequence_suffix =  "suffix";
	private ConsoleSample consoleSample;
	public static final String SEQUENCE = "sequence";
	public static final String AUTOINCREMENT = "autoincrement";
	public static final String GLOBAL_SEQUENCE = "global sequence";
	public static final String ENTITY_ASSOCIATED_SEQUENCE = "entity linked sequence";
	
	private boolean isTargetDirTouched = false;
	private JTextField rootPackageTf, 
	       modelNameTf, 
	       targetDirTf, 
	       versionNameTf, 
	       sequencePatternTf;
	private JComboBox pkPolicyCb, sequencePatternCb;
	private JLabel sequencePatternL, sequenceL, globalSequenceNameL, entityAttachedSequenceSuffixL;
	
	public WebServiceCommonPanel(ConsoleSample consoleSample) {
		this.consoleSample = consoleSample;
		globalSequenceNameL = createLabel(global_sequence_name);
		entityAttachedSequenceSuffixL = createLabel(entity_attached_sequence_suffix);
	}

	public void fill(BasicIntegrationConfiguration bic) {
		bic.setRootpackage(rootPackageTf.getText());
		bic.setModelName(modelNameTf.getText());
//		bic.setDefaultBusinesspackage(modelNameTf.getText());
		String pkPolicy = pkPolicyCb.getSelectedItem().toString();
		bic.setPrimaryKeyPolicy(PrimaryKeyPolicyPatternEnum.getPrimaryKeyPolicy(pkPolicy));
		bic.setOutputDir(targetDirTf.getText());
		bic.setVersion(versionNameTf.getText());
		if (pkPolicy.equals(SEQUENCE)) {
			String sequencePattern = sequencePatternCb.getSelectedItem().toString();
			bic.setSequencePattern(sequencePattern);
			if (sequencePattern.equals(GLOBAL_SEQUENCE)) 
				bic.setSequenceGlobalName(sequencePatternTf.getText());
			else
				bic.setSequenceEntitySuffix(sequencePatternTf.getText());
		}	
	}
	
	public void fillPanel (JPanel panel) {
		panel.add(createLabel(root_package),   "skip");
		rootPackageTf = createTextField("");
		panel.add(rootPackageTf,      "wrap");
		
		panel.add(createLabel(model_name),   "skip");
		modelNameTf = createTextField("",new ModelNameListener());
		panel.add(modelNameTf);

		panel.add(createLabel(version_name),   "center");
		versionNameTf = createTextField("1.0");
		panel.add(versionNameTf,      "wrap para");
		
		panel.add(createLabel(primary_key_policy),   "skip");
		pkPolicyCb = createCombo(new String[] {SEQUENCE, AUTOINCREMENT}, new PkPolicyItemListener());
		panel.add(pkPolicyCb);
		
		// sequence specific
		sequenceL = createLabel(sequence_pattern);
		panel.add(sequenceL,   "center");
		sequencePatternCb = createCombo(new String[] {GLOBAL_SEQUENCE, ENTITY_ASSOCIATED_SEQUENCE}, new SequencePatternItemListener());
		panel.add(sequencePatternCb, "wrap");
		
		panel.add(createLabel(""),   "skip");
		setSequencePatternLable();
		panel.add(sequencePatternL,   "skip, center");
		sequencePatternTf = createTextField("");
		panel.add(sequencePatternTf,      "wrap para");
		//sequence end
	   
		panel.add(createLabel("target dir"),   "skip");
	    targetDirTf = createTextField(getDefaultTargetDir(), new TargetDirListener());
		panel.add(targetDirTf,      "span, growx, wrap para");		
	}

	private void setSequencePatternLable() {
		if (sequencePatternL==null)
			sequencePatternL = createLabel("");
		if (sequencePatternCb.getSelectedItem().toString().equals(GLOBAL_SEQUENCE))
			sequencePatternL.setText(global_sequence_name); 
		else 
			sequencePatternL.setText(entity_attached_sequence_suffix); 
	}

	private class PkPolicyItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange()==ItemEvent.SELECTED) {
				if (e.getItem().toString().equals(SEQUENCE)) {
					showSequenceDetails();
				} else {
					hideSequenceDetails();
				}
			}
		}
	}

	private class SequencePatternItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange()==ItemEvent.SELECTED) {
				setSequencePatternLable();
			}
		}
	}
	private void hideSequenceDetails() {
		sequenceL.setVisible(false);
		sequencePatternCb.setVisible(false);
		sequencePatternL.setVisible(false);
		sequencePatternTf.setVisible(false);
	}
	
	private void showSequenceDetails() {
		sequenceL.setVisible(true);
		sequencePatternCb.setVisible(true);
		setSequencePatternLable();
		sequencePatternL.setVisible(true);
		sequencePatternTf.setVisible(true);
		
	}
	private class TargetDirListener implements FocusListener {
		String value, previousValue;
		public void focusLost(FocusEvent arg0) {
			value = targetDirTf.getText();
			if (previousValue!=null && value!=null) {
				if (!isTargetDirTouched)
					isTargetDirTouched=!(previousValue.equals(value));
			}
		}
		
		public void focusGained(FocusEvent arg0) {
			previousValue = targetDirTf.getText();
		}
	}
	
	private class ModelNameListener implements FocusListener {

		public void focusLost(FocusEvent arg0) {
			rebuildDefaultTargetDir();
		}
		
		public void focusGained(FocusEvent arg0) {
		}
	}
	
	public void rebuildDefaultTargetDir() {
		if (!isTargetDirTouched) {
			targetDirTf.setText(getDefaultTargetDir());
		}
	}

	private String getDefaultTargetDir() {
		StringBuffer sb = new StringBuffer ("../output");
		String formattedModelName = getFormattedModelName();
		if (formattedModelName!=null)
			sb.append("/"+formattedModelName);
		String formattedTargetName = getFormattedTargetName();
		if (formattedTargetName!=null)
			sb.append("/"+formattedTargetName);		
		return sb.toString();
	}

	private String getFormattedTargetName() {
		return getFormattedName(getTechnologyName());
	}

	private String getTechnologyName() {
		return consoleSample.getTargetPanel().getTargetTechnology();
	}

	private String getFormattedModelName() {
		return getFormattedName(modelNameTf.getText());
	}
	private String getFormattedName(String name) {
		if (name==null || name.trim().equals(""))
			return null;
		return getJavaFormattedName(name);
	}

	private String getJavaFormattedName(String text) {
		return RestrictedCodeUtils.convertToValidJava(text);
	}

	public void applyCurrentPrimaryKeyPolicy(Database database) {
		pkPolicyCb.setSelectedItem(database.getPrimaryKeyPolicy().getType());
		
	}	
	
	
	
}
