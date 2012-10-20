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
import net.sf.minuteProject.loader.presentation.Presentation;
import net.sf.minuteProject.loader.presentation.PresentationHolder;
import net.sf.minuteProject.loader.presentation.node.Block;
import net.sf.minuteProject.loader.presentation.node.Flow;
import net.sf.minuteProject.loader.presentation.node.Page;
import net.sf.minuteProject.loader.presentation.node.Window;
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

public class PresentationFlowGenerator extends AbstractGenerator {

	private static Logger logger = Logger.getLogger(ModelGenerator.class);
	
	private PresentationHolder presentationholder;
	private InitHolder initholder;
	private Targetlocation targetlocation;
	private Target target;
	
	public PresentationFlowGenerator(String configurationFile) {
		super(configurationFile);
	}
	
	public static void main(String args[]) throws Exception {
		if (args.length < 1) {
			System.exit(1);
		}
		PresentationFlowGenerator presentationFlowGenerator = new PresentationFlowGenerator(args[0]);
		presentationFlowGenerator.init(args);

	}
	
	private void init(String args[]) throws Exception{
		Date startDate = new Date();
	    logger.info("start time = "+new Date());

		loadInit(args[0]);
		loadPresentationDefinition(initholder);
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
//				String filename = target.getFilename();
//				String dir = target.getDir();
				loadTarget (target);	
			}
		}
	}
	
	private void loadPresentationDefinition(InitHolder initholder) throws Exception {
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
	    Presentation loader = new Presentation(dir, filename);
		presentationholder = loader.load();		
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
		if (template.getScopeSpecificValue().equals("page"))
			generatePageSpecific(template);
		if (template.getScopeSpecificValue().equals("flow"))
			generateFlowSpecific(template);	
		if (template.getScopeSpecificValue().equals("block"))
			generateBlockSpecific(template);	
		if (template.getScopeSpecificValue().equals("window"))
			generateWindowSpecific(template);			
	}
	
	private void generatePageSpecific(Template template) throws MinuteProjectException {	
		List<Page> pages = presentationholder.getDictionary().getPages();
		for (Page page : pages) {
			writeTemplateResult(page, template);
		}
	}
	
	private void generateFlowSpecific(Template template) throws MinuteProjectException {	
		List<Flow> flows = presentationholder.getDictionary().getFlows();
		for (Flow flow : flows) {
			writeTemplateResult(flow, template);
		}
	}
	
	private void generateBlockSpecific(Template template) throws MinuteProjectException {	
		List<Block> blocks = presentationholder.getDictionary().getBlocks();
		for (Block block : blocks) {
			if (template.isToGenerate(block)) {
				writeTemplateResult(block, template);
			}
		}
	}

	private void generateWindowSpecific(Template template) throws MinuteProjectException {	
		List<Window> windows = presentationholder.getDictionary().getWindows();
		for (Window window : windows) {
			if (template.isToGenerate(window)) {
				writeTemplateResult(window, template);
			}
		}
	}
	
//	protected void writeTemplateResult(GeneratorBean bean, Template template) throws MinuteProjectException {
//		String outputFilename = template.getGeneratorOutputFileNameForConfigurationBean(bean, template);
//		VelocityContext context = getVelocityContext(template);
//		String beanName = getAbstractBeanName(bean);
//		context.put(beanName, bean);
//		context.put("template", template);
//		putCommonContextObject(context, template);
//		produce(context, template, outputFilename);
//	}
	
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
