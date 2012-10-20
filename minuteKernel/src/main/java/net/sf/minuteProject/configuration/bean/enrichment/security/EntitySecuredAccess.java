package net.sf.minuteProject.configuration.bean.enrichment.security;

import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.utils.parser.ParserUtils;

public class EntitySecuredAccess extends AbstractConfiguration {
	
	String roles, profiles, filters;

	public List<String> getRoleList() {
		return ParserUtils.getList(roles);
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public List<String> getProfileList() {
		return ParserUtils.getList(profiles);
	}

	public void setProfiles(String profiles) {
		this.profiles = profiles;
	}

	public List<String> getFilterList() {
		return ParserUtils.getList(filters);
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	public String getRoles() {
		return roles;
	}

	public String getProfiles() {
		return profiles;
	}

	public String getFilters() {
		return filters;
	}
	
	
}
