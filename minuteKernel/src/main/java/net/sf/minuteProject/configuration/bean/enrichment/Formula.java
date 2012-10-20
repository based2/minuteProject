package net.sf.minuteProject.configuration.bean.enrichment;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;

public class Formula extends AbstractConfiguration{

	private boolean isValidation;

	public boolean isValidation() {
		return isValidation;
	}

	public void setValidation(boolean isValidation) {
		this.isValidation = isValidation;
	}

	public void setValidation(String isValidation) {
		this.isValidation = ("true".equals(isValidation))?true:false;
	}
	
}
