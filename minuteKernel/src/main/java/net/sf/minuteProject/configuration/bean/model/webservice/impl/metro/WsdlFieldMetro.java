package net.sf.minuteProject.configuration.bean.model.webservice.impl.metro;

import com.sun.tools.ws.processor.model.jaxb.JAXBProperty;

import net.sf.minuteProject.configuration.bean.model.webservice.Field;

public class WsdlFieldMetro extends WsdlObjectMetro implements Field {

	private JAXBProperty property;
	
	public WsdlFieldMetro(JAXBProperty property) {
		this.property = property;
	}

	@Override
	public String getType() {
		return property.getType().getType().fullName();
	}
	
	@Override
	public String getName() {
		return property.getName();
	}

}
