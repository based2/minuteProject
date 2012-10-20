package net.sf.minuteProject.plugin.osem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.minuteProject.configuration.bean.model.data.Reference;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.model.data.Database;

public class OsemUtils {
   
	public static List<Table> getSearchRootTables(Database database) {
		List<Table> searchable = new ArrayList<Table> ();
		for (Table table : database.getDataModel().getModel().getBusinessModel().getBusinessPackage().getEntities()) {
			if (table.isSearchable() && table.hasProperty("is-searchable-root")) {
				searchable.add(table);
			}
		}
		return searchable;
	}
	
	public static List<Reference> getSearchableChildren (Table table) {
		List<Reference> ref = new ArrayList<Reference>();
		for(Reference reference : table.getChildren()) {
			if (reference.getForeignTable().isSearchable())
				ref.add(reference);
		}
		return ref;
	}
	
}
