package net.sf.minuteProject.configuration.bean.model.statement;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.Package;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.model.data.constant.Direction;
import net.sf.minuteProject.configuration.bean.model.data.impl.DDLUtils.TableDDLUtils;
import net.sf.minuteProject.exception.MinuteProjectException;
import net.sf.minuteProject.utils.ConvertUtils;
import net.sf.minuteProject.utils.sql.QueryUtils;

public class Query extends AbstractConfiguration {
	
	private Queries queries;
	private QueryBody queryBody;
	private QueryWhat queryWhat;
	private QueryWhere queryWhere;
	private QueryParams queryParams;
	private QueryParams outputParams;
	private boolean isSet = false;
	private Package pack;
	private String type, category;
	
	public QueryParams getInputParams () {
		return QueryUtils.getInputParams(this);
	}
	
	public QueryParams getOutputParams (){
		if (outputParams==null && !isSet) {
			try {
				outputParams = QueryUtils.getOutputParams(this);
			} catch (MinuteProjectException e) {
				isSet=true;
				//TODO log error
			}
			isSet = true;
		}
		return outputParams;
	}
	
	public Queries getQueries() {
		return queries;
	}
	public void setQueries(Queries queries) {
		this.queries = queries;
	}
	public QueryBody getQueryBody() {
		return queryBody;
	}
	public void setQueryBody(QueryBody queryBody) {
		this.queryBody = queryBody;
	}
	public void setQueryBody(String s) {
		queryBody = new QueryBody();
		queryBody.setValue(s);
	}
	public QueryWhat getQueryWhat() {
		return queryWhat;
	}
	public void setQueryWhat(QueryWhat queryWhat) {
		this.queryWhat = queryWhat;
	}
	public void setQueryWhat(String s) {
		queryWhat = new QueryWhat();
		queryWhat.setValue(s);
	}
	public QueryWhere getQueryWhere() {
		return queryWhere;
	}
	public void setQueryWhere(QueryWhere queryWhere) {
		this.queryWhere = queryWhere;
	}
	public void setQueryWhere(String s) {
		queryWhere = new QueryWhere();
		queryWhere.setValue(s);
	}
	public QueryParams getQueryParams() {
		if (queryParams==null) queryParams=new QueryParams();
		return queryParams;
	}
	
	public void setQueryParams(QueryParams queryParams) {
		this.queryParams = queryParams;
		this.queryParams.setQuery(this);
	}
//	
//	public String getTechnicalPackage(Template template) {
//		return getModel().getTechnicalPackage(template);
//	}
	
//	private Model getModel() {
//		return getQueries().getStatementModel().getModel();
//	}
	
	public Table getInputBean () {
		return getEntity(Direction.IN);
	}
	
	public Table getOutputBean () {
		return getEntity(Direction.OUT);
	}

	public Table getEntity(Direction dir) {
		org.apache.ddlutils.model.Table table = new org.apache.ddlutils.model.Table();
		setTableName(table, dir);
//		table.setName(getName());
//		table.setCatalog(catalog);
		table.setType(Table.TABLE);
		addColumns(table, dir);
		Table entity = new TableDDLUtils(table);
		entity.setPackage(getPackage());
//		entity.getTechnicalPackage(template)
		entity.setDatabase(getQueries().getStatementModel().getModel().getDataModel().getDatabase());
		return entity;
	}

	private void setTableName(org.apache.ddlutils.model.Table table,
			Direction dir) {
		String queryparamName = getQueryParams().getName();
		if (dir.equals(Direction.IN) && !StringUtils.isEmpty(queryparamName))
			table.setName(queryparamName);
		else
			table.setName(getName());
	}

	public Package getPackage() {
		return pack;
	}

	public void setPackage(Package pack) {
		this.pack = pack;
	}

//	public net.sf.minuteProject.configuration.bean.Package getPackage() {
//		return getModel().getConfiguration().getPackage();
//	}
//	
//	private Package getPackage (Table table) {
//		Package pack = new Package();
//		pack.setName(getPackageName());
//		return pack;
//	}
//	
//	private String getPackageName() {
//		StringBuffer sb = new StringBuffer();
//		String packageRoot = getModel().getPackageRoot();
//		if (packageRoot!=null)
//			sb.append(packageRoot+".");
//		sb.append(getModel().getConfiguration().getName());
//		String queriesName = getQueries().getName();
//		if (queriesName!=null)
//			sb.append("."+getQueries().getName());
//		return sb.toString();
//	}

	public String getTechnicalPackage(Template template) {
		net.sf.minuteProject.configuration.bean.Package p = getPackage();
		if (p == null)
			return "ERROR_PACKAGE_IS_NULL";
		return p.getTechnicalPackage(template);
	}

	private void addColumns(org.apache.ddlutils.model.Table table,
			Direction direction) {
		List<QueryParam> list = getColumns(direction);
		for (QueryParam queryParam : list) {
			table.addColumn(getColumn(queryParam));
		}
	}

	private List<QueryParam> getColumns(Direction direction) {
		if (Direction.IN.equals(direction))
			return getInputParams().getQueryParams();
		if (getOutputParams()!=null)
			return getOutputParams().getQueryParams();
		return new ArrayList<QueryParam>();
	}

	private org.apache.ddlutils.model.Column getColumn(QueryParam queryParam) {
		org.apache.ddlutils.model.Column column = new org.apache.ddlutils.model.Column();
		column.setName(queryParam.getName());
		String type = queryParam.getType();
		column.setType(convertType(type));
		column.setSize(queryParam.getSize()+"");
		column.setScale(queryParam.getScale());
		column.setDefaultValue(queryParam.getDefaultValue());
		if (ConvertUtils.DB_DECIMAL_TYPE.equals(type) && queryParam.getScale()>0) {
			column.setType(ConvertUtils.DB_DOUBLE_TYPE);
		}
//		column.setPrecisionRadix(queryParam.getPrecisionRadix());
		// column.setTypeCode(fc.getTypeCode());
		column.setRequired(queryParam.isMandatory());
		return column;
	}
	
	private String convertType(String type) {
		return ConvertUtils.getDDLUtilsTypeFromDBType(type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
