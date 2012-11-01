package net.sf.minuteProject.loader.catalog.databasecatalog;


import java.util.Date;

import org.apache.log4j.Logger;
import net.sf.minuteProject.loader.XMLRulesLoader;

import net.sf.minuteProject.loader.catalog.databasecatalog.DatabasecatalogHolder;

/**
 * @author Florian Adler
 *
 */
public class DatabasecatalogLoader extends XMLRulesLoader {

	private static Logger logger = Logger.getLogger(DatabasecatalogLoader.class);

    private final static String PATH_CONFIG = "net/sf/minuteProject/loader/catalog/databasecatalog/Digester-Databasecatalog-rules.xml";
	
	public DatabasecatalogLoader(String config){
        super(config, PATH_CONFIG);
	}
	
	public DatabasecatalogLoader(String configDir, String config) {
		super(config, configDir, PATH_CONFIG);
	}

	public DatabasecatalogHolder load() throws Exception {
		if (getConfigDir()==null)
			return load(getConfig(), getDigesterRule());
		return load(getConfigDir(), getConfig(), getDigesterRule());
	}
	
	public DatabasecatalogHolder load(String configuration, String rules) throws Exception {
		DatabasecatalogHolder Databasecatalogholder = new DatabasecatalogHolder();
		loadDigester(Databasecatalogholder, configuration, rules);
        return Databasecatalogholder;		
	}
	
	public DatabasecatalogHolder load(String fileDirName, String fileName, String rules) throws Exception {
        return load(fileDirName + "/" +fileName, rules);
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
