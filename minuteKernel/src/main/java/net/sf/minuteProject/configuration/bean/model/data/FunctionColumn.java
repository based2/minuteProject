package net.sf.minuteProject.configuration.bean.model.data;

import net.sf.minuteProject.configuration.bean.model.data.constant.Direction;

public interface FunctionColumn extends BaseColumn {

	public Direction getDirection();
	
	public void setDirection(Direction direction);
	
	public void setPrecision(int precision);

	public int getPrecision();

	public boolean isReturn();

	public Function getFunction();

	public void setFunction(Function function);
	
}
