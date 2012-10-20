package net.sf.minuteProject.configuration.bean.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.enrichment.Entity;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.model.data.View;

public class Scope extends AbstractConfiguration{
	
	private static final String TABLE = "TABLE";
	private static final String VIEW = "VIEW";
	
	private List<Strategy> strategies;
	private String entity;
	private Table tableEntity;
	private View viewEntity;
	private String entityType;
	private Service service;
	
	public void addStrategy (Strategy strategy) {
		if (strategies==null)
			strategies = new ArrayList<Strategy> ();
		strategy.setScope(this);
		strategies.add(strategy);
	}

	public List<Strategy> getStrategies() {
		return strategies;
	}

	public void setStrategies(List<Strategy> strategies) {
		this.strategies = strategies;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public Table getTableEntity() {
		return tableEntity;
	}

	public void setTableEntity(Table tableEntity) {
		this.tableEntity = tableEntity;
	}

	public View getViewEntity() {
		return viewEntity;
	}
	
	public GeneratorBean getModelEntity () {
		//TODO
		if (getTableEntity()!=null) {
			setEntityType(TABLE);
			return getTableEntity();
		} 
		else if (getViewEntity()!=null) {
			setEntityType(VIEW);
			return getViewEntity();
		}
		return null;
	}
	
	public String getTechnicalPackage(Template template) {
		GeneratorBean bean = getModelEntity();
		if (bean!=null)
			return bean.getTechnicalPackage(template);
		return "TECH PACKAGE NOT DEFINED";
	}

	public String getName() {
		GeneratorBean bean = getModelEntity();
		if (bean!=null)
			return bean.getName();
		return "NAME NOT DEFINED";
	}

	public void setViewEntity(View viewEntity) {
		this.viewEntity = viewEntity;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
	

	
	
}
