package net.sf.minuteProject.console.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.miginfocom.layout.PlatformDefaults;

public class UIUtils {

	static final Color LABEL_COLOR = new Color(0, 70, 213);
	public static void addSeparator(JPanel panel, String text)
	{
		JLabel l = createLabel(text);
		l.setForeground(LABEL_COLOR);

		panel.add(l, "gapbottom 1, span, split 2, aligny center");
		panel.add(configureActiveComponent(new JSeparator()), "gapleft rel, growx");
	}	
	public static JLabel createLabel(String text)
	{
		return createLabel(text, SwingConstants.LEADING);
	}	
	public static JLabel createLabel(String text, int align)
	{
		final JLabel b = new JLabel(text, align);
		configureActiveComponent(b);
		return b;
	}	
	public static JComponent configureActiveComponent(JComponent c)
	{
//		if (benchRuns == 0) {
//			c.addMouseMotionListener(toolTipListener);
//			c.addMouseListener(constraintListener);
//		}
		return c;
	}	
	public static JPanel createTabPanel(LayoutManager lm)
	{
		JPanel panel = new JPanel(lm);
		configureActiveComponent(panel);
		panel.setOpaque(false);
		return panel;
	}

	public static JTextField createTextField(int cols)
	{
		return createTextField("", cols);
	}

	public static JTextField createTextField(String text, FocusListener focusListener) {
		JTextField jTextField = createTextField(text);
		jTextField.addFocusListener(focusListener);
		return jTextField;
	}
	
	public static JTextField createTextField(String text)
	{
		return createTextField(text, 0);
	}
	
	public static JPasswordField createPasswordField(String text)
	{
		return createPasswordField(text, 0);
	}

	public static JPasswordField createPasswordField(String text, int i) {
		final JPasswordField p = new JPasswordField(text, i);
		return p;
	}
	
	public static JTextField createTextField(String text, int cols)
	{
		final JTextField b = new JTextField(text, cols);

		configureActiveComponent(b);

		return b;
	}
	
	public static JScrollPane createTextAreaScroll(String text, int rows, int cols, boolean hasVerScroll, boolean isEditable)
	{
		JTextArea ta = new JTextArea(text, rows, cols);
		ta.setFont(UIManager.getFont("TextField.font"));
		ta.setWrapStyleWord(true);
		ta.setLineWrap(true);
		ta.setEditable(isEditable);
		JScrollPane scroll = new JScrollPane(
			    ta,
			    hasVerScroll ? ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED : ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
			    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		return scroll;
	}
	
	public static void updateTextAreaScroll (JScrollPane pane, String text) {
		JViewport jview = (JViewport)pane.getComponent(0);
		Component comp = jview.getView();
		JTextArea jTextArea = null;
		if (comp instanceof JTextArea) {
			jTextArea = (JTextArea) comp;
		} else {
			System.out.println("comp = "+comp);
		}
		if (jTextArea!=null)
			jTextArea.setText(text);
	}
	
	public static void updateTableScroll (JScrollPane pane, String [][]data, String[] title) {
		JViewport jview = (JViewport)pane.getComponent(0);
		Component comp = jview.getView();
		JTable jtable = null;
		if (comp instanceof JTable) {
			jtable = (JTable) comp;
		} else {
			System.out.println("comp = "+comp);
		}
		if (jtable!=null) {
			TableModel dataModel = new DefaultTableModel(data, title);
			jtable.setModel(dataModel);
			Dimension size = jtable.getPreferredScrollableViewportSize();
			jtable.setPreferredScrollableViewportSize
			    (new Dimension(Math.min(jtable.getPreferredSize().width, size.width), 50));
		}
	}
	
	public static JComboBox createCombo(String[] items, ItemListener itemListener) {
		JComboBox jComboBox = createCombo(items);
		jComboBox.addItemListener(itemListener);
		return jComboBox;
	}
	
	public static JComboBox createCombo(String[] items, ActionListener actionListener) {
		JComboBox jComboBox = createCombo(items);
		jComboBox.addActionListener(actionListener);
		return jComboBox;
	}
	public static JComboBox createCombo(String[] items)
	{
		JComboBox combo = new JComboBox(items);

		if (PlatformDefaults.getCurrentPlatform() == PlatformDefaults.MAC_OSX)
			combo.setOpaque(false);

		return combo;
	}	
	
	public static JButton createButton(String text, ActionListener actionListener)
	{
		JButton b = new JButton(text);
		if (actionListener!=null)
			b.addActionListener(actionListener);
		return b;
	}	
}
