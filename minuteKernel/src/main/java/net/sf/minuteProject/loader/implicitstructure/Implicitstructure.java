package net.sf.minuteProject.loader.implicitstructure;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.apache.log4j.Logger;

import net.sf.minuteProject.loader.implicitstructure.ImplicitstructureHolder;

/**
 * @author Florian Adler
 *
 */
public class Implicitstructure {
	private static Logger logger = Logger.getLogger(Implicitstructure.class);
	
	private String config;
	private String configDir;
	
	public Implicitstructure (String config) {
	   setConfig(config);
	}
	
	public Implicitstructure (String configDir, String config) {
		setConfig(config);
		setConfigDir(configDir);
	}
	
	public String getDigesterRule() {
		return "net/sf/minuteProject/loader/implicitstructure/Digester-Implicitstructure-rules.xml";
	}
	
	public static void main(String args[]) throws Exception {
		if (args.length < 1) {
			System.exit(1);
		}
		Date startDate = new Date();
	    logger.info("start time = "+new Date());
	    Implicitstructure loader = new Implicitstructure(args[0]);
		ImplicitstructureHolder Implicitstructureholder = loader.load();
		Date endDate = new Date();
		logger.info("time taken : "+(endDate.getTime()-startDate.getTime())/1000+ "s.");
	}
	
	public ImplicitstructureHolder load() throws Exception{
		if (getConfigDir()==null)
			return load(getConfig(), getDigesterRule());
		return load(getConfigDir(), getConfig(), getDigesterRule());
	}
	
	public ImplicitstructureHolder load (String configuration, String rules) throws Exception{
		ImplicitstructureHolder Implicitstructureholder = new ImplicitstructureHolder();
		loadDigester(Implicitstructureholder, getInputStream(configuration), rules);
        return Implicitstructureholder;		
	}
	
	public ImplicitstructureHolder load (String fileDirName, String fileName, String rules) throws Exception{
		ImplicitstructureHolder Implicitstructureholder = new ImplicitstructureHolder();
		loadDigester(Implicitstructureholder, getInputStream(fileDirName, fileName), rules);
        return Implicitstructureholder;		
	}
	
	private InputStream getInputStream (String fileName) {
		return getClass().getClassLoader().getSystemResourceAsStream(fileName);
	}
	
	private InputStream getInputStream (String fileDirName, String fileName)  throws Exception{
		return new FileInputStream (new File (fileDirName+"/"+fileName));
	}

	private void loadDigester (Object object, InputStream input, String rules) throws Exception {
        URL rulesURL = getClass().getClassLoader().getResource(rules);
        Digester digester = DigesterLoader.createDigester(rulesURL);
        digester.push(object);
        digester.parse(input);
	}
	
	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}
	
	public String getConfigDir() {
		return configDir;
	}
	
	public void setConfigDir(String configDir) {
		this.configDir = configDir;
	}
	
}
