package net.sf.minuteProject.application;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.apache.commons.lang.StringUtils;
import org.apache.ddlutils.model.Database;
import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.BusinessModel;
import net.sf.minuteProject.configuration.bean.Configuration;
import net.sf.minuteProject.configuration.bean.FunctionModel;
import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.Package;
import net.sf.minuteProject.configuration.bean.Target;
import net.sf.minuteProject.configuration.bean.Targets;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.TemplateTarget;
import net.sf.minuteProject.configuration.bean.enrichment.Action;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Component;
import net.sf.minuteProject.configuration.bean.model.data.Function;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.model.data.constant.Direction;
import net.sf.minuteProject.configuration.bean.model.webservice.Entity;
import net.sf.minuteProject.configuration.bean.service.Scope;
import net.sf.minuteProject.configuration.bean.system.Plugin;

import net.sf.minuteProject.configuration.bean.view.Service;
import net.sf.minuteProject.configuration.bean.view.View;
import net.sf.minuteProject.exception.MinuteProjectException;
import net.sf.minuteProject.integration.bean.BasicIntegrationConfiguration;
import net.sf.minuteProject.loader.target.TargetHolder;
import net.sf.minuteProject.plugin.format.I18nUtils;
import net.sf.minuteProject.utils.BslaLibraryUtils;
import net.sf.minuteProject.utils.BslaViewLibraryUtils;
import net.sf.minuteProject.utils.ColumnUtils;
import net.sf.minuteProject.utils.CommonUtils;
import net.sf.minuteProject.utils.ConvertUtils;
import net.sf.minuteProject.utils.DatabaseUtils;
import net.sf.minuteProject.utils.EnumUtils;
import net.sf.minuteProject.utils.FormatUtils;
import net.sf.minuteProject.utils.ModelUtils;
import net.sf.minuteProject.utils.ReferenceUtils;
import net.sf.minuteProject.utils.ServiceUtils;
import net.sf.minuteProject.utils.SqlUtils;
import net.sf.minuteProject.utils.TableUtils;
import net.sf.minuteProject.utils.TemplateUtils;
import net.sf.minuteProject.utils.TestUtils;
import net.sf.minuteProject.utils.URLUtils;
import net.sf.minuteProject.utils.ViewUtils;
import net.sf.minuteProject.utils.WebUtils;
import net.sf.minuteProject.utils.io.UpdatedAreaUtils;

/**
 * @author Florian Adler
 * 
 */
public class WsdlGenerator extends AbstractGenerator {

	private static Logger logger = Logger.getLogger(ModelGenerator.class);
	public static final String GENERATOR_MODEL_RULES = "net/sf/minuteProject/configuration/model-config-rules.xml";
	public static final String GENERATOR_MODEL_PROPERTY_RULES = "net/sf/minuteProject/configuration/model-property-config-rules.xml";

	/*
	 * context object 
	 */
	private CommonUtils commonUtils = new CommonUtils();
	private ConvertUtils convertUtils = new ConvertUtils();
	private ColumnUtils columnUtils = new ColumnUtils();
	private ViewUtils viewUtils = new ViewUtils();
	private FormatUtils formatUtils = new FormatUtils();
	private BslaLibraryUtils bslaLibraryUtils = new BslaLibraryUtils();
	private DatabaseUtils databaseUtils = new DatabaseUtils();
	private ModelUtils modelUtils = new ModelUtils();
	private URLUtils urlUtils = new URLUtils();
	private TestUtils testUtils = new TestUtils();
	private WebUtils webUtils = new WebUtils();
	private SqlUtils sqlUtils = new SqlUtils();
	private TableUtils tableUtils = new TableUtils();
	private ReferenceUtils referenceUtils = new ReferenceUtils();
	private EnumUtils enumUtils = new EnumUtils();
	private I18nUtils i18nUtils = new I18nUtils();
	private UpdatedAreaUtils updatedAreaUtils = new UpdatedAreaUtils();

	private Model model;

	private String modelConfig;

	public String getModelConfig() {
		return modelConfig;
	}

	public void setModelConfig(String modelConfig) {
		this.modelConfig = modelConfig;
	}

	/**
	 * Constructs the generator with its configuration
	 * 
	 * @param configurationFile
	 */
	public WsdlGenerator(String configurationFile) {
		super(configurationFile);
	}

	public WsdlGenerator(BasicIntegrationConfiguration bic) {
		super(bic);
	}

	@Override
	public AbstractConfiguration getConfigurationRoot() {
		return new Configuration();
	}

	@Override
	public String getConfigurationRulesFile() {
		return GENERATOR_MODEL_RULES;
	}

	@Override
	public String getPropertyConfigurationRulesFile() {
		return GENERATOR_MODEL_PROPERTY_RULES;
	}

	public void generate() throws MinuteProjectException {
		Configuration configuration = (Configuration) load();
		generate(configuration);		
	}
	
