package net.sf.minuteProject.configuration.bean.model.statement;

import java.util.ArrayList;
import java.util.List;

public class QueryParamOptionalSections {

	private List<QueryParamOptionalSection> queryParamOptionalSection;

	public List<QueryParamOptionalSection> getQueryParamOptionalSection() {
		if (queryParamOptionalSection==null)
			queryParamOptionalSection = new ArrayList<QueryParamOptionalSection>();
		return queryParamOptionalSection;
	}

	public void setQueryParamOptionalSection(
			List<QueryParamOptionalSection> queryParamOptionalSection) {
		this.queryParamOptionalSection = queryParamOptionalSection;
	}
	
	public void addQueryParamOptionalSection(
			QueryParamOptionalSection queryParamOptionalSection) {
		getQueryParamOptionalSection().add(queryParamOptionalSection);
	}
		
}
