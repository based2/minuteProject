package net.sf.minuteProject.loader.mapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.apache.log4j.Logger;

import net.sf.minuteProject.loader.mapping.MappingHolder;

/**
 * @author Florian Adler
 *
 */
public class Mapping {
	private static Logger logger = Logger.getLogger(Mapping.class);
	
	private String config;
	private String configDir;
	private MappingHolder holder;
	
	public Mapping (String config) {
	   setConfig(config);
	}
	
	public Mapping (String configDir, String config) {
		setConfig(config);
		setConfigDir(configDir);
	}
	
	public String getDigesterRule() {
		return "net/sf/minuteProject/loader/mapping/Digester-Mapping-rules.xml";
	}
	
	public static void main(String args[]) throws Exception {
		if (args.length < 1) {
			System.exit(1);
		}
		Date startDate = new Date();
	    logger.info("start time = "+new Date());
	    Mapping loader = new Mapping(args[0]);
		MappingHolder Mappingholder = loader.load();
		Date endDate = new Date();
		logger.info("time taken : "+(endDate.getTime()-startDate.getTime())/1000+ "s.");
	}
	
	public MappingHolder load() throws Exception{
		if (getConfigDir()==null)
			return load(getConfig(), getDigesterRule());
		return load(getConfigDir(), getConfig(), getDigesterRule());
	}

	public MappingHolder load (MappingHolder mappingHolder, String configuration, String rules) throws Exception{

		loadDigester(mappingHolder, getInputStream(configuration), rules);
		//TODO add multiple loader for import
//		loadDigester(mappingHolder, getInputStream(configuration), rules);
        return mappingHolder;		
	}
	
	public MappingHolder load (String configuration, String rules) throws Exception{
		holder = new MappingHolder();
		holder = load(holder, configuration, rules);
//		holder = load(holder, configuration, rules);
		return holder;
//		MappingHolder Mappingholder = new MappingHolder();
//		loadDigester(Mappingholder, getInputStream(configuration), rules);
//		//TODO add multiple loader for import
//		loadDigester(Mappingholder, getInputStream(configuration), rules);
//        return Mappingholder;		
	}
	
	public MappingHolder load (String fileDirName, String fileName, String rules) throws Exception{
		holder = new MappingHolder();
//		holder = load(holder, configuration, rules);
//		holder = load(holder, configuration, rules);
//		return holder;
//		MappingHolder Mappingholder = new MappingHolder();
		loadDigester(holder, getInputStream(fileDirName, fileName), rules);
//		loadDigester(holder, getInputStream(fileDirName, fileName), rules);		
//		loadDigester(holder, getInputStream(fileDirName, fileName), rules);
//		loadDigester(holder, getInputStream(fileDirName, fileName), rules);
        return holder;		
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
