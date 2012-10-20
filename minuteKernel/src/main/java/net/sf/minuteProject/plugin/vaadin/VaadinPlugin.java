package net.sf.minuteProject.plugin.vaadin;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.model.data.Table;

public class VaadinPlugin {

	public List<VaadinApplication> getVaadinApplications (Model model) {
		List<VaadinApplication> list = new ArrayList<VaadinApplication>();
		Table[] tables = model.getDataModel().getDatabase().getEntities();
		for (Table table : tables) {
			if (isVaadinApplication(table)) 
				list.add(new VaadinApplication(table));
		}
		return list;
	}
	
	public boolean isVaadinApplication (Table table) {
		return (!table.isManyToMany())?true:false;
	}
}
