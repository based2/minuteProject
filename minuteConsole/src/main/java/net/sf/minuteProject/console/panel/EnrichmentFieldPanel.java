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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import net.sf.minuteProject.configuration.bean.Condition;
import net.sf.minuteProject.console.face.FillBasicConfiguration;
import net.sf.minuteProject.integration.bean.BasicIntegrationConfiguration;

public class EnrichmentFieldPanel extends JPanel implements FillBasicConfiguration{

	private JLabel fieldJL;
	private JScrollPane fieldJSP;
	private JButton addPackageLineButton, removePackageLinesButton;
	private ClickListener clickListener;
	private DefaultTableModel dataEntityModel, dataFieldModel;
	private JTable tableField;
	
	public EnrichmentFieldPanel() {
//		entityJL = createLabel("entities");
//		clickListener = new ClickListener();
//		addPackageLineButton = getAddPackageLineButton();
//		removePackageLinesButton = getRemovePackageLinesButton();
//		dataEntityModel = new DefaultTableModel(new String[][]{{"",""},{"",""}}, new String[] {"entity ","enrichment"});
//		tableEntity = new JTable(dataEntityModel);
//		TableColumn entityEnrichment = tableEntity.getColumnModel().getColumn(1);
//		entityEnrichment.setCellEditor(new DefaultCellEditor(getEntityEnrichment()));

		fieldJL = createLabel("fields");
		clickListener = new ClickListener();
		addPackageLineButton = getAddPackageLineButton();
		removePackageLinesButton = getRemovePackageLinesButton();
		dataFieldModel = new DefaultTableModel(new String[][]{{"","",""},{"","",""}}, new String[] {"entity","field(s)","enrichment"});
		tableField = new JTable(dataFieldModel);
		TableColumn fieldEnrichment = tableField.getColumnModel().getColumn(2);
		fieldEnrichment.setCellEditor(new DefaultCellEditor(getFieldEnrichment()));
		
		
	}

	private JComboBox getFieldEnrichment() {
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("");
		comboBox.addItem("is semantic reference"); // immutable add semantic ref (one field OX), add enum
		comboBox.addItem("is/are abstract reference"); // add semantic ref
		comboBox.addItem("is stereotype"); // choose stereotype
		comboBox.addItem("is enumeration");
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

		panel.add(fieldJL,"skip");
		fieldJSP = getJSP (tableField);
		panel.add(fieldJSP, "span, growx");
//		
		panel.add(createLabel(""),   "skip");
		panel.add(addPackageLineButton,"skip");
		panel.add(removePackageLinesButton,"wrap para");	

	}

	private JScrollPane getJSP(JTable table) {
		Dimension size = tableField.getPreferredScrollableViewportSize();
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
				dataFieldModel.addRow(new String[]{"",""});
			}
			else if (e.getSource() == removePackageLinesButton) {
				int [] rows = tableField.getSelectedRows();
				int s = rows.length;
				for (int i = s-1; i > -1; i--) {
					dataFieldModel.removeRow(rows[i]);
				}
			}
		}
	}
}
