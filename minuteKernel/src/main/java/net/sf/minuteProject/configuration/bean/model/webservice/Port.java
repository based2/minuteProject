package net.sf.minuteProject.configuration.bean.model.webservice;

import java.util.List;

import net.sf.minuteProject.configuration.bean.GeneratorBean;

public interface Port extends GeneratorBean{

	public List<Operation> getOperations();

	public String getNamespace();
	
}
