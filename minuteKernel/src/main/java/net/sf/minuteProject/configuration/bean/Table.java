package net.sf.minuteProject.configuration.bean;

public class Table extends AbstractConfiguration{
	private String type;
	private String referenceViewField;
	private String referenceViewParent;
	private String referenceViewChild;
	
	public String getReferenceViewChild() {
		return referenceViewChild;
	}
	public void setReferenceViewChild(String referenceViewChild) {
		this.referenceViewChild = referenceViewChild;
	}
	public String getReferenceViewField() {
		return referenceViewField;
	}
	public void setReferenceViewField(String referenceViewField) {
		this.referenceViewField = referenceViewField;
	}
	public String getReferenceViewParent() {
		return referenceViewParent;
	}
	public void setReferenceViewParent(String referenceViewParent) {
		this.referenceViewParent = referenceViewParent;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
