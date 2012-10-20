package net.sf.minuteProject.configuration.bean.presentation;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;

/**
 * @author florian adler
 *
 */

public class PresentationEntityInput extends AbstractConfiguration{

	private PresentationEntityInputs presentationEntityInputs;
	private List<PresentationField> presentationFields;
	private String type;
	
	public List<PresentationField> getPresentationFields() {
		if (presentationFields==null)
			presentationFields = new ArrayList<PresentationField>();
		return presentationFields;
	}

	public void setPresentationFields(List<PresentationField> presentationFields) {
		this.presentationFields = presentationFields;
	}
	
	public void addPresentationField (PresentationField presentationField) {
		getPresentationFields().add(presentationField);
	}

	public PresentationEntityInputs getPresentationEntityInputs() {
		return presentationEntityInputs;
	}

	public void setPresentationEntityInputs(
			PresentationEntityInputs presentationEntityInputs) {
		this.presentationEntityInputs = presentationEntityInputs;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
