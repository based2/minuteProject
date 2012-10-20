package net.sf.minuteProject.configuration.bean.presentation;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;

public class PresentationEntityOutputs extends AbstractConfiguration{

	private PresentationEntity presentationEntity;
	private List<PresentationEntityOutput> presentationEntityOutputs;
	private String type;
	
	public List<PresentationEntityOutput> getPresentationEntityOutputs() {
		if (presentationEntityOutputs==null)
			presentationEntityOutputs = new ArrayList<PresentationEntityOutput>();
		return presentationEntityOutputs;
	}

	public void setPresentationEntityOutputs(List<PresentationEntityOutput> presentationEntityOutputs) {
		this.presentationEntityOutputs = presentationEntityOutputs;
	}
	
	public void addPresentationEntityOutput (PresentationEntityOutput presentationEntityOutput) {
		presentationEntityOutput.setPresentationEntityOutputs(this);
		getPresentationEntityOutputs().add(presentationEntityOutput);
	}

	public PresentationEntity getPresentationEntity() {
		return presentationEntity;
	}

	public void setPresentationEntity(PresentationEntity presentationEntity) {
		this.presentationEntity = presentationEntity;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
