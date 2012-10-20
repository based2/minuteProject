package net.sf.minuteProject.configuration.bean.model.data.impl.UMLNotation;

import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Reference;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.model.data.impl.ReferenceAbstract;

public class ReferenceUMLNotation extends ReferenceAbstract {

	TableUMLNotation foreignTable;
	ColumnUMLNotation foreignColumn;
	TableUMLNotation localTable;
	ColumnUMLNotation localColumn;
	
	public ReferenceUMLNotation(Reference reference) {
		super(reference);
		foreignTable = new TableUMLNotation (reference.getForeignTable());
		foreignColumn = new ColumnUMLNotation (reference.getForeignColumn(), foreignTable);
		localTable = new TableUMLNotation (reference.getLocalTable());
		localColumn = new ColumnUMLNotation (reference.getLocalColumn(), localTable);
		setAlias(reference.getAlias());
//		setMasterRelationship(reference.isMasterRelationship());
	}

	public Table getForeignTable () {
		return foreignTable;
	}
	
	public Column getForeignColumn () {
		return foreignColumn;
	}
	
	public String getForeignColumnName () {
		return foreignColumn.getName();
	}
	
	public Column getLocalColumn () {
		return localColumn;
	}
	
	public String getLocalColumnName () {
		if (localColumn==null)
			return "NULL_VALUE";
		return localColumn.getName();
	}

	public String getLocalTableName() {
		return localTable.getName();
	}

	public void setLocalTableName(String localTableName) {
		
	}

	public TableUMLNotation getLocalTable() {
		return localTable;
	}

	public void setLocalTable(Table localTable) {
		// TODO Auto-generated method stub
		
	}




	
}
