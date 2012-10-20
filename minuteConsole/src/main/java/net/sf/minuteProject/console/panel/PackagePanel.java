package net.sf.minuteProject.console.panel;

import static net.sf.minuteProject.console.utils.UIUtils.createLabel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.sf.minuteProject.configuration.bean.Condition;
import net.sf.minuteProject.console.face.FillBasicConfiguration;
import net.sf.minuteProject.integration.bean.BasicIntegrationConfiguration;

public class PackagePanel extends JPanel implements FillBasicConfiguration{

	private JLabel packageJL;
	private JScrollPane packageJSP;
	private JButton addPackageLineButton, removePackageLinesButton;
	private ClickListener clickListener;
	private DefaultTableModel dataModel;
	private JTable table;
	
	public PackagePanel() {
		packageJL = createLabel("packaging");
		clickListener = new ClickListener();
		addPackageLineButton = getAddPackageLineButton();
		removePackageLinesButton = getRemovePackageLinesButton();
		dataModel = new DefaultTableModel(new String[][]{{"",""},{"",""}}, new String[] {"entities starting with goes to ","package"});
		table = new JTable(dataModel);
	}
	
	public void fill(BasicIntegrationConfiguration bic) {
		bic.setPackageConditions(getConditions());
	}

	public List<Condition> getConditions() {
		int nbRow = dataModel.getRowCount();
		List<Condition> conditions = new ArrayList<Condition>();
		for (int i = 0; i < nbRow; i++) {
			Condition condition = new Condition();
			condition.setType("package");
			condition.setStartsWith((String)dataModel.getValueAt(i,	0));
			condition.setResult((String)dataModel.getValueAt(i,	1));
			conditions.add(condition);
		}
		return conditions;
	}
	
	public void fillPanel (JPanel panel) {
		panel.add(packageJL,"skip");
		packageJSP = getPackageJSP ();
		panel.add(packageJSP, "span, growx");
		
		panel.add(createLabel(""),   "skip");
		panel.add(addPackageLineButton,"skip");
		panel.add(removePackageLinesButton,"wrap para");
	}

	private JScrollPane getPackageJSP() {
		Dimension size = table.getPreferredScrollableViewportSize();
		table.setPreferredScrollableViewportSize (new Dimension(Math.min(getPreferredSize().width, size.width), 50));
		return new JScrollPane(table);
	}
	
	private JButton getAddPackageLineButton() {
		JButton b = new JButton("Add package");
		b.addActionListener(clickListener);
		return b;
	}
	
	private JButton getRemovePackageLinesButton() {
		JButton b  = new JButton("Remove packages");
		b.addActionListener(clickListener);
		return b;
	}
	
	private class ClickListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == addPackageLineButton) {
				dataModel.addRow(new String[]{"",""});
			}
			else if (e.getSource() == removePackageLinesButton) {
				int [] rows = table.getSelectedRows();
				int s = rows.length;
				for (int i = s-1; i > -1; i--) {
					dataModel.removeRow(rows[i]);
				}
			}
		}
	}	
}
