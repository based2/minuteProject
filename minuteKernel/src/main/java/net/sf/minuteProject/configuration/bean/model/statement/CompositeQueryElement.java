package net.sf.minuteProject.configuration.bean.model.statement;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;

public class CompositeQueryElement extends AbstractConfiguration{

	private String refid;
	private Query query;
	private Composite composite;

	public String getRefid() {
		return refid;
	}

	public void setRefid(String refid) {
		this.refid = refid;
	}

	public Query getQuery() {
		if (query==null)
			query = retrieveQuery();
		return query;
	}

	private Query retrieveQuery() {
		for (Query query : composite.getComposites().getStatementModel().getQueries().getQueries()) {
			if (query.getId().equals(refid))
				return query;
		}
		return null;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public void setComposite(Composite composite) {
		this.composite = composite;
	}
	
}
