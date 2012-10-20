package net.sf.minuteProject.application;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.apache.commons.lang.StringUtils;
import org.apache.ddlutils.model.Database;
import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.Configuration;
import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.Package;
import net.sf.minuteProject.configuration.bean.Target;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.TemplateTarget;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.service.Scope;
import net.sf.minuteProject.configuration.bean.system.Plugin;
import net.sf.minuteProject.configuration.bean.view.Function;
import net.sf.minuteProject.configuration.bean.view.Service;
import net.sf.minuteProject.configuration.bean.view.View;
import net.sf.minuteProject.exception.MinuteProjectException;
import net.sf.minuteProject.utils.BslaLibraryUtils;
import net.sf.minuteProject.utils.BslaViewLibraryUtils;
import net.sf.minuteProject.utils.ColumnUtils;
import net.sf.minuteProject.utils.CommonUtils;
import net.sf.minuteProject.utils.ConvertUtils;
import net.sf.minuteProject.utils.DatabaseUtils;
import net.sf.minuteProject.utils.FormatUtils;
import net.sf.minuteProject.utils.ModelUtils;
import net.sf.minuteProject.utils.ReferenceUtils;
import net.sf.minuteProject.utils.SqlUtils;
import net.sf.minuteProject.utils.TableUtils;
import net.sf.minuteProject.utils.TestUtils;
import net.sf.minuteProject.utils.URLUtils;
import net.sf.minuteProject.utils.ViewUtils;
import net.sf.minuteProject.utils.WebUtils;

/**
 * @author Florian Adler
 * 
 */
public class ModelServiceGenerator extends ModelViewGenerator {

	private static Logger logger = Logger.getLogger(ModelGenerator.class);
//	public static final String GENERATOR_MODEL_RULES = "net/sf/minuteProject/configuration/model-config-rules.xml";

	/*
	 * context object 
	 */
//	private CommonUtils commonUtils;
//	private ConvertUtils convertUtils;
//	private ColumnUtils columnUtils;
//	private ViewUtils viewUtils;
//	private FormatUtils formatUtils;
//	private BslaLibraryUtils bslaLibraryUtils;
//	private DatabaseUtils databaseUtils;
//	private ModelUtils modelUtils;
//	private URLUtils urlUtils;
//	private TestUtils testUtils;
//	private WebUtils webUtils;
//	private SqlUtils sqlUtils;
//	private TableUtils tableUtils;

	
	private Model model;

//	private String modelConfig;
//
//	public String getModelConfig() {
//		return modelConfig;
//	}
//
//	public void setModelConfig(String modelConfig) {
//		this.modelConfig = modelConfig;
//	}

	/**
	 * Constructs the generator with its configuration
	 * 
	 * @param configurationFile
	 */
	public ModelServiceGenerator(String configurationFile) {
		super(configurationFile);
	}

//	@Override
//	public AbstractConfiguration getConfigurationRoot() {
//		return new Configuration();
//	}
//
//	@Override
//	public String getConfigurationRulesFile() {
//		return GENERATOR_MODEL_RULES;
//	}

	public static void main(String args[]) {
		String config;
		if (args.length < 1) {
			System.exit(1);
		}
		config = args[0];
		Date startDate = new Date();
	    logger.info("start time = "+new Date());
		ModelGenerator generator = new ModelGenerator(config);
		// Model model = (Model) generator.load();
		try {
			generator.generate();
		} catch (MinuteProjectException e) {
			generator.exit ("");
		}
		
		
//		Configuration configuration = (Configuration) generator.load();
//		Model model = configuration.getModel();
//		generator.setModel(model);
//		generator.loadModel(model);
//		generator.loadTarget(model.getConfiguration(), model.getConfiguration()
//				.getTarget());
//		generator.generate(model.getConfiguration().getTarget());
		
		
		Date endDate = new Date();
		//logger.info("start date = "+startDate.getTime());
		//logger.info("end date = "+endDate.getTime());
		logger.info("time taken : "+(endDate.getTime()-startDate.getTime())/1000+ "s.");
	}

	protected void loadModel(Model model) {
		model.getDataModel().loadDatabase();
		model.getBusinessModel().complementDataModelWithTables();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.minuteProject.application.Generator#generate(net.sf.minuteProject.configuration.bean.Template)
	 */
	public void generate(Template template) throws MinuteProjectException {
		// TODO Auto-generated method stub
		// getView();
		if (template.getEntitySpecific().equals("true"))
			generateArtifactsByEntity(template);
		else if (template.getPackageSpecific().equals("true"))
			generateArtifactsByPackage(template);
		else if (template.getModelSpecific().equals("true"))
			generateArtifactsByModel(template);
		else if (template.getApplicationSpecific().equals("true"))
			generateArtifactsByApplication(template);
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	protected void generateArtifactsByModel(Template template) throws MinuteProjectException {
		writeTemplateResult(getModel(), template);
	}

	protected void generateArtifactsByPackage(Template template) throws MinuteProjectException {
		List<Package> packages = model.getBusinessModel().getBusinessPackage()
				.getPackages();
		for (Package pack : packages) {
			writeTemplateResult(pack, template);
		}
	}

	protected void generateArtifactsByEntity(Template template) throws MinuteProjectException {	
		for (Scope scope : model.getBusinessModel().getService().getScopes() ) {
			writeTemplateResult(scope, template);
		}		
	}

	private void generateArtifactsByApplication(Template template) throws MinuteProjectException {	
		writeTemplateResult(getModel().getConfiguration(), template);
	}
	
	protected void writeTemplateResult(GeneratorBean bean, Template template) throws MinuteProjectException {
		String outputFilename = template
				.getGeneratorOutputFileNameForConfigurationBean(bean, template);
		VelocityContext context = getVelocityContext(template);
		String beanName = getAbstractBeanName(bean);
		context.put(beanName, bean);
		context.put("template", template);
		putCommonContextObject(context, template);
		try {
			produce(context, template, outputFilename);
		} catch (Exception ex) {
			throwException(ex, "ERROR on template "+template.getName()+" - on bean "+bean.getName());
		}
	}

	protected void putCommonContextObject(VelocityContext context, Template template) {
		putStandardContextObject(context);
		putPluginContextObject(context, template);
		context.put("model", model);
	}	
	
}
