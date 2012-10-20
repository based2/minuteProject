package net.sf.minuteProject.configuration.bean.presentation;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;

public class PresentationEntityInputs extends AbstractConfiguration{

	private PresentationEntity presentationEntity;
	private List<PresentationEntityInput> presentationEntityInputs;
	
	public List<PresentationEntityInput> getPresentationEntityInputs() {
		if (presentationEntityInputs==null)
			presentationEntityInputs = new ArrayList<PresentationEntityInput>();
		return presentationEntityInputs;
	}

	public void setPresentationEntityInputs(List<PresentationEntityInput> presentationEntityInputs) {
		this.presentationEntityInputs = presentationEntityInputs;
	}
	
	public void addPresentationEntityInput (PresentationEntityInput presentationEntityInput) {
		presentationEntityInput.setPresentationEntityInputs(this);
		getPresentationEntityInputs().add(presentationEntityInput);
	}

	public PresentationEntity getPresentationEntity() {
		return presentationEntity;
	}

	public void setPresentationEntity(PresentationEntity presentationEntity) {
		this.presentationEntity = presentationEntity;
	}
	
	
}
