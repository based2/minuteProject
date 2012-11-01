package net.sf.minuteProject.loader;

import net.sf.minuteProject.loader.catalog.databasecatalog.DatabasecatalogHolder;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

/**
 * User: Basile Chandesris
 * Date: 01/11/12 19:51
 */
public class XMLRulesLoader {

    private static Logger logger = Logger.getLogger(XMLRulesLoader.class);

    private String config;
    private String configDir;
    private String digesterRule;

    public XMLRulesLoader() {

    }

    public XMLRulesLoader(String config, String rule) {
        setConfig(config);
        digesterRule = rule;
    }

    public XMLRulesLoader(String configDir, String config, String rule) {
        this(config, rule);
        setConfigDir(configDir);
    }

    public String getDigesterRule() {
        return digesterRule;
    }

    private XMLRulesLoader load() throws Exception {
        if (getConfigDir()==null)
            return load(getConfig(), getDigesterRule());
        return load(getConfigDir(), getConfig(), getDigesterRule());
    }

    private XMLRulesLoader load(String configuration, String rules) throws Exception{
        XMLRulesLoader Databasecatalogholder = new XMLRulesLoader();
        loadDigester(Databasecatalogholder, getInputStream(configuration), rules);
        return Databasecatalogholder;
    }

    private XMLRulesLoader load (String fileDirName, String fileName, String rules) throws Exception{
        XMLRulesLoader Databasecatalogholder = new XMLRulesLoader();
        loadDigester(Databasecatalogholder, getInputStream(fileDirName, fileName), rules);
        return Databasecatalogholder;
    }

    public InputStream getInputStream(String fileName) {
        return getClass().getClassLoader().getSystemResourceAsStream(fileName);
    }

    public InputStream getInputStream (String fileDirName, String fileName)  throws Exception{
        return new FileInputStream(new File(fileDirName+"/"+fileName));
    }

    public void loadDigester(Object object, InputStream input, String rules) throws Exception {
        try  {
            Digester digester = DigesterLoader.createDigester(new InputSource(rules));
            digester.push(object);
            digester.parse(input);
        } catch (Exception e)  {
            logger.error("LoadXML failed for "+ rules, e);
            throw e;
        }
    }

    public void loadDigester(Object object, String input, String rules) throws Exception {
        try  {
            Digester digester = DigesterLoader.createDigester(new InputSource(rules));
            digester.push(object);
            digester.parse(new InputSource(input));
        } catch (Exception e)  {
            logger.error("LoadXML failed for "+ rules, e);
            throw e;
        }
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
        logger.info("start time = "+startDate);
        XMLRulesLoader loader = new XMLRulesLoader(args[0], "net/sf/minuteProject/loader/catalog/databasecatalog/Digester-Databasecatalog-rules.xml");
        Date endDate = new Date();
        logger.info("time taken : "+(endDate.getTime()-startDate.getTime())/1000+ "s.");
    }
}
