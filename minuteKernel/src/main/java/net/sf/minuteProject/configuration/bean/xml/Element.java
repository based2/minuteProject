package net.sf.minuteProject.configuration.bean.xml;

import java.util.List;

import net.sf.minuteProject.configuration.bean.GeneratorBean;

public interface Element extends GeneratorBean{

	public String getElementName();
	
	public String getName();
	
	public String getType ();
	
	public void setName(String name);
	
	public String getText();
	
	public List getAttributes();
	
	public String getAttributeValue (String attributeName);
	
	public List<Element> getNodes();
	
	public List<Element> getSequenceElement();
	
	public boolean isComplexType();
	
	public List<Element> getSequenceAttribute();
	
	public String getTypeAttributeValue();
	
	public String getNameAttributeValue();
	
	public boolean isElementComplexType();
	
	public String getPath();
	
	public Element getFirstSequenceElement ();
	
	public void setParentElement(Element element);
	
	public String getDefault ();
}
