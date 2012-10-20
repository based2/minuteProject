package net.sf.minuteProject.configuration.bean.model.webservice;

import java.util.List;

public interface EntityModel extends WsdlObject{

	public List<String> getEntityNames();
	
	public List<Entity> getEntities();
}