	protected void generate (Configuration configuration) throws MinuteProjectException {
		Model model = configuration.getModel();
		setModel(model);
		load(model);
//		applyConventions(model);
//		applyLimitations(model);
		if (hasTarget())
			loadAndGenerate(model.getConfiguration().getTarget());
		if (hasTargets())
			loadAndGenerate(model.getConfiguration().getTargets());
	}
	
	private void applyLimitations(Model model) {
		model.getBusinessModel().applyLimitations();
	}

	protected void applyConventions (Model model) {
		model.getBusinessModel().applyConventions();
	}
	
	protected boolean hasTarget () {
		return model.getConfiguration().hasTarget();
	}
	
	protected boolean hasTargets () {
		return model.getConfiguration().hasTargets();
	}
	
	protected void loadAndGenerate (Target target) throws MinuteProjectException {
		loadTarget(model.getConfiguration(), target);
		applyTargetConventionAndGenerate(model.getConfiguration().getTarget());		
	}

	protected void loadAndGenerate (Targets targets) throws MinuteProjectException {
		Target targetFinal = new Target();
		Configuration configuration = model.getConfiguration();
		for (Target target : targets.getTargets()) {
//			TargetHolder targetHolder = new TargetHolder();
			loadTarget(configuration, target);
			configuration.getTarget().setIsGenerable(target.isGenerable());
//			generate(configuration.getTarget());
//			targetHolder.setTarget(model.getConfiguration().getTarget());
			
			targetFinal.complement(configuration.getTarget());
			targetFinal.complementAdditional (target);
			configuration.setTarget(new Target());
		}	
		configuration.setTarget(targetFinal);
		applyTargetConventionAndGenerate(configuration.getTarget());
//		for (Target target : targets.getTargets()) {
//			complementWithTargetInfo(configuration, target);
//			System.out.println("---");
//			generate(configuration.getTarget());
//		}
//		targets.getAbstractConfigurationRoot().setTarget(targetHolder);
			
	}
	
	private void applyTargetConventionAndGenerate (Target target) throws MinuteProjectException {
		applyTargetConvention(target);
		generate(target);
	}
	
	private void applyTargetConvention(Target target) {
		model.getConfiguration().applyConventions();
	}

