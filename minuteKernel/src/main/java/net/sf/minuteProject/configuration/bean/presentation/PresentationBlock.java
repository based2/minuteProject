package net.sf.minuteProject.configuration.bean.presentation;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;

public class PresentationBlock extends AbstractConfiguration{
	
	private Presentation presentation;
	private String type;
	private List <EntityBlocks> entityBlockss;
	private List <FieldBlocks> fieldBlockss;
	
	
	public Presentation getPresentation() {
		return presentation;
	}

	public void setPresentation(Presentation presentation) {
		this.presentation = presentation;
	}

	public void addEntityBlocks (EntityBlocks entityBlocks) {
		entityBlocks.setPresentationBlock(this);
		if (entityBlockss==null) 
			entityBlockss = new ArrayList();
		entityBlockss.add(entityBlocks);
	}

	public void addFieldBlocks (FieldBlocks fieldBlocks) {
		if (fieldBlockss==null) 
			fieldBlockss = new ArrayList();
		fieldBlockss.add(fieldBlocks);
	}	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public List<EntityBlocks> getEntityBlockss() {
		return entityBlockss;
	}

	public void setEntityBlockss(List<EntityBlocks> entityBlockss) {
		this.entityBlockss = entityBlockss;
	}

	public List<FieldBlocks> getFieldBlockss() {
		return fieldBlockss;
	}

	public void setFieldBlockss(List<FieldBlocks> fieldBlockss) {
		this.fieldBlockss = fieldBlockss;
	}
	
	
	
}
