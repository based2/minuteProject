package net.sf.minuteProject.configuration.bean.presentation;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;

public class PresentationField extends AbstractConfiguration{

	private PresentationEntity presentationEntity;
	private String refersToLinkField, defaultValue, filledByEntityName, filledByEntityFieldName;
	private boolean isVisible;
	private String presentationComponent; //DD combo
	private String filterOperandType; //comparator =, !=, like, between, >
	private String filterContentType; //upper case, lowerCase
	
	public boolean isVisible() {
		return isVisible;
	}
	public void setIsVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	public PresentationEntity getPresentationEntity() {
		return presentationEntity;
	}
	public void setPresentationEntity(PresentationEntity presentationEntity) {
		this.presentationEntity = presentationEntity;
	}
	public String getRefersToLinkField() {
		return refersToLinkField;
	}
	public void setRefersToLinkField(String refersToLinkField) {
		this.refersToLinkField = refersToLinkField;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getFilledByEntityName() {
		return filledByEntityName;
	}
	public void setFilledByEntityName(String filledByEntityName) {
		this.filledByEntityName = filledByEntityName;
	}
	public String getFilledByEntityFieldName() {
		return filledByEntityFieldName;
	}
	public void setFilledByEntityFieldName(String filledByEntityFieldName) {
		this.filledByEntityFieldName = filledByEntityFieldName;
	}
	public String getFilterContentType() {
		return filterContentType;
	}
	public void setFilterContentType(String filterContentType) {
		this.filterContentType = filterContentType;
	}
	public String getFilterOperandType() {
		return filterOperandType;
	}
	public void setFilterOperandType(String filterOperandType) {
		this.filterOperandType = filterOperandType;
	}

	public String getPresentationComponent() {
		return presentationComponent;
	}
	public void setPresentationComponent(String presentationComponent) {
		this.presentationComponent = presentationComponent;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	
	
}
