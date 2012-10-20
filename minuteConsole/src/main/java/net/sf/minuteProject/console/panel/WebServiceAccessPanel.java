package net.sf.minuteProject.console.panel;

import static net.sf.minuteProject.console.utils.UIUtils.createCombo;
import static net.sf.minuteProject.console.utils.UIUtils.createLabel;
import static net.sf.minuteProject.console.utils.UIUtils.createTextField;
import static net.sf.minuteProject.console.utils.UIUtils.createPasswordField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import net.sf.minuteProject.configuration.bean.WebServiceModel;
import net.sf.minuteProject.configuration.bean.model.webservice.Wsdl;
import net.sf.minuteProject.console.ConsoleSample;
import net.sf.minuteProject.console.face.FillBasicConfiguration;
import net.sf.minuteProject.integration.bean.BasicIntegrationConfiguration;
import net.sf.minuteProject.loader.catalog.databasecatalog.DatabasecatalogHolder;
import net.sf.minuteProject.loader.catalog.databasecatalog.node.Database;
import net.sf.minuteProject.utils.catalog.DatabaseCatalogUtils;

@SuppressWarnings("serial")
public class WebServiceAccessPanel extends JPanel implements FillBasicConfiguration{

    public String rootdir, dir, file, url;
    
    private JLabel schemaL;
    private JComboBox webServiceImplCb;
    private JTextField urlTf, rootdirTf, dirTf, fileTf;
    private ConsoleSample consoleSample;
    
    public WebServiceAccessPanel(ConsoleSample consoleSample) {
   	 	this.consoleSample = consoleSample;
	}

	public void fill(BasicIntegrationConfiguration bic) {
		WebServiceModel webServiceModel = new WebServiceModel();
		Wsdl wsdl = fillWsdl();
		webServiceModel.setWsdl(wsdl);
		bic.setWebServiceModel(webServiceModel);
	}
	
	private Wsdl fillWsdl() {
		Wsdl wsdl = new Wsdl();
		wsdl.setRootdir(rootdir);
		wsdl.setDir(dir);
		wsdl.setFile(file);
		return wsdl;
	}

	public void fillPanel (JPanel panel) {

		panel.add(createLabel("url"), "skip");
		urlTf = createTextField("");
		panel.add(urlTf,      "span, growx");
		
		panel.add(createLabel("directory"),   "skip");
		dirTf = createTextField("");
		panel.add(dirTf);
		
		panel.add(createLabel("file"),   "center");
		fileTf = createPasswordField("");
		panel.add(fileTf,      "wrap");		
		
		schemaL = createLabel("root directory");
		panel.add(schemaL,  "skip");
		rootdirTf = createTextField(15);
		panel.add(rootdirTf,      "wrap para");
	}



}
