package net.sf.minuteProject.utils.criteria;

import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.db.type.FieldType;

public class CriteriaUtils {

	public static String getColumnTypeCriteria (Column column) {
		if (column!=null) {
			String type = column.getType();
			if (type!=null) {
				if (FieldType.DATE.toString().equals(type))
					return "DateCriteria";
				if (FieldType.TIMESTAMP.toString().equals(type))
					return "TimestampCriteria";
				if (FieldType.INTEGER.toString().equals(type))
					return "IntegerCriteria";
				if (FieldType.BIGINT.toString().equals(type) ||
					FieldType.DECIMAL.toString().equals(type))
					return "LongCriteria";		
			}
		}
		return "StringCriteria";
	}
}
