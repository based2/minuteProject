package net.sf.minuteProject.configuration.bean;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import net.sf.minuteProject.application.ModelGenerator;
import net.sf.minuteProject.configuration.bean.enumeration.Extension;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.system.Plugin;
import net.sf.minuteProject.configuration.bean.view.Function;
import net.sf.minuteProject.configuration.bean.view.View;
import net.sf.minuteProject.utils.CommonUtils;
import net.sf.minuteProject.utils.FormatUtils;
import net.sf.minuteProject.utils.LicenceUtils;
import net.sf.minuteProject.utils.ModelUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;

public class Template extends TemplateTarget {

	public static final String FORMAT_JAVA_CLASS = "FORMAT_JAVA_CLASS";
	public static final String FORMAT_DB_OBJECT = "FORMAT_DB_OBJECT";
	public static final String FORMAT_LOWER_CASE_JAVA_CLASS = "FORMAT_LOWER_CASE_JAVA_CLASS";
	public static final String FORMAT_UPPER_CASE_FIRST_LETTER_ONLY_JAVA_CLASS = "FORMAT_UPPER_CASE_FIRST_LETTER_ONLY_JAVA_CLASS";
	public static final String FORMAT_UPPER_CASE_FIRST_LETTER = "FORMAT_UPPER_CASE_FIRST_LETTER";
	public static final String FORMAT_LOWER_CASE_FIRST_LETTER = "FORMAT_LOWER_CASE_FIRST_LETTER";
	public static final String BLANK = "";
		
	private String templateFileName;
	private String subdir;
	private String outputsubdir; 
	private String technicalPackage;
	private String fileExtension;
	private String filePrefix; 
	private String fileSuffix; 
	private String fileNameFormat;
	private String entitySpecific;
	private String packageSpecific;
	private String modelSpecific;
	private String viewSpecific;
	private String serviceSpecific;
	private String functionSpecific;
	private String fieldSpecific;
	private String nodeAttributeNameSpecific;
	private String nodeNameValue;
	private String nodeAttributeNameValue;
	private String addModelName;
	private String addModelDirName;
	private String addTechnicalDirName;
	private String addBusinessPackageDirName;
	private String addEntityDirName;
	private String entityDirNameFormat;
	private String addScopeName;
	private String applicationSpecific;
	private String componentSpecific;
	private TemplateTarget templateTarget;
	private String fileNameBuilderPlugin, extensionNameBuilderPlugin, packageNameBuilderPlugin;
	private String fileNameBuilderMethod, extensionNameBuilderMethod, packageNameBuilderMethod;
	private String isTemplateToGenerateMethod, checkTemplateToGenerate;
	private String scopeSpecificValue;
	private String entityDirNameSuffix;
	private String entityDirNamePrefix;
	private String appendEndPackageDir;
	private String isToGenerate;
	private boolean isUpdatable = false;
	private boolean hasUpdatableNature = false;
	private boolean convertPackageToDir = true;
	
	private String chmod;
	private int numberOfGeneratedArtifacts;
	private String beginningCommentSnippet, endingCommentSnippet;
	
	private static Logger logger = Logger.getLogger(Template.class);
	
//	private enum Extension {java, xml, xsd, properties, sql};
	private Extension extension;
	
	public Template () {}
	
	public Template (TemplateTarget templateTarget) {
		this.setOutputdir(templateTarget.getOutputdir());
		this.setDir(templateTarget.getDir());
		this.setTemplateTarget(templateTarget);
		this.setRootdir(templateTarget.getRootdir());
	}

	public String getPropertyValue(String name) {
		String s = super.getPropertyValue(name);
		return (s!=null)?s:templateTarget.getTemplateTargetPropertyValue(name);
	}
	
	public String getPropertyValue(String name, String defaultValue) {
		String s = getPropertyValue(name);
		return (s!=null)?s:defaultValue;
	}
	
	public Boolean getPropertyValue(String name, boolean defaultValue) {
		String s = getPropertyValue(name);
		return (s!=null)?Boolean.valueOf(s):defaultValue;
	}
	public Boolean hasPropertyValue(String name, boolean defaultValue) {
		String s = getPropertyValue(name);
		return (s!=null)?true:defaultValue;
	}
	
