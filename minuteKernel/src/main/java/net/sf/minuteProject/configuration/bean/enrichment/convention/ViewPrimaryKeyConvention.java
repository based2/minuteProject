package net.sf.minuteProject.configuration.bean.enrichment.convention;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.sf.minuteProject.configuration.bean.BusinessModel;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.View;
import net.sf.minuteProject.configuration.bean.model.data.impl.DDLUtils.ViewDDLUtils;
import net.sf.minuteProject.utils.ColumnUtils;

@SuppressWarnings("serial")
public class ViewPrimaryKeyConvention extends TableDefaultPrimaryKeyConvention {

//	public static final String APPLY_DEFAULT_PK_OTHERWISE_FIRST_FIELD_IS_PK = "apply-default-primary-key-otherwise-first-one";
//	public String defaultPrimaryKeyNames;
//	
//	public String getDefaultPrimaryKeyNames() {
//		return defaultPrimaryKeyNames;
//	}
//	public void setDefaultPrimaryKeyNames(String defaultPrimaryKeyNames) {
//		this.defaultPrimaryKeyNames = defaultPrimaryKeyNames;
//	}
//	
//	public void setDefaultValue(String defaultValue) {
//		this.defaultPrimaryKeyNames = defaultValue;
//	}
	
	@Override
	public void apply(BusinessModel model) {
		if (model.getBusinessPackage()!=null) {
			for (View view : model.getBusinessPackage().getViews()) {
				if (view.getPrimaryKeyColumns().length==0)
					apply (view);
			}
		}
	}
	private void apply(View view) {
		view.setVirtualPrimaryKeys(getVirtualPrimaryKey(view));
	}
//	
//	private Column[] getVirtualPrimaryKey(View view) {
//		List<Column> pks = getPksByDefaultPrimaryKeyNames(view);
//		if (pks==null || pks.isEmpty())
//			pks = getPksByFirstColumn(view);
//		return (Column[])pks.toArray(new Column[pks.size()]);
//	}
//	
//	private List<Column> getPksByFirstColumn(View view) {
//		List<Column> pks = new ArrayList<Column>();
//		Column column = view.getColumn(0);
//		if (column!=null)
//			pks.add(column);		
//		return pks;
//	}
//	
//	private List<Column> getPksByDefaultPrimaryKeyNames(View view) {
//		List<Column> pks = new ArrayList<Column>();
//		String pk = getDefaultPrimaryKeyNames();
//		if (pk!=null) {
//			pk = StringUtils.remove(pk, " ");
//			for (String columnName : getDefaultPrimaryKeyNames().split(",")) {
//				Column column = ColumnUtils.getColumn(view, columnName);
//				if (column!=null)
//					pks.add(column);
//			}
//		}
//		return pks;
//	}
	
}
