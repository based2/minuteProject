package net.sf.minuteProject.loader.catalog.technologycatalog;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import net.sf.minuteProject.loader.XMLRulesLoader;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.apache.log4j.Logger;

import net.sf.minuteProject.loader.catalog.technologycatalog.TechnologycatalogHolder;

/**
 * @author Florian Adler
 *
 */
public class TechnologycatalogLoader extends XMLRulesLoader {
	private static Logger logger = Logger.getLogger(TechnologycatalogLoader.class);
    private final static String PATH_CONFIG = "net/sf/minuteProject/loader/catalog/technologycatalog/Digester-Technologycatalog-rules.xml";
	
	public TechnologycatalogLoader(String config) {
        super(config, PATH_CONFIG);
    }

    public TechnologycatalogLoader(String configDir, String config) {
        super(config, configDir, PATH_CONFIG);
    }
	
	public TechnologycatalogHolder load() throws Exception{
		if (getConfigDir()==null)
			return load(getConfig(), getDigesterRule());
		return load(getConfigDir(), getConfig(), getDigesterRule());
	}
	
	public TechnologycatalogHolder load (String configuration, String rules) throws Exception{
		TechnologycatalogHolder Technologycatalogholder = new TechnologycatalogHolder();
		loadDigester(Technologycatalogholder, configuration, rules);
        return Technologycatalogholder;		
	}
	
	public TechnologycatalogHolder load (String fileDirName, String fileName, String rules) throws Exception{
		return load(fileDirName + "/" + fileName, rules);
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
	
}
