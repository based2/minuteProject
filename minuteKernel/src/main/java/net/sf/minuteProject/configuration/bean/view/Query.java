package net.sf.minuteProject.configuration.bean.view;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;

/**
 * @author Florian Adler
 *
 */
public class Query extends AbstractConfiguration{
	
	private String value;
	private Function function;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

}
