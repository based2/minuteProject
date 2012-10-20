package net.sf.minuteProject.configuration.bean.model.webservice;

import java.util.List;

import net.sf.minuteProject.configuration.bean.GeneratorBean;

public interface Service extends GeneratorBean{

	public List<Port> getPorts();
	
	
}
