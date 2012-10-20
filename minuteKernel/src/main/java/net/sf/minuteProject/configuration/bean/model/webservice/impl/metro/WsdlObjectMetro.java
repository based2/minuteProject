package net.sf.minuteProject.configuration.bean.model.webservice.impl.metro;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.model.webservice.WsdlEntity;
import net.sf.minuteProject.configuration.bean.model.webservice.WsdlObject;

public class WsdlObjectMetro extends AbstractConfiguration implements WsdlObject{

	protected WsdlEntity entity;
	protected String namespace;
	
	WsdlObjectMetro (){}
	
	WsdlObjectMetro (WsdlObject wsdlObject) {
		this.entity = wsdlObject.getWsdlEntity();
	}

	public WsdlEntity getWsdlEntity() {
		return entity;
	}
	
	@Override
	public String getAlias() {
		return getName();
	}

	public String getNamespace() {
		return namespace;
	}
}
