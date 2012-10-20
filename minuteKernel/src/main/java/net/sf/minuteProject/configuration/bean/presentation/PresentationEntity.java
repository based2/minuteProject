package net.sf.minuteProject.configuration.bean.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;

public class PresentationEntity extends AbstractConfiguration {

	private PresentationEntities presentationEntities;
	private String entity, type;
	private List<String> roleList;
	private String roles;
	private List<PresentationField> presentationFields;
	private List<PresentationEntityInputs> presentationEntityInputs;
	private List<PresentationEntityOutputs> presentationEntityOutputs;
	private List<PresentationEntityMappings> presentationEntityMappings;
	
	public List<PresentationEntityInputs> getPresentationEntityInputs() {
		if (presentationEntityInputs==null)
			presentationEntityInputs = new ArrayList<PresentationEntityInputs>();
		return presentationEntityInputs;
	}

	public void setPresentationEntityInputs(List<PresentationEntityInputs> presentationEntityInputs) {
		this.presentationEntityInputs = presentationEntityInputs;
	}
	
	public void addPresentationEntityInputs (PresentationEntityInputs presentationEntityInputs) {
		presentationEntityInputs.setPresentationEntity(this);
		getPresentationEntityInputs().add(presentationEntityInputs);
	}

	
	public List<PresentationEntityOutputs> getPresentationEntityOutputs() {
		if (presentationEntityOutputs==null)
			presentationEntityOutputs = new ArrayList<PresentationEntityOutputs>();
		return presentationEntityOutputs;
	}

	public void setPresentationEntityOutputs(List<PresentationEntityOutputs> presentationEntityOutputs) {
		this.presentationEntityOutputs = presentationEntityOutputs;
	}
	
	public void addPresentationEntityOutputs (PresentationEntityOutputs presentationEntityOutputs) {
		presentationEntityOutputs.setPresentationEntity(this);
		getPresentationEntityOutputs().add(presentationEntityOutputs);
	}
	
	
	public List<PresentationEntityMappings> getPresentationEntityMappings() {
		if (presentationEntityMappings==null)
			presentationEntityMappings = new ArrayList<PresentationEntityMappings>();
		return presentationEntityMappings;
	}

	public void setPresentationEntityMappings(List<PresentationEntityMappings> presentationEntityMappings) {
		this.presentationEntityMappings = presentationEntityMappings;
	}
	
	public void addPresentationEntityMappings (PresentationEntityMappings presentationEntityMappings) {
		presentationEntityMappings.setPresentationEntity(this);
		getPresentationEntityMappings().add(presentationEntityMappings);
	}
	
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}

	public PresentationEntities getPresentationEntities() {
		return presentationEntities;
	}

	public void setPresentationEntities(PresentationEntities presentationEntities) {
		this.presentationEntities = presentationEntities;
	}

	public List<PresentationField> getPresentationFields() {
		return presentationFields;
	}
	public void setPresentationFields(List<PresentationField> presentationFields) {
		this.presentationFields = presentationFields;
	}
	public void addPresentationField (PresentationField presentationField) {
		presentationField.setPresentationEntity(this);
		if (presentationFields==null)
			presentationFields = new ArrayList<PresentationField>();
		presentationFields.add(presentationField);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
		StringTokenizer st = new StringTokenizer(roles,",");
		roleList = new ArrayList<String>();
		while (st.hasMoreElements()) {
			String element = (String) st.nextElement();
			this.roleList.add(element);
		}
	}

	public List<String> getRoleList() {
		if (roleList==null)
			roleList = new ArrayList<String>();
		return roleList;
	}

	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}
	
	
	
	
	
}
