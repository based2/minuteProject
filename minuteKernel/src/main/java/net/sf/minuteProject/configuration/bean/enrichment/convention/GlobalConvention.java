package net.sf.minuteProject.configuration.bean.enrichment.convention;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.BusinessModel;
import net.sf.minuteProject.configuration.bean.model.data.ForeignKey;
import net.sf.minuteProject.configuration.bean.model.data.Reference;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.utils.TableUtils;

public class GlobalConvention extends ModelConvention {

	public final static String ELIMINATE_NO_SELECTED_ENTITY_FOREIGN_KEY ="eliminate-no-selected-entity-foreign-key";

	@Override
	public void apply(BusinessModel model) {
		if (ELIMINATE_NO_SELECTED_ENTITY_FOREIGN_KEY.equals(type))
			for (Table table : getEntityNotInPackage(model)) {
				apply (table, model);
			}
	}

	private List<Table> getEntityNotInPackage(BusinessModel model) {
		List<Table> entities = new ArrayList<Table>();
		for (Table table : model.getModel().getDataModel().getDatabase().getTables()) {
			if (model.getBusinessPackage()!=null) {
				boolean isInModel = false;
				for (Table t : model.getBusinessPackage().getTables()) {
					if (t.getName().equals(table.getName())) {
						isInModel = true;
						break;
					}
				}
				if (!isInModel) entities.add(table);
			}
		}
	
		return entities;
	}

	private void apply(Table table, BusinessModel model) {
		processChildEntities (table, model);
		processParentEntities (table, model);
	}
	
	private void processChildEntities(Table table, BusinessModel model) {
		for (Reference reference : table.getChildren()){
			Table t =TableUtils.getTable(model.getModel().getDataModel().getDatabase(), reference.getForeignTableName());
			for (Reference r : t.getParents()) {
				if (r.getForeignTableName().equals(table.getName()))
					r=null;
			}
			for (Reference r : t.getChildren()) {
				if (r.getForeignTableName().equals(table.getName()))
					r=null;
			}			
			reference = null;
		}
	}
	
	private void processParentEntities(Table table, BusinessModel model) {
		for (Reference reference :table.getParents())
			reference = null;
	}



	private void apply(Table table) {
		for (ForeignKey fk : table.getForeignKeys()) {
			for (Reference ref : fk.getReferences()) {
				ref=null;
			}
			fk=null;
		}
			
	}

}
