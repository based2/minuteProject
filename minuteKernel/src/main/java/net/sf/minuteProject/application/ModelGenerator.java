package net.sf.minuteProject.application;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.BusinessModel;
import net.sf.minuteProject.configuration.bean.Configuration;
import net.sf.minuteProject.configuration.bean.FunctionModel;
import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.Package;
import net.sf.minuteProject.configuration.bean.StatementModel;
import net.sf.minuteProject.configuration.bean.Target;
import net.sf.minuteProject.configuration.bean.Targets;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.enrichment.Action;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Component;
import net.sf.minuteProject.configuration.bean.model.data.Function;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.model.data.constant.Direction;
import net.sf.minuteProject.configuration.bean.model.statement.Composite;
import net.sf.minuteProject.configuration.bean.model.statement.CompositeQueryElement;
import net.sf.minuteProject.configuration.bean.model.statement.Queries;
import net.sf.minuteProject.configuration.bean.model.statement.Query;
import net.sf.minuteProject.configuration.bean.service.Scope;
import net.sf.minuteProject.exception.MinuteProjectException;
import net.sf.minuteProject.integration.bean.BasicIntegrationConfiguration;
import net.sf.minuteProject.plugin.format.I18nUtils;
import net.sf.minuteProject.utils.BslaLibraryUtils;
import net.sf.minuteProject.utils.ColumnUtils;
import net.sf.minuteProject.utils.CommonUtils;
import net.sf.minuteProject.utils.ConvertUtils;
import net.sf.minuteProject.utils.DatabaseUtils;
import net.sf.minuteProject.utils.EnumUtils;
import net.sf.minuteProject.utils.FormatUtils;
import net.sf.minuteProject.utils.ModelUtils;
import net.sf.minuteProject.utils.ReferenceUtils;
import net.sf.minuteProject.utils.RoutineUtils;
import net.sf.minuteProject.utils.ServiceUtils;
import net.sf.minuteProject.utils.SqlUtils;
import net.sf.minuteProject.utils.TableUtils;
import net.sf.minuteProject.utils.TemplateUtils;
import net.sf.minuteProject.utils.TestUtils;
import net.sf.minuteProject.utils.TriggerUtils;
import net.sf.minuteProject.utils.URLUtils;
import net.sf.minuteProject.utils.ViewUtils;
import net.sf.minuteProject.utils.WebUtils;
import net.sf.minuteProject.utils.enrichment.SemanticReferenceUtils;
import net.sf.minuteProject.utils.io.UpdatedAreaUtils;
import net.sf.minuteProject.utils.java.JavaUtils;
import net.sf.minuteProject.utils.sql.QueryUtils;
import net.sf.minuteProject.utils.sql.StatementUtils;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;

/**
 * @author Florian Adler
 * 
 */
public class ModelGenerator extends AbstractGenerator {

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
	private JavaUtils javaUtils = new JavaUtils();
	private RoutineUtils routineUtils = new RoutineUtils();
	private StatementUtils statementUtils = new StatementUtils();
	private TriggerUtils triggerUtils = new TriggerUtils();
	private QueryUtils queryUtils = new QueryUtils();
	private SemanticReferenceUtils semanticReference = new SemanticReferenceUtils();

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
	public ModelGenerator(String configurationFile) {
		super(configurationFile);
	}

	public ModelGenerator(BasicIntegrationConfiguration bic) {
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
		Model model = getEnrichedModel(configuration);
		if (hasTarget())
			loadAndGenerate(model.getConfiguration().getTarget());
		if (hasTargets())
			loadAndGenerate(model.getConfiguration().getTargets());
	}
	
