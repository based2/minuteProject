package net.sf.minuteProject.application;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.AbstractConfigurationRoot;
import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.Target;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.exception.MinuteProjectException;
import net.sf.minuteProject.loader.init.Init;
import net.sf.minuteProject.loader.init.InitHolder;
import net.sf.minuteProject.loader.init.node.Configuration;
import net.sf.minuteProject.loader.init.node.Definition;
import net.sf.minuteProject.loader.init.node.Targetlocation;
import net.sf.minuteProject.loader.mapping.Mapping;
import net.sf.minuteProject.loader.mapping.MappingHolder;
import net.sf.minuteProject.loader.mapping.node.Bean;
import net.sf.minuteProject.loader.mapping.node.BeanMapping;
import net.sf.minuteProject.loader.mapping.node.BeanMappings;
import net.sf.minuteProject.loader.mapping.node.Beans;
import net.sf.minuteProject.loader.mapping.node.Flow;
import net.sf.minuteProject.loader.mapping.node.Flows;
import net.sf.minuteProject.loader.mapping.node.Service;
import net.sf.minuteProject.loader.mapping.node.Services;
import net.sf.minuteProject.loader.mapping.node.Validation;
import net.sf.minuteProject.loader.mapping.node.Validations;
import net.sf.minuteProject.loader.presentation.Presentation;
import net.sf.minuteProject.loader.presentation.PresentationHolder;
import net.sf.minuteProject.plugin.xml.schema.XmlSchemaUtils;
import net.sf.minuteProject.utils.BslaLibraryUtils;
import net.sf.minuteProject.utils.CommonUtils;
import net.sf.minuteProject.utils.ConvertUtils;
import net.sf.minuteProject.utils.DatabaseUtils;
import net.sf.minuteProject.utils.FormatUtils;
import net.sf.minuteProject.utils.ModelUtils;
import net.sf.minuteProject.utils.ViewUtils;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;

public class MappingGenerator extends AbstractGenerator {

	private static Logger logger = Logger.getLogger(MappingGenerator.class);
	
	private MappingHolder mappingHolder;
	private InitHolder initholder;
	private Targetlocation targetlocation;
	private Target target;
	
	public MappingGenerator(String configurationFile) {
		super(configurationFile);
	}
	
	public static void main(String args[]) throws Exception {
		if (args.length < 1) {
			System.exit(1);
		}
		MappingGenerator mappingGenerator = new MappingGenerator(args[0]);
		mappingGenerator.init(args);

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
				loadPresentationDefinition (dir, filename);	
			}
		}
	}

	private void loadTarget(Targetlocation initTarget) throws Exception {
		target = populateTarget(initTarget);
		loadTarget(initholder.getConfiguration(), target);	
	}
	
	private void loadPresentationDefinition(String dir, String filename) throws Exception {
	    Mapping loader = new Mapping(dir, filename);
		mappingHolder = loader.load();		
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
		if (template.getScopeSpecificValue().equals("mapBean"))
			generateMapBeanSpecific(template);
		if (template.getScopeSpecificValue().equals("bean"))
			generateBeanSpecific(template);	
		if (template.getScopeSpecificValue().equals("validation"))
			generateValidationSpecific(template);	
		if (template.getScopeSpecificValue().equals("flow"))
			generateFlowSpecific(template);			
		if (template.getScopeSpecificValue().equals("service"))
			generateServiceSpecific(template);
	}

	private void generateFlowSpecific (Template template) throws MinuteProjectException {	
		Flows flows = mappingHolder.getBeanMap().getFlows();
		if (flows != null) {
			for (Flow flow : flows.getFlows()) {
				writeTemplateResult(flow, template);
			}
		}
	}
	
	private void generateValidationSpecific (Template template) throws MinuteProjectException {	
		Validations validations = mappingHolder.getBeanMap().getValidations();
		if (validations != null) {
			for (Validation validation : validations.getValidations()) {
				boolean isToGenerate = true;
	    		if (template.getCheckTemplateToGenerate()!=null && template.getCheckTemplateToGenerate().equals("true")) {
	    			if (!template.isToGenerate(validation)) {
	    				isToGenerate =false;
	    			}
	    		} 
	    		if (isToGenerate)			
//				    writeTemplateResult(bean, template);				
	    			writeTemplateResult(validation, template);
			}
		}
	}
	
	private void generateMapBeanSpecific(Template template) throws MinuteProjectException {	
		BeanMappings beanMappings = mappingHolder.getBeanMap().getMappings();
		if (beanMappings != null) {
			for (BeanMapping mapping : beanMappings.getMappings()) {
				writeTemplateResult(mapping, template);
			}
		}
	}
	
	private void generateBeanSpecific(Template template) throws MinuteProjectException {	
		Beans beans = mappingHolder.getBeanMap().getBeans();
		if (beans!=null) {
			for (Bean bean : beans.getBeans()) {
				boolean isToGenerate = true;
	    		if (template.getCheckTemplateToGenerate()!=null && template.getCheckTemplateToGenerate().equals("true")) {
	    			if (!template.isToGenerate(bean)) {
	    				isToGenerate =false;
	    			}
	    		} 
	    		if (isToGenerate)			
				    writeTemplateResult(bean, template);
			}
		}
	}
	
	private void generateServiceSpecific(Template template) throws MinuteProjectException {	
		Services services = mappingHolder.getBeanMap().getServices();
		if (services!=null) {
			for (Service service : services.getServices()) {
				boolean isToGenerate = true;
	    		if (template.getCheckTemplateToGenerate()!=null && template.getCheckTemplateToGenerate().equals("true")) {
	    			if (!template.isToGenerate(service)) {
	    				isToGenerate =false;
	    			}
	    		} 
	    		if (isToGenerate)			
				    writeTemplateResult(service, template);
			}
		}
	}
//	
//	private void generateBlockSpecific(Template template) throws Exception {	
//		List<Block> blocks = presentationholder.getDictionary().getBlocks();
//		for (Block block : blocks) {
//			if (template.isToGenerate(block)) {
//				writeTemplateResult(block, template);
//			}
//		}
//	}
//
//	private void generateWindowSpecific(Template template) throws Exception {	
//		List<Window> windows = presentationholder.getDictionary().getWindows();
//		for (Window window : windows) {
//			if (template.isToGenerate(window)) {
//				writeTemplateResult(window, template);
//			}
//		}
//	}
	
//	protected void writeTemplateResult(GeneratorBean bean, 
//			Template template) throws MinuteProjectException {
//		String outputFilename = template.getGeneratorOutputFileNameForConfigurationBean(bean, template);
//		VelocityContext context = getVelocityContext(template);
//		String beanName = getAbstractBeanName(bean);
//		context.put(beanName, bean);
//		context.put("template", template);
//		putCommonContextObject(context, template);
//		produce(context, template, outputFilename);
//	}
//	
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
	}

}
