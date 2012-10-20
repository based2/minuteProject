package net.sf.minuteProject.configuration.bean.enrichment;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.enrichment.rule.Derivation;
import net.sf.minuteProject.configuration.bean.enrichment.rule.Rule;

public class Field extends AbstractConfiguration {

	private Entity entity;
	private String linkToTargetEntity, linkToTargetField, linkReferenceAlias;
	private String bidirectional;
	private Stereotype stereotype;
	private String type, length, typeAlias;
	private boolean isMandatory, isId, isSearchable, isEditable;
	private List<Trigger> triggers;
	private List<Derivation> derivations;

	public boolean isBidirectional () {
		if (bidirectional!=null && bidirectional.equals("true"))
			return true;
		return false;
	}
	
	public String getLinkToTargetEntity() {
		return linkToTargetEntity;
	}

	public void setLinkToTargetEntity(String linkToTargetEntity) {
		this.linkToTargetEntity = linkToTargetEntity.toUpperCase();
	}

	public String getLinkToTargetField() {
		return linkToTargetField;
	}

	public void setLinkToTargetField(String linkToTargetField) {
		this.linkToTargetField = linkToTargetField.toUpperCase();
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public String getBidirectional() {
		return bidirectional;
	}

	
	public void setBidirectional(String bidirectional) {
		this.bidirectional = bidirectional;
	}

	public Stereotype getStereotype() {
		return stereotype;
	}

	public void setStereotype(Stereotype stereotype) {
		this.stereotype = stereotype;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public boolean isMandatory() {
		return isMandatory;
	}

	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}
	
	public void setIsMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

	public boolean isId() {
		return isId;
	}

	public void setId(boolean isId) {
		this.isId = isId;
	}

	public void setIsId(boolean isId) {
		this.isId = isId;
	}

	public boolean isSearchable() {
		return isSearchable;
	}

	public void setSearchable(boolean isSearchable) {
		this.isSearchable = isSearchable;
	}

	public boolean isEditable() {
		return isEditable;
	}

	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}

	public List<Trigger> getTriggers() {
		if (triggers==null) triggers = new ArrayList<Trigger>();
		return triggers;
	}

	public void setTriggers(List<Trigger> triggers) {
		this.triggers = triggers;
	}
	
	public void addTrigger (Trigger trigger) {
		getTriggers().add(trigger);
	}
	
	public String getLinkReferenceAlias() {
		return linkReferenceAlias;
	}

	public void setLinkReferenceAlias(String linkReferenceAlias) {
		this.linkReferenceAlias = linkReferenceAlias;
	}

	public List<Derivation> getDerivations() {
		if (derivations==null)
			derivations = new ArrayList<Derivation>();
		return derivations;
	}

	public void addDerivation(Derivation derivation) {
		getDerivations().add(derivation);
	}

	public void setTypeAlias(String typeAlias) {
		this.typeAlias = typeAlias;
	}

	public String getTypeAlias() {
		return typeAlias;
	}

}
