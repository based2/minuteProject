package net.sf.minuteProject.configuration.bean.model.webservice.impl.metro;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.model.webservice.Response;
import net.sf.minuteProject.configuration.bean.model.webservice.WsdlObject;

public class WsdlResponseMetro extends WsdlMessageMetro implements Response{

	private com.sun.tools.ws.processor.model.Response response;
	
	WsdlResponseMetro(com.sun.tools.ws.processor.model.Response response) {
		this.response = response;
		entity=new WsdlTechnicalEntityMetro(response.getEntity());
		setParameters(response.getParametersList());
	}
	
	@Override
	public String getName() {
		return response.toString();
	}	
}
