package net.sf.minuteProject.configuration.bean.enrichment.convention;

import java.util.List;

import net.sf.minuteProject.configuration.bean.BusinessModel;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.utils.ColumnUtils;

public class TablePrimaryKeyConvention extends PrimaryKeyConvention<Table> {

	public TablePrimaryKeyConvention(){}
	@Override
	protected List<Table> getEntity(BusinessModel model) {
		return model.getBusinessPackage().getTables();
	}
//
//	public static final String APPLY_PK_ON_ENTITY_WITH_TWO_COLUMN_AS_FK = "apply-primary-key-on-entity-with-two-columns-only-and-foreign-key-otherwise-specified";
//
//	@Override
//	public void apply(BusinessModel model) {
//		if (APPLY_PK_ON_ENTITY_WITH_TWO_COLUMN_AS_FK.equals(type)) {
//			if (model.getBusinessPackage()!=null) {
//				for (Table table : model.getBusinessPackage().getTables()) {
//						apply (table);
//				}
//			}			
//		}
//	}
//
//	private void apply(Table table) {
//		if (table.getPrimaryKeyColumns().length==0 
//			&& table.getColumns().length==2
//			&& ColumnUtils.isForeignKey(table.getColumns()[0])
//			&& ColumnUtils.isForeignKey(table.getColumns()[1])
//			)		
//			table.setPrimaryKeys(table.getColumns());
//	}

}
