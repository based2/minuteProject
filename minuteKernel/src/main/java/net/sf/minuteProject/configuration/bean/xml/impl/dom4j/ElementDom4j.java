package net.sf.minuteProject.configuration.bean.xml.impl.dom4j;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.Node;

import net.sf.minuteProject.configuration.bean.Configuration;
import net.sf.minuteProject.configuration.bean.Package;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.TemplateTarget;
import net.sf.minuteProject.configuration.bean.xml.impl.AbstractElement;
import net.sf.minuteProject.plugin.xml.schema.XmlSchemaUtils;
import net.sf.minuteProject.utils.FormatUtils;

public class ElementDom4j extends AbstractElement{

	private Package pack;
	private Element element;
	private String elementName;
	private net.sf.minuteProject.configuration.bean.xml.Element parentElement;
	private List<net.sf.minuteProject.configuration.bean.xml.Element> nodes;

	public ElementDom4j (Element element) {
		this.element = element;
		if (element!=null)
			setElementName(element.getName());
		//setName(getNameAttributeValue());
	}
	
	// method

	public String getTechnicalPackage(Template template) {
		TemplateTarget templateTarget = template.getTemplateTarget();
		Configuration configuration = (Configuration)templateTarget.getTarget().getAbstractConfigurationRoot();
	    StringBuffer sb = new StringBuffer(
    		templateTarget.getPackageRoot()+"."+
    		configuration.getModel().getName()+"."+
    		template.getTechnicalPackage());
		return sb.toString();
	}

	
	public String getAttributeValue(String attributeName) {
		Attribute attribute = this.element.attribute(attributeName);
		if (attribute!=null)
			return attribute.getValue();
		return null;
	}
	
	public String getTypeAttributeValue() {
		return getAttributeValue("type");
	}
	
	public String getNameAttributeValue() {
		return getAttributeValue("name");
	}
	
	public boolean isElementComplexType() {
		//complexType full nested
		if (getTypeAttributeValue()==null) {
			List nodes = getNodes();
			if (!nodes.isEmpty()) {
				ElementDom4j ele = ((ElementDom4j)nodes.get(0));
				if (ele.getName().equals("complexType")) {
					return true;
				}
			}
		}
		// complexType by reference
		boolean isComplexType = XmlSchemaUtils.isComplexType(this.getTypeAttributeValue());
		//XmlSchemaUtils.isComplexType(xmlType)
		return isComplexType;
	}
	
	public boolean isComplexType() { 
		return getElementName().equals("complexType");
	}

	public net.sf.minuteProject.configuration.bean.xml.Element getSequence() {
		if (isComplexType()) {
//			return this;
			List<net.sf.minuteProject.configuration.bean.xml.Element> nodes2 = getNodes();
			if (!nodes2.isEmpty()) {
				for (net.sf.minuteProject.configuration.bean.xml.Element ele2 : nodes2) {
					if (ele2.getName().equals("sequence")) {
						return ele2;
					}					
				}
			}
		}
		
		if (getTypeAttributeValue()==null) {
			List nodes = getNodes();
			if (!nodes.isEmpty()) {
				ElementDom4j ele = ((ElementDom4j)nodes.get(0));
				if (ele.getName().equals("complexType")) {
					List nodes2 = ele.getNodes();
					if (!nodes2.isEmpty()) {
						ElementDom4j ele2 = ((ElementDom4j)nodes2.get(0));
						if (ele2.getName().equals("sequence")) {
							return ele2;
						}
					}
				}
			}
		}
		return null;
	}
	
	private List<net.sf.minuteProject.configuration.bean.xml.Element> getComplexType() {
		if (isComplexType() 
			&& getNameAttributeValue()!=null) {
			List<net.sf.minuteProject.configuration.bean.xml.Element> list = new ArrayList<net.sf.minuteProject.configuration.bean.xml.Element>();
			list.add(this);
			return list;
		}
		
		if (getTypeAttributeValue()==null) {
			List<net.sf.minuteProject.configuration.bean.xml.Element> nodes = getNodes();
			if (!nodes.isEmpty()) {
				for (net.sf.minuteProject.configuration.bean.xml.Element element1 : nodes) {
					ElementDom4j ele = ((ElementDom4j)element1);
					if (ele.getName().equals("complexType")) {
						return ele.getNodes();
					}					
				}
			}
		}
		return null;
	}
	
