package net.sf.minuteProject.plugin.fitnesse;

import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.utils.FormatUtils;

public class FitnesseUtils {

	public static String getRenderingEntityPackageName(Template template, GeneratorBean bean) {
		Table table = (Table)bean;
		return FormatUtils.firstUpperCase(table.getDatabase().getDataModel().getModel().getName())+"FitnesseWiki"+getAppender(template);
	}
	
	public static String getRenderingModelPackageName(Template template, GeneratorBean bean) {
		Model model = (Model)bean;
		return FormatUtils.firstUpperCase(model.getName())+"FitnesseWiki"+getAppender(template);
	}

	private static String getAppender(Template template) {
		return template.getPropertyValue("package-appender");
	}
}