	public Model getEnrichedModel (Configuration configuration) {
		Model model = configuration.getModel();
		setModel(model);
		loadModel(model);
		applyConventions(model);
		applyLimitations(model);
		return model;
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

	protected void loadModel(Model model) {
		model.getDataModel().loadDatabase();
		BusinessModel businessModel = model.getBusinessModel();
		businessModel.secureEntityType();
		businessModel.complementDataModelWithTables();
		businessModel.complementDataModelWithTransferEntitiesEnrichment();
		if (model.hasFunctionModel()) {
			FunctionModel functionModel = model.getFunctionModel();
			functionModel.complementFunctionWithFunctionEntity();
		}
		if (model.hasStatementModel()) {
			StatementModel statementModel = model.getStatementModel();
			statementModel.complementStatement();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.minuteProject.application.Generator#generate(net.sf.minuteProject.configuration.bean.Template)
	 */
	public void generate(Template template) throws MinuteProjectException {
		String scopeSpecificValue = template.getScopeSpecificValue();
		if (template.getFieldSpecific().equals("true") || SCOPE_DATAMODEL_FIELD.equals(scopeSpecificValue))
			generateArtifactsByField(template);
		else if (template.getEntitySpecific().equals("true") || SCOPE_DATAMODEL_ENTITY.equals(scopeSpecificValue))
			generateArtifactsByEntity(template);
		else if (template.getPackageSpecific().equals("true") || SCOPE_DATAMODEL_PACKAGE.equals(scopeSpecificValue))
			generateArtifactsByPackage(template);
		else if (template.getModelSpecific().equals("true") || SCOPE_DATAMODEL_MODEL.equals(scopeSpecificValue))
			generateArtifactsByModel(template);
		else if (template.getServiceSpecific().equals("true"))
			generateArtifactsByService(template);
		else if (template.getApplicationSpecific().equals("true") || SCOPE_DATAMODEL_APPLICATION.equals(scopeSpecificValue))
			generateArtifactsByApplication(template);
		else if (template.getComponentSpecific().equals("true"))
			generateArtifactsByComponent(template);
		else {
			if (SCOPE_DATAMODEL_FUNCTION_INPUT.equals(scopeSpecificValue))
				generateArtifactsByFunction(template, Direction.IN);
			else if (SCOPE_DATAMODEL_FUNCTION_OUTPUT.equals(scopeSpecificValue))
				generateArtifactsByFunction(template, Direction.OUT);		
			else if (SCOPE_DATAMODEL_FUNCTION.equals(scopeSpecificValue))
				generateArtifactsByFunction(template);
			else if (SCOPE_TARGET_TEMPLATE.equals(scopeSpecificValue))
				generateArtifactsByTargetTemplate(template);	
			else if (SCOPE_TRANSFER_ENTITY_TEMPLATE.equals(scopeSpecificValue))
				generateArtifactsByTransferEntity(template);
			else if (SCOPE_ACTION_TEMPLATE.equals(scopeSpecificValue))
				generateArtifactsByAction(template);
			else if (QUERY_ACTION_TEMPLATE.equals(scopeSpecificValue))
				generateArtifactsByQuery(template);
			else if (SDD_COMPOSITE_TEMPLATE.equals(scopeSpecificValue))
				generateArtifactsBySddCompositeBean(template, Direction.IN);
			else if (SDD_INPUT_COMPOSITE_TEMPLATE.equals(scopeSpecificValue))
				generateArtifactsBySddCompositeBean(template, Direction.IN);
			else if (SDD_OUTPUT_COMPOSITE_TEMPLATE.equals(scopeSpecificValue))
				generateArtifactsBySddCompositeBean(template, Direction.OUT);
			else if (SDD_INPUT_BEAN_TEMPLATE.equals(scopeSpecificValue))
				generateArtifactsBySddBean(template, Direction.IN);
			else if (SDD_OUTPUT_BEAN_TEMPLATE.equals(scopeSpecificValue))
				generateArtifactsBySddBean(template, Direction.OUT);
		}
	}

	private void generateArtifactsByAction(Template template) throws MinuteProjectException {
		for (Table table : getModel().getBusinessModel().getBusinessPackage().getTransferEntities()) {
			for (Action action : table.getActions()) {
				writeTemplateResult(action, template);
			}
		}
	}
	
	private void generateArtifactsByQuery(Template template) throws MinuteProjectException {
		if (getModel().getStatementModel()!=null && getModel().getStatementModel().getQueries()!=null) {
			for (Query query : getModel().getStatementModel().getQueries().getQueries()) {
				writeTemplateResult(query, template);
			}
		}
	}

	protected void generateArtifactsByTargetTemplate(Template template) throws MinuteProjectException {
		writeTemplateResult(getModel().getConfiguration(), template);
	}
	
	protected void generateArtifactsByModel(Template template) throws MinuteProjectException {
		if (isToGenerate(getModel(), template))
			if (isToGenerate(getModel(), template))
				writeTemplateResult(getModel(), template);
	}

	protected void generateArtifactsByPackage(Template template) throws MinuteProjectException {
		List<Package> packages = getModel().getBusinessModel().getBusinessPackage().getPackages();
		for (Package pack : packages) {
			writeTemplateResult(pack, template);
		}
	}

	protected void generateArtifactsByField(Template template) throws MinuteProjectException {	
		for (Table table : getModel().getBusinessModel().getBusinessPackage().getTables()) {
			generateArtifactsByField(template, table);
		}
		for (Table table : getModel().getBusinessModel().getBusinessPackage().getTransferEntities()) {
			generateArtifactsByField (template, table);
		}		
	}

	protected void generateArtifactsByField(Template template, Table table) throws MinuteProjectException{
		table = getDecoratedTable(table);
		for (Column column : table.getColumns()) {
    		if (isToGenerate(column, template))
			   writeTemplateResult(column, template);
		}
	}

	protected void generateArtifactsByTransferEntity(Template template) throws MinuteProjectException {	
		for (Table table : getModel().getBusinessModel().getBusinessPackage().getTransferEntities()) {
			generateArtifactsByEntity (table, template);
		}
	}
	
	protected void generateArtifactsByEntity(Template template) throws MinuteProjectException {	
		for (Table table : getModel().getBusinessModel().getBusinessPackage().getTables()) {
			generateArtifactsByEntity (table, template);
		}
	}
	
	protected void generateArtifactsByEntity(Table table, Template template) throws MinuteProjectException {	
		table = getDecoratedTable(table);
		if (isToGenerate(table, template))
		   writeTemplateResult(table, template);		
	}
	
	private boolean isToGenerate (GeneratorBean bean, Template template) {
		if (template.getCheckTemplateToGenerate()!=null && template.getCheckTemplateToGenerate().equals("true")) {
			if (!template.isToGenerate(bean)) {
				return false;
			}
		} 	
		return true;
	}

	protected void generateArtifactsByService(Template template) throws MinuteProjectException {	
		for (Scope scope : getModel().getBusinessModel().getService().getScopes()) {
			if (ServiceUtils.isToGenerate(template, scope))
				writeTemplateResult(scope, template);
		}		
	}

	private void generateArtifactsByApplication(Template template) throws MinuteProjectException {	
		if (isToGenerate(getModel(), template))
			writeTemplateResult(getModel().getConfiguration(), template);
	}
	
	protected void generateArtifactsByComponent(Template template) throws MinuteProjectException {	
		writeTemplateResult(getModel().getConfiguration(), template);
	}
	
	protected void generateArtifactsByFunction(Template template) throws MinuteProjectException {	
		for (Function function : getModel().getDataModel().getDatabase().getFunctions()) {
			writeTemplateResult(function, template);
		}
	}
	
	protected void generateArtifactsByFunction(Template template, Direction direction) throws MinuteProjectException {	
		for (Function function : getModel().getDataModel().getDatabase().getFunctions()) {
			List<Direction> functionDirections = function.getDirections();
//			for (Direction dir : direction) { // dir has to be put in the correct order IN or OUT before NONE, INOUT
				for (Direction fdir : functionDirections) {
					if (direction.equals(fdir)) {
						writeTemplateResult(function.getEntity(direction), template);
						//break;
					}
				}
//			}
		}
	}
		
	protected void generateArtifactsBySddBean(Template template, Direction direction) throws MinuteProjectException {	
		StatementModel statementModel = getModel().getStatementModel();
		if (statementModel!=null) {
			for (Query query : statementModel.getQueries().getQueries()) {
				Table table = query.getEntity(direction);
				if (table.getColumns().length>0) {
					writeTemplateResult(table, template);
				}
			}
		}
	}
	
	protected void generateArtifactsBySddCompositeBean(Template template, Direction direction) throws MinuteProjectException {	
		StatementModel statementModel = getModel().getStatementModel();
		if (statementModel!=null) {
			for (Composite composite : statementModel.getComposites().getComposites()) {
				writeTemplateResult(composite.getComposite(direction), template);
			}
		}
	}
	
	protected void writeTemplateResult(GeneratorBean bean, Template template) throws MinuteProjectException {
		// enable cache
		bean.enableCache();
		//velocity bean manipulation
		String outputFilename = template.getGeneratorOutputFileNameForConfigurationBean(bean, template);
		//context
		VelocityContext context = getVelocityContext(template);
		Map<String,String> updatedAreas = TemplateUtils.getUpdatedAreas(template, bean);
		if (updatedAreas!=null) {
			if (updatedAreas.containsKey(UpdatedAreaUtils.MP_MANAGED_STOP_GENERATING))
				return; //stop generating directive
			context.put("updatedAreas", updatedAreas);
		}
		String beanName = getAbstractBeanName(bean);
		context.put(beanName, bean);
		if (bean instanceof Component) {
			Component component = (Component) bean;
			Table table = component.getTable();
			context.put("table", table);
		}		
		if (bean instanceof Function) {
			context.put("function", bean);
			context.put("table", ((Function)bean).getEntity(Direction.ANY)); //to give access to model
//			context.put("table", bean);
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
		context.put("javaUtils", javaUtils);
		context.put("routineUtils", routineUtils);
		context.put("statementUtils", statementUtils);
		context.put("triggerUtils", triggerUtils);
		context.put("queryUtils", queryUtils);
		context.put("semanticReferenceUtils", semanticReference);
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
	

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}


    public static void main(String args[]) {
        String config;
        if (args.length < 1) {
            System.exit(1);
        }
        config = args[0];
        Date startDate = new Date();
        logger.info("start time = "+new Date());
        ModelGenerator generator = new ModelGenerator(config);
        try {
            generator.generate();
        } catch (MinuteProjectException e) {
            generator.exit ("");
        }
        Date endDate = new Date();
        logger.info("time taken : "+(endDate.getTime()-startDate.getTime())/1000+ "s.");
    }

	
}