	protected void load(Model model) {
		model.getWebServiceModel().load();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.minuteProject.application.Generator#generate(net.sf.minuteProject.configuration.bean.Template)
	 */
	public void generate(Template template) throws MinuteProjectException {
		if (SCOPE_WSDL.equals(template.getScopeSpecificValue()))
			generateArtifactsByWsdl(template);			
		if (SCOPE_WSDL_ENTITY.equals(template.getScopeSpecificValue()))
			generateArtifactsByWsdlEntity(template);			
		if (SCOPE_WSDL_SERVICE.equals(template.getScopeSpecificValue()))
			generateArtifactsByWsdlService(template);			
	}

	private void generateArtifactsByWsdl(Template template) throws MinuteProjectException {
		writeTemplateResult(getModel().getWebServiceModel().getWsdlModel(), template);
	}
	
	private void generateArtifactsByWsdlEntity(Template template) throws MinuteProjectException {
		List<Entity> entities = getModel().getWebServiceModel().getWsdlModel().getEntityModel().getEntities();
		for (Entity entity : entities)
			writeTemplateResult(entity, template);
	}
	
	private void generateArtifactsByWsdlService(Template template) throws MinuteProjectException {
		List<net.sf.minuteProject.configuration.bean.model.webservice.Service> services = getModel().getWebServiceModel().getWsdlModel().getServices();
		for (net.sf.minuteProject.configuration.bean.model.webservice.Service service : services)
			writeTemplateResult(service, template);
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

		
	protected void writeTemplateResult(GeneratorBean bean, Template template) throws MinuteProjectException {
		// enable cache
		bean.enableCache();
		//velocity bean manipulation
		String outputFilename = template.getGeneratorOutputFileNameForConfigurationBean(bean, template);
		//context
		VelocityContext context = getVelocityContext(template);
		Map<String,String> updatedAreas = TemplateUtils.getUpdatedAreas(template, bean);
		if (updatedAreas!=null)
			if (updatedAreas.containsKey(UpdatedAreaUtils.MP_MANAGED_STOP_GENERATING))
				return; //stop generating directive
			context.put("updatedAreas", updatedAreas);
		String beanName = getAbstractBeanName(bean);
		context.put(beanName, bean);
		if (bean instanceof Component) {
			Component component = (Component) bean;
			Table table = component.getTable();
			context.put("table", table);
		}		
		if (bean instanceof Function) {
			context.put("table", bean);
		}		
		if (beanName.equals("view"))
			context.put("table", bean);		
		context.put("template", template);
		putCommonContextObject(context, template);
        //
		try {
			produce(context, template, outputFilename);
		} catch (Exception ex) {
			logger.error("ERROR on template "+template.getName()+" - on bean "+bean.getName());
			ex.printStackTrace();
			throwException(ex, "ERROR : "+ex.getMessage());		
//			logger.error("ERROR : "+ex.getMessage());
//			throw ex;
		}
	}


	protected void putCommonContextObject(VelocityContext context, Template template) {
		putStandardContextObject(context);
		putPluginContextObject(context, template);
		context.put("model", model);
	}
	
//	protected void putPluginContextObject (VelocityContext context, Template template) {
//		List <Plugin> plugins = template.getTemplateTarget().getTarget().getPlugins();
//		for (Plugin plugin : plugins) {
//			ClassLoader cl = ClassLoader.getSystemClassLoader();
//			try {
//				Class clazz = cl.loadClass(plugin.getClassName());
//				Object velocityObject = clazz.newInstance();
//				context.put(plugin.getName(), velocityObject);
//			} catch (ClassNotFoundException e) {
//				logger.info("cannot find plugin "+plugin.getName()+" via class "+plugin.getClassName());
//				e.printStackTrace();
//			} catch (InstantiationException e) {
//				logger.info("cannot instantiate plugin "+plugin.getName()+" via class "+plugin.getClassName());
//			} catch (IllegalAccessException e) {
//				logger.info("cannot access plugin "+plugin.getName()+" via class "+plugin.getClassName());
//			}
//		}
//	}
	
	protected void putStandardContextObject (VelocityContext context) {
		super.putStandardContextObject(context);
		context.put("convertUtils", getConvertUtils());
		context.put("commonUtils", getCommonUtils());
		context.put("columnUtils", getColumnUtils());
		context.put("viewUtils", getViewUtils());
		context.put("formatUtils", getFormatUtils());
		context.put("bslaLibraryUtils", getBslaLibraryUtils());
		context.put("databaseUtils", getDatabaseUtils());
		context.put("modelUtils", getModelUtils());
		context.put("URLUtils", getUrlUtils());
		context.put("TestUtils", getTestUtils());
		context.put("WebUtils", getWebUtils());
		context.put("sqlUtils", getSqlUtils());
		context.put("tableUtils", getTableUtils());
		context.put("testUtils", getTestUtils());	
		context.put("referenceUtils", referenceUtils);
		context.put("enumUtils", enumUtils);
		context.put("i18nUtils", i18nUtils);
		context.put("updatedAreaUtils", updatedAreaUtils);
	}
	
	public BslaLibraryUtils getBslaLibraryUtils() {
		if (bslaLibraryUtils==null)
			bslaLibraryUtils = new BslaLibraryUtils();
		return bslaLibraryUtils;
	}

	public ColumnUtils getColumnUtils() {
		if (columnUtils==null)
			columnUtils = new ColumnUtils();
		return columnUtils;
	}

	public CommonUtils getCommonUtils() {
		if (commonUtils==null)
			commonUtils = new CommonUtils();
		return commonUtils;
	}

	public ConvertUtils getConvertUtils() {
		if (convertUtils == null)
			convertUtils = new ConvertUtils();
		return convertUtils;
	}

	public DatabaseUtils getDatabaseUtils() {
		if (databaseUtils == null)
			databaseUtils = new DatabaseUtils();
		return databaseUtils;
	}

	public FormatUtils getFormatUtils() {
		if (formatUtils == null)
			formatUtils = new FormatUtils();
		return formatUtils;
	}

	public ModelUtils getModelUtils() {
		if (modelUtils == null)
			modelUtils = new ModelUtils();
		return modelUtils;
	}

	public SqlUtils getSqlUtils() {
		if (sqlUtils == null)
			sqlUtils = new SqlUtils();
		return sqlUtils;
	}

	public TableUtils getTableUtils() {
		if (tableUtils == null)
			tableUtils = new TableUtils();
		return tableUtils;
	}

	public TestUtils getTestUtils() {
		if (testUtils == null)
			testUtils = new TestUtils();
		return testUtils;
	}

	public URLUtils getUrlUtils() {
		if (urlUtils == null)
			urlUtils = new URLUtils();
		return urlUtils;
	}

	public ViewUtils getViewUtils() {
		if (viewUtils == null)
			viewUtils = new ViewUtils();
		return viewUtils;
	}

	public WebUtils getWebUtils() {
		if (webUtils == null)
			webUtils = new WebUtils();
		return webUtils;
	}

	/* 
	 * private getter of the context object 
	 */


    public static void main(String args[]) {
        String config;
        if (args.length < 1) {
            System.exit(1);
        }
        config = args[0];
        Date startDate = new Date();
        logger.info("start time = "+new Date());
        WsdlGenerator generator = new WsdlGenerator(config);
        try {
            generator.generate();
        } catch (MinuteProjectException e) {
            generator.exit ("");
        }
        Date endDate = new Date();
        logger.info("time taken : "+(endDate.getTime()-startDate.getTime())/1000+ "s.");
    }
	
}
