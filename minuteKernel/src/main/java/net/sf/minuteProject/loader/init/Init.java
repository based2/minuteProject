package net.sf.minuteProject.loader.init;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.apache.log4j.Logger;

import net.sf.minuteProject.loader.init.InitHolder;

/**
 * @author Florian Adler
 *
 */
public class Init {
	private static Logger logger = Logger.getLogger(Init.class);
	
	private String config;
	
	public Init (String config) {
	   setConfig(config);
	}
	public String getDigesterRule() {
		return "net/sf/minuteProject/loader/init/Digester-Init-rules.xml";
	}
	
	public static void main(String args[]) throws Exception {
		if (args.length < 1) {
			System.exit(1);
		}
		Date startDate = new Date();
	    logger.info("start time = "+new Date());
	    Init loader = new Init(args[0]);
		InitHolder Initholder = loader.load();
		Date endDate = new Date();
		logger.info("time taken : "+(endDate.getTime()-startDate.getTime())/1000+ "s.");
	}
	
	public InitHolder load() throws Exception{
		return load(getConfig(), getDigesterRule());
	}
	
	public InitHolder load (String configuration, String rules) throws Exception{
		InitHolder initholder = new InitHolder();
		loadConfiguration(initholder, getConfigurationInputStream(configuration), rules);
        return initholder;		
	}
	
	private InputStream getConfigurationInputStream (String configurationFileName) {
		return getClass().getClassLoader().getSystemResourceAsStream(configurationFileName);
	}
	

	private void loadConfiguration (Object object, InputStream input, String rules) throws Exception {
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
	
}
