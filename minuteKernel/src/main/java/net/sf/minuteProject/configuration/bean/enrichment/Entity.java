package net.sf.minuteProject.configuration.bean.enrichment;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.enrichment.group.FieldGroup;
import net.sf.minuteProject.configuration.bean.enrichment.rule.Constraint;
import net.sf.minuteProject.configuration.bean.enrichment.rule.Rule;
import net.sf.minuteProject.configuration.bean.enrichment.security.EntitySecuredAccess;
import net.sf.minuteProject.configuration.bean.model.data.Database;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.model.data.impl.DDLUtils.TableDDLUtils;
import net.sf.minuteProject.utils.ConvertUtils;
import net.sf.minuteProject.utils.TableUtils;

import org.apache.ddlutils.model.Column;
import org.apache.ddlutils.model.ForeignKey;
import org.apache.ddlutils.model.Reference;

public class Entity extends AbstractConfiguration {
	
	private String structure;
	private VirtualPrimaryKey virtualPrimaryKey;
	private List<Field> fields;
	private List<Action> actions;
	private Enrichment enrichment;
	private String contentType; //pseudo-static, reference, life-business-data
	private SemanticReference semanticReference;
	private boolean isLinkEntity, isTransferEntity, isSearchable;
	private EntitySecuredAccess entitySecuredAccess;
	private String type, masterRelationshipField;
	private List<FieldGroup> fieldGroups;
	private List<Constraint> constraints;
	
	public EntitySecuredAccess getEntitySecuredAccess() {
		return entitySecuredAccess;
	}

	public void setEntitySecuredAccess(EntitySecuredAccess entitySecuredAccess) {
		this.entitySecuredAccess = entitySecuredAccess;
	}

	public VirtualPrimaryKey getVirtualPrimaryKey() {
		return virtualPrimaryKey;
	}

	public void setVirtualPrimaryKey(VirtualPrimaryKey virtualPrimaryKey) {
		this.virtualPrimaryKey = virtualPrimaryKey;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public void setField (Field field) {
		addField(field);
	}
	
	public void addField (Field field) {
		field.setEntity(this);
		getFields().add(field);
	}
	
	public List<Field> getFields() {
		if (fields==null)
			fields = new ArrayList<Field> ();
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public Enrichment getEnrichment() {
		return enrichment;
	}

	public void setEnrichment(Enrichment enrichment) {
		this.enrichment = enrichment;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public SemanticReference getSemanticReference() {
		return semanticReference;
	}

	public void setSemanticReference(SemanticReference semanticReference) {
		this.semanticReference = semanticReference;
	}

	public boolean isLinkEntity() {
		return isLinkEntity;
	}

	public void setLinkEntity(boolean isLinkEntity) {
		this.isLinkEntity = isLinkEntity;
	}

	public boolean isTransferEntity() {
		return isTransferEntity;
	}

	public void setTransferEntity(boolean isTransferEntity) {
		this.isTransferEntity = isTransferEntity;
	}

	public boolean isSearchable() {
		return isSearchable;
	}

	public void setSearchable(boolean isSearchable) {
		this.isSearchable = isSearchable;
	}

	public String getType() {
		return (type==null)?Table.TABLE:type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<FieldGroup> getFieldGroups() {
		if (fieldGroups==null) fieldGroups = new ArrayList<FieldGroup>();
		return fieldGroups;
	}
	
	public void addFieldGroup (FieldGroup fieldGroup) {
		getFieldGroups().add(fieldGroup);
	}
	
	public List<Action> getActions() {
		if (actions==null) actions = new ArrayList<Action>();
		return actions;
	}
	
	public void addAction (Action action) {
		getActions().add(action);
	}

	public List<Constraint> getConstraints() {
		if (constraints==null)
			constraints = new ArrayList<Constraint>();
		return constraints;
	}

	public void addConstraint (Constraint constraint) {
		getConstraints().add(constraint);
	}
	
	//use for m2m in grails, jpa2 xml rootnavigation
	public String getMasterRelationshipField() {
		return masterRelationshipField;
	}

	public void setMasterRelationshipField(String masterRelationshipField) {
		this.masterRelationshipField = masterRelationshipField;
	}

	public Table getTable (Database database) {
		Table table = new TableDDLUtils(getTable(this, database));
		for (Action action : this.getActions()) {
			action.setParent(table);
		}
		table.setActions (this.getActions());
		table.setConstraints(this.getConstraints());
		table.setFieldGroups(this.getFieldGroups());
		return table;
	}
	
	private org.apache.ddlutils.model.Table getTable(Entity entity, Database database) {
		org.apache.ddlutils.model.Table table = new org.apache.ddlutils.model.Table();
		table.setType(Table.TABLE);
		table.setName(entity.getName());
		for (Field field : entity.getFields()) {
			table.addColumn(getColumn(field));
		}	
		for (Field field : entity.getFields()) {
			if (isForeignKey(field))
				table.addForeignKey(getForeignKey(field, database));
		}			
		return table;
	}

	private boolean isForeignKey(Field field) {
		return (field.getLinkToTargetEntity()!=null && !field.getLinkToTargetEntity().trim().equals(""));
	}

	private ForeignKey getForeignKey(Field field, Database database) {
		ForeignKey foreignKey = new ForeignKey();
		foreignKey.addReference(getReference (field));
		foreignKey.setForeignTable(getForeignTable(field, database));
		foreignKey.setForeignTableName(field.getLinkToTargetEntity());
		return foreignKey;
	}

	private org.apache.ddlutils.model.Table getForeignTable(Field field, Database database) {
		Table table = TableUtils.getTable(database, field.getLinkToTargetEntity());
		org.apache.ddlutils.model.Table t = new org.apache.ddlutils.model.Table();
		t.setName(table.getName());
		return null;
	}

	private Reference getReference(Field field) {
		Reference reference = new Reference();
		Column localColumn = new Column();
		localColumn.setName(field.getName());
		reference.setLocalColumn(localColumn);
		reference.setLocalColumnName(field.getName());

		Column foreignColumn = new Column();
		foreignColumn.setName(field.getLinkToTargetField());
		reference.setForeignColumn(foreignColumn);
		reference.setForeignColumnName(field.getLinkToTargetField());
//		reference.setLocalColumn(localColumn);
//		reference.setForeignColumn(foreignColumn)
		return reference;
	}

	private org.apache.ddlutils.model.Column getColumn(Field field) {
		org.apache.ddlutils.model.Column column = new org.apache.ddlutils.model.Column();
		column.setName(field.getName());
		column.setType(ConvertUtils.getDBFullTypeFromUMLType(field.getType()));
		column.setRequired(field.isMandatory());
		column.setSize(field.getLength());
		column.setPrimaryKey(field.isId());
		return column;
	}
	
}