	public Boolean hasPropertyValue(String name, String value) {
		String s = getPropertyValue(name);
		return (s!=null)?(s.equals(value)):false;
	}
	
	public TemplateTarget getTemplateTarget() {
		return templateTarget;
	}
	public void setTemplateTarget(TemplateTarget templateTarget) {
		this.templateTarget = templateTarget;
	}
	public String getEntitySpecific() {
		if (entitySpecific==null)
			entitySpecific="false";
		return entitySpecific;
	}
	public String getNodeAttributeNameSpecific() {
		if (nodeAttributeNameSpecific==null)
			nodeAttributeNameSpecific="false";
		return nodeAttributeNameSpecific;
	}
	public void setNodeAttributeNameSpecific(String nodeAttributeNameSpecific) {
		this.nodeAttributeNameSpecific = nodeAttributeNameSpecific;
	}
	public String getNodeAttributeNameValue() {
		return nodeAttributeNameValue;
	}
	public void setNodeAttributeNameValue(String nodeAttributeNameValue) {
		this.nodeAttributeNameValue = nodeAttributeNameValue;
	}
	public String getNodeNameValue() {
		return nodeNameValue;
	}
	public void setNodeNameValue(String nodeNameValue) {
		this.nodeNameValue = nodeNameValue;
	}
	public void setEntitySpecific(String entitySpecific) {
		this.entitySpecific = entitySpecific;
	}
	public String getFileExtension(GeneratorBean bean) {
		String pluginResult = getPluginExtension(bean);
		if (pluginResult!=null)
			return pluginResult;
		return getFileExtension();
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
		extension = Extension.fromValue(fileExtension);
	}
	public String getFilePrefix() {
		if (filePrefix==null) return BLANK;
		return filePrefix;
	}
	public void setFilePrefix(String filePrefix) {
		this.filePrefix = filePrefix;
	}
	public String getFileSuffix() {
		if (fileSuffix==null) return BLANK;
		return fileSuffix;
	}
	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}
	public String getModelSpecific() {
		if (modelSpecific==null)
			modelSpecific="false";
		return modelSpecific;
	}
	public void setModelSpecific(String modelSpecific) {
		this.modelSpecific = modelSpecific;
	}
	public String getOutputsubdir() {
		return outputsubdir;
	}
	public void setOutputsubdir(String outputsubdir) {
		this.outputsubdir = outputsubdir;
	}
	public String getPackageSpecific() {
		if (packageSpecific==null)
			return "false";
		return packageSpecific;
	}
	public void setPackageSpecific(String packageSpecific) {
		this.packageSpecific = packageSpecific;
	}
	public String getSubdir() {
		return subdir;
	}
	public void setSubdir(String subdir) {
		this.subdir = subdir;
	}
	public String getTechnicalPackage() {
		return technicalPackage;
	}
	public void setTechnicalPackage(String technicalPackage) {
		this.technicalPackage = technicalPackage;
	}
	public String getTemplateFileName() {
		return templateFileName;
	}
	public void setTemplateFileName(String templateFileName) {
		this.templateFileName = templateFileName;
	}
	public String getOutputFileName (GeneratorBean bean) {
		return getOutputFileMain(bean)+"."+getFileExtension(bean);
	}	

	public String getAppendEndPackageDir() {
		return appendEndPackageDir;
	}

	public void setAppendEndPackageDir(String appendEndPackageDir) {
		this.appendEndPackageDir = appendEndPackageDir;
	}

	/**
	 * Returns the name of the file without the extention
	 * @param input
	 * @return
	 */
	public String getOutputFileMain (GeneratorBean bean) {
		return getFormatFileName(getOutputFileNameMain(bean));
	}
	
	public String getFormatFileName (String fileName) {
		if (fileNameFormat!=null && !fileNameFormat.equals("")) {
			if (fileNameFormat.equals(FORMAT_UPPER_CASE_FIRST_LETTER_ONLY_JAVA_CLASS))
				return FormatUtils.firstUpperCaseOnly(fileName);
			if (fileNameFormat.equals(FORMAT_UPPER_CASE_FIRST_LETTER))
				return FormatUtils.firstUpperCase(fileName);	
			if (fileNameFormat.equals(FORMAT_LOWER_CASE_FIRST_LETTER))
				return FormatUtils.firstLowerCase(fileName);			
		}
		return fileName;
	}
	
	public String getOutputFileNameMain (GeneratorBean bean) {
		if (bean==null) return "ERROR NO FILE NAME";
		String pluginResult = getPluginFileMain(bean);
		if (pluginResult!=null)
			return pluginResult;
		return getNonPluginFileMain(bean.getGeneratedBeanName());
	}
	
	public String getNonPluginFileMain (String input) {
		if ((addModelName!=null && addModelName.equals("false")) ||
			(addScopeName!=null && addScopeName.equals("false")) )
			return getFilePrefix()+getFileSuffix();
		return getFilePrefix()+input+getFileSuffix();
	}
	
	private String getPluginExtension (GeneratorBean bean) {
		return getPluginName(bean, extensionNameBuilderPlugin, extensionNameBuilderMethod);
	}
	
	
	private String getPluginFileMain (GeneratorBean bean) {
		return getPluginName(bean, fileNameBuilderPlugin, fileNameBuilderMethod);
	}
	
	public String getPluginPackageMain (GeneratorBean bean) {
		return getPluginName(bean, packageNameBuilderPlugin, packageNameBuilderMethod);
	}	
	
	public String getPluginName (GeneratorBean bean, String builderPlugin, String builderMethod) {
		if (builderPlugin!=null && builderMethod!=null) {
			// lookup builder in the plugin
			Plugin plugin = getFileBuilderPlugin(builderPlugin);
			if (plugin!=null) {
				String result = getPluginBuildFileName (plugin, builderMethod, bean);
				if (result != null)
					return result;
			}
		}	
		return null;
	}	
	
	private Plugin getFileBuilderPlugin (String fileNameBuilderPlugin) {
		List<Plugin> plugins = this.getTemplateTarget().getTarget().getPlugins();
		for (Plugin plugin : plugins) {
			if (plugin.getName().equals(fileNameBuilderPlugin))
				return plugin;
		}		
		return null;
	}
	
	public boolean isToGenerate(GeneratorBean bean) {
		if (!isToGenerate()) return false;
		boolean isToGenerate = 
			getPluginIsToGenerate(
				getFileBuilderPlugin(getIsTemplateToGenerateMethodPluginName()), 
				getIsTemplateToGenerateMethodFunctionName(),
				bean);
		if (!isToGenerate)
			return false;
		return true;
	}
	
	public boolean isToGenerate () {
		if (isToGenerate!=null && isToGenerate.equals("false")) return false;
		if (getCheckTemplateToGenerate()!=null && getCheckTemplateToGenerate().equals("false")) return false;
		else return true;
	}
	
	private String getIsTemplateToGenerateMethodPluginName () {
		return StringUtils.substringBefore(getIsTemplateToGenerateMethod(), ".");
	}
	
	private String getIsTemplateToGenerateMethodFunctionName () {
		return StringUtils.substringAfterLast(getIsTemplateToGenerateMethod(), ".");
	}
	
	private boolean getPluginIsToGenerate (Plugin plugin, String function, GeneratorBean bean) {
		if (plugin==null || function==null)
			return false;
		if (plugin.equals(""))
			return true;
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		try {
			Class clazz = cl.loadClass(plugin.getClassName());
			Object pluginObject = clazz.newInstance();
			Class arg [] = new Class [2];
			arg [0] = Template.class;
			arg [1] = GeneratorBean.class;
			Object obj [] = new Object [2];
			obj [0] = this;
			obj [1] = bean;
			Method method = clazz.getMethod(function, arg);
			Boolean result = (Boolean) method.invoke(pluginObject, obj);
			return result;
		} catch (ClassNotFoundException e) {
			logger.info("cannot find plugin "+plugin.getName()+" via class "+plugin.getClassName());
		} catch (InstantiationException e) {
			logger.info("cannot instantiate plugin "+plugin.getName()+" via class "+plugin.getClassName());
		} catch (IllegalAccessException e) {
			logger.info("cannot access plugin "+plugin.getName()+" via class "+plugin.getClassName());
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			logger.info("cannot access plugin "+plugin.getName()+" via method "+function+ " security exception "+e.getMessage());
//			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			logger.info("cannot access plugin "+plugin.getName()+" via method "+function+ " NoSuchMethodException exception "+e.getMessage());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			logger.info("cannot access plugin "+plugin.getName()+" via method "+function+ " IllegalArgumentException exception "+e.getMessage());
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			logger.info("cannot access plugin "+plugin.getName()+" via method "+function+ " InvocationTargetException exception "+e.getMessage());
		}
		return false;
	}

	private String getPluginBuildFileName (Plugin plugin, String fileNameBuilderMethod, GeneratorBean bean) {
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		try {
			Class clazz = cl.loadClass(plugin.getClassName());
			Object pluginObject = clazz.newInstance();
			Class arg [] = new Class [2];
			arg [0] = Template.class;
			arg [1] = GeneratorBean.class;
			Object obj [] = new Object [2];
			obj [0] = this;
			obj [1] = bean;
			Method method = clazz.getMethod(fileNameBuilderMethod, arg);
			String result = (String) method.invoke(pluginObject, obj);
			return result;
		} catch (ClassNotFoundException e) {
			logger.info("cannot find plugin "+plugin.getName()+" via class "+plugin.getClassName());
			e.printStackTrace();
		} catch (InstantiationException e) {
			logger.info("cannot instantiate plugin "+plugin.getName()+" via class "+plugin.getClassName());
		} catch (IllegalAccessException e) {
			logger.info("cannot access plugin "+plugin.getName()+" via class "+plugin.getClassName());
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			logger.info("cannot access plugin method "+plugin.getName()+" via method "+fileNameBuilderMethod+ " security exception "+e.getMessage());
//			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			logger.info("cannot access plugin method "+plugin.getName()+" via method "+fileNameBuilderMethod+ " NoSuchMethodException exception "+e.getMessage());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			logger.info("cannot access plugin method "+plugin.getName()+" via method "+fileNameBuilderMethod+ " IllegalArgumentException exception "+e.getMessage());
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			logger.info("cannot access plugin method "+plugin.getName()+" via method "+fileNameBuilderMethod+ " InvocationTargetException exception "+e.getMessage());
		}
		return null;
	}
	
	public void setPackageRoot(String packageRoot) {
		super.setPackageRoot(packageRoot);
	}
	
