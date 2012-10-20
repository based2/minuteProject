package net.sf.minuteProject.plugin.xml.schema;

import org.apache.commons.lang.StringUtils;

import net.sf.minuteProject.configuration.bean.Configuration;
import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.xml.Document;
import net.sf.minuteProject.configuration.bean.xml.Element;
import net.sf.minuteProject.plugin.xml.schema.format.DigesterFormat;

public class DigesterUtils {
	
	public static String schema2digesterConfig(Element element, Document document, Configuration configuration) {
		DigesterFormat digesterFormat = new DigesterFormat();
		Element baseElement = XmlSchemaUtils.getBaseElement (document, configuration);
		if (baseElement==null)
			return digesterFormat.schema2digesterConfig(element, configuration);
		else
			return digesterFormat.schema2digesterConfigExploded(baseElement, document, configuration);
	}
	
	public static String getDigesterFullPathConfigXml (Configuration configuration) {
		DigesterFormat digesterFormat = new DigesterFormat();
		return digesterFormat.getDigesterFullPathConfigXml(configuration);		
	}
	
	public static String getFirstSequenceElementJavaNameImport (Element element, Configuration configuration) {
		return DigesterFormat.getFirstSequenceElementJavaNameImport(element, configuration);
	}
	
	public static String getLoaderBeanName (Configuration configuration) {
		return DigesterFormat.getLoaderBeanName(configuration);
	}
	
	public static String getDigesterHolderBeanName (Configuration configuration) {
		return DigesterFormat.getDigesterHolderBeanName(configuration);
	}
	
	public static String getDigesterHolderBeanNameVariable (Configuration configuration) {
		return DigesterFormat.getDigesterHolderBeanNameVariable(configuration);
	}
	
	public static String getDigesterHolderBeanNameImport (Configuration configuration) {
		return DigesterFormat.getDigesterHolderBeanNameImport(configuration);
	}
}
