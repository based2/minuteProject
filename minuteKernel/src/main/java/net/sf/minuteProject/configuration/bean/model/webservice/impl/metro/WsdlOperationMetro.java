package net.sf.minuteProject.configuration.bean.model.webservice.impl.metro;

import java.util.List;

import net.sf.minuteProject.application.AbstractGenerator;
import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.Package;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.model.webservice.WsdlEntity;
import net.sf.minuteProject.configuration.bean.model.webservice.Operation;
import net.sf.minuteProject.configuration.bean.model.webservice.Request;
import net.sf.minuteProject.configuration.bean.model.webservice.Response;
import net.sf.minuteProject.configuration.bean.model.webservice.WsdlObject;
import net.sf.minuteProject.configuration.bean.system.Property;
import net.sf.minuteProject.exception.MinuteProjectException;

public class WsdlOperationMetro extends WsdlObjectMetro implements Operation {

	private com.sun.tools.ws.processor.model.Operation operation;

	private Request request;
	private Response response;
	
	public WsdlOperationMetro (com.sun.tools.ws.processor.model.Operation operation) {
		this.operation = operation;
		entity=new WsdlTechnicalEntityMetro(operation.getEntity());
		request = new WsdlRequestMetro(operation.getRequest());
		response = new WsdlResponseMetro(operation.getResponse());
	}
	
	@Override
	public String getName() {
		return operation.getName().toString();
	}

	public Request getRequest() {
		return request;
	}

	public Response getResponse() {
		return response;
	}
	
}