//	private Model getModel (Template template) {
//		return ((Configuration)(template.getTemplateTarget().getTarget().getAbstractConfigurationRoot())).getModel();
//	}
	
    public String getGeneratorOutputFileNameForView (View view, Template template) {
    	StringBuffer sb = new StringBuffer(template.getOutputdir());
    	sb.append("//"+ModelUtils.getTechnicalPackage(view, template));
		//String outputFileDir = FormatUtils.getDirFromPackage(sb.toString());
    	String outputFileDir = sb.toString();
		new File (outputFileDir.toString()).mkdirs();
		String TemplateFileName = CommonUtils.getFileName(template,view);
		String outputFilename = outputFileDir+"//"+TemplateFileName;
		return outputFilename;
	}

    public String getGeneratorOutputFileNameForFunction (Function function, Template template) {
    	StringBuffer sb = new StringBuffer(template.getOutputdir());
    	sb.append("//"+ModelUtils.getTechnicalPackage(function, template));
		String outputFileDir = FormatUtils.getDirFromPackage(sb.toString());
		new File (outputFileDir.toString()).mkdirs();
		String TemplateFileName = CommonUtils.getFileName(template,function);
		String outputFilename = outputFileDir+"//"+TemplateFileName;
		return outputFilename;
	}
    
    public String getGeneratorOutputFileNameForConfigurationBean (GeneratorBean bean, Template template) {
    	
    	String outputFileDir = CommonUtils.getPhysicalDirectory(bean, template);
		new File (outputFileDir.toString()).mkdirs();
		String TemplateFileName = CommonUtils.getFileName(template,bean);
		String outputFilename = outputFileDir+"//"+TemplateFileName;
		return outputFilename;    	
    }

