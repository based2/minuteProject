package net.sf.minuteProject.utils;

import org.apache.commons.lang.StringUtils;

import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Function;
import net.sf.minuteProject.configuration.bean.model.data.FunctionColumn;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.model.data.constant.Direction;

public class RoutineUtils {

	public static boolean isReturn (Function function, Column column) {
		for (FunctionColumn fc : function.getFunctionColumns()) {
			if (fc.getName().toLowerCase().equals(column.getName().toLowerCase()))
				return fc.isReturn();
		}
		return false;
	}
	public static Column getColumn(FunctionColumn functionColumn) {
		if (functionColumn==null) return null;
		Table table = functionColumn.getFunction().getEntity(Direction.ANY);
		for (Column column : table.getColumns()) {
			if (functionColumn.getName().toLowerCase().equals(column.getName().toLowerCase()))
				return column;
		}
		return null;
	}
	
	public static String getFunctionCallName(Function function) {
		return StringUtils.upperCase(function.getName()+"_CALL");
	}
	
	public static String getFunctionCall(Function function) {
		return "call "+function.getName()+"("+getInput(function)+")";
	}
	private static String getInput(Function function) {
		StringBuffer sb = new StringBuffer();
		int size = function.getInputColumns().length;
		for (int i=0; i<size; i++) {
			sb.append("?");
			if (i+1!=size)
				sb.append(",");
		}
		return sb.toString();
	}
}
