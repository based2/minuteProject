package net.sf.minuteProject.application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationRoot;
import net.sf.minuteProject.configuration.bean.Configuration;
import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.Target;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.TemplateTarget;
import net.sf.minuteProject.configuration.bean.model.data.DataModelFactory;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.system.Plugin;
import net.sf.minuteProject.exception.MinuteProjectException;
import net.sf.minuteProject.integration.bean.BasicIntegrationConfiguration;
import net.sf.minuteProject.utils.BslaLibraryUtils;
import net.sf.minuteProject.utils.CommonUtils;
import net.sf.minuteProject.utils.ConvertUtils;
import net.sf.minuteProject.utils.DatabaseUtils;
import net.sf.minuteProject.utils.FormatUtils;
import net.sf.minuteProject.utils.ModelUtils;
import net.sf.minuteProject.utils.TemplateUtils;
import net.sf.minuteProject.utils.ViewUtils;
import net.sf.minuteProject.utils.io.FileUtils;
import net.sf.minuteProject.utils.property.PropertyUtils;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 * @author Florian Adler
 *
 */
public abstract class AbstractGenerator implements Generator {
	
	protected static final String SCOPE_DATAMODEL_FUNCTION = "function";
	protected static final String SCOPE_DATAMODEL_ENTITY = "entity";
	protected static final String SCOPE_DATAMODEL_FIELD = "field";
	protected static final String SCOPE_DATAMODEL_PACKAGE = "package";
	protected static final String SCOPE_DATAMODEL_APPLICATION = "application";
	protected static final String SCOPE_DATAMODEL_MODEL = "model";
	protected static final String SCOPE_DATAMODEL_FUNCTION_INPUT = "function-input-entity";
	protected static final String SCOPE_DATAMODEL_FUNCTION_OUTPUT = "function-output-entity";
	protected static final String SCOPE_TARGET_TEMPLATE = "target";
	protected static final String SCOPE_TRANSFER_ENTITY_TEMPLATE = "transfer-entity";
	protected static final String SCOPE_ACTION_TEMPLATE="action";
	protected static final String QUERY_ACTION_TEMPLATE="query";
	protected static final String SCOPE_WSDL="wsdl";
	protected static final String SCOPE_WSDL_ENTITY="wsdl-entity";
	protected static final String SCOPE_WSDL_SERVICE="wsdl-service";
	protected static final String SDD_INPUT_BEAN_TEMPLATE="sdd-input-bean";
	protected static final String SDD_COMPOSITE_TEMPLATE ="composite";
	protected static final String SDD_INPUT_COMPOSITE_TEMPLATE ="sdd-input-composite-bean";
	protected static final String SDD_OUTPUT_COMPOSITE_TEMPLATE ="sdd-output-composite-bean";
	protected static final String SDD_OUTPUT_BEAN_TEMPLATE="sdd-output-bean";
	
	private static Logger logger = Logger.getLogger(AbstractGenerator.class);
	private String configurationFile;
	private String templatePath;
	private String templateLibPath;
	private Boolean isTemplateLibPathToReset = true;
	private Boolean isTemplatePathToReset = true;
	private BasicIntegrationConfiguration bic; 
	
	/**
	 * The default constructor get the value of the configuration to which the generator is associated
	 * @param configurationFile
	 */
	public AbstractGenerator (String configurationFile) {
		this.configurationFile = configurationFile;
		resetTemplatePath();
	}
	public AbstractGenerator(BasicIntegrationConfiguration bic) {
		this.bic = bic;
		resetTemplatePath();
	}
	/**
	 * gets the configuration file that is to be loaded
	 * @return String
	 */
	public String getConfigurationFile() {
		return this.configurationFile;
	}
		
	/**
	 * gets the configuration rule file that is to be loaded
	 * @return
	 */
	public abstract String getConfigurationRulesFile();
	
	/**
	 * gets the configuration rule file that is to be loaded
	 * @return
	 */
	public abstract String getPropertyConfigurationRulesFile();
	
