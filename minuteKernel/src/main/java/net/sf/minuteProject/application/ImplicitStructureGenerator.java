package net.sf.minuteProject.application;

import java.io.File;
import java.util.Date;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationRoot;
import net.sf.minuteProject.configuration.bean.Target;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.exception.MinuteProjectException;
//import net.sf.minuteProject.configuration.bean.file.Lines;
import net.sf.minuteProject.loader.implicitstructure.Implicitstructure;
import net.sf.minuteProject.loader.implicitstructure.ImplicitstructureHolder;
import net.sf.minuteProject.loader.implicitstructure.node.BaseStructure;
import net.sf.minuteProject.loader.implicitstructure.node.Lines;
import net.sf.minuteProject.loader.init.Init;
import net.sf.minuteProject.loader.init.InitHolder;
import net.sf.minuteProject.loader.init.node.Configuration;
import net.sf.minuteProject.loader.init.node.Definition;
import net.sf.minuteProject.loader.init.node.Targetlocation;
import net.sf.minuteProject.utils.ModelUtils;
import net.sf.minuteProject.utils.io.FileUtils;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;

public class ImplicitStructureGenerator extends AbstractGenerator {

	private static Logger logger = Logger.getLogger(ModelGenerator.class);
	
	private ImplicitstructureHolder implicitstructureHolder;
	private InitHolder initholder;
	private File file;
	private Target target;
	private Lines lines;
	
	public ImplicitStructureGenerator(String configurationFile) {
		super(configurationFile);
		resetTemplatePath();
	}
	
	public static void main(String args[]) throws Exception {
		if (args.length < 1) {
			System.exit(1);
		}
		ImplicitStructureGenerator implicitGenerator = new ImplicitStructureGenerator(args[0]);
		implicitGenerator.init(args);

	}
	
	private void init(String args[]) throws Exception{
		Date startDate = new Date();
	    logger.info("start time = "+new Date());

		loadInit(args[0]);
		loadDefinition(initholder);
		loadTarget(initholder);
		
		generate(initholder.getConfiguration().getTarget());
		
		Date endDate = new Date();
		logger.info("time taken : "+(endDate.getTime()-startDate.getTime())+ "ms.");		
	}
	
	private void loadInit(String filename) throws Exception {
	    Init loader = new Init(filename);
		initholder = loader.load();	
	}

	private void loadTarget(InitHolder initholder) throws Exception {
		Configuration configuration = initholder.getConfiguration();
		if (configuration!=null) {
			Targetlocation target = configuration.getTargetlocation();
			if (target!=null) {
				loadTarget (target);	
			}
		}
	}
	
	private void loadDefinition(InitHolder initholder) throws Exception {
		Configuration configuration = initholder.getConfiguration();
		if (configuration!=null) {
			Definition definition = configuration.getDefinition();
			if (definition!=null) {
				String filename = definition.getFilename();
				String dir = definition.getDir();
				loadDefinition (dir, filename);	
			}
		}
	}

	private void loadTarget(Targetlocation initTarget) throws Exception {
		target = populateTarget(initTarget);
		loadTarget(initholder.getConfiguration(), target);	
	}
	
	private void loadDefinition(String dir, String filename) throws Exception {
	    Implicitstructure loader = new Implicitstructure(dir, filename);
	    implicitstructureHolder = loader.load();	
	    String location = implicitstructureHolder.getBaseStructure().getLocation();
//	    String separator = implicitstructureHolder.getBaseStructure().getSeparator();
	    if (location!=""){
	    	file = new File(location);
	    	if (!file.exists()) 
	    		file = new File(dir+"/"+location);
//	    	lines = FileUtils.getLines(file, separator);
	    }
	}

	private Target populateTarget (Targetlocation initTarget) {
		Target target = new Target();
		target.setDir(initTarget.getDir());
		target.setFileName(initTarget.getFilename());
		return target;
	}
//	public void loadTarget (AbstractConfigurationRoot abstractConfigurationRoot, Target target) throws Exception {
//		loadConfiguration(abstractConfigurationRoot, getTargetConfigurationInputStream(abstractConfigurationRoot, target), GENERATOR_TARGET_RULES);
//		complementWithTargetInfo(abstractConfigurationRoot, target);
//	}

	@Override
	public AbstractConfiguration getConfigurationRoot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getConfigurationRulesFile() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getPropertyConfigurationRulesFile() {
		return null;
	}

	public void generate(Template template) throws MinuteProjectException {
		if (template.getScopeSpecificValue().equals("document"))
			generateDocumentSpecific(template);
//		if (template.getScopeSpecificValue().equals("line"))
//			generateBeanSpecific(template);	
	}

	private void generateDocumentSpecific (Template template) throws MinuteProjectException {	
		BaseStructure baseStructure = implicitstructureHolder.getBaseStructure();
		writeTemplateResult(baseStructure, template);
	}
	
	protected void putStandardContextObject(VelocityContext context) {
		super.putStandardContextObject(context);
		FileUtils fileUtils = new FileUtils();
		context.put("file", file);
		context.put("fileUtils", new FileUtils());
		context.put("lines", fileUtils.getLines( file, implicitstructureHolder.getBaseStructure().getSeparator()));
	}

}
