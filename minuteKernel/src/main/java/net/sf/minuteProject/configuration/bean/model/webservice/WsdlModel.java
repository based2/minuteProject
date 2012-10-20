package net.sf.minuteProject.configuration.bean.model.webservice;

import java.util.List;

import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.WebServiceModel;

public interface WsdlModel extends GeneratorBean{

	public List<Service> getServices();
	
	public EntityModel getEntityModel();
	
	public WebServiceModel getWebServiceModel();
}
