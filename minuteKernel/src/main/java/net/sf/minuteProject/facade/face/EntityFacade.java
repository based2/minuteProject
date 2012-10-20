package net.sf.minuteProject.facade.face;

import java.util.List;

import net.sf.minuteProject.configuration.bean.Reference;

import org.apache.ddlutils.model.Column;

public interface EntityFacade {
	
	public Column getPrimaryKey();
	
	public List<Column> getAttributes ();
	
	public List<Reference> getParents ();
	
	public List<Reference> getChildren ();

}
