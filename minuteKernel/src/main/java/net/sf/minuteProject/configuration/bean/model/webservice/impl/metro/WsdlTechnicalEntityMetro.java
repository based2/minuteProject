package net.sf.minuteProject.configuration.bean.model.webservice.impl.metro;

import net.sf.minuteProject.configuration.bean.model.webservice.WsdlEntity;

public class WsdlTechnicalEntityMetro extends WsdlObjectMetro implements WsdlEntity{

	private com.sun.tools.ws.wsdl.framework.Entity entity;
	
	WsdlTechnicalEntityMetro (com.sun.tools.ws.wsdl.framework.Entity entity) {
		this.entity = entity;
	}
	
	@Override
	public String getName() {
		return entity.getElementName().toString();
	}	
}
