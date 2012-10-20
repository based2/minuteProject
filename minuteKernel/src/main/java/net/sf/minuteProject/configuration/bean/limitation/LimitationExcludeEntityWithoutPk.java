package net.sf.minuteProject.configuration.bean.limitation;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.BusinessModel;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.utils.TableUtils;

public class LimitationExcludeEntityWithoutPk extends Limitation {

	@Override
	public void apply(BusinessModel model) {
		List<Table> tables = model.getBusinessPackage().getEntities();
		for (Table table : tables) {
			if (table.getPrimaryKeyColumns().length==0) {
				if (TableUtils.isView(table))
					model.getBusinessPackage().getViews().remove(table);
				else
					model.getBusinessPackage().getTables().remove(table);		
			}
		}
		model.getBusinessPackage().resetEntities();
	}

}
