package net.sf.minuteProject.configuration.bean.view;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.Template;

/**
 * @author Florian Adler
 *
 */
public class Function extends AbstractConfiguration{
	
	private Input input;
	private Output output;
	private Query query;
	private Service service;
	private String method;
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Input getInput() {
		return input;
	}
	public void setInput(Input input) {
		this.input = input;
	}
	public Output getOutput() {
		return output;
	}
	public void setOutput(Output output) {
		this.output = output;
	}
	public Query getQuery() {
		return query;
	}
	public void setQuery(Query query) {
		this.query = query;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}

	public String getTechnicalPackage(Template template) {
		StringBuffer sb = new StringBuffer(getService().getTechnicalPackage(template));
		sb.append("."+getName());
		return sb.toString();
	}
	
}
