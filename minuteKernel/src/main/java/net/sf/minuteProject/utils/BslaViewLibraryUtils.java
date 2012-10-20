package net.sf.minuteProject.utils;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.Package;
import net.sf.minuteProject.configuration.bean.view.Service;
import net.sf.minuteProject.configuration.bean.view.View;

import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.model.Table;

public class BslaViewLibraryUtils extends CommonUtils{
	
	// use introspection instead
	public static final String BslaDomainObjectTemplateName = "BslaDomainObject";
	public static final String BslaIbatisDaoSqlImplTemplateName = "BslaViewIbatisDaoSqlImpl";
	public static final String BslaDaoInterfaceTemplateName = "BslaViewDaoInterface";

	
	public static String getLevelTemplateFullPath (AbstractConfiguration bean, Template template, String targetTemplateName) {
		return getPackageName(bean, template, targetTemplateName) +"."+ getTemplateClassName (bean, template, targetTemplateName);
	}

	public static String getLevelTemplateFullClassPath (AbstractConfiguration bean, Template template, String targetTemplateName) {
		return FormatUtils.getDirFromPackage(getLevelTemplateFullPath(bean, template, targetTemplateName));
	}

	public static String getDaoInterfaceName (Service service, Template template) {
		return getTemplateClassName (service, template, BslaDaoInterfaceTemplateName);
	}
	
	public static String getDaoInterfaceVariableName (Service service, Template template) {
		return FormatUtils.getJavaNameVariableFirstLetter(getTemplateClassName (service, template, BslaDaoInterfaceTemplateName));
	}	
		
	public static String getIbatisDaoSqlImplImport (Service service, Template template) {
		return getLevelTemplateFullPath(service, template, BslaIbatisDaoSqlImplTemplateName);
		//return getPackageName(model, table, template, BslaIbatisDaoSqlImplTemplateName) +"."+ getIbatisDaoSqlImplName (table, template);		
	}	
}
