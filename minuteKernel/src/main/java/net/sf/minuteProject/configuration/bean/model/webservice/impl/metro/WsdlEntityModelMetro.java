package net.sf.minuteProject.configuration.bean.model.webservice.impl.metro;

import java.util.ArrayList;
import java.util.List;

import com.sun.tools.ws.processor.model.jaxb.JAXBMapping;
import com.sun.tools.ws.processor.model.jaxb.JAXBModel;

import net.sf.minuteProject.configuration.bean.model.webservice.Entity;
import net.sf.minuteProject.configuration.bean.model.webservice.EntityModel;

public class WsdlEntityModelMetro extends WsdlObjectMetro implements EntityModel {

	private JAXBModel jaxbModel;
	private List<Entity> entities;
	
	WsdlEntityModelMetro (JAXBModel jaxbModel) {
		this.jaxbModel = jaxbModel;
		jaxbModel.getMappings().get(0);
	}
	
	public List<String> getEntityNames() {
		return new ArrayList<String>(jaxbModel.getGeneratedClassNames());
	}

	@Override
	public List<Entity> getEntities() {
		if (entities==null)
			entities = initEntities();
		return entities;
	}

	private List<Entity> initEntities() {
		List<Entity> entities = new ArrayList<Entity>();
		for (JAXBMapping mapping : jaxbModel.getMappings())
			entities.add(new WsdlEntityMetro(mapping));
		return entities;
	}
}
