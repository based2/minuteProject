package net.sf.minuteProject.configuration.bean.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;

public class Entity extends AbstractConfiguration{

	private List <Field> fields;

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	
	public void addField (Field field) {
		if (getFields()==null)
			setFields(new ArrayList<Field>());
		field.setEntity(this);
		getFields().add(field);
	}
}
