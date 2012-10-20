package net.sf.minuteProject.console.component;

import static net.sf.minuteProject.console.utils.UIUtils.createTextField;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CheckBoxField extends JPanel{

	private JCheckBox box;
	private JLabel label;
	private JTextField tf;
	
	public CheckBoxField(String name, String description, String defaultValue, boolean isChecked, String tip) {
		box = new JCheckBox();
		box.setText(description);
		this.setName(name);
		box.setSelected(isChecked);
		box.setOpaque(true);
		tf = new JTextField();
		tf = createTextField(defaultValue);
	}

	public JCheckBox getBox() {
		return box;
	}

	public JLabel getLabel() {
		return label;
	}

	public JTextField getTf() {
		return tf;
	}
	
}