	/**
	 * gets the configuration root element 
	 * @return AbstractConfiguration
	 */
	public abstract AbstractConfiguration getConfigurationRoot();
	
//	protected abstract void generate(Configuration configuration) throws Exception;
	/* (non-Javadoc)
	 * @see net.sf.minuteProject.application.Generator#load(java.lang.String, java.lang.String)
	 */
	public final AbstractConfiguration load (String configuration, String rules) throws Exception{
		AbstractConfiguration abstractConfiguration = getConfigurationRoot();
		abstractConfiguration.setConfigurationFileInClassPath(configuration);
		loadConfiguration(abstractConfiguration, getConfigurationInputStream(configuration), rules);
        return abstractConfiguration;		
	}
	
	private InputStream getConfigurationInputStream (String configurationFileName) {
		return getClass().getClassLoader().getSystemResourceAsStream(configurationFileName);
	}
	
	public void loadTarget (AbstractConfigurationRoot abstractConfigurationRoot, Target target) throws MinuteProjectException {
		try {
			loadConfiguration(abstractConfigurationRoot, getTargetConfigurationInputStream(abstractConfigurationRoot, target), GENERATOR_TARGET_RULES);
		} catch (Exception e) {
			throwException(e, "CANNOT LOAD TARGET FILE "+resolveFileAbsolutePath(abstractConfigurationRoot, target)+" - CHECK IT IS IN THE CLASSPATH AND WELL FORMATTED");
		}
		complementWithTargetInfo(abstractConfigurationRoot, target);
	}


	public void copyAndComplementWithTargetInfo (AbstractConfigurationRoot abstractConfigurationRoot, Target target) throws Exception {
//		target = new Target();//(abstractConfigurationRoot, target);
		Target target2 = abstractConfigurationRoot.getTarget();
		target.setTemplateTargets(target2.getTemplateTargets());
		target.setAbstractConfigurationRoot(target2.getAbstractConfigurationRoot());
		target.setArchitectureTarget(target2.getArchitectureTarget());
		target.setPlugins(target2.getPlugins());
//		for (Target target2 : abstractConfigurationRoot.getTargets().getTargets()) {
//			if (target2==target) {
//				target2.setDir(target.getDir());
//				target2.setCanonicalDir(target.getCanonicalDir());
//				target2.setOutputdirRoot(target.getOutputdirRoot());
//				target2.setTemplatedirRoot(target.getTemplatedirRoot());				
//			}
//		}
	}
	
	public void complementWithTargetInfo (AbstractConfigurationRoot abstractConfigurationRoot, Target target) {
		Target target2 = abstractConfigurationRoot.getTarget();
		target2.setDir(target.getDir());
		target2.setCanonicalDir(target.getCanonicalDir());
		target2.setOutputdirRoot(target.getOutputdirRoot());
		target2.setTemplatedirRoot(target.getTemplatedirRoot());
		target2.getProperties().addAll(target.getProperties());
		target.setAbstractConfigurationRoot(abstractConfigurationRoot);
	}
	
	protected InputStream getTargetConfigurationInputStream (AbstractConfigurationRoot abstractConfigurationRoot, Target target) throws FileNotFoundException {
		String filePath = resolveFileAbsolutePath(abstractConfigurationRoot, target);
		String dirPath = FileUtils.stripFileName(filePath);
		target.setCanonicalDir(dirPath);
		return new FileInputStream (new File (filePath));
	}

	private String resolveFileAbsolutePath(AbstractConfigurationRoot abstractConfigurationRoot, Target target) {
		String dir = target.getDir();
		if (dir!=null) { // absolute path provided
			return dir+"/"+target.getFileName();
		}
		else {//relative path
			String result = FileUtils.getFileFullPath(abstractConfigurationRoot.getConfigurationFileInClassPath(), dir, target.getFileName());
			return result;
		}
	}

