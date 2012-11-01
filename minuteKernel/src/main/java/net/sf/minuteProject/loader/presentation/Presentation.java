package net.sf.minuteProject.loader.presentation;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import net.sf.minuteProject.loader.XMLRulesLoader;
import net.sf.minuteProject.loader.mapping.MappingHolder;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.apache.log4j.Logger;

import net.sf.minuteProject.loader.presentation.PresentationHolder;

/**
 * @author Florian Adler
 *
 */
public class Presentation extends XMLRulesLoader {
	private static Logger logger = Logger.getLogger(Presentation.class);

    private final static String PATH_CONFIG = "net/sf/minuteProject/loader/presentation/Digester-Presentation-rules.xml";

    public Presentation(String config) {
        super(config, PATH_CONFIG);
    }

    public Presentation(String configDir, String config) {
        super(config, configDir, PATH_CONFIG);
    }
	
	public PresentationHolder load() throws Exception{
		if (getConfigDir()==null)
			return load(getConfig(), getDigesterRule());
		return load(getConfigDir(), getConfig(), getDigesterRule());
	}
	
	public PresentationHolder load(String configuration, String rules) throws Exception {
		PresentationHolder Presentationholder = new PresentationHolder();
		loadDigester(Presentationholder, configuration, rules);
        return Presentationholder;		
	}
	
	public PresentationHolder load(String fileDirName, String fileName, String rules) throws Exception {
        return load(fileDirName + "/" + fileName, rules);
	}

    public static void main(String args[]) throws Exception {
        if (args.length < 1) {
            System.exit(1);
        }
        Date startDate = new Date();
        logger.info("start time = "+new Date());
        Presentation loader = new Presentation(args[0]);
        PresentationHolder Presentationholder = loader.load();
        Date endDate = new Date();
        logger.info("time taken : "+(endDate.getTime()-startDate.getTime())/1000+ "s.");
    }
	
}
