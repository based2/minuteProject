package net.sf.minuteProject.configuration.bean.presentation;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;

public class EntityBlocks extends AbstractConfiguration{

	private PresentationBlock presentationBlock;
	private List <EntityBlock> entityBlocks;

	
	public PresentationBlock getPresentationBlock() {
		return presentationBlock;
	}

	public void setPresentationBlock(PresentationBlock presentationBlock) {
		this.presentationBlock = presentationBlock;
	}

	public void addEntityBlock (EntityBlock entityBlock) {
		entityBlock.setEntityBlocks(this);
		if (entityBlocks==null) 
			entityBlocks = new ArrayList();
		entityBlocks.add(entityBlock);
	}

	public List<EntityBlock> getEntityBlocks() {
		return entityBlocks;
	}

	public void setEntityBlocks(List<EntityBlock> entityBlocks) {
		this.entityBlocks = entityBlocks;
	}
	
	
}
