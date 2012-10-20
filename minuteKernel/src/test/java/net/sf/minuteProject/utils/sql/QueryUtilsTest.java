package net.sf.minuteProject.utils.sql;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.model.statement.Query;
import net.sf.minuteProject.configuration.bean.model.statement.QueryBody;
import net.sf.minuteProject.configuration.bean.model.statement.QueryParam;
import net.sf.minuteProject.configuration.bean.model.statement.QueryParams;
import junit.framework.TestCase;

public class QueryUtilsTest extends TestCase{

	public static final String query1Jdbc = "SELECT A, B from T where C = ? and D = ?";
	public static final String query1Full = "SELECT A, B from T where C = 1 and D = 'test'";
	public static final String query1SampleParam1 = "1";
	public static final String query1SampleParam2 = "test";
	public static final String query1Param1Type = "int";
	public static final String query1Param2Type = "string";
	
	Query query1;
	
	public void setUp() {
		query1=new Query();
		query1.setQueryBody(getQueryBody());
		query1.setQueryParams(getQueryParams());
	}
	
	private QueryParams getQueryParams() {
		QueryParams queryParams = new QueryParams();
		queryParams.setQueryParams(getQueryParamList());
		return queryParams;
	}

	private List<QueryParam> getQueryParamList() {
		List<QueryParam> list = new ArrayList<QueryParam>();
		list.add(getQueryParam1());
		list.add(getQueryParam2());
		return list;
	}

	private QueryParam getQueryParam1() {
		QueryParam queryParam = new QueryParam();
		queryParam.setType(query1Param1Type);
		queryParam.setSample(query1SampleParam1);
		return queryParam;
	}

	private QueryParam getQueryParam2() {
		QueryParam queryParam = new QueryParam();
		queryParam.setType(query1Param2Type);
		queryParam.setSample(query1SampleParam2);
		return queryParam;
	}

	private QueryBody getQueryBody() {
		QueryBody qb = new QueryBody();
		qb.setValue(query1Jdbc);
		return qb;
	}

	public void testQueryUtils() {
		QueryUtils queryUtils = new QueryUtils();
		String s = queryUtils.getFullQuerySample(query1);
		assertTrue(s, query1Full.equals(s));
	}
}
