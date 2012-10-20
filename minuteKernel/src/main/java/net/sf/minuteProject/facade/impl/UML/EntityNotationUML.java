package net.sf.minuteProject.facade.impl.UML;

import java.util.List;

import org.apache.ddlutils.model.Column;

import net.sf.minuteProject.configuration.bean.Reference;
import net.sf.minuteProject.configuration.bean.model.data.impl.DDLUtils.TableDDLUtils;
import net.sf.minuteProject.facade.face.EntityFacade;

public class EntityNotationUML implements EntityFacade{

	public TableDDLUtils table;
	
	public List<Column> getAttributes() {
		return null;
	}

	public List<Reference> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Reference> getParents() {
		// TODO Auto-generated method stub
		return null;
	}

	public Column getPrimaryKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
