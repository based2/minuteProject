package net.sf.minuteProject.console;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

import net.sf.minuteProject.console.face.FillBasicConfiguration;
import net.sf.minuteProject.console.panel.ConsolePanel;
import net.sf.minuteProject.console.panel.ModelAccessPanel;
import net.sf.minuteProject.console.panel.ModelCommonPanel;
import net.sf.minuteProject.console.panel.TargetPanel;
import net.sf.minuteProject.integration.bean.BasicIntegrationConfiguration;

/**
 * 
 * @author florian
 */
public class Console extends JFrame{

//	private ModelAccessPanel modelAccessPanel;
//	private ModelCommonPanel modelCommonPanel;
//	private TargetPanel targetPanel;
//	
	private ConsolePanel consolePanel;

	/** Creates new form TestFrame */
	public Console() {
		initComponents();
	}

	private void initComponents() {
		consolePanel = new ConsolePanel();
		BoxLayout bl = new BoxLayout(consolePanel, BoxLayout.Y_AXIS);
		consolePanel.setLayout(bl);
		getContentPane().add(consolePanel);
		pack();
	}

	/**
	 * @param args
	 *           the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Console().setVisible(true);
			}
		});
	}



}