	protected void loadConfiguration (Object object, InputStream input, String rules) throws Exception {
        URL rulesURL = getClass().getClassLoader().getResource(rules);
        Digester digester = DigesterLoader.createDigester(rulesURL);
        digester.push(object);
        digester.parse(input);
	}
	
	/**
	 * load the configuration root element
	 * @return AbstractConfiguration
	 * @throws Exception 
	 * @throws Exception
	 */
	public final AbstractConfiguration load() throws MinuteProjectException {
		if (getConfigurationFile()!=null)
			return loadFromConfigurationFile();
		return loadFromObject();
	}
	
	public final AbstractConfiguration loadFromConfigurationFile() throws MinuteProjectException {
		AbstractConfiguration abstractConfiguration = null;
		try {
			abstractConfiguration = load(getConfigurationFile(), getConfigurationRulesFile());
		} catch (Exception e) {
			throwException(e, "CANNOT LOAD CONFIGURATION FILE "+getConfigurationFile()+" - CHECK IT IS IN THE CLASSPATH");
		}
		return abstractConfiguration;		
	}
	
	public final AbstractConfiguration loadFromObject() throws MinuteProjectException {
		AbstractConfiguration abstractConfiguration = null;
		if (bic!=null)
			abstractConfiguration = bic.getConfiguration();
		else
			throwException("NO CONFIGURATION PROVIDED");
		return abstractConfiguration;	
	}
	
	/* (non-Javadoc)
	 * @see net.sf.minuteProject.application.Generator#generate(net.sf.minuteProject.configuration.bean.AbstractConfiguration, net.sf.minuteProject.configuration.bean.Target)
	 */
	public void generate (Target target) throws MinuteProjectException {
		if (target.isGenerable()) {
	    	for (TemplateTarget templateTarget : target.getTemplateTargets()) {
	    		if (templateTarget.isGenerable()) {
		    		logger.info(">template target set: "+templateTarget.getName()+" in "+templateTarget.getOutputdir());
		    		if (templateTarget!=null && templateTarget.getTemplates()!=null) {
			    		for (Template template : templateTarget.getTemplates()) {
			        		logger.info(">>template: "+template.getName()+" in "+template.getOutputdir());
			        		this.generate(template);    		
			        	} 
		    		}
	    		}
	    	} 
		}
	}
	
	public void getSolutionPortfolio (String solutionPortfolioFileName) {
		
	}
	
    protected VelocityContext getVelocityContext(Template template) {
//		Properties p = new Properties();
		
//		Velocity.clearProperty(Velocity.FILE_RESOURCE_LOADER_PATH);
//		Velocity.clearProperty(Velocity.VM_LIBRARY);
//		p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH,getTemplatePath(template));
//		p.setProperty(Velocity.VM_LIBRARY,getTemplateRelativeLibPath(template));
		VelocityContext context = new VelocityContext();
		try {
			Velocity.setExtendedProperties(getExtendedProperties(template));
//			Velocity.addProperty(Velocity.FILE_RESOURCE_LOADER_PATH,getTemplatePath(template));
//			Velocity.addProperty(Velocity.VM_LIBRARY,getTemplateRelativeLibPath(template));
//			Velocity.init(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return context;
    }  
    
	private ExtendedProperties getExtendedProperties(Template template) {
		ExtendedProperties extendedProperties = new ExtendedProperties();
		extendedProperties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH,getTemplatePath(template));
		extendedProperties.setProperty(Velocity.VM_LIBRARY,getTemplateRelativeLibPath(template));
		return extendedProperties;
	}
	
