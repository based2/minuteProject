package net.sf.minuteProject.configuration.bean.model.data;

import net.sf.minuteProject.configuration.bean.DataModel;
import net.sf.minuteProject.configuration.bean.model.data.impl.DDLUtils.DatabaseDDLUtils;
import net.sf.minuteProject.configuration.bean.model.data.impl.UMLNotation.TableUMLNotation;

public class DataModelFactory {
	
	private static DataModelFactory dataModelFactory;
	
	public static DataModelFactory getInstance () {
		if (dataModelFactory==null)
			dataModelFactory = new DataModelFactory();
		return dataModelFactory;
	}
	
	public static Database getDatabase (DataModel dataModel) {
		return new DatabaseDDLUtils(dataModel);
	}
	
	public static Table getTable (Table table) {
		//table.getDatabase().getDataModel().getModel().get
		return new TableUMLNotation (table);
	}

}