//	public String getPhysicalDirectory(GeneratorBean bean, Template template) {
//		StringBuffer sb = new StringBuffer(template.getOutputdir());
//    	String sb1 = new String(CommonUtils.getPackageName(bean, template));
//    	String dir = FormatUtils.getDirFromPackage(sb1, convertPackageToDir);
//    	sb.append("//");
//    	sb.append(dir);
//    	if (addEntityDirName!=null && addEntityDirName.equals("true")) {
//    		sb.append("//");
//    		sb.append(getEntityDirName(bean.getGeneratedBeanName()));
//    	}
//    	if (appendEndPackageDir!=null) {
//    		sb.append("//"+appendEndPackageDir);
//    	}
//    	String outputFileDir = sb.toString();
//		return outputFileDir;
//	}
    
    public String getEntityDirName (String input) {
    	StringBuffer output = new StringBuffer();
		if (entityDirNamePrefix!=null && !entityDirNamePrefix.equals("")) {
			output.append(entityDirNamePrefix);
		}
    	output.append(getEntityDirNameFormat(input));
		if (entityDirNameSuffix!=null && !entityDirNameSuffix.equals("")) {
			output.append(entityDirNameSuffix);
		}
		return output.toString();
    }
    
    private String getEntityDirNameFormat (String input) {
		if (entityDirNameFormat!=null && !entityDirNameFormat.equals("")) {
			if (entityDirNameFormat.equals(FORMAT_UPPER_CASE_FIRST_LETTER_ONLY_JAVA_CLASS))
				return FormatUtils.firstUpperCaseOnly(input);
		}
		return input;
    }
    
    
    public String getAddModelName() {
		return addModelName;
	}
	public void setAddModelName(String addModelName) {
		this.addModelName = addModelName;
	}

	public String getFunctionSpecific() {
		return functionSpecific;
	}

	public void setFunctionSpecific(String functionSpecific) {
		this.functionSpecific = functionSpecific;
	}

	public String getServiceSpecific() {
		if (serviceSpecific==null)
			serviceSpecific="false";
		return serviceSpecific;
	}

	public void setServiceSpecific(String serviceSpecific) {
		this.serviceSpecific = serviceSpecific;
	}

	public String getViewSpecific() {
		if (viewSpecific==null)
			viewSpecific="false";
		return viewSpecific;
	}

	public void setViewSpecific(String viewSpecific) {
		this.viewSpecific = viewSpecific;
	}

	public String getApplicationSpecific() {
		if (applicationSpecific==null)
			applicationSpecific="false";
		return applicationSpecific;
	}

	public void setApplicationSpecific(String applicationSpecific) {
		this.applicationSpecific = applicationSpecific;
	}

	public String getFileNameBuilderMethod() {
		return fileNameBuilderMethod;
	}

	public void setFileNameBuilderMethod(String fileNameBuilderMethod) {
		this.fileNameBuilderMethod = fileNameBuilderMethod;
	}

	public String getFileNameBuilderPlugin() {
		return fileNameBuilderPlugin;
	}

	public void setFileNameBuilderPlugin(String fileNameBuilderPlugin) {
		this.fileNameBuilderPlugin = fileNameBuilderPlugin;
	}

	public String getComponentSpecific() {
		if (componentSpecific==null)
			return "false";
		return componentSpecific;
	}

	public void setComponentSpecific(String componentSpecific) {
		this.componentSpecific = componentSpecific;
	}

	public String getIsTemplateToGenerateMethod() {
		return isTemplateToGenerateMethod;
	}

	public void setIsTemplateToGenerateMethod(String isTemplateToGenerateMethod) {
		this.isTemplateToGenerateMethod = isTemplateToGenerateMethod;
	}

	public String getIsToGenerate() {
		return isToGenerate;
	}

	public void setIsToGenerate(String isToGenerate) {
		this.isToGenerate = isToGenerate;
	}

	public String getScopeSpecificValue() {
		return scopeSpecificValue;
	}

	public void setScopeSpecificValue(String scopeSpecificValue) {
		this.scopeSpecificValue = scopeSpecificValue;
	}
	
    public String getAddModelDirName() {
		return addModelDirName;
	}

	public void setAddModelDirName(String addModelDirName) {
		this.addModelDirName = addModelDirName;
	}

	public String getAddTechnicalDirName() {
		return addTechnicalDirName;
	}

	public void setAddTechnicalDirName(String addTechnicalDirName) {
		this.addTechnicalDirName = addTechnicalDirName;
	}
	
	public String getAddEntityDirName() {
		return addEntityDirName;
	}

	public void setAddEntityDirName(String addEntityDirName) {
		this.addEntityDirName = addEntityDirName;
	}

	public String getAddScopeName() {
		return addScopeName;
	}

	public void setAddScopeName(String addScopeName) {
		this.addScopeName = addScopeName;
	}

	public String getAddBusinessPackageDirName() {
		return addBusinessPackageDirName;
	}

	public void setAddBusinessPackageDirName(String addBusinessPackageDirName) {
		this.addBusinessPackageDirName = addBusinessPackageDirName;
	}

	public String getFileNameFormat() {
		return fileNameFormat;
	}

	public void setFileNameFormat(String fileNameFormat) {
		this.fileNameFormat = fileNameFormat;
	}

	public String getEntityDirNameFormat() {
		return entityDirNameFormat;
	}

	public void setEntityDirNameFormat(String entityDirNameFormat) {
		this.entityDirNameFormat = entityDirNameFormat;
	}

	public String getEntityDirNamePrefix() {
		return entityDirNamePrefix;
	}

	public void setEntityDirNamePrefix(String entityDirNamePrefix) {
		this.entityDirNamePrefix = entityDirNamePrefix;
	}

	public String getEntityDirNameSuffix() {
		return entityDirNameSuffix;
	}

	public void setEntityDirNameSuffix(String entityDirNameSuffix) {
		this.entityDirNameSuffix = entityDirNameSuffix;
	}

	public String getCheckTemplateToGenerate() {
		return checkTemplateToGenerate;
	}

	public void setCheckTemplateToGenerate(String checkTemplateToGenerate) {
		this.checkTemplateToGenerate = checkTemplateToGenerate;
	}

	public String getFieldSpecific() {
		if (fieldSpecific==null)
			return "false";
		return fieldSpecific;
	}

	public void setFieldSpecific(String fieldSpecific) {
		this.fieldSpecific = fieldSpecific;
	}
	
	public String getPackageRoot() {
		if (packageRoot==null && getTemplateTarget().getTarget()!=null){
			Configuration configuration = (Configuration) getTemplateTarget().getTarget().getAbstractConfigurationRoot();
			setPackageRoot(configuration.getModel().getPackageRoot());
		} //else
			//setPackageRoot("");
		return packageRoot;
	}
	
	public Target getTarget() {
		return getTemplateTarget().getTarget();
	}
	
	public String getOutputdir() {
		return super.getOutputdir();
	}

	public String getChmod() {
		return chmod;
	}

	public void setChmod(String chmod) {
		this.chmod = chmod;
	}

	public String getPackageNameBuilderPlugin() {
		return packageNameBuilderPlugin;
	}

	public void setPackageNameBuilderPlugin(String packageNameBuilderPlugin) {
		this.packageNameBuilderPlugin = packageNameBuilderPlugin;
	}

	public String getPackageNameBuilderMethod() {
		return packageNameBuilderMethod;
	}

	public void setPackageNameBuilderMethod(String packageNameBuilderMethod) {
		this.packageNameBuilderMethod = packageNameBuilderMethod;
	}

	public String getExtensionNameBuilderPlugin() {
		return extensionNameBuilderPlugin;
	}

	public void setExtensionNameBuilderPlugin(String extensionNameBuilderPlugin) {
		this.extensionNameBuilderPlugin = extensionNameBuilderPlugin;
	}

	public String getExtensionNameBuilderMethod() {
		return extensionNameBuilderMethod;
	}

	public void setExtensionNameBuilderMethod(String extensionNameBuilderMethod) {
		this.extensionNameBuilderMethod = extensionNameBuilderMethod;
	}

	public void increaseNumberOfGeneratedArtifacts() {
		numberOfGeneratedArtifacts++;
	}

	public int getNumberOfGeneratedArtifacts() {
		return numberOfGeneratedArtifacts;
	}

	public boolean isUpdatable() {
		return isUpdatable && hasUpdatableNature;
	}

	public void setUpdatable (boolean isUpdatable) {
		this.isUpdatable = isUpdatable;
	}

	public boolean hasUpdatableNature() {
		return hasUpdatableNature;
	}

	public void setHasUpdatableNature(boolean hasUpdatableNature) {
		this.hasUpdatableNature = hasUpdatableNature;
	}

	public String getBeginningCommentSnippet() {
		if (beginningCommentSnippet==null)
			beginningCommentSnippet=getBeginningCommentSnippetFromExtension();
		return beginningCommentSnippet;
	}

	public String getEndingCommentSnippet() {
		if (endingCommentSnippet==null)
			endingCommentSnippet=getEndingCommentSnippetFromExtension();
		return endingCommentSnippet;
	}
	
	public boolean isConvertPackageToDir() {
		return convertPackageToDir;
	}

	public void setConvertPackageToDir(boolean convertPackageToDir) {
		this.convertPackageToDir = convertPackageToDir;
	}

	private String getBeginningCommentSnippetFromExtension() {
		return extension.lineCommentBeginning();
//		if (Extension.java.toString().equals(fileExtension)) return "//";
//		if (Extension.xml.toString().equals(fileExtension)) return "<!--";
//		if (Extension.sql.toString().equals(fileExtension)) return "--";
//		if (Extension.properties.toString().equals(fileExtension)) return "#";
//		return "//missing extension";
	}
	
	private String getEndingCommentSnippetFromExtension() {
		return extension.lineCommentEnding();
//		if (Extension.java.toString().equals(fileExtension)) return "";
//		if (Extension.xml.toString().equals(fileExtension)) return "-->";
//		if (Extension.sql.toString().equals(fileExtension)) return "";
//		if (Extension.properties.toString().equals(fileExtension)) return "";
//		return "";
	}
	
