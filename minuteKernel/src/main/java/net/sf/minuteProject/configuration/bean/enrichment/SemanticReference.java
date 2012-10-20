package net.sf.minuteProject.configuration.bean.enrichment;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.enrichment.path.SqlPath;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.utils.FormatUtils;

public class SemanticReference extends AbstractConfiguration {

	private List<SqlPath> sqlPaths;
//	private List<Column> columns;

	public List<SqlPath> getSqlPaths() {
		if (sqlPaths==null)
			sqlPaths = new ArrayList<SqlPath>();
		return sqlPaths;
	}

	public void setSqlPaths(List<SqlPath> sqlPaths) {
		this.sqlPaths = sqlPaths;
	}
	
	public void addSqlPath(SqlPath sqlPath) {
		getSqlPaths().add(sqlPath);
	}
	
	public List<String> getSemanticReferenceSqlPath () {
		List<String> list = new ArrayList<String>();
		for (SqlPath sqlPath : getSqlPaths()) {
			list.add(sqlPath.getPath());
		}
		return list;
	}
	
	public List<String> getSemanticReferenceBeanPath () {
		List<String> list = new ArrayList<String>();
		for (String sqlPath : getSemanticReferenceSqlPath()) {
			list.add(sqlPath);//getBeanPath(sqlPath));
		}
		return list;
	}
	
	private String getBeanPath(String sqlPath) {
		return FormatUtils.getJavaNameVariable(sqlPath);
	}
	
}
