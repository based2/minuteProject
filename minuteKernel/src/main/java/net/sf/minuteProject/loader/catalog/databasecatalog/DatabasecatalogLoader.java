package net.sf.minuteProject.loader.catalog.databasecatalog;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.apache.log4j.Logger;

import net.sf.minuteProject.loader.catalog.databasecatalog.DatabasecatalogHolder;

/**
 * @author Florian Adler
 *
 */
public class DatabasecatalogLoader {
	private static Logger logger = Logger.getLogger(DatabasecatalogLoader.class);
	
	private String config;
	private String configDir;
	
	public DatabasecatalogLoader (String config) {
	   setConfig(config);
	}
	
	public DatabasecatalogLoader (String configDir, String config) {
		setConfig(config);
		setConfigDir(configDir);
	}
	
	public String getDigesterRule() {
		return "net/sf/minuteProject/loader/catalog/databasecatalog/Digester-Databasecatalog-rules.xml";
	}

	public DatabasecatalogHolder load() throws Exception{
		if (getConfigDir()==null)
			return load(getConfig(), getDigesterRule());
		return load(getConfigDir(), getConfig(), getDigesterRule());
	}
	
	public DatabasecatalogHolder load (String configuration, String rules) throws Exception{
		DatabasecatalogHolder Databasecatalogholder = new DatabasecatalogHolder();
		loadDigester(Databasecatalogholder, getInputStream(configuration), rules);
        return Databasecatalogholder;		
	}
	
	public DatabasecatalogHolder load (String fileDirName, String fileName, String rules) throws Exception{
		DatabasecatalogHolder Databasecatalogholder = new DatabasecatalogHolder();
		loadDigester(Databasecatalogholder, getInputStream(fileDirName, fileName), rules);
        return Databasecatalogholder;		
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


    public static void main(String args[]) throws Exception {
        if (args.length < 1) {
            System.exit(1);
        }
        Date startDate = new Date();
        logger.info("start time = "+new Date());
        DatabasecatalogLoader loader = new DatabasecatalogLoader(args[0]);
        DatabasecatalogHolder Databasecatalogholder = loader.load();
        Date endDate = new Date();
        logger.info("time taken : "+(endDate.getTime()-startDate.getTime())/1000+ "s.");
    }
	
}
