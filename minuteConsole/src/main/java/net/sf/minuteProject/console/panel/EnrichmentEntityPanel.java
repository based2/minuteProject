package net.sf.minuteProject.console.panel;

import static net.sf.minuteProject.console.utils.UIUtils.createLabel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import net.sf.minuteProject.configuration.bean.Condition;
import net.sf.minuteProject.console.face.FillBasicConfiguration;
import net.sf.minuteProject.integration.bean.BasicIntegrationConfiguration;

public class EnrichmentEntityPanel extends JPanel implements FillBasicConfiguration{

	private JLabel entityJL, fieldJL;
	private JScrollPane entityJSP, fieldJSP;
	private JButton addPackageLineButton, removePackageLinesButton;
	private ClickListener clickListener;
	private DefaultTableModel dataEntityModel, dataFieldModel;
	private JTable tableEntity, tableField;
	
	public EnrichmentEntityPanel() {
		entityJL = createLabel("entities");
		clickListener = new ClickListener();
		addPackageLineButton = getAddPackageLineButton();
		removePackageLinesButton = getRemovePackageLinesButton();
		dataEntityModel = new DefaultTableModel(new String[][]{{"",""},{"",""}}, new String[] {"entity ","enrichment"});
		tableEntity = new JTable(dataEntityModel);
		TableColumn entityEnrichment = tableEntity.getColumnModel().getColumn(1);
		entityEnrichment.setCellEditor(new DefaultCellEditor(getEntityEnrichment()));
	
	}

	private JComboBox getEntityEnrichment() {
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("");
		comboBox.addItem("is master entity"); // immutable add semantic ref (one field OX), add enum
		comboBox.addItem("is reference entity"); // add semantic ref
		comboBox.addItem("is linked entity");
		comboBox.addItem("");
		comboBox.addItem("add comment...");
		comboBox.addItem("add alias...");
		return comboBox;
	}

	public void fill(BasicIntegrationConfiguration bic) {
		bic.setPackageConditions(getConditions());
	}

	public List<Condition> getConditions() {
		int nbRow = dataEntityModel.getRowCount();
		List<Condition> conditions = new ArrayList<Condition>();
		for (int i = 0; i < nbRow; i++) {

		}
		return conditions;
	}
	
	public void fillPanel (JPanel panel) {
		panel.add(entityJL,"skip");
		entityJSP = getJSP (tableEntity);
		panel.add(entityJSP, "span, growx, wrap");
		
		panel.add(createLabel(""),   "skip");
		panel.add(addPackageLineButton,"skip");
		panel.add(removePackageLinesButton,"wrap para");	

	}

	private JScrollPane getJSP(JTable table) {
		Dimension size = tableEntity.getPreferredScrollableViewportSize();
		table.setPreferredScrollableViewportSize (new Dimension(Math.min(getPreferredSize().width, size.width), 50));
		return new JScrollPane(table);
	}
	
	private JButton getAddPackageLineButton() {
		JButton b = new JButton("Add line");
		b.addActionListener(clickListener);
		return b;
	}
	
	private JButton getRemovePackageLinesButton() {
		JButton b  = new JButton("Remove lines");
		b.addActionListener(clickListener);
		return b;
	}
	
	private class ClickListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == addPackageLineButton) {
				dataEntityModel.addRow(new String[]{"",""});
			}
			else if (e.getSource() == removePackageLinesButton) {
				int [] rows = tableEntity.getSelectedRows();
				int s = rows.length;
				for (int i = s-1; i > -1; i--) {
					dataEntityModel.removeRow(rows[i]);
				}
			}
		}
	}
}
