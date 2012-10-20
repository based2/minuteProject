package net.sf.minuteProject.configuration.bean.view;

import net.sf.minuteProject.configuration.bean.parameter.LogicalLink;
import net.sf.minuteProject.configuration.bean.parameter.ParameterSet;

/**
 * @author Florian Adler
 *
 */
public class Input extends ParameterSet{
	
	private ParameterSet parameterSet;
	private Function function;
	
	public Input() {
		
	}
	
	public Input(ParameterSet parameterSet) {
		this.parameterSet = parameterSet;
	}

	public ParameterSet getParameterSet() {
		return parameterSet;
	}

	public void setParameterSet(ParameterSet parameterSet) {
		this.parameterSet = parameterSet;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

}
