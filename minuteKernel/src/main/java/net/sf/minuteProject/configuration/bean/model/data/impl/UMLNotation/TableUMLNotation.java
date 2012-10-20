package net.sf.minuteProject.configuration.bean.model.data.impl.UMLNotation;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Database;
import net.sf.minuteProject.configuration.bean.model.data.ForeignKey;
import net.sf.minuteProject.configuration.bean.model.data.Reference;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.model.data.impl.TableAbstract;
import net.sf.minuteProject.configuration.bean.system.Property;
import net.sf.minuteProject.utils.ReferenceUtils;

public class TableUMLNotation extends TableAbstract {
	
	List<Column> noPrimaryKeyNoForeignKeyColumnsName;
	List<Reference> children;
	List<Reference> parents;
	
	public TableUMLNotation (Table table) {
		super (table);
		setDatabase(table.getDatabase()); 
	}

	public Column [] getAttributes () {
		if (noPrimaryKeyNoForeignKeyColumnsName == null) {
			noPrimaryKeyNoForeignKeyColumnsName = new ArrayList<Column>();
			for (int i = 0; i < getNoPrimaryKeyNoForeignKeyColumns().length; i++) {
				ColumnUMLNotation columnUMLNotation = new ColumnUMLNotation (getNoPrimaryKeyNoForeignKeyColumns()[i], this);
				noPrimaryKeyNoForeignKeyColumnsName.add(columnUMLNotation);
			}
		}
		return (Column[])noPrimaryKeyNoForeignKeyColumnsName.toArray(new Column[noPrimaryKeyNoForeignKeyColumnsName.size()]);//(ColumnUMLNotation[])getNoPrimaryKeyNoForeignKeyColumns();
	}	
	
	public Reference [] getChildren() {
		if (children == null) {
			children = new ArrayList<Reference>();
//			for (int i = 0; i < super.getTable().getChildren().length; i++) {
//				ReferenceUMLNotation referenceUMLNotation = new ReferenceUMLNotation (super.getTable().getChildren()[i]);
//				children.add(referenceUMLNotation);
//			}
			for (Reference reference : super.getTable().getChildren()) {
				ReferenceUMLNotation ref = new ReferenceUMLNotation (reference);
//				Reference ref = ReferenceUtils.getReference(reference.getLocalColumn());
				children.add(ref);
			}			
		}
		return (Reference[])children.toArray(new Reference[children.size()]);//(ColumnUMLNotation[])getNoPrimaryKeyNoForeignKeyColumns();
	}
	
	public Reference [] getParents() {
		if (parents == null) {
			parents = new ArrayList<Reference>();
			for (int i = 0; i < super.getParents().length; i++) {
				ReferenceUMLNotation reference = new ReferenceUMLNotation (super.getParents()[i]);
//DO NOT USE REFERENCE yet pb with m2m w/ foreign key convention
//				Reference reference = ReferenceUtils.getReference(super.getParents()[i].getLocalColumn());
				parents.add(reference);
			}
		}
		return (Reference[])parents.toArray(new Reference[parents.size()]);//(ColumnUMLNotation[])getNoPrimaryKeyNoForeignKeyColumns();
	}

	public String getContentType() {
		return super.getContentType();
	}
	
	public boolean hasLob() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasUniqueIndex() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setType(String type) {
		
		
	}

	@Override
	public void setPrimaryKeys(Column[] virtualPrimaryKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setForeignKey(ForeignKey foreignKey) {
		// TODO Auto-generated method stub
		
	}
	
}
