package net.sf.minuteProject.configuration.bean.presentation;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;

public class PresentationEntityMappings extends AbstractConfiguration {

	private PresentationEntity presentationEntity;
	private List<PresentationEntityMapping> presentationEntityMappings;
	private String type;
	
	public List<PresentationEntityMapping> getPresentationEntityMappings() {
		if (presentationEntityMappings==null)
			presentationEntityMappings = new ArrayList<PresentationEntityMapping>();
		return presentationEntityMappings;
	}

	public void setPresentationEntityMappings(List<PresentationEntityMapping> presentationEntityMappings) {
		this.presentationEntityMappings = presentationEntityMappings;
	}
	
	public void addPresentationEntityMapping (PresentationEntityMapping presentationEntityMapping) {
		presentationEntityMapping.setPresentationEntityMappings(this);
		getPresentationEntityMappings().add(presentationEntityMapping);
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
