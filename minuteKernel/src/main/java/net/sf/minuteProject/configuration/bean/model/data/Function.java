package net.sf.minuteProject.configuration.bean.model.data;

import java.util.List;

import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.model.data.constant.Direction;

public interface Function extends GeneratorBean{

	public void setName(String name);
	
	public FunctionColumn [] getInputColumns ();
	
	public FunctionColumn [] getOutputColumns ();
	
	public void setCatalog(String catalog);
	
	public String getCatalog ();
	
	public void addColumn(FunctionColumn functionColumn);
	
	public FunctionColumn [] getFunctionColumns ();
	
	public FunctionColumn [] getColumns ();
	
	public List<Direction> getDirections();
	
	public Table getEntity (Direction direction);
	
	public void setDatabase (Database database);
	
	public boolean hasReturn();
//
//	public String getReturnType(Function function);

	public FunctionColumn getReturnFunctionColumn();

}