	public List<net.sf.minuteProject.configuration.bean.xml.Element> getSeq() {
		return getSequenceElement();
	}
	public List<net.sf.minuteProject.configuration.bean.xml.Element> getSequenceElement() {
		//return getNodes();
		net.sf.minuteProject.configuration.bean.xml.Element sequence = getSequence();
		if (sequence!=null) {
			return sequence.getNodes();
		}
		return null;
	}
	
	public List<net.sf.minuteProject.configuration.bean.xml.Element> getSequenceAttribute() {
		List<net.sf.minuteProject.configuration.bean.xml.Element> complexType = getComplexType();
		List<net.sf.minuteProject.configuration.bean.xml.Element> attributes 
		   = new ArrayList<net.sf.minuteProject.configuration.bean.xml.Element>();
		if (complexType!=null) {
			if (!complexType.isEmpty()) {
				for (net.sf.minuteProject.configuration.bean.xml.Element element : complexType) {
					if (element.getName().equals("attribute")) {
						attributes.add(element);
					}					
				}
			}
		}
		return attributes;
	}
	
	public String getType () {
		if (isElementComplexType())
			return getNameAttributeValue();
		return getTypeAttributeValue();
			
	}
	
	public String getJavaType () {
		return FormatUtils.getJavaName(getType());
	}
	
//	public String getSequenceType() {
//		net.sf.minuteProject.configuration.bean.xml.Element sequence = getSequence();
//		if (sequence!=null) {
//			String name = sequence.getNameAttributeValue();
//			if (name!=null)
//				return name;
//			else
//				//TODO
//				return ""+"list";
//		}
//		return null;		
//	}
	
	public List<net.sf.minuteProject.configuration.bean.xml.Element> getNodes() {
		if (nodes==null) {
			nodes = new ArrayList<net.sf.minuteProject.configuration.bean.xml.Element> ();
	        for ( int i = 0, size = element.nodeCount(); i < size; i++ ) {
	            Node node = element.node(i);
	            //element.getDocument().getRootElement();
	            if ( node instanceof Element ) {
	            	Element ele = (Element)node;
	            	ElementDom4j elementDom4j = new ElementDom4j(ele);
	            	nodes.add(elementDom4j);
	            }
	        }
		}
		return nodes;
	}
	
	public net.sf.minuteProject.configuration.bean.xml.Element getFirstSequenceElement () {
		Element root = element.getDocument().getRootElement();
		net.sf.minuteProject.configuration.bean.xml.Element rootEle = new ElementDom4j(root);
		List<net.sf.minuteProject.configuration.bean.xml.Element> list = rootEle.getNodes();
		if (!list.isEmpty())
			return list.get(0);
		return null;
	}
	
	public String getName() {
		if (isComplexType() 
			&& getNameAttributeValue()==null) {
			net.sf.minuteProject.configuration.bean.xml.Element parent = getParentElement();
			if (parent!=null && parent.getElementName().equals("element"))
				//return parent.getName();
				return parent.getNameAttributeValue();
		}
		return element.getName();
	}
	
	public void setParentElement(net.sf.minuteProject.configuration.bean.xml.Element element) {
		parentElement = element;
	}
	
	public net.sf.minuteProject.configuration.bean.xml.Element getParentElement() {
		return parentElement;
//		ElementDom4j ele = new ElementDom4j(element.getParent());
//		ele.setName(getNameAttributeValue());
//		return ele;
	}
	
	public void setName(String name) {
		element.setName(name);
	}
	
	public Package getPackage() {
		return pack;
	}
	
	public void setPackage(Package pack) {
		this.pack = pack;
	}

	@Override
	public String getText() {
		return element.getText();
	}	
	
	public List getAttributes() {
		return element.attributes();
	}

	public void setNodes(List nodes) {
		this.nodes = nodes;
	}
	public String getElementName() {
		return elementName;
	}
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	public String getPath() {
		return element.getPath();
	}

	public String getDefault() {
		// TODO Auto-generated method stub
		return getAttributeValue ("default");
	}
	
	// getter setter adder
	
	
	
}
