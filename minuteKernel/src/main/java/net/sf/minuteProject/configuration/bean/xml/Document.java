package net.sf.minuteProject.configuration.bean.xml;

import java.util.List;

import net.sf.minuteProject.configuration.bean.GeneratorBean;

public interface Document extends GeneratorBean{

	public List<Element> getElements();
	
	public Element getFirstElementByNameAttribute (String elementName);
	
	public Element getFirstComplexTypeElementByNameAttribute (String elementName);
}
