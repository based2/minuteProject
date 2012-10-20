package net.sf.minuteProject.configuration.bean.model.webservice.impl.metro;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.model.webservice.Message;
import net.sf.minuteProject.configuration.bean.model.webservice.Parameter;

public class WsdlMessageMetro extends WsdlObjectMetro implements Message {

	List<Parameter> parameters;

	public List<Parameter> getParameters() {
		if (parameters==null)
			parameters = new ArrayList<Parameter>();
		return parameters;
	}

	private List<Parameter> initParameters() {
		parameters = new ArrayList<Parameter>();
		return parameters;
	}

	protected void addParameter (com.sun.tools.ws.processor.model.Parameter parameter) {
		getParameters().add(new WsdlParameterMetro(parameter));
	}
	
	protected void setParameters(List<com.sun.tools.ws.processor.model.Parameter> parameters) {
		for (com.sun.tools.ws.processor.model.Parameter parameter : parameters)
			addParameter(parameter);
	}
	
}
