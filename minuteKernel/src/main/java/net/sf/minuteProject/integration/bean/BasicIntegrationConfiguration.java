package net.sf.minuteProject.integration.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;

import net.sf.minuteProject.configuration.bean.BeanCommon;
import net.sf.minuteProject.configuration.bean.BusinessModel;
import net.sf.minuteProject.configuration.bean.BusinessPackage;
import net.sf.minuteProject.configuration.bean.Condition;
import net.sf.minuteProject.configuration.bean.Configuration;
import net.sf.minuteProject.configuration.bean.DataModel;
import net.sf.minuteProject.configuration.bean.GenerationCondition;
import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.Target;
import net.sf.minuteProject.configuration.bean.Targets;
import net.sf.minuteProject.configuration.bean.WebServiceModel;
import net.sf.minuteProject.configuration.bean.connection.Driver;
import net.sf.minuteProject.configuration.bean.enrichment.Enrichment;
import net.sf.minuteProject.configuration.bean.enrichment.convention.Convention;
import net.sf.minuteProject.configuration.bean.enrichment.convention.Conventions;
import net.sf.minuteProject.configuration.bean.enrichment.convention.KernelConvention;
import net.sf.minuteProject.configuration.bean.enrichment.convention.TableDefaultPrimaryKeyConvention;
import net.sf.minuteProject.configuration.bean.enrichment.convention.TargetConvention;
import net.sf.minuteProject.configuration.bean.enrichment.convention.ViewPrimaryKeyConvention;
import net.sf.minuteProject.configuration.bean.strategy.datamodel.PrimaryKeyPolicy;
import net.sf.minuteProject.configuration.bean.strategy.datamodel.PrimaryKeyPolicyPattern;
import net.sf.minuteProject.configuration.bean.strategy.datamodel.PrimaryKeyPolicyPatternEnum;
import net.sf.minuteProject.loader.catalog.databasecatalog.node.Database;
import net.sf.minuteProject.loader.catalog.databasecatalog.node.MavenArtifact;
import net.sf.minuteProject.loader.catalog.technologycatalog.TechnologycatalogHolder;
import net.sf.minuteProject.loader.catalog.technologycatalog.node.Technology;
import net.sf.minuteProject.utils.catalog.CatalogUtils;
import net.sf.minuteProject.utils.catalog.DatabaseCatalogUtils;
import net.sf.minuteProject.utils.catalog.TechnologyCatalogUtils;
import net.sf.minuteProject.utils.io.FileUtils;
import net.sf.minuteProject.utils.parser.ParserUtils;
import net.sf.minuteProject.utils.technology.TechnologyUtils;

public class BasicIntegrationConfiguration extends BeanCommon{

	private String 
	    schema, 
		driver, 
		url, 
		username, 
		password, 
		targetTechnology, 
		database, 
		rootpackage,
		businesspackage,
		version,
		modelName,
		outputDir,
		catalogDir,
		sequencePattern,
		sequenceGlobalName,
		sequenceEntitySuffix,
		filterFile,
		filterFileType,
		virtualPrimaryKey,
		templateRootDir
		;
	private PrimaryKeyPolicyPatternEnum primaryKeyPolicy;
	private Technology choosenTechnology;
	private Database choosenDatabase;
	private List<Condition> conditions;
	private List<Convention> conventions, kernelConventions;
	private Boolean areTablesIncluded, areViewsIncluded, enableUpdatableAreaConvention, isPkConventionSet=false;
	private WebServiceModel webServiceModel;
	
	public Configuration getConfiguration () {
		Configuration configuration = new Configuration();
		configuration.setModel(getModel());
		configuration.setTargets(getTargets());
		configuration.setCatalogDir(getCatalogDir());
		configuration.setConventions(getKernelConventions());
		return configuration;
	}
	
	private Targets getTargets() {
		Targets targets = new Targets();
//		Target target = getChoosenTarget();
//		targets.addTarget(target);
		for (Target target2 : getAllRelatedTechnologies()) {
			targets.addTarget(target2);
		}
		return targets;
	}

	private List<Target> getDependentTargetTechnologies() {
		List<Target> list = new ArrayList<Target>();
		List<Technology> technologies = TechnologyCatalogUtils.getDependentTechnologies(getChoosenTechnology(), getCatalogDir());
		for (Technology technology : technologies) {
			list.add(getTarget(technology, false));
		}
		return list;
	}
	
