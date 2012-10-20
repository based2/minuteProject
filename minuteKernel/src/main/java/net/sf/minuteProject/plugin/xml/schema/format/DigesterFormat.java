package net.sf.minuteProject.plugin.xml.schema.format;

import java.util.List;

import org.dom4j.Attribute;

import net.sf.minuteProject.application.XmlGenerator;
import net.sf.minuteProject.configuration.bean.Configuration;
import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.xml.Document;
import net.sf.minuteProject.configuration.bean.xml.Element;
import net.sf.minuteProject.plugin.xml.schema.XmlSchemaUtils;
import net.sf.minuteProject.utils.CommonUtils;
import net.sf.minuteProject.utils.FormatUtils;

public class DigesterFormat {

	public static final String TEMPLATE_NODE_XML = "DigesterConfigBean";
	public static final String TEMPLATE_DIGESTER_CONFIG_XML = "DigesterConfigXML";
	public static final String TEMPLATE_DIGESTER_HOLDER_BEAN = "DigesterHolderBean";
	public static final String TEMPLATE_LOADER_BEAN = "DigesterConfigBean";
	
	public int indent = 0;
	public StringBuffer stringBuffer = new StringBuffer();

	public String schema2digesterConfigExploded(Element element, Document document, Configuration configuration) {
		String name = element.getNameAttributeValue();
		return schema2digesterConfigExploded(element, name, document, configuration);		
	}
	
	public String schema2digesterConfigExploded(Element element, String refName, Document document, Configuration configuration) {
		indent++;
		String padding = "";

		//schema2digesterConfigExploded(element, document, configuration);

		if (element.getSequenceElement()!=null) {
			boolean isComplexTyp = XmlSchemaUtils.isComplexType(element);
			if (isComplexTyp) {
				padding = XmlSchemaUtils.indent(indent);
				stringBuffer.append(formatDigester(element, refName, configuration, padding));
			}
			for (Element ele : element.getSequenceElement()) {
				//padding = "";
				boolean isComplexType = XmlSchemaUtils.isComplexType(ele.getTypeAttributeValue());
//				String fulltype = ele.getTypeAttributeValue();
				String type = XmlSchemaUtils.getFlatType(ele.getTypeAttributeValue());
				String name = ele.getNameAttributeValue();
				Element complex =(isComplexType)? document.getFirstComplexTypeElementByNameAttribute(type):null;
				if (complex!=null) {
//					if (isComplexType) {
//						padding = XmlSchemaUtils.indent(indent);
//						stringBuffer.append(formatDigester(complex, configuration, padding));
//					}
					schema2digesterConfigExploded(complex, name, document, configuration);
//					if (isComplexType) {
//						stringBuffer.append(formatDigesterEnd(padding));
//					}
				}
			}
			if (isComplexTyp) {
				stringBuffer.append(formatDigesterEnd(padding));
			}
		}
		indent--;
		return stringBuffer.toString();
	}
	
	public String schema2digesterConfig(Element element, Configuration configuration) {
		indent++;
		for (Element ele : element.getNodes()) {
			String padding = "";
			boolean isComplexType = XmlSchemaUtils.isComplexType(ele);
			if (isComplexType) {
				padding = XmlSchemaUtils.indent(indent);
				stringBuffer.append(formatDigester(ele, configuration, padding));
			}
			schema2digesterConfig(ele, configuration);
			if (isComplexType) {
				stringBuffer.append(formatDigesterEnd(padding));
			}
		}
		indent--;
		return stringBuffer.toString();
	}
	
