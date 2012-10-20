package net.sf.minuteProject.configuration.bean.enrichment;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.enrichment.group.EntityGroup;
import net.sf.minuteProject.configuration.bean.enrichment.group.FieldGroup;
import net.sf.minuteProject.configuration.bean.enrichment.group.Group;
import net.sf.minuteProject.configuration.bean.enrichment.security.SecurityColor;

public class Package extends AbstractConfiguration {

	private SecurityColor securityColor;
	private Enrichment enrichment;
	private String alias;
	private List<Group> groups;
	
	public SecurityColor getSecurityColor() {
		return securityColor;
	}

	public void setSecurityColor(SecurityColor securityColor) {
		this.securityColor = securityColor;
	}

	public Enrichment getEnrichment() {
		return enrichment;
	}

	public void setEnrichment(Enrichment enrichment) {
		this.enrichment = enrichment;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public List<Group> getEntityGroups() {
		if (groups==null) groups = new ArrayList<Group>();
		return groups;
	}
	
	public void addEntityGroup (EntityGroup entityGroup) {
		getEntityGroups().add(entityGroup);
	}
	
}