	private List<Target> getAllRelatedTechnologies() {
		boolean isDefaultOutputToAppend = false;
		List<Target> list = new ArrayList<Target>();
		List<Technology> technologies = TechnologyCatalogUtils.getAllRelatedTechnologies(getChoosenTechnology(), getCatalogDir());
//		if (isDefaultOutputToAppend(technologies)) 
			isDefaultOutputToAppend = true;
		for (Technology technology : technologies) {
//			//add properties
//			technology.getProperties().addAll(getChoosenTechnology().getProperties());
			list.add(getTarget(technology, isDefaultOutputToAppend));
		}
		return list;
	}

	private boolean isDefaultOutputToAppend(List<Technology> technologies) {
		int i = 0;
		for (Technology technology : technologies) {
			if (technology.isGenerable())
				i++;
			if (i>1)
				return true;
		}
		return false;
	}

	private Target getTarget(Technology technology, boolean isDefaultOutputToAppend) {
		Target target = new Target();
		target.setName(technology.getName());
		target.setProperties(technology.getProperties());
		target.setFileName(technology.getTemplateConfigFileName());
		target.setDir(getTemplateSetFullPath(technology.getTemplateConfigFileName()));
		target.setTemplatedirRoot(TechnologyUtils.getTechnologyTemplateDir(technology, templateRootDir));		
		target.setOutputdirRoot(getOutputDir(technology, isDefaultOutputToAppend));
		target.setIsGenerable(technology.isGenerable());
		return target;
	}

	private String getTemplateSetFullPathAndFileName(String fileName) {
		return FileUtils.getFileFullPathFromFileInRootClassPath(getCatalogDir()+"/"+ fileName);
	}
	
	private String getTemplateSetFullPath(String fileName) {
		String canonicalFileName = getTemplateSetFullPathAndFileName(fileName);
		return StringUtils.removeEnd(canonicalFileName, fileName);
	}

	private Database getChoosenDatabase() {
		if (choosenDatabase==null)
			choosenDatabase = DatabaseCatalogUtils.getPublishedDatabase(getDatabase(), getCatalogDir());
		return choosenDatabase;		
	}

//	private Target getChoosenTarget() {
//		Technology technology = getChoosenTechnology(); 	
//		Target target = getTarget(technology);
//		return target;
//	}

	private Technology getChoosenTechnology() {
		if (choosenTechnology==null)
			choosenTechnology = TechnologyCatalogUtils.getPublishedTechnology(targetTechnology, getCatalogDir());
		return choosenTechnology;
	}

	private Model getModel() {
		Model model = new Model();
		model.setDataModel(getDataModel());
//		model.setWebServiceModel(getWebServiceModel());
		model.setName(modelName);
		model.setPackageRoot(rootpackage);
		model.setVersion(getVersion());
		model.setBusinessModel(getBusinessModel());
		return model;
	}

	private WebServiceModel getWebServiceModel() {
		return webServiceModel;
	}

	private BusinessModel getBusinessModel() {
		BusinessModel businessModel = new BusinessModel();
		businessModel.setGenerationCondition(getGenerationCondition());
		businessModel.setBusinessPackage(getBusinessPackage());
		businessModel.setEnrichment(getEnrichment());
		return businessModel;
	}

	private Enrichment getEnrichment() {
		Enrichment enrichment = new Enrichment();
		enrichment.setConventions(getConventions());
		return enrichment;
	}

	private Conventions getKernelConventions() {
		Conventions conventions = new Conventions();
		fillKernelConventions();
		conventions.setConventions(getKernelConventionList());
		return conventions;
	}
	
	private Conventions getConventions() {
		Conventions conventions = new Conventions();
		fillPkConventions();
		conventions.setConventions(getConventionList());
		return conventions;
	}

	private List<Convention> getConventionList() {
		if (conventions==null)
		   conventions = new ArrayList<Convention>();
		return conventions;
	}
	
	private List<Convention> getKernelConventionList() {
		if (kernelConventions==null)
			kernelConventions = new ArrayList<Convention>();
		return kernelConventions;
	}
	
	private void fillPkConventions() {
		if (isPkConventionSet) {
			getConventionList().add(getPkForTableConvention());
			getConventionList().add(getPkForViewConvention());
		}
	}
	
