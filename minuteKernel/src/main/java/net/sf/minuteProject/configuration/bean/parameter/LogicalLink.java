package net.sf.minuteProject.configuration.bean.parameter;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.model.Entity;

public class LogicalLink  extends AbstractConfiguration{

	private Entity entity;

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	
	
}
