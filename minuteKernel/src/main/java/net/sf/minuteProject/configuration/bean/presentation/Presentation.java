package net.sf.minuteProject.configuration.bean.presentation;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.Configuration;

public class Presentation extends AbstractConfiguration{
	
	private PresentationEntities presentationEntities;
	private List <PresentationBlock> presentationBlocks;
	
	private PresentationEntityInputs presentationEntityInputs;
	private PresentationEntityOutputs presentationEntityOutputs;
	private PresentationEntityMappings presentationEntityMappings;
	
	private Configuration configuration;
	
	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public void addPresentationBlock (PresentationBlock presentationBlock) {
		presentationBlock.setPresentation(this);
		if (presentationBlocks==null)
			presentationBlocks = new ArrayList();
		presentationBlocks.add(presentationBlock);
	}

	public void setPresentationBlocks(List<PresentationBlock> presentationBlocks) {
		this.presentationBlocks = presentationBlocks;
	}

	public List<PresentationBlock> getPresentationBlocks() {
		return presentationBlocks;
	}

	public PresentationEntityMappings getPresentationEntityMappings() {
		return presentationEntityMappings;
	}

	public void setPresentationEntityMappings(
			PresentationEntityMappings presentationEntityMappings) {
		this.presentationEntityMappings = presentationEntityMappings;
	}



	public PresentationEntities getPresentationEntities() {
		return presentationEntities;
	}

	public void setPresentationEntities(PresentationEntities presentationEntities) {
		this.presentationEntities = presentationEntities;
	}

	public PresentationEntityInputs getPresentationEntityInputs() {
		return presentationEntityInputs;
	}

	public void setPresentationEntityInputs(
			PresentationEntityInputs presentationEntityInputs) {
		this.presentationEntityInputs = presentationEntityInputs;
	}

	public PresentationEntityOutputs getPresentationEntityOutputs() {
		return presentationEntityOutputs;
	}

	public void setPresentationEntityOutputs(
			PresentationEntityOutputs presentationEntityOutputs) {
		this.presentationEntityOutputs = presentationEntityOutputs;
	}
	
	
	
}
