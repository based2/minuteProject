package net.sf.minuteProject.configuration.bean.model.webservice;

import net.sf.minuteProject.configuration.bean.GeneratorBean;

public interface Operation extends WsdlObject{

	public Request getRequest() ;

	public Response getResponse() ;
}
