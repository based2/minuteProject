package net.sf.minuteProject.configuration.bean.model.data.impl.DDLUtils;

import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.IndexColumn;
import net.sf.minuteProject.configuration.bean.model.data.Table;

public class IndexColumnDDLUtils implements IndexColumn{

	private org.apache.ddlutils.model.IndexColumn indexColumn;
	private Table table;
	
	public IndexColumnDDLUtils (org.apache.ddlutils.model.IndexColumn indexColumn, Table table) {
	    this.indexColumn = indexColumn;	
	    this.table = table;
	}

	public boolean equalsIgnoreCase(IndexColumn other) {
		// TODO Auto-generated method stub
		return false;
	}

	public Column getColumn() {
		// TODO Auto-generated method stub
		return new ColumnDDLUtils(indexColumn.getColumn(), table);
	}

	public String getName() {
		// TODO Auto-generated method stub
		return indexColumn.getName();
	}

	public int getOrdinalPosition() {
		// TODO Auto-generated method stub
		return indexColumn.getOrdinalPosition();
	}

	public String getSize() {
		// TODO Auto-generated method stub
		return indexColumn.getSize();
	}

}
