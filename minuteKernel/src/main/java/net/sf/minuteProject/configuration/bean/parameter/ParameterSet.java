package net.sf.minuteProject.configuration.bean.parameter;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.parameter.Parameter;

/**
 * @author Florian Adler
 *
 */
public class ParameterSet extends AbstractConfiguration{

	private List<Parameter> parameters;
	
	public List<Parameter> getParameters () {
		if (parameters == null)
			parameters = new ArrayList<Parameter>();		
		return parameters;
	}

	public void addParameter (Parameter parameter) {
		getParameters().add(parameter);
	}
	
}