	protected void putPluginContextObject (VelocityContext context, Template template) {
		List <Plugin> plugins = template.getTemplateTarget().getTarget().getPlugins();
		for (Plugin plugin : plugins) {
			ClassLoader cl = ClassLoader.getSystemClassLoader();
			try {
				Class clazz = cl.loadClass(plugin.getClassName());
				Object velocityObject = clazz.newInstance();
				context.put(plugin.getName(), velocityObject);
			} catch (ClassNotFoundException e) {
				logger.info("cannot find plugin "+plugin.getName()+" via class "+plugin.getClassName());
				e.printStackTrace();
			} catch (InstantiationException e) {
				logger.info("cannot instantiate plugin "+plugin.getName()+" via class "+plugin.getClassName());
			} catch (IllegalAccessException e) {
				logger.info("cannot access plugin "+plugin.getName()+" via class "+plugin.getClassName());
			}
		}
	}
	
    protected String getTemplatePath (Template template) {
    	if ((templatePath==null || templatePath.equals("")) && isTemplatePathToReset) {
    		isTemplatePathToReset = false;
    		Configuration c = (Configuration)template.getTemplateTarget().getTarget().getAbstractConfigurationRoot();
    		Hashtable<String, String> ht = new Hashtable<String, String>();
        	TemplateTarget templateTarget = template.getTemplateTarget();
        	Target target = templateTarget.getTarget();
    		
    		for (TemplateTarget templateTarget2 : target.getTemplateTargets()) {
    			String absoluteRootDir = templateTarget2.getAbsoluteRootDir();
    			if (absoluteRootDir!=null) {
    				ht.put(absoluteRootDir, absoluteRootDir);
    			}
    			String templateFullDir = templateTarget2.getTemplateFullDir();
    			if (templateFullDir!=null) {
    				ht.put(templateFullDir, templateFullDir);
    			}
    		}
			for (String templateAssociated : target.getTemplatedirRefs()) {
				for (String absoluteRootDir :target.getAbsoluteRootDirs(templateAssociated))
					ht.put(absoluteRootDir, absoluteRootDir);
			}
    		templatePath = getVelocityPath(ht,null);//getVelocityPath(ht, c.getCatalogDir());
    	}
    	return templatePath;
    }
    
    private String getTemplateRelativeLibPath (Template template) {
    	if (templateLibPath==null && isTemplateLibPathToReset) {
    		isTemplateLibPathToReset = false;
    		Configuration c = (Configuration)template.getTemplateTarget().getTarget().getAbstractConfigurationRoot();
    		Hashtable<String, String> ht = new Hashtable<String, String>();
        	TemplateTarget templateTarget = template.getTemplateTarget();
        	Target target = templateTarget.getTarget();
//    		StringBuffer sb = new StringBuffer();
    		for (TemplateTarget templateTarget2 : target.getTemplateTargets()) {
    			String libdir = templateTarget2.getLibdir();
    			if (libdir!=null && !libdir.equals("")) {
    				ht.put(libdir, libdir);
//	    			sb.append(templateTarget2.getLibdir());
//	    			sb.append(","); //TODO change for last element
    			}
    		}
    		
    		templateLibPath = getVelocityPath(ht, null);
    	}
    	return templateLibPath;    	
    }
    
    private String getVelocityPath (Hashtable<String, String> ht, String prefix) {
    	StringBuffer sb = new StringBuffer();
		Enumeration<String> e = ht.elements();
		while (e.hasMoreElements()) {
			if (prefix!=null)
				sb.append(prefix+"/");
			sb.append(e.nextElement());
			if (e.hasMoreElements())
				sb.append(",");
		}
		return sb.toString();
    }
    
	protected void produce(VelocityContext context, Template template, String outputFilename) throws Exception{
       org.apache.velocity.Template velocityTemplate = getVelocityTemplate(template, outputFilename);
       writeFile(context, velocityTemplate, outputFilename, template);
       writeFilePostProcessing (template, outputFilename);
       template.increaseNumberOfGeneratedArtifacts ();
    }
	
	private void writeFilePostProcessing(Template template, String outputFilename) {
		String chmod = template.getChmod();
		if (chmod!=null && !chmod.equals("")) {
			File file = new File(outputFilename);
			try {
				Runtime.getRuntime().exec("chmod "+chmod+" "+file.getCanonicalPath());
			} catch (IOException e) {
				//do nothing example on windows chmod does not exist
			}
		}
	}
	
