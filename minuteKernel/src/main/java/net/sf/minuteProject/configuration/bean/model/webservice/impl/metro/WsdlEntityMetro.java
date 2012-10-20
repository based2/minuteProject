package net.sf.minuteProject.configuration.bean.model.webservice.impl.metro;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.model.webservice.Entity;
import net.sf.minuteProject.configuration.bean.model.webservice.Field;

import com.sun.tools.ws.processor.model.jaxb.JAXBMapping;
import com.sun.tools.ws.processor.model.jaxb.JAXBProperty;

public class WsdlEntityMetro extends WsdlObjectMetro implements Entity{

	private JAXBMapping mapping;
	private List<Field> fields;
	
	WsdlEntityMetro (JAXBMapping mapping) {
		this.mapping = mapping;
	}
	
	@Override
	public String getName() {
		return mapping.getElementName().toString();
	}

	@Override
	public List<Field> getFields() {
		if (fields==null)
			fields=initFields();
		return fields;
	}

	private List<Field> initFields() {
		List<Field> fields=new ArrayList<Field>();
		for (JAXBProperty property : mapping.getWrapperStyleDrilldown())
			fields.add(new WsdlFieldMetro(property));
		return fields;
	}	
}
