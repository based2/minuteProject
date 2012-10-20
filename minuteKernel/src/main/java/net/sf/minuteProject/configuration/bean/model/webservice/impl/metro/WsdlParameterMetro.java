package net.sf.minuteProject.configuration.bean.model.webservice.impl.metro;

import net.sf.minuteProject.configuration.bean.model.webservice.Parameter;

public class WsdlParameterMetro extends WsdlObjectMetro implements Parameter{

	com.sun.tools.ws.processor.model.Parameter parameter;
	public WsdlParameterMetro(com.sun.tools.ws.processor.model.Parameter parameter) {
		this.parameter = parameter;
	}
	
	@Override
	public String getName() {
		return parameter.getTypeName();
	}
}