	private org.apache.velocity.Template getVelocityTemplate (Template template, String outputFilename) throws Exception {
	   org.apache.velocity.Template velocityTemplate =  null;
	   try {
	   		velocityTemplate = Velocity.getTemplate(template.getTemplateFileName());
	   }
       catch( ResourceNotFoundException rnfe )
       {
           System.out.println("Error : cannot find template " + template.getTemplateFileName() );
       }
       catch( ParseErrorException pee )
       {
           System.out.println("Error : Syntax error in template " + template.getTemplateFileName() + ":" + pee );
       }
       return velocityTemplate;
	}
	
	private void writeFile (VelocityContext context, org.apache.velocity.Template velocityTemplate, String outputFilename, Template template) throws Exception {
		FileOutputStream fos = new FileOutputStream(outputFilename);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

		String licence = template.getLicence();
		if (template.isLicenceAtBeginning() && licence!=null)
			writer.append(licence);
		if ( velocityTemplate != null)
			velocityTemplate.merge(context, writer);
		if (!template.isLicenceAtBeginning() && licence!=null)
			writer.append(licence);
		writer.flush();
		writer.close();  
	}
	

   protected String getAbstractBeanName (GeneratorBean bean) {
		String beanName = StringUtils.lowerCase(bean.getClass().getName());
		beanName = StringUtils.substring(beanName,
				beanName.lastIndexOf(".") + 1);
		// TODO change
		if (beanName.equals("tableddlutils") || beanName.equals("tableumlnotation"))
			return "table";
		if (beanName.equals("columnddlutils"))
			return "column";		
		if (beanName.equals("viewddlutils"))
			return "view";	
		if (beanName.equals("componentddlutils"))
			return "component";			
		if (beanName.equals("functionddlutils"))
			return "function";	
		if (beanName.equals("wsdlmodelmetro"))
			return "wsdlmodel";			
		return beanName;
   }
	
    protected Table getDecoratedTable (Table table) {
    	return DataModelFactory.getTable(table);
    }
    
	protected void writeTemplateResult(GeneratorBean bean, Template template) throws MinuteProjectException{
		String outputFilename = template.getGeneratorOutputFileNameForConfigurationBean(bean, template);
		VelocityContext context = getVelocityContext(template);
		String beanName = getAbstractBeanName(bean);
		context.put(beanName, bean);
		context.put("template", template);
		putCommonContextObject(context, template);
		try {
			produce(context, template, outputFilename);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("ERROR on template "+template.getName()+" - on bean "+bean.getName());
			throwException(e, e.getMessage());
		}
	}
	
	protected void putCommonContextObject(VelocityContext context, Template template) {
		putStandardContextObject(context);
		putPluginContextObject(context, template);
	}
	
	protected void putStandardContextObject(VelocityContext context) {
		context.put("convertUtils", new ConvertUtils());
		context.put("commonUtils", new CommonUtils());
		context.put("viewUtils", new ViewUtils());
		context.put("formatUtils", new FormatUtils());
		context.put("bslaLibraryUtils", new BslaLibraryUtils());
		context.put("databaseUtils", new DatabaseUtils());
		context.put("modelUtils", new ModelUtils());
		context.put("templateUtils", new TemplateUtils());
		context.put("propertyUtils", new PropertyUtils());
	}

    protected void exit (String message) {
//		logger.error(message);
		System.exit(-1);
    }
    
    protected void throwException (Exception e, String error) throws MinuteProjectException {
		logger.error(error);
		MinuteProjectException mpe = new MinuteProjectException();
		mpe.setError(error);
		if (e!=null)
			mpe.setMessage(e.getMessage());
		throw mpe;
    }
    
    protected void throwException (String error) throws MinuteProjectException {
    	throwException(null, error);
    }
    
    public void resetTemplatePath() {
    	isTemplateLibPathToReset = true;
    	isTemplatePathToReset = true;
    }

}
