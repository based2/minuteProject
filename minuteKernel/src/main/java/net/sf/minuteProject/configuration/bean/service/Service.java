package net.sf.minuteProject.configuration.bean.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.BusinessModel;
import net.sf.minuteProject.configuration.bean.enrichment.Entity;

public class Service extends AbstractConfiguration{

	private List<Scope> scopes;
	private BusinessModel businessModel;
	
	public void addScope (Scope scope) {
		if (scopes==null)
			scopes = new ArrayList<Scope> ();
		scope.setService(this);
		scopes.add(scope);
	}

	public List<Scope> getScopes() {
		return scopes;
	}

	public void setScopes(List<Scope> scopes) {
		this.scopes = scopes;
	}

	public BusinessModel getBusinessModel() {
		return businessModel;
	}

	public void setBusinessModel(BusinessModel businessModel) {
		this.businessModel = businessModel;
	}
	
	
}
