package net.sf.minuteProject.utils;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.Package;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.view.View;

public class BslaLibraryUtils extends CommonUtils{
	
	// TODO change the entire class so that the dependencies can be managed elsewhere
	// TODO then clean the whole dirty stuff
	// use introspection instead
	public static final String BslaDomainObjectTemplateName = "BslaDomainObject";
	public static final String BslaDomainObjectUMLTemplateName = "DomainObjectUML";
	public static final String BslaIbatisDaoSqlImplTemplateName = "BslaIbatisDaoSqlImpl";
	public static final String BslaHiberateImplTemplateName = "BslaHibernateDaoImplUML";	
	public static final String BslaJPAImplTemplateName = "BslaJPADaoImplUML";	
	public static final String BslaDaoInterfaceTemplateName = "BslaDaoInterface";
	public static final String BslaDaoInterfaceUMLTemplateName = "BslaDaoInterfaceUML";
	public static final String ResourceBundle = "ResourceBundle";
	
//	private static String getModelLevelTemplateFullPath (Model model, Template template, String targetTemplateName) {
//		return getPackageName(model, template, targetTemplateName) +"."+ getTemplateClassName (model, template, targetTemplateName);
//	}

	public static String getModelLevelTemplateFullClassPath (Model model, Template template, String targetTemplateName) {
		return FormatUtils.getDirFromPackage(getModelLevelTemplateFullPath(model, template, targetTemplateName));
	}
	
//	private static String getPackageLevelTemplateFullPath(Model model, Package pack, Template template, String targetTemplateName) {
//		return getPackageName(model, pack, template, targetTemplateName) +"."+ getTemplateClassName (pack, template, targetTemplateName);
//	}
//
//	public static String getPackageLevelTemplateFullClassPath(Model model, Package pack, Template template, String targetTemplateName) {
//		return FormatUtils.getDirFromPackage(getPackageLevelTemplateFullPath(model, pack, template, targetTemplateName));
//	}
	
	private static String getEntityLevelTemplateFullPath(Table table, String targetTemplateName) {
		return getPackageName(table.getDatabase().getDataModel().getModel(), table, targetTemplateName) +"."+ getTemplateClassName (table, table.getDatabase().getDataModel().getModel(), targetTemplateName);
	}

	private static String getEntityTemplateFullClassPath(Model model, Table table, Template template, String targetTemplateName) {
		return FormatUtils.getDirFromPackage(getEntityLevelTemplateFullPath(model, table, template, targetTemplateName));
	}
	
	public static String getDomainObjectImport (Model model, Table table, Template template) {
		return getEntityLevelTemplateFullPath(model, table, template, BslaDomainObjectTemplateName);
	}

	public static String getDomainObjectUMLImport (Model model, Table table, Template template) {
		try {
			return getEntityLevelTemplateFullPath(model, table, template, BslaDomainObjectUMLTemplateName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String getDomainObjectImport2 (Model model, Table table, Template template) {
		if (table!=null)
			return getEntityLevelTemplateFullPath(model, table, template, BslaDomainObjectTemplateName);
		return "ERROR_TABLE_NULL while getting domain import";
	}

	public static String getDaoInterfaceName (Table table, Template template) {
		return getTemplateClassName (table, template, BslaDaoInterfaceTemplateName);
	}
	
	public static String getDaoInterfaceUMLName (Table table, Template template) {
		return getTemplateClassName (table, template, BslaDaoInterfaceUMLTemplateName);
	}	
	
	public static String getDaoInterfaceVariableName (Table table, Template template) {
		return FormatUtils.getJavaNameVariableFirstLetter(getTemplateClassName (table, template, BslaDaoInterfaceTemplateName));
	}	
	
	public static String getDaoInterfaceClassName (Table table, Template template) {
		return getTemplateClassName (table, template, BslaDaoInterfaceTemplateName);
	}	

	public static String getDaoInterfaceVariableUMLName (Table table, Template template) {
		return FormatUtils.getJavaNameVariableFirstLetter(getTemplateClassName (table, template, BslaDaoInterfaceUMLTemplateName));
		//return FormatUtils.getJavaNameVariable(getTemplateClassName (table, template, BslaDaoInterfaceUMLTemplateName));
	}

	public static String getDaoInterfaceClassUMLName (Table table, Template template) {
		return getTemplateClassName (table, template, BslaDaoInterfaceUMLTemplateName);
	}

	public static String getIbatisDaoSqlImplName (Table table, Template template) {
		return getTemplateClassName (table, template, BslaIbatisDaoSqlImplTemplateName);
	}	
	public static String getDaoInterfaceImport (Model model, Table table, Template template) {
		return getEntityLevelTemplateFullPath(model, table, template, BslaDaoInterfaceTemplateName);
	}
	
	public static String getDaoInterfaceUMLImport (Model model, Table table, Template template) {
		return getEntityLevelTemplateFullPath(model, table, template, BslaDaoInterfaceUMLTemplateName);
	}
	
	public static String getIbatisDaoSqlImplImport (Model model, Table table, Template template) {
		return getEntityLevelTemplateFullPath(model, table, template, BslaIbatisDaoSqlImplTemplateName);
	}	
	
	public static String getHibernateImplNameImport (Model model, Table table, Template template) {
		return getEntityLevelTemplateFullPath(model, table, template, BslaHiberateImplTemplateName);
	}	
	
	public static String getJPAImplNameImport (Model model, Table table, Template template) {
		return getEntityLevelTemplateFullPath(model, table, template, BslaJPAImplTemplateName);
	}	
	
	public static String getBundle(Table table) {
		return getEntityLevelTemplateFullPath (table, ResourceBundle);
	}
	
	public static String getJavaDefaultMask (Column column) {
		String type = ConvertUtils.getJavaTypeFromDBFullType(column);
		if (type!=null) {
			if (ConvertUtils.JAVA_TIMESTAMP_TYPE.equals(type))  return "timestampMask__";
			if (ConvertUtils.JAVA_STRING_TYPE.equals(type))     return "stringMask__";
			if (ConvertUtils.JAVA_BIGDECIMAL_TYPE.equals(type)) return "bigDecimalMask__";
			if (ConvertUtils.JAVA_BIGINTEGER_TYPE.equals(type)) return "bigIntegerMask__";
			if (ConvertUtils.JAVA_LONG_TYPE.equals(type))       return "longMask__";
			if (ConvertUtils.JAVA_INTEGER_TYPE.equals(type))    return "integerMask__";	
		}
		return CommonUtils.getJavaDefaultMask(column);
	}
	
	public static String getJPAPersistenceXmlLocation (Template template, String def) {
		if (template.hasProperty("use-persistence-standard-location"))
			return "classpath:META-INF/persistence.xml";
		return def;
	}
}
