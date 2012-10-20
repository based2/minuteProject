package net.sf.minuteProject.configuration.bean.model.statement;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;

public class QueryParams extends AbstractConfiguration {

	private List<QueryParam> queryParams;
	private Query query;

	public boolean isEmpty () {
		return getQueryParams().isEmpty();
	}
	
	public List<QueryParam> getQueryParams() {
		if (queryParams == null) {
			if (!StringUtils.isEmpty(refid)) {
				queryParams = getReferenceQueryParams(refid);
			}
			if (queryParams == null)
				queryParams = new ArrayList<QueryParam>();
		}
		return queryParams;
	}

	private List<QueryParam> getReferenceQueryParams(String refid) {
		if (query != null) {
			Queries queries = query.getQueries();
			if (queries != null)
				for (Query q : queries.getQueries()) {
					if (refid.equals(q.getId())) {
						return q.getQueryParams().getQueryParams();//copy(q);
					}
				}
		}
		return null;
	}

//	private List<QueryParam> copy(Query q) {
//		List<QueryParam> r = new ArrayList<QueryParam>();
//		for (QueryParam qp : q.getQueryParams().getQueryParams()) {
//			QueryParam param = new QueryParam();
//			param.setName(qp.getName());
////			column.setName(queryParam.getName());
//			String type = qp.getType();
//			param.setType(type);
//			param.setSize(qp.getSize());
//			param.setScale(qp.getScale());
//			r.add(param);
//		}
//		return r;
//	}

	public void setQueryParams(List<QueryParam> queryParams) {
		this.queryParams = queryParams;
	}

	public void addQueryParam(QueryParam queryParam) {
		getQueryParams().add(queryParam);
	}

	public void setQuery(Query query) {
		this.query = query;
	}
}
