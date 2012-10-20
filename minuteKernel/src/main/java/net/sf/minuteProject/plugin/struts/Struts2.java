package net.sf.minuteProject.plugin.struts;

import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.utils.CommonUtils;
import net.sf.minuteProject.utils.FormatUtils;

public class Struts2 {

	public static final String STRUTS2_ACTION_TEMPLATE_NAME = "Struts2Action";
	public static final String STRUTS2_MANAGE_SCREEN_TEMPLATE_NAME = "Struts2ManageScreen";
	public static final String STRUTS2_URL_EXTENTION = ".action";
	
	
	public static String getActionSubmit (Table table) {
		return "submit"+getActionSubmitManageScreen(table);
	}

	public static String getActionDisplayManageScreen (Table table) {
		return "display"+getActionSubmitManageScreen(table);
	}
	
	public static String getActionEdit (Table table) {
		return "edit"+getActionSubmitManageScreen(table);
	}
	
	public static String getActionSearch (Table table) {
		return "search"+getActionSubmitManageScreen(table);
	}
	
	public static String getValidationFileName (Template template, GeneratorBean bean) {
		Table table = (Table)bean;
		String innerFileName = getActionSubmit(table);
		return getActionFileName(table)+"-"+template.getNonPluginFileMain(innerFileName);
	}

	private static String getActionFileName (Table table) {
		return CommonUtils.getTemplateArtifactMainName(table, STRUTS2_ACTION_TEMPLATE_NAME);
	}
	
	private static String getActionSubmitManageScreen (Table table) {
		return CommonUtils.getTemplateArtifactMainName(table, STRUTS2_MANAGE_SCREEN_TEMPLATE_NAME);
	}
	
	/**
	 * technical indirection level
	 * @param model
	 * @param table
	 * @return
	 */
	public String getURLActionEntity(Model model, Table table, String action) {
		String packageName = CommonUtils.getBusinessPackageName(model, table);
		String searchFileName = action+STRUTS2_URL_EXTENTION;
		return getRelativeURLApplicationRoot()+"/"+packageName+"/"+searchFileName;
	}
	
	public String getURLSearchEntity(Model model, Table table) {
		return getURLActionEntity(model, table, getActionSearch(table));
	}
	
	public String getURLManageEntity(Model model, Table table) {
		return getURLActionEntity(model, table, getActionDisplayManageScreen(table));
	}
	
	public String getURLEditEntity(Model model, Table table) {
		return getURLActionEntity(model, table, getActionEdit(table));
	}
	
	public String getURLSubmitEntity(Model model, Table table) {
		return getURLActionEntity(model, table, getActionSubmit(table));
	}
	
	public String getRelativeURLApplicationRoot() {
		return "${ctx}";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
