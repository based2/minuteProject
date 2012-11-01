package net.sf.minuteProject.loader.mapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import net.sf.minuteProject.loader.XMLRulesLoader;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.apache.log4j.Logger;

import net.sf.minuteProject.loader.mapping.MappingHolder;

/**
 * @author Florian Adler
 *
 */
public class Mapping extends XMLRulesLoader {
	private static Logger logger = Logger.getLogger(Mapping.class);
    private final static String PATH_CONFIG = "net/sf/minuteProject/loader/mapping/Digester-Mapping-rules.xml";

	private MappingHolder holder;

    public Mapping(String config) {
        super(config, PATH_CONFIG);
        holder = new MappingHolder();
    }

    public Mapping(String configDir, String config) {
        super(config, configDir, PATH_CONFIG);
        holder = new MappingHolder();
    }
	
	public MappingHolder load() throws Exception {
		if (getConfigDir()==null)
			return load(getConfig(), getDigesterRule());
		return load(getConfigDir(), getConfig(), getDigesterRule());
	}

	public MappingHolder load(MappingHolder mappingHolder, String configuration, String rules) throws Exception {
		loadDigester(mappingHolder, configuration, rules);
		//TODO add multiple loader for import
//		loadDigester(mappingHolder, getInputStream(configuration), rules);
        return holder;
	}
	
	public MappingHolder load(String configuration, String rules) throws Exception {
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
        return load(fileDirName + "/" + fileName, rules);
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
	
}
