package net.sf.minuteProject.plugin.loadtest;

import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.utils.ColumnUtils;

public class LoadTestUtils {

	public static String getPrimaryKeyValue (Column column) {
		//load in thread safe hashtable the max value for integer like pk (increment strategy)
		if (ColumnUtils.isNaturalPk(column))
			return getUniqueReferenceValue (column);
		return "";
	}

	public static String getUniqueReferenceValue(Column column) {
		// TODO Auto-generated method stub
		return null;
	}
}