	private String getType (Element element) {
		return FormatUtils.getJavaName(element.getNameAttributeValue());
	}
	private static String getNodeFullClassPathJavaName(Element element, Configuration configuration) {
		Template template = CommonUtils.getTemplate(configuration, TEMPLATE_NODE_XML);
		String s = CommonUtils.getPackageName (element, template);
		String x = FormatUtils.getJavaName(element.getNameAttributeValue());
		return s+"."+x;
	}
	/**
	 * $padding<pattern value="$name">
$padding <object-create-rule classname="net.sf.minuteProject.configuration.bean.presentation.$type"/>
	$padding<set-next-rule methodname="set$type" paramtype="net.sf.minuteProject.configuration.bean.presentation.$type"/>
	$padding<set-properties-rule/>
	 * @param padding
	 * @param name
	 * @param type
	 * @return
	 */
	private String formatDigester(Element element, Configuration configuration, String padding) {
		String name = element.getNameAttributeValue();
		return formatDigester(element, name, configuration, padding);
	}
	private String formatDigester(Element element, String refName, Configuration configuration, String padding) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(formatDigesterObject(element, refName, configuration, padding));
		stringBuffer.append(formatDigesterObjectProperty(element, configuration, padding));
		stringBuffer.append(formatDigesterObjectAttribute(element, configuration, padding));
		return stringBuffer.toString();
	}
	
	private String formatDigesterObject(Element element, String refName, Configuration configuration, String padding) {
		StringBuffer stringBuffer = new StringBuffer();
		String type = FormatUtils.getJavaName(refName);
		String fullType = getNodeFullClassPathJavaName(element, configuration);//ele.getType();
//		String name = element.getNameAttributeValue();
		stringBuffer.append(appendLine(padding, "<pattern value=\""+refName+"\">"));
		stringBuffer.append(appendLine(padding, " <object-create-rule classname=\""+fullType+"\"/>"));
		String prefix;
		if (XmlSchemaUtils.isCollection(element))
			prefix="add";
		else
			prefix="set";
		stringBuffer.append(appendLine(padding, " <set-next-rule methodname=\""+prefix+type+"\""+
				" paramtype=\""+fullType+"\"/>"));
		//stringBuffer.append(appendLine(padding," <set-properties-rule/>"));
		return stringBuffer.toString();
	}
	
	private String formatDigesterObject(Element element, Configuration configuration, String padding) {
		String name = element.getNameAttributeValue();
		return formatDigesterObject(element, name, configuration, padding);
	}
	
	private String formatDigesterObjectProperty(Element element, Configuration configuration, String padding) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(appendLine(padding," <set-properties-rule>"));
		List<Element> elements = element.getSequenceElement();
		if (elements!=null) {
			for (Element element2 : elements) {
				if (!element2.isElementComplexType())
					stringBuffer.append(appendLine(padding,
//							" <alias attr-name="package-root" prop-name="packageRoot" />" 
							"  <alias prop-name=\""+FormatUtils.getJavaNameVariable(element2.getNameAttributeValue())+"\""
							+" attr-name=\""+element2.getNameAttributeValue()+"\"/>"));			

//							" <bean-property-setter-rule  propertyname=\""+FormatUtils.getJavaNameVariable(element2.getNameAttributeValue())+"\""
//							+" pattern=\""+element2.getNameAttributeValue()+"\"/>"));			
			}
		}
		if (XmlSchemaUtils.isPackageNameAvailable(configuration)) {
			stringBuffer.append(appendLine(padding,"  <alias prop-name=\"packageName\" attr-name=\"package-name\"/>"));
		}
		stringBuffer.append(appendLine(padding," </set-properties-rule>"));
		return stringBuffer.toString();
	}
	
	private String formatDigesterObjectAttribute(Element element, Configuration configuration, String padding) {
		StringBuffer stringBuffer = new StringBuffer();
		List<Element> atts = element.getSequenceAttribute();
		for (Element att : atts) {
			stringBuffer.append(appendLine(padding," <bean-property-setter-rule pattern=\""+FormatUtils.getJavaNameVariable(att.getNameAttributeValue())+"\"/>"));			
		}
		return stringBuffer.toString();
	}
	
	private String formatDigesterEnd(String padding) {
		return appendLine(padding, "</pattern>");
	}
	
	private String appendLine(String padding, String content) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(padding);
		stringBuffer.append(content);
		stringBuffer.append("\n");
		return stringBuffer.toString();
	}

	public static String getJavaName (String name) {
		return FormatUtils.getJavaName(name);
	}
	
	public String getDigesterFullPathConfigXml (Configuration configuration) {
		Template template = CommonUtils.getTemplate(configuration, TEMPLATE_DIGESTER_CONFIG_XML);
		return CommonUtils.getArtifactRelativePathDirAndFullName(template, configuration.getModel());
	}
	
	public static String getFirstSequenceElementJavaNameImport (Element element, Configuration configuration) {
		return getNodeFullClassPathJavaName(element, configuration);
	}
	
	public static String getDigesterHolderBeanNameImport (Configuration configuration) {
//		Template template = CommonUtils.getTemplate(configuration, TEMPLATE_DIGESTER_CONFIG_XML);
		return CommonUtils.getArtifactFullClasspath(configuration.getModel(), TEMPLATE_DIGESTER_HOLDER_BEAN);
	}

	public static String getDigesterHolderBeanName (Configuration configuration) {
		Template template = CommonUtils.getTemplate(configuration, TEMPLATE_DIGESTER_HOLDER_BEAN);
		return CommonUtils.getClassName(configuration, template);
	}
	
	public static String getLoaderBeanName (Configuration configuration) {
		return getBeanName(configuration, TEMPLATE_LOADER_BEAN);
	}
	
	private static String getBeanName (Configuration configuration, String templateName) {
		Template template = CommonUtils.getTemplate(configuration, templateName);
		return CommonUtils.getClassName(configuration, template);
	}
	
	public static String getDigesterHolderBeanNameVariable (Configuration configuration) {
		return getJavaName(getDigesterHolderBeanName(configuration));
	}
	
}
