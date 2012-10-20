package net.sf.minuteProject.plugin.grails;

import java.util.List;

import net.sf.minuteProject.configuration.bean.enrichment.SemanticReference;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.utils.FormatUtils;
import net.sf.minuteProject.utils.TableUtils;

public class GrailsUtils {

	public static String getToString (Table table) {
		return getToString (table, null);
	}
	public static String getToString (Table table, String def) {
		if (def!=null) {
			return def;
		}
		String semanticRef = getToStringFromSemanticReferenceTable(table);
		return (semanticRef!=null)?semanticRef:getGrailsToString (table);
	}

	private static String getGrailsToString(Table table) {
		for (Column column: table.getAttributes()) {
			return FormatUtils.getJavaNameVariable(column.getAlias());
		}
		return FormatUtils.getJavaNameVariable(table.getAlias());
	}

	private static String getToStringFromSemanticReferenceTable(Table table) {
		if (TableUtils.hasSemanticReference(table)) {
			return getToStringFromSemanticReference(table);
		} 
		return null;
	}

	private static String getToStringFromSemanticReference(Table table) {
		StringBuffer sb = new StringBuffer();
		List<Column> columns = TableUtils.getSemanticReferenceColumns(table);
		for (int i = 0; i < columns.size(); i++) {
			sb.append(FormatUtils.getJavaNameVariable(columns.get(i).getAlias()));
			if (i+1!=columns.size())
				sb.append(" - ");
		}
		return sb.toString();
	}
	
	public static String getM2MVariableName (Table table, String targetTableName, String linkTableName, String localColumnName) {
		//${targetTableVariableName}Via${linkTableName}By${localColumnName}s
		String targetTableVariableName = FormatUtils.getJavaNameVariable(targetTableName);
		String linkTableClassName = FormatUtils.getJavaName(linkTableName);
		String localColumnClassName = FormatUtils.getJavaName(localColumnName);
		if (TableUtils.hasChild(table, targetTableName)) {
			return targetTableVariableName+"Via"+linkTableClassName+"By"+localColumnClassName+"s";
		}
		return targetTableVariableName+"s";
	}
	
	public String getVersionField (Table table) {
		return FormatUtils.getJavaNameVariable(TableUtils.getVersionColumn(table).getAlias());
	}
}
