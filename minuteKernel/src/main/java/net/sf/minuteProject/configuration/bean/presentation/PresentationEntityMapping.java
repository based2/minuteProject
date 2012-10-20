package net.sf.minuteProject.configuration.bean.presentation;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;

/**
 * @author florian adler
 *
 */

public class PresentationEntityMapping extends AbstractConfiguration{

	private PresentationEntityMappings presentationEntityMappings;
	private String presentationInput;
	private String presentationEntityOutput;
	
	public String getPresentationEntityOutput() {
		return presentationEntityOutput;
	}
	public void setPresentationEntityOutput(String presentationEntityOutput) {
		this.presentationEntityOutput = presentationEntityOutput;
	}
	public String getPresentationInput() {
		return presentationInput;
	}
	public void setPresentationInput(String presentationInput) {
		this.presentationInput = presentationInput;
	}
	public PresentationEntityMappings getPresentationEntityMappings() {
		return presentationEntityMappings;
	}
	public void setPresentationEntityMappings(
			PresentationEntityMappings presentationEntityMappings) {
		this.presentationEntityMappings = presentationEntityMappings;
	}
	
}
