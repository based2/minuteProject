package net.sf.minuteProject.configuration.bean.model.statement;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.StatementModel;

public class Queries extends AbstractConfiguration{

	public static final String DEFAULT_PACKAGE_NAME = "statement";

	private StatementModel statementModel;
	
	private List<Query> queries;

	public StatementModel getStatementModel() {
		return statementModel;
	}

	public void setStatementModel(StatementModel statementModel) {
		this.statementModel = statementModel;
	}

	public List<Query> getQueries() {
		if (queries==null)
			queries = new ArrayList<Query>();
		return queries;
	}

	public void setQueries(List<Query> queries) {
		this.queries = queries;
	}
	
	public void addQuery (Query query) {
		query.setQueries(this);
		getQueries().add(query);
	}
}
