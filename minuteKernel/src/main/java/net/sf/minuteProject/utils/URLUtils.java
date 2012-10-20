package net.sf.minuteProject.utils;

import java.util.Iterator;

import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.model.Entity;
import net.sf.minuteProject.configuration.bean.model.Field;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.parameter.LogicalLink;
import net.sf.minuteProject.utils.enrichment.EnrichmentUtils;

public class URLUtils {

	public static String ACTION_NAME = "action";
	public static String defaultRootURLBeginner = "crud.do";
	
	public String getURLSearchEntity(Model model, Table table) {
		String packageName = CommonUtils.getBusinessPackageName(model, table);
		String searchFileName = CommonUtils.getTableClassName(table)+"SearchScreen.html";
		String projectName = model.getConfiguration().getProjectname();
		return getRelativeURLApplicationRoot()+"/html/"+projectName+"/"+packageName+"/"+searchFileName;
	}
	
	public String getURLManageEntity(Model model, Table table) {
		return getDefaultRootURLBeginner()+"?table="+table.getName()+"&"+ACTION_NAME+"=manage";
		//return "crud.do?service=routingService+&inputObject="+BslaLibraryUtils.getDomainObjectImport(model, table, new Template())+"&name="+table.getName()+"&method=manage";
	}
	
	public String getURLSearchResultEntity(Model model, Table table) {
		return getDefaultRootURLBeginner()+"?table="+table.getName()+"&"+ACTION_NAME+"=search";
		//return "crud.do?service=routingService+&inputObject="+BslaLibraryUtils.getDomainObjectImport(model, table, new Template())+"&name="+table.getName()+"&method=manage";
	}

	public String getURLAddReferencedEntity(String tableName, String linkField, String entityInSession, String referenceTablePK ) {
		return getDefaultRootURLBeginner()+"?table="+tableName+"&"+ACTION_NAME+"=add&"+linkField+"=<c:out value=\"${"+entityInSession+"."+DBTemplateUtils.getJavaNameVariable(referenceTablePK)+"}\"/>";
	}	
	
	public String getEditURL (String tableName, String field) {
		return getEditURL(tableName, field, "entity");
	}
	
	public String getEditURL (String tableName, String field, String entity) {
		return getDefaultRootURLBeginner()+"?table="+tableName+"&"+ACTION_NAME+"=edit&"+DBTemplateUtils.getJavaNameVariable(field)+"=<c:out value=\"${"+entity+"."+DBTemplateUtils.getJavaNameVariable(field)+"}\"/>";	
	}	

	public String getSearchByIdURL (LogicalLink link) {
		Entity linkEntity= link.getEntity();
		return getSearchByIdURL(linkEntity.getRefname(),((Field) linkEntity.getFields().get(0)).getRefname());
	}
	
	public String getSearchByIdURL (String tableName, String field) {
		return getSearchByIdURL(tableName, field, "entity");
	}
	//deprecated
	public String getSearchByIdURL (String tableName, String field, String entity) {
		String url = getDefaultRootURLBeginner()+"?table="+tableName+"&"+ACTION_NAME+"=searchOnPkFull&"+DBTemplateUtils.getJavaNameVariable(field)+"=<c:out value=\"${"+entity+"."+DBTemplateUtils.getJavaNameVariable(field)+"}\"/>";
		return url;
	}	
	
	public String getSearchByIdURL (String tableName, String field, String primaryKey, String entity) {
		String url = getDefaultRootURLBeginner()+"?table="+tableName+"&"+ACTION_NAME+"=searchOnPkFull&"+DBTemplateUtils.getJavaNameVariable(primaryKey)+"=<c:out value=\"${"+entity+"."+DBTemplateUtils.getJavaNameVariable(field)+"}\"/>";
		return url;
	}	
	
	
	public String getSearchByIdURL (Table table) {
		String tablename = table.getName();
		StringBuffer sb = new StringBuffer();
		sb.append(getDefaultRootURLBeginner()+"?table="+tablename+"&"+ACTION_NAME+"=searchOnPkFull");
		for (int i = 0; i < table.getPrimaryKeyColumns().length ; i++) {
			String columnName = table.getPrimaryKeyColumns()[i].getName();
			String columnVariable = DBTemplateUtils.getJavaNameVariable(columnName);
			sb.append("&"+columnVariable+"=<c:out value=\"${entity."+columnVariable+"}\"/>");
		}
		return sb.toString();
	}		
	
	public String getRelativeURLApplicationRoot(Model model) {
		return "/${ctx}";
	}

	public String getRelativeURLApplicationRoot() {
		return "/${ctx}";
	}
	
	public String getRelativeURLPackageRoot(Model model, Table table) {
		return "/jsp/myProject";
	}	
	
	public String getManageEntity(Model model, Table table) {
		String packageName = CommonUtils.getBusinessPackageName(model, table);
		String manageFileName = CommonUtils.getTableClassName(table)+"ManageScreen.jsp";
		return packageName+"/"+manageFileName;
	}

	public static String getDefaultRootURLBeginner() {
		return defaultRootURLBeginner;
	}

	public static void setDefaultRootURLBeginner(String defaultRootURLBeginner1) {
		defaultRootURLBeginner = defaultRootURLBeginner1;
	}	
	
	public static boolean hasMenuLinkDirectResultAccess (Table table) {
		return EnrichmentUtils.hasMenuLinkDirectResultAccess (table);
		
	}
	
}
