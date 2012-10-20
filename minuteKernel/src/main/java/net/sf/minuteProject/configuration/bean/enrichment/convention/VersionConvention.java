package net.sf.minuteProject.configuration.bean.enrichment.convention;

import java.util.List;

import net.sf.minuteProject.configuration.bean.BusinessModel;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.utils.ColumnUtils;
import net.sf.minuteProject.utils.parser.ParserUtils;

import org.apache.commons.lang.StringUtils;

public class VersionConvention extends ModelConvention {

	public static final String defaultVersionColumnName = "version";
	
	@Override
	public void apply(BusinessModel model) {
		if (model.getBusinessPackage()!=null) {
			for (Table table : model.getBusinessPackage().getEntities()) {
				apply (table, model);
			}
		}
	}

	private void apply(Table table, BusinessModel model) {
		for (String s : getVersionColumnName()) {
			apply (ColumnUtils.getColumn(table, StringUtils.upperCase(s)));
		}
	}

	private void apply(Column column) {
		if (column!=null) {
			column.setVersion(true);
		}		
	}

	public String getDefaultValue() {
		if (defaultValue==null || defaultValue.isEmpty())
			defaultValue = defaultVersionColumnName;
		return defaultValue;
	}
	
	private List<String> getVersionColumnName () {
		return ParserUtils.getList(defaultValue);
	}

}
