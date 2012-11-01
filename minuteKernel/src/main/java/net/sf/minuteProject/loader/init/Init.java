package net.sf.minuteProject.loader.init;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import net.sf.minuteProject.loader.XMLRulesLoader;
import net.sf.minuteProject.loader.mapping.MappingHolder;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.apache.log4j.Logger;

import net.sf.minuteProject.loader.init.InitHolder;

/**
 * @author Florian Adler
 *
 */
public class Init extends XMLRulesLoader {
	private static Logger logger = Logger.getLogger(Init.class);
    private final static String PATH_CONFIG = "net/sf/minuteProject/loader/init/Digester-Init-rules.xml";

    public Init(String config) {
        super(config, PATH_CONFIG);
    }
	
	public InitHolder load() throws Exception {
		return load(getConfig(), getDigesterRule());
	}
	
	public InitHolder load(String configuration, String rules) throws Exception {
		InitHolder initholder = new InitHolder();
        loadDigester(initholder, configuration, rules);
        return initholder;		
	}

    public static void main(String args[]) throws Exception {
        if (args.length < 1) {
            System.exit(1);
        }
        Date startDate = new Date();
        logger.info("start time = "+startDate);
        Init loader = new Init(args[0]);
        InitHolder Initholder = loader.load();
        Date endDate = new Date();
        logger.info("time taken : "+(endDate.getTime()-startDate.getTime())/1000+ "s.");
    }
}