	private void fillKernelConventions() {
		if (isEnableUpdatableAreaConvention()) {
			getKernelConventionList().add(getEnableUpdatableAreaConvention());
		}
	}

	private Convention getEnableUpdatableAreaConvention() {
		Convention convention = new TargetConvention();
		convention.setType(TargetConvention.ENABLE_UPDATABLE_CODE);
		return convention;		
	}

	private Convention getPkForTableConvention() {
		Convention convention = new TableDefaultPrimaryKeyConvention ();
		convention.setDefaultValue(getVirtualPrimaryKey());
		return convention;
	}
	
	private Convention getPkForViewConvention() {
		Convention convention = new ViewPrimaryKeyConvention ();
		convention.setDefaultValue(getVirtualPrimaryKey());
		return convention;
	}

	private BusinessPackage getBusinessPackage() {
		BusinessPackage businessPackage = new BusinessPackage();
		businessPackage.setDefaultPackage(getModelName());
		businessPackage.setConditions(conditions);
		return businessPackage;
	}

	private GenerationCondition getGenerationCondition() {
		GenerationCondition generationCondition = new GenerationCondition();
		generationCondition.setExcludeTables(!getAreTablesIncluded());
		generationCondition.setExcludeViews(!getAreViewsIncluded());
		generationCondition.setDefaultType(getFilterFileType());
		List<String> filenames = ParserUtils.getList(getFilterFile());
		String conditionType = getConditionType();
		for (String filename : filenames) {
			generationCondition.addCondition(getCondition(filename, conditionType));
		}	
		return generationCondition;
	}

	private String getConditionType() {
		if (filterFileType==null)
			return GenerationCondition.FILTER_FILE_TYPE_INCLUDE;
		return (filterFileType.equals(GenerationCondition.FILTER_FILE_TYPE_EXCLUDE))? GenerationCondition.FILTER_FILE_TYPE_INCLUDE: GenerationCondition.FILTER_FILE_TYPE_EXCLUDE;
	}

	private Condition getCondition(String filename, String type) {
		Condition condition = new Condition();
		condition.setType(type);
		condition.setStartsWith(filename);
		return condition;
	}

	private DataModel getDataModel() {
		DataModel dataModel = new DataModel();
		dataModel.setBasicDataSource(getBasicDataSource());
		if (getChoosenDatabase().useSchema())
			dataModel.setSchema(schema);
		dataModel.setPrimaryKeyPolicy(getPrimaryKeyPolicyConfig());
		dataModel.setDriver(getDriverMaven());
		return dataModel;
	}
	

	private Driver getDriverMaven() {
		Driver driver = new Driver();
		MavenArtifact mavenArtifact = getChoosenDatabase().getMavenArtifact();
		driver.setArtifactId(mavenArtifact.getMvnArtifactId());
		driver.setGroupId(mavenArtifact.getMvnGroupId());
		driver.setVersion(mavenArtifact.getMvnVersion());
		return driver;
	}

	private PrimaryKeyPolicy getPrimaryKeyPolicyConfig() {
		PrimaryKeyPolicy primaryKeyPolicy = new PrimaryKeyPolicy();
		boolean isGlobalForEach = getOneForEach();
		primaryKeyPolicy.setOneForEachTable(isGlobalForEach);
		primaryKeyPolicy.setOneGlobal(!isGlobalForEach);
		primaryKeyPolicy.addPrimaryKeyPolicyPattern(getPrimaryKeyPolicyPattern());
		return primaryKeyPolicy;
	}

	private boolean getOneForEach() {
		if (primaryKeyPolicy.equals(PrimaryKeyPolicyPatternEnum.SEQUENCE)) {
			if (sequencePattern.equals("global sequence")) return false;
			else return true;
		}
		return false;
	}

	private PrimaryKeyPolicyPattern getPrimaryKeyPolicyPattern() {
		PrimaryKeyPolicyPattern primaryKeyPolicyPattern = new PrimaryKeyPolicyPattern();
		if (primaryKeyPolicy.equals(PrimaryKeyPolicyPatternEnum.SEQUENCE)) {
			primaryKeyPolicyPattern.setName(PrimaryKeyPolicyPattern.SEQUENCE);
			if (sequencePattern.equals("global sequence")) {
				primaryKeyPolicyPattern.setSequenceName(getSequenceGlobalName());		
			} else {
				primaryKeyPolicyPattern.setSuffix(getSequenceEntitySuffix());
			}
		}
		else if (primaryKeyPolicy.equals("autoincrement"))
			primaryKeyPolicyPattern.setName(PrimaryKeyPolicyPattern.AUTOINCREMENT);
		return primaryKeyPolicyPattern;
	}

