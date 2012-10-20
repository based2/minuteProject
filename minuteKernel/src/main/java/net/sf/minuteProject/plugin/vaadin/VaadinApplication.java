package net.sf.minuteProject.plugin.vaadin;

import net.sf.minuteProject.configuration.bean.model.data.Table;

public class VaadinApplication {

	Table table;
	
	public VaadinApplication(Table table) {
		this.table = table;
	}

	public Table getEntity() {
		return table;
	}
	
	public String toString () {
		return getEntity().toString();
	}
}
