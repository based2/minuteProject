package net.sf.minuteProject.configuration.bean.enrichment.convention;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.sf.minuteProject.configuration.bean.BusinessModel;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.utils.ColumnUtils;

public abstract class PrimaryKeyConvention<T extends Table> extends ModelConvention {

	public static final String APPLY_PK_ON_ENTITY_WITH_TWO_COLUMN_AS_FK = "apply-primary-key-on-entity-with-two-columns-only-and-foreign-key-otherwise-specified";
//
	public static final String APPLY_DEFAULT_PK_OTHERWISE_FIRST_FIELD_IS_PK = "apply-default-primary-key-otherwise-first-one";
	
	public String defaultPrimaryKeyNames;
	
	public String getDefaultPrimaryKeyNames() {
		return defaultPrimaryKeyNames;
	}
	public void setDefaultPrimaryKeyNames(String defaultPrimaryKeyNames) {
		this.defaultPrimaryKeyNames = defaultPrimaryKeyNames;
	}
	
	public void setDefaultValue(String defaultValue) {
		this.defaultPrimaryKeyNames = defaultValue;
	}
	
	public void applyDefaultPkConvention(BusinessModel model) {
		if (model.getBusinessPackage()!=null) {
			for (T t : getEntity(model)) {
				if (t.getPrimaryKeyColumns().length==0)
					applyDefaultPkConvention (t);
			}
		}
	}
	public void applyPkOnM2MConvention(BusinessModel model) {
		if (model.getBusinessPackage()!=null) {
			for (T t : getEntity(model)) {
				applyPkOnM2MConvention (t);
			}
		}	
	}
	
	private void applyPkOnM2MConvention(T t) {
		if (t.getPrimaryKeyColumns().length==0 
			&& t.getColumns().length==2
			&& ColumnUtils.isForeignKey(t.getColumns()[0])
			&& ColumnUtils.isForeignKey(t.getColumns()[1])
			)		
			t.setPrimaryKeys(t.getColumns());
	}
	
	protected void applyDefaultPkConvention(T t) {
		t.setPrimaryKeys(getVirtualPrimaryKey(t));
	}
	
	protected Column[] getVirtualPrimaryKey(T t) {
		List<Column> pks = getPksByDefaultPrimaryKeyNames(t);
		if (pks==null || pks.isEmpty())
			pks = getPksByFirstColumn(t);
		return (Column[])pks.toArray(new Column[pks.size()]);
	}
	
	protected List<Column> getPksByFirstColumn(T t) {
		List<Column> pks = new ArrayList<Column>();
		Column column = t.getColumn(0);
		if (column!=null)
			pks.add(column);		
		return pks;
	}
	
	protected List<Column> getPksByDefaultPrimaryKeyNames(T t) {
		List<Column> pks = new ArrayList<Column>();
		String pk = getDefaultPrimaryKeyNames();
		if (pk!=null) {
			pk = StringUtils.remove(pk, " ");
			for (String columnName : getDefaultPrimaryKeyNames().split(",")) {
				Column column = ColumnUtils.getColumn(t, columnName);
				if (column!=null)
					pks.add(column);
			}
		}
		return pks;
	}
	
	@Override
	public void apply(BusinessModel model) {
		if (APPLY_PK_ON_ENTITY_WITH_TWO_COLUMN_AS_FK.equals(type)) {
			applyPkOnM2MConvention(model);
		}
		if (APPLY_DEFAULT_PK_OTHERWISE_FIRST_FIELD_IS_PK.equals(type)) {
			applyDefaultPkConvention(model);
		}
	}

	protected abstract List<T> getEntity(BusinessModel model);



}
