package net.sf.minuteProject.configuration.bean.enrichment.convention

import net.sf.minuteProject.configuration.bean.BusinessModel;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.system.Property;

class CheckConstraintConvention extends ModelConvention {

	String fieldPattern, fieldPatternType, fieldContentType
	
	@Override
	public void apply(BusinessModel model) {
		// TODO Auto-generated method stub
		if (isValid) {
			for (Table table : model.getBusinessPackage().getEntities()) {
				apply(table);
			}
		}
	}

	private boolean isValid () {
		fieldContentType!=null && fieldPattern!=null && fieldPatternType!=null
	}
	
	public void apply(Table table) {
		for (Column column: table.getColumns()) {
			apply(column)
		}
	}
	
	public void apply(Column column) {
		if (match(column)) {
			Property property = new Property()
			column.addProperty(property)
			for (Property p: getProperties()) {
				property.addProperty(p);
			}
		}
	}
}
