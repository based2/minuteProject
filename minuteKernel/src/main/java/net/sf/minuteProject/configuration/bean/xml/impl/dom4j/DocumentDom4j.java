package net.sf.minuteProject.configuration.bean.xml.impl.dom4j;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Node;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.xml.Document;
import net.sf.minuteProject.configuration.bean.xml.Element;

public class DocumentDom4j extends AbstractConfiguration implements Document{

	private org.dom4j.Document document;
	private List<Element> elements;
	
	public DocumentDom4j (org.dom4j.Document document) {
		this.document = document;
		treeWalkWithParent (document.getRootElement());
	}
	
	public List<Element> getElements() {
		if (elements == null)
			elements = new ArrayList<Element>();
		return elements;
	}

	public Element getFirstElementByNameAttribute (String elementName) {
		for (Element element : elements) {
			if (element.getNameAttributeValue()!=null && 
				element.getNameAttributeValue().equals(elementName))
				return element;
		}
		return null;
	}

	public Element getFirstComplexTypeElementByNameAttribute (String elementName) {
		for (Element element : elements) {
			if (element.getNameAttributeValue()!=null && 
				element.isComplexType() && 
				element.getNameAttributeValue().equals(elementName))
				return element;
		}
		return null;
	}
	
    public void treeWalkWithParent(org.dom4j.Element element) {
        for ( int i = 0, size = element.nodeCount(); i < size; i++ ) {
            Node node = element.node(i);
            if ( node instanceof org.dom4j.Element ) {
            	org.dom4j.Element ele = (org.dom4j.Element)node;
            	ElementDom4j elementDom4jParent = new ElementDom4j(element);
            	ElementDom4j elementDom4j = new ElementDom4j(ele);
            	elementDom4j.setParentElement((Element)elementDom4jParent);
            	getElements().add(elementDom4j);
            	treeWalkWithParent( ele);
            }
        }
    }
    
}
