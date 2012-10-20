package net.sf.minuteProject.configuration.bean.model.webservice.impl.metro;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.model.webservice.Request;
import net.sf.minuteProject.configuration.bean.model.webservice.WsdlObject;

public class WsdlRequestMetro extends WsdlMessageMetro implements Request{

	private com.sun.tools.ws.processor.model.Request request;
	
	WsdlRequestMetro (com.sun.tools.ws.processor.model.Request request) {
		this.request = request;
		entity=new WsdlTechnicalEntityMetro(request.getEntity());
		setParameters(request.getParametersList());
	}
	
	@Override
	public String getName() {
		return request.toString();
	}
}
