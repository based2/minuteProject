package net.sf.minuteProject.loader.implicitstructure;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import net.sf.minuteProject.loader.XMLRulesLoader;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.apache.log4j.Logger;

import net.sf.minuteProject.loader.implicitstructure.ImplicitstructureHolder;

/**
 * @author Florian Adler
 *
 */
public class Implicitstructure extends XMLRulesLoader {
	private static Logger logger = Logger.getLogger(Implicitstructure.class);

    private final static String PATH_CONFIG = "net/sf/minuteProject/loader/implicitstructure/Digester-Implicitstructure-rules.xml";

    public Implicitstructure(String config) {
        super(config, PATH_CONFIG);
    }

    public Implicitstructure(String configDir, String config) {
        super(config, configDir, PATH_CONFIG);
    }
	
	public ImplicitstructureHolder load() throws Exception{
		if (getConfigDir()==null)
			return load(getConfig(), getDigesterRule());
		return load(getConfigDir(), getConfig(), getDigesterRule());
	}
	
	public ImplicitstructureHolder load (String configuration, String rules) throws Exception{
		ImplicitstructureHolder Implicitstructureholder = new ImplicitstructureHolder();
		loadDigester(Implicitstructureholder, configuration, rules);
        return Implicitstructureholder;		
	}
	
	public ImplicitstructureHolder load (String fileDirName, String fileName, String rules) throws Exception{
        return load(fileDirName + "/" + fileName, rules);
	}

    public static void main(String args[]) throws Exception {
        if (args.length < 1) {
            System.exit(1);
        }
        Date startDate = new Date();
        logger.info("start time = "+startDate);
        Implicitstructure loader = new Implicitstructure(args[0]);
        ImplicitstructureHolder Implicitstructureholder = loader.load();
        Date endDate = new Date();
        logger.info("time taken : "+(endDate.getTime()-startDate.getTime())/1000+ "s.");
    }
}
