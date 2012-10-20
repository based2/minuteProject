package net.sf.minuteProject.configuration.bean.enrichment;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;

public class XmlEnrichment extends AbstractConfiguration {

	private String baseElement;
	private String isPackageNameAvailable;

	public String getBaseElement() {
		return baseElement;
	}

	public void setBaseElement(String baseElement) {
		this.baseElement = baseElement;
	}

	public String getIsPackageNameAvailable() {
		return isPackageNameAvailable;
	}

	public void setIsPackageNameAvailable(String isPackageNameAvailable) {
		this.isPackageNameAvailable = isPackageNameAvailable;
	}
	
	public boolean isPackageNameAvailable() {
		if (getIsPackageNameAvailable()!=null && getIsPackageNameAvailable().equals("true"))
			return true;
		return false;
	}
	
}
