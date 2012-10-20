package net.sf.minuteProject.utils;

import java.io.File;
import java.util.List;
import java.util.Map;

import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.system.Property;
import net.sf.minuteProject.utils.io.FileUtils;

public class TemplateUtils {

	public static boolean hasTag(String tag, Template template) {
		return template.getPropertyByTag(tag)!=null;		
	}
	
	public static boolean hasTagValue(String tag, String value, Template template) {
		Property property = template.getPropertyByTag(tag);
		if (property!=null)
		   return value.equals(property.getValue());
		return false;
	}
	
//	public static boolean isUpdatable (Template template, GeneratorBean bean) {
//		if (!template.isUpdatable()) return false;
//		File file = new File(template.getGeneratorOutputFileNameForConfigurationBean(bean, template));
//		return file.exists();
//	}

	public static Map<String,String> getUpdatedAreas (Template template, GeneratorBean bean) {
		if (!template.isUpdatable()) return null;
		File file = new File(template.getGeneratorOutputFileNameForConfigurationBean(bean, template));
		return getAddedAreas (file);
	}
	
	public static Map<String,String> getAddedAreas (File file) {
		if (!file.exists()) return null;
		return FileUtils.getAddedArea(file);
	}
	
	public static String commentLine(Template template, String line) {
		return template.getBeginningCommentSnippet()+line+template.getEndingCommentSnippet();
	}
	
	public static String commentText(Template template, String text) {
		return template.getBeginningCommentSnippet()+text+template.getEndingCommentSnippet();
	}
	
	
}