//	private String getBeginningTextCommentSnippetFromExtension() {
//		if (Extension.java.toString().equals(fileExtension)) return "/**";
//		if (Extension.xml.toString().equals(fileExtension)) return "<!--";
//		if (Extension.sql.toString().equals(fileExtension)) return "--";
//		if (Extension.properties.toString().equals(fileExtension)) return "#";
//		return "//missing extension";
//	}
//	
//	private String getEndingTextCommentSnippetFromExtension() {
//		if (Extension.java.toString().equals(fileExtension)) return "*/";
//		if (Extension.xml.toString().equals(fileExtension)) return "-->";
//		if (Extension.sql.toString().equals(fileExtension)) return "--";
//		if (Extension.properties.toString().equals(fileExtension)) return "#";
//		return "";
//	}

	public String getLicence() {
		if (extension==null)// || extension.equals(Extension.xml))
			return null;
		return extension.getLicence()+getTemplateSignature();
	}

	public boolean isLicenceAtBeginning () {
		if (extension==null)
			return false;
		return extension.licenceAtBeginning();
	}
	private String getTemplateSignature() {
		return getComment(getTemplateSignatureTxt());
	}
	
	public String getComment(String text) {
		return extension.format(text);
	}
	
	public String getTextComment(String text) {
		if (text==null)
			text = "";
		return extension.format(" \n"+text+"\n");
	}

	private String getTemplateSignatureTxt() {
		return 
			" template reference : \n"+
			" - name      : "+getName()+"\n"+
			" - file name : "+getTemplateFileName()+"\n"+
			" - time      : "+FormatUtils.renderCurrentTime()+"\n"+
			"";
	}
		
}
