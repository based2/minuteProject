package net.sf.minuteProject.utils.enrichment

import net.sf.minuteProject.configuration.bean.enrichment.SemanticReference;
import net.sf.minuteProject.configuration.bean.enrichment.path.SqlPath;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.utils.ColumnUtils;
import net.sf.minuteProject.utils.FormatUtils;
import net.sf.minuteProject.utils.StringUtils;
import net.sf.minuteProject.utils.TableUtils;

class SemanticReferenceUtils {
	
	public static Column convertPathToColumn (Table table, SqlPath sqlpath) {
		ColumnUtils.getColumn(table, sqlpath.path)
	}
	
	public static Column convertPathToColumn (Table table, String sqlpath) {
		ColumnUtils.getColumn(table, sqlpath)
	}
	
	static String getSemanticReferenceMethod (Table table, SemanticReference semanticReference) {
		StringBuffer sb = new StringBuffer()
		int cpt=0
		for (SqlPath sqlpath : semanticReference.getSqlPaths()) {
			if (cpt>0)
				sb.append("And")
			Column column = convertPathToColumn(table, sqlpath)
			sb.append(FormatUtils.getJavaName(column.getAlias()))
			cpt++
		}
		sb
	}
	
	public static boolean hasSemanticReference(Table table) {
		if (table.getSemanticReference()==null)
			return false;
		return (table.getSemanticReference().getSqlPaths().size>0)?true:false
	}

	public static String getSemanticReferenceListAsString(Table table) {
		if (!hasSemanticReference(table))
			return ""
		return StringUtils.asNameStringList (table.getSemanticReference().getSqlPaths(), "getPath")
	}

}
