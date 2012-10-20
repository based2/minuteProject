package net.sf.minuteProject.configuration.bean.query;

import java.util.List;

import net.sf.minuteProject.configuration.bean.enrichment.path.SqlPath;

public class DistinctCountQuery extends Query {

	private String entity;
	
	private List<SqlPath> fields;
	
}
