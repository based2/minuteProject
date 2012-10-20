package net.sf.minuteProject.configuration.bean.presentation;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;

public class PresentationEntities extends AbstractConfiguration{

	private Presentation presentation;
	private List<PresentationEntity> presentationEntitys;
	
	public void addPresentationEntity (PresentationEntity presentationEntity) {
		presentationEntity.setPresentationEntities(this);
		if (presentationEntitys==null)
			presentationEntitys = new ArrayList<PresentationEntity>();
		presentationEntitys.add(presentationEntity);
	}
	
	public List<PresentationEntity> getPresentationEntitys() {
		return presentationEntitys;
	}

	public void setPresentationEntitys(List<PresentationEntity> presentationEntitys) {
		this.presentationEntitys = presentationEntitys;
	}

	public Presentation getPresentation() {
		return presentation;
	}

	public void setPresentation(Presentation presentation) {
		this.presentation = presentation;
	}
	
}
