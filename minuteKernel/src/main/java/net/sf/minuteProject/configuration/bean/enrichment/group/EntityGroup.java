package net.sf.minuteProject.configuration.bean.enrichment.group;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.utils.parser.ParserUtils;

public class EntityGroup implements Group {

	private List<String> entityList;
	private String entities;
	private boolean isVisible;

	public List<String> getEntityList() {
		if (entityList==null) {
			entityList = new ArrayList<String>();
			entityList.addAll(ParserUtils.getList(entities));
		}	
		return entityList;
	}
//
//	private void setEntityList(List<String> entityList) {
//		this.entityList = entityList;
//	}
	
	public void setEntities(String entities) {
		this.entities = entities;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	@Override
	public List<String> getList() {
		return getEntityList();
	}
	
}