	private BasicDataSource getBasicDataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setUrl(url);
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);
		basicDataSource.setDriverClassName(driver);
		return basicDataSource;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PrimaryKeyPolicyPatternEnum getPrimaryKeyPolicy() {
		return primaryKeyPolicy;
	}

	public void setPrimaryKeyPolicy(PrimaryKeyPolicyPatternEnum primaryKeyPolicy) {
		this.primaryKeyPolicy = primaryKeyPolicy;
	}

	public void setTargetTechnology(String targetTechnology) {
		this.targetTechnology = targetTechnology;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getRootpackage() {
		return rootpackage;
	}

	public void setRootpackage(String rootpackage) {
		this.rootpackage = rootpackage;
	}

	public String getBusinesspackage() {
		return businesspackage;
	}

	public void setBusinesspackage(String businesspackage) {
		this.businesspackage = businesspackage;
	}

	public String getVersion() {
		if (version==null)
			version = "1.0";
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getOutputDir(Technology technology, boolean isDefaultOutputToAppend) {
		if (isDefaultOutputToAppend)
			return outputDir+"/"+technology.getDefaultOutputdir();
		return outputDir;
	}
	public String getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	public String getCatalogDir() {
		if (catalogDir==null)
			catalogDir=CatalogUtils.getDefaultCatalogDir();
		return catalogDir;
	}

	public void setCatalogDir(String catalogDir) {
		this.catalogDir = catalogDir;
	}

	public String getSequencePattern() {
		return sequencePattern;
	}

	public void setSequencePattern(String sequencePattern) {
		this.sequencePattern = sequencePattern;
	}

	public String getSequenceGlobalName() {
		return sequenceGlobalName;
	}

	public void setSequenceGlobalName(String sequenceGlobalName) {
		this.sequenceGlobalName = sequenceGlobalName;
	}

	public String getSequenceEntitySuffix() {
		return sequenceEntitySuffix;
	}

	public void setSequenceEntitySuffix(String sequenceEntitySuffix) {
		this.sequenceEntitySuffix = sequenceEntitySuffix;
	}

	public String getFilterFile() {
		return filterFile;
	}

	public void setFilterFile(String filterFile) {
		this.filterFile = filterFile;
	}

	public String getFilterFileType() {
		return filterFileType;
	}

	public void setFilterFileType(String filterFileType) {
		this.filterFileType = filterFileType;
	}

	public void setPackageConditions(List<Condition> conditions) {
		this.conditions = conditions;		
	}

	public Boolean getAreTablesIncluded() {
		return areTablesIncluded;
	}

	public void setAreTablesIncluded(Boolean areTablesIncluded) {
		this.areTablesIncluded = areTablesIncluded;
	}

	public Boolean getAreViewsIncluded() {
		return areViewsIncluded;
	}

	public void setAreViewsIncluded(Boolean areViewsIncluded) {
		this.areViewsIncluded = areViewsIncluded;
	}

	public String getVirtualPrimaryKey() {
		return virtualPrimaryKey;
	}

	public void setVirtualPrimaryKey(String virtualPrimaryKey) {
		this.isPkConventionSet=true;
		this.virtualPrimaryKey = virtualPrimaryKey;
	}

	public Boolean getIsPkConventionSet() {
		return isPkConventionSet;
	}

	public void setIsPkConventionSet(Boolean isPkConventionSet) {
		this.isPkConventionSet = isPkConventionSet;
	}

	
	public void addConvention(Convention convention) {
		getConventionList().add(convention);		
	}

	public String getTemplateRootDir() {
		return templateRootDir;
	}

	public void setTemplateRootDir(String templateRootDir) {
		this.templateRootDir = templateRootDir;
	}

	public void setEnableUpdatableAreaConvention(boolean value) {
		this.enableUpdatableAreaConvention = value;
	}
	
	public boolean isEnableUpdatableAreaConvention() {
		return enableUpdatableAreaConvention;
	}

	public void setWebServiceModel(WebServiceModel webServiceModel) {
		this.webServiceModel = webServiceModel;
	}
}
