package net.sf.minuteProject.plugin.xml.schema;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.sf.minuteProject.configuration.bean.BusinessModel;
import net.sf.minuteProject.configuration.bean.Configuration;
import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.enrichment.XmlEnrichment;
import net.sf.minuteProject.configuration.bean.xml.Document;
import net.sf.minuteProject.configuration.bean.xml.Element;
import net.sf.minuteProject.utils.CommonUtils;
import net.sf.minuteProject.utils.FormatUtils;

public class XmlSchemaUtils {

	public XmlSchemaUtils() {
	}
	
	public Boolean isComplexElement(Template template, GeneratorBean bean) {
		if (! (bean instanceof Element)) 
		   return false;
		Element element = (Element)bean;
		return element.isElementComplexType();
	}

	public Boolean isTrue(Template template, GeneratorBean bean) {
		return true;
	}
	
	public static String indent(Integer position) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < position; i++) {
			stringBuffer.append(" ");
		}
		return stringBuffer.toString();
	}
	
	public static String indent(String position) {
		Integer posix = Integer.parseInt(position);
		return indent(posix);
	}
	
	public static String digesterContent(Element element, Document document, Configuration configuration) {
		return DigesterUtils.schema2digesterConfig(element, document, configuration);
	}
	
	public static String getDigesterFullPathConfigXml (Configuration configuration) {
		return DigesterUtils.getDigesterFullPathConfigXml(configuration);
	}
	public static String getPackageName(GeneratorBean generatorBean, Template template) {
		return CommonUtils.getPackageName(generatorBean, template);
	}
	
	public static String getSequenceJavaType (Element element) {
		String type = element.getTypeAttributeValue();
		if (type!=null)
			return getSequenceJavaType(type);
		return getSequenceJavaClassName(element);
	}
	
	private static String getSequenceJavaType (String xmlType) {
//		if (xmlType.equals("xs:string") || xmlType.equals("xsd:string"))
//			return "String";
//		if (xmlType.equals("xs:positiveInteger") || xmlType.equals("xsd:positiveInteger"))
//			return "Integer";
//		if (xmlType.equals("xs:boolean") || xmlType.equals("xsd:boolean"))
//			return "Boolean";
//		if (xmlType.equals("xs:decimal") || xmlType.equals("xsd:decimal"))
//			return "BigDecimal";

		// to change with the namespace of the schema
		String namespace = getNamespace(xmlType);
		String type = getFlatType(xmlType);
		if (namespace.equals("xs") || namespace.equals("xsd")) {
			if (type.equals("string"))
				return "String";
			if (type.equals("positiveInteger"))
				return "Integer";
			if (type.equals("boolean"))
				return "Boolean";
			if (type.equals("decimal"))
				return "BigDecimal";			
		}
		else { // custom schema
			// search element corresponding to the full name
			
			// search element corresponding to the flat type
			return FormatUtils.getJavaName(type);
		}
		return "String";
	}
	
	public static boolean isComplexType(String xmlType) {
		String namespace = getNamespace(xmlType);
		String type = getFlatType(xmlType);
		if (namespace.equals("xs") || namespace.equals("xsd")) 
			return false;
		return true;
	}
	public static String getNamespace (String xmlType) {
		return StringUtils.substringBefore(xmlType, ":");
	}
	
	public static String getFlatType (String xmlType) {
		return StringUtils.substringAfter(xmlType, ":");
	}
	
	public static String getSequenceJavaVariableName (Element element) {
		if (element==null)
			return "ELEMENT must not be null!";
		return FormatUtils.getJavaNameVariable(element.getNameAttributeValue());
//		return element.getNameAttributeValue();
	}
	
	public static boolean isBaseElement(Element baseElement, Element element) {
		return baseElement.getName().equals(element.getName());
	}
	public static String getSequenceJavaClassName (Element element) {
		if (element==null)
			return "ELEMENT must not be null!";
		return FormatUtils.getJavaName(element.getNameAttributeValue());
	}
	
	public static boolean isCollection (Element element) {
		String maxOccurs = element.getAttributeValue("maxOccurs");
		if (maxOccurs!=null 
			&& !maxOccurs.equals("")
			&& !maxOccurs.equals("1")
			&& !maxOccurs.equals("0")) {
			return true;
		}
		return false;
	}

	public static String getBaseElement (Configuration configuration) {
		XmlEnrichment xmlEnrichment = getXmlEnrichment(configuration);
		if (xmlEnrichment!=null)
		   return xmlEnrichment.getBaseElement();
		return null;
	}
	
	private static XmlEnrichment getXmlEnrichment (Configuration configuration) {
		BusinessModel businessModel = configuration.getModel().getBusinessModel();
		if (businessModel!=null && businessModel.getXmlEnrichment()!=null)
		   return businessModel.getXmlEnrichment();
		return null;		
	}
	
	public static Element getBaseElement (Document document, Configuration configuration) {
		return document.getFirstElementByNameAttribute(getBaseElement(configuration));
	}
	
	public static String getFirstSequenceElementName(Element element) {
		return element.getNameAttributeValue();
	}

	public static String getFirstSequenceElementJavaName(Document document, Configuration configuration) {
		return FormatUtils.getJavaName(getFirstSequenceElementName(getBaseElement(document, configuration)));
	}
	
	public static String getFirstSequenceElementJavaNameVariable(Document document, Configuration configuration) {
//		Element element = getBaseElement(document, configuration);
		return FormatUtils.getJavaNameVariable(getFirstSequenceElementName(getBaseElement(document, configuration)));
	}
	
	public static String getFirstSequenceElementJavaNameImport(Document document, Configuration configuration) {
		return DigesterUtils.getFirstSequenceElementJavaNameImport(getBaseElement(document, configuration), configuration);
	}
	
	public static String getLoaderBeanName  (Configuration configuration) {
		return DigesterUtils.getLoaderBeanName(configuration);
	}
	
	public static String getDigesterHolderBeanName (Configuration configuration) {
		return DigesterUtils.getDigesterHolderBeanName(configuration);
	}
	
	public static String getDigesterHolderBeanNameVariable (Configuration configuration) {
		return DigesterUtils.getDigesterHolderBeanNameVariable(configuration);
	}
	
	public static String getDigesterHolderBeanNameImport (Configuration configuration) {
		return DigesterUtils.getDigesterHolderBeanNameImport(configuration);
	}
	
	private static boolean returnTrue() {
		return true;
	}
	
	public static boolean isRefElement (Element element) {
		return element.getNameAttributeValue().endsWith("Ref");
	}
	
	public static Element getRefElement (Element element, List<Element> elements) {
		if (element==null)
			return null;
		if (isRefElement(element)) {
			String name = element.getNameAttributeValue();
			if (name.endsWith("Ref")) {
				String searchName = StringUtils.substringBeforeLast(name, "Ref");
				for (Element element2 : elements) {
					String compareName = element2.getNameAttributeValue();
					if (compareName!=null && compareName.equals(searchName))
						return element2;
				}
			}
		}
		return null;
	}
	
	public static boolean isPackageNameAvailable(Configuration configuration) {
		XmlEnrichment xmlEnrichment = getXmlEnrichment(configuration);
		if (xmlEnrichment!=null)
		   return xmlEnrichment.isPackageNameAvailable();
		return false;
	}
//
//	public static String getBooleanDefaultValue(Element element, String bool) {
//		String def = getDefaultValue(element);
//		if (def==null)
//			return "false";
//		return def;
//	}
	
	public static String getBooleanDefaultValue(Element element) {
		String def = getDefaultValue(element);
		if (def==null)
			return "false";
		return def;
	}
	
	public static String getDefaultValue(Element element) {
		return element.getDefault();
	}
	
	public List<Element> getComplexElement (List<Element> elements) {
		List<Element> returnedElements = new ArrayList<Element>();
		for (Element element : elements) {
			if (element.isComplexType()) {
				returnedElements.add(element);
			}
		}
		return returnedElements;
	}
	
	public static boolean isComplexType(Element element) {
		return (element.isComplexType() 
			|| (element.getName().equals("element") && element.isElementComplexType()));
	}
	
}
