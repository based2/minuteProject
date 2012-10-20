package net.sf.minuteProject.configuration.bean.presentation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Table;

public class EntityBlock extends AbstractConfiguration {
	
	private EntityBlocks entityBlocks;
	private String strategy;
	private String type;
	private String entity;
	private List <Field> fields;
	private List <Column> columns;

	public EntityBlocks getEntityBlocks() {
		return entityBlocks;
	}

	public void setEntityBlocks(EntityBlocks entityBlocks) {
		this.entityBlocks = entityBlocks;
	}

	public void addField (Field field) {
		if (fields==null) 
			fields = new ArrayList();
		fields.add(field);
	}
	
	public List<Column> getColumns () {
		Table table = getEntityBlocks().getPresentationBlock().getPresentation().getConfiguration().getModel().getDataModel().getDatabase().findTable(getEntity());
		if (columns==null) {
			columns = new ArrayList<Column>();
			if (table!=null) {
				for (Iterator iter = getFields().iterator(); iter.hasNext();) {
					Field field = (Field)iter.next();
					for (int i = 0; i <table.getColumns().length; i++) {
						Column column = table.getColumn(i);
						if (field.getName().equals(column.getName()))
							columns.add(column);
					}
				}
			}
		}
		return columns;
	}
	
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	public String getStrategy() {
		return strategy;
	}
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
