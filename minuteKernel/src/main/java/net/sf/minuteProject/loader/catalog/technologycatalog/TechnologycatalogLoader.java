package net.sf.minuteProject.loader.catalog.technologycatalog;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.apache.log4j.Logger;

import net.sf.minuteProject.loader.catalog.technologycatalog.TechnologycatalogHolder;

/**
 * @author Florian Adler
 *
 */
public class TechnologycatalogLoader {
	private static Logger logger = Logger.getLogger(TechnologycatalogLoader.class);
	
	private String config;
	private String configDir;
	
	public TechnologycatalogLoader (String config) {
	   setConfig(config);
	}
	
	public TechnologycatalogLoader (String configDir, String config) {
		setConfig(config);
		setConfigDir(configDir);
	}
	
	public String getDigesterRule() {
		return "net/sf/minuteProject/loader/catalog/technologycatalog/Digester-Technologycatalog-rules.xml";
	}
	
	public static void main(String args[]) throws Exception {
		if (args.length < 1) {
			System.exit(1);
		}
		Date startDate = new Date();
	    logger.info("start time = "+new Date());
	    TechnologycatalogLoader loader = new TechnologycatalogLoader(args[0]);
		TechnologycatalogHolder Technologycatalogholder = loader.load();
		Date endDate = new Date();
		logger.info("time taken : "+(endDate.getTime()-startDate.getTime())/1000+ "s.");
	}
	
	public TechnologycatalogHolder load() throws Exception{
		if (getConfigDir()==null)
			return load(getConfig(), getDigesterRule());
		return load(getConfigDir(), getConfig(), getDigesterRule());
	}
	
	public TechnologycatalogHolder load (String configuration, String rules) throws Exception{
		TechnologycatalogHolder Technologycatalogholder = new TechnologycatalogHolder();
		loadDigester(Technologycatalogholder, getInputStream(configuration), rules);
        return Technologycatalogholder;		
	}
	
	public TechnologycatalogHolder load (String fileDirName, String fileName, String rules) throws Exception{
		TechnologycatalogHolder Technologycatalogholder = new TechnologycatalogHolder();
		loadDigester(Technologycatalogholder, getInputStream(fileDirName, fileName), rules);
        return Technologycatalogholder;		
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
