package net.sf.minuteProject.configuration.bean.model.data.impl.DDLUtils;

import java.util.ArrayList;
import java.util.List;

import org.apache.ddlutils.model.IndexColumn;

import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Index;
import net.sf.minuteProject.configuration.bean.model.data.Table;

public class IndexDDLUtils implements Index{

	private org.apache.ddlutils.model.Index index;
	private Table table;
	private List<Column> columns;
	
	public IndexDDLUtils(org.apache.ddlutils.model.Index index, Table table) {
		this.index = index;
		this.table = table;
	}
	
	public void addColumn(Column column) {
		// TODO Auto-generated method stub
		
	}

	public boolean equalsIgnoreCase(Index otherIndex) {
		return false;
	}

	public Column getColumn(int idx) {
		IndexColumnDDLUtils indexColumnDDLUtils = new IndexColumnDDLUtils (index.getColumn(idx), table);
		return indexColumnDDLUtils.getColumn();
	}

	public int getColumnCount() {
		return index.getColumnCount();
	}

	public Column[] getColumns() {
		if (columns==null) {
			columns = new ArrayList<Column>();
			for (IndexColumn indexColumn : index.getColumns()) {
				IndexColumnDDLUtils indexColumnDDLUtils = new IndexColumnDDLUtils (indexColumn, table);
				columns.add(indexColumnDDLUtils.getColumn());
			}
		}
		return (Column[]) columns.toArray(new Column[columns.size()]);
	}

	public String getName() {
		return index.getName();
	}

	public boolean hasColumn(Column column) {
		for (Column col : getColumns()) {
			if (col.getName().equals(column.getName()))
				return true;
		}
		return false;
	}

	public boolean isUnique() {
		return index.isUnique();
	}

	public void removeColumn(Column column) {
		// TODO Auto-generated method stub
		
	}

	public void removeColumn(int idx) {
		// TODO Auto-generated method stub
		
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	public String toVerboseString() {
		// TODO Auto-generated method stub
		return null;
	}

}
