package net.sf.minuteProject.utils.enrichment;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Database;
import net.sf.minuteProject.configuration.bean.model.data.Reference;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.model.data.View;
import net.sf.minuteProject.configuration.bean.system.Property;
import net.sf.minuteProject.loader.mapping.node.Variable;
import net.sf.minuteProject.utils.TableUtils;

public class EnrichmentUtils {
	
	public static final String generateForTag = "generateForTag";
	
	public static boolean hasMenuLinkDirectResultAccess (GeneratorBean bean) {
		if (hasTag(bean, "menuLinkDirectResultAccess"))
			return true;
		return false;
	}

	// set caching in model map for entity tag
	public static boolean hasEntityTag (Model model, String tag){
		for (Table entity : model.getBusinessModel().getBusinessPackage().getEntities()) {
			if (hasTag(entity, tag))
				return true;
		}
		return false;
	}
	
	// set caching in model map for field tag
	public static boolean hasFieldTag (Model model, String tag){
		for (Table entity : model.getBusinessModel().getBusinessPackage().getEntities()) {
			if (hasFieldTag(entity, tag))
				return true;
		}
		return false;
	}
	
	public static boolean hasFieldTag (Table table, String tag) {
		for (Column column : table.getColumns()) {
			if (hasTag(column, tag))
				return true;
		}
		return false;
	}
	
	public static boolean hasTag (GeneratorBean bean, String tag) {
		for (Property property : bean.getProperties()) {
			if (property.getName().equals(tag))
				return true;
		}
		return false;
	}

	//TODO caching
	public static Table[] getTablesWithTag (Model model, String tag) {
		List<Table> tables = new ArrayList<Table>();
		for (Table entity : model.getBusinessModel().getBusinessPackage().getEntities()) {
			if (hasFieldTag(entity, tag))
				 tables.add(entity);
		}
		return (Table[]) tables.toArray(new Table[tables.size()]);		
	}

	//TODO caching
	public static Column[] getColumnsWithTag (Table table, String tag) {
		List<Column> columns = new ArrayList<Column>();
		for (Column column : table.getColumns()) {
			if (hasTag(column, tag))
				 columns.add(column);
		}
		return (Column[]) columns.toArray(new Column[columns.size()]);		
	}
	
	public static Property[] getProperties (GeneratorBean bean, String tag) {
		for (Property property : bean.getProperties()) {
			if (property.getName().equals(tag))
				return property.getPropertiesArray();
		}
		return null;
	}
	
	public static Table[] getLinkedEntityByForeignKeyForTag (Table table, String tag) {
		List<Table> list = new ArrayList<Table>();
		Reference[] reference = table.getParents();
		for (int i = 0; i < reference.length; i++) {
			Table linkedTable = reference[i].getForeignTable();
			if (hasTag(linkedTable, tag))
				list.add(linkedTable);
		}
		return (Table[]) list.toArray(new Table[list.size()]);
	}
	
	public static Reference[] getLinkedReferenceByForeignKeyForTag (Table table, String tag) {
		List<Reference> list = new ArrayList<Reference>();
		Reference[] reference = table.getParents();
		for (int i = 0; i < reference.length; i++) {
			Table linkedTable = reference[i].getForeignTable();
			if (hasTag(linkedTable, tag))
				list.add(reference[i]);
		}
		return (Reference[]) list.toArray(new Reference[list.size()]);
	}

	public static Reference[] getLinkedReferenceByForeignKey (Table table) {
		List<Reference> list = new ArrayList<Reference>();
		Reference[] reference = table.getParents();
		for (int i = 0; i < reference.length; i++) {
			Table linkedTable = reference[i].getForeignTable();
//			if (hasTag(linkedTable, tag))
				list.add(reference[i]);
		}
		return (Reference[]) list.toArray(new Reference[list.size()]);
	}
	
	public static Table[] getLinkedMany2ManyEntity (Table table) {
		return getLinkedEntityByChildren (table, true);
	}
	
	public static Table[] getLinkedMany2ManyEntityForTag (Table table, String tag) {
		return getLinkedEntityByChildrenForTag(table, tag, true);
	}

	
	public static Table[] getLinkedEntityByChildrenForTag (Table table, String tag) {
		return getLinkedEntityByChildrenForTag(table, tag, false);
	}
	
	/*
	 * get linked ref for m2m => ref[] 
	 *  :Reference[] getLinkedMany2ManyReferenceForTag (Table table, String tag)
	 * for each ref
	 *   get other side of the relationship ref input(ref)-> table,
	 *   :Reference getTargetReferenceEntityByMany2ManyForTag (Reference origin, String tag)
	 */
	public static Table[] getLinkedEntityByChildrenForTag (Table table, String tag, boolean filterMany2Many) {
		List<Table> list = new ArrayList<Table>();
		Reference[] reference = table.getChildren();
		for (int i = 0; i < reference.length; i++) {
			Table child = reference[i].getForeignTable();
			if (hasTag(child, tag))
				if (filterMany2Many) {
					if (child.isManyToMany())
						list.add(child);
				} else
				list.add(child);
		}
		return (Table[]) list.toArray(new Table[list.size()]);
	}
	
	public static Table[] getLinkedEntityByChildren(Table table, boolean filterMany2Many) {
		List<Table> list = new ArrayList<Table>();
		Reference[] reference = table.getChildren();
		for (int i = 0; i < reference.length; i++) {
			Table child = reference[i].getForeignTable();
//			if (hasTag(child, tag))
			if (filterMany2Many) {
				if (child.isManyToMany())
					list.add(child);
			} else
			list.add(child);
		}
		return (Table[]) list.toArray(new Table[list.size()]);
	}
	
	public static Table getLinkedEntityByMany2ManyForTag (Table origin, Table many2many, String tag) {
		if (many2many.isManyToMany()) {
			Table[] m2mTables = getLinkedEntityByForeignKeyForTag(many2many, tag);
			for (int i = 0; i < m2mTables.length; i++) {
				if (!m2mTables[i].getName().equals(origin.getName()))
					return m2mTables[i];
			}
		}
		return null;
	}

	public static Reference getTargetReferenceEntityByMany2ManyForTag (Reference origin, String tag) {
		Table m2m = origin.getForeignTable();
//		System.out.println("m2m = "+m2m.getName());
		return getTargetReferenceEntityByMany2ManyForTag(origin, m2m, tag);
	}
	
	public static Reference getTargetReferenceEntityByMany2Many (Reference origin) {
		Table m2m = origin.getForeignTable();
		return getTargetReferenceEntityByMany2Many(origin, m2m);
	}
	
	public static Reference getTargetReferenceEntityByMany2ManyForTag (Reference origin, Table many2many, String tag) {
		if (many2many.isManyToMany()) {
			Reference[] m2mReference = getLinkedReferenceByForeignKeyForTag(many2many, tag);
			for (int i = 0; i < m2mReference.length; i++) {
				Reference ref = m2mReference[i];
//				System.out.println("origin = "+origin.getLocalColumnName()+"-"+origin.getLocalTableName()+"-"+origin.getForeignColumnName()+"-"+origin.getForeignTableName());
//				System.out.println("ref = "+ref.getLocalColumnName()+"-"+ref.getLocalTableName()+"-"+ref.getForeignColumnName()+"-"+ref.getForeignTableName());
				if (!isEqual(origin, ref))
					return ref;
//				if (!ref.getLocalColumnName().equals(origin.getForeignColumnName()) ||
//					!ref.getLocalTableName().equals(origin.getForeignTableName()) ||
//					!ref.getForeignColumnName().equals(origin.getLocalColumnName()) ||
//					!ref.getForeignColumnName().equals(origin.getLocalColumnName()) )
//				   return ref;
			}
		}
		return null;
	}
	
	public static Reference getTargetReferenceEntityByMany2Many (Reference origin, Table many2many) {
		if (many2many.isManyToMany()) {
			Reference[] m2mReference = getLinkedReferenceByForeignKey(many2many);
			for (int i = 0; i < m2mReference.length; i++) {
				Reference ref = m2mReference[i];
				if (!isEqual(origin, ref)) {
//					if (origin.isMasterRelationship()) 
//						ref.setAggregateRelationship();
					return ref;
				}
			}
		}
		return null;
	}
	
	private static boolean isEqual (Reference origin, Reference ref) {
		if (!ref.getLocalColumnName().equals(origin.getForeignColumnName()) ||
			!ref.getLocalTableName().equals(origin.getForeignTableName()) ||
			!ref.getForeignColumnName().equals(origin.getLocalColumnName()) ||
			!ref.getForeignColumnName().equals(origin.getLocalColumnName()) )
			   return false;
		return true;
	}

	private static boolean isEqualLocal (Reference origin, Reference ref) {
		if (!ref.getLocalTableName().equals(origin.getLocalTableName()) )
			   return false;
		return true;
	}
	
	public static Reference[] getLinkedRefenceByChildrenForTag (Table table, String tag, boolean filterMany2Many) {
		List<Reference> list = new ArrayList<Reference>();
		Reference[] reference = table.getChildren();
		for (int i = 0; i < reference.length; i++) {
			Table child = reference[i].getForeignTable();
			if (hasTag(child, tag))
				if (filterMany2Many) {
					if (child.isManyToMany())
						list.add(reference[i]);
				} else
				list.add(reference[i]);
		}
		return (Reference[]) list.toArray(new Reference[list.size()]);
	}
	
	public static Reference[] getLinkedRefenceByChildren (Table table, boolean filterMany2Many) {
		List<Reference> list = new ArrayList<Reference>();
//		System.out.println(">>table="+table);
//		System.out.println(">>table="+table.getName());
		Reference[] reference = table.getChildren();
		for (int i = 0; i < reference.length; i++) {
			Table child = reference[i].getForeignTable();
//			if (hasTag(child, tag))
				if (filterMany2Many) {
					if (child.isManyToMany()) {
						boolean present = false;
						for (Reference ref : list) {
							if (ref==reference[i]) present = true;
							break;
						}
						if (!present) 
							list.add(reference[i]);
					}
				} else
					list.add(reference[i]);
		}
		return (Reference[]) list.toArray(new Reference[list.size()]);
	}
	
	public static Reference[] getLinkedReferenceByChildrenForTag (Table table, String tag) {
		return getLinkedRefenceByChildrenForTag(table, tag, false);
	}
	
	public static Reference[] getLinkedMany2ManyReferenceForTag (Table table, String tag) {
		return getLinkedRefenceByChildrenForTag(table, tag, true);
	}
	
	public static Reference[] getLinkedMany2ManyReference (Table table) {
		return getLinkedRefenceByChildren(table,  true);
	}

	public static Reference[] getLinkedTargetReferenceByMany2ManyForTag (Table table, String tag) {
		List<Reference> list = new ArrayList<Reference>();
		Reference[] referenceOrigin = getLinkedMany2ManyReferenceForTag(table, tag);
		for (int i = 0; i < referenceOrigin.length; i++) {
			Reference ref = getTargetReferenceEntityByMany2ManyForTag(referenceOrigin[i], tag);
			list.add(ref);
		}
		return (Reference[]) list.toArray(new Reference[list.size()]);
	}
	
	public static Reference[] getLinkedTargetReferenceByMany2Many (Table table) {
		List<Reference> list = new ArrayList<Reference>();
		if(table!=null) {
			Reference[] referenceOrigin = getLinkedMany2ManyReference(table);
			for (int i = 0; i < referenceOrigin.length; i++) {
				Reference ref = getTargetReferenceEntityByMany2Many(referenceOrigin[i]);
				list.add(ref);
			}
		}
		return (Reference[]) list.toArray(new Reference[list.size()]);
	}
	
	public static Reference[] getDistinctLinkedTargetReferenceByMany2ManyForTag (Table table, String tag) {
		List<Reference> list = new ArrayList<Reference>();
		Reference[] referenceOrigin = getLinkedTargetReferenceByMany2ManyForTag (table, tag);
		for (int i = 0; i < referenceOrigin.length; i++) {
			boolean isToAdd = true;
			for (Reference reference : list) {
				if (isEqualLocal(referenceOrigin[i], reference))
					isToAdd = false;
			}
			if (isToAdd)
				list.add(referenceOrigin[i]);
		}
		return (Reference[]) list.toArray(new Reference[list.size()]);
	}
	
	public static boolean isToGenerateBasedOnModelEntityTag(Template template, Model model) {
		List<Property> templateProp = template.getProperties();
		for (Property property : templateProp) {
			if (property.getName().equals(generateForTag)) {
				return hasEntityTag(model, property.getName());
			}
		}
		return false;
	}

	public static boolean isToGenerateBasedOnModelFieldTag(Template template, Model model) {
		List<Property> templateProp = template.getProperties();
		for (Property property : templateProp) {
			if (property.getName().equals(generateForTag)) {
				return hasFieldTag(model, property.getName());
			}
		}
		return false;
	}

	public static boolean isToGenerateBasedOnTag(Template template, GeneratorBean bean) {
//		List<Property> beanProp = bean.getProperties();
//		List<Property> templateProp = template.getProperties();
//		for (Property property : templateProp) {
//			if (property.getName().equals(generateForTag) || property.getTag().equals(generateForTag)) {
//				for (Property property2 : beanProp) {
//					if (property2.getName().equals(property.getValue()))
//						return true;
//				}
//			}
//		}
		return (getPropertyGenerateBasedOnTag(template, bean)==null)?false:true;
	}

	public static String getGenerateBasedOnTagAlias(Template template,
			GeneratorBean bean) {
		// TODO Auto-generated method stub
		Property p =getPropertyGenerateBasedOnTag(template, bean);
		if (p!=null) {
			return p.getAlias();
		}			
		return null;
	}
	public static Property getPropertyGenerateBasedOnTag(Template template, GeneratorBean bean) {
		List<Property> beanProp = bean.getProperties();
		List<Property> templateProp = template.getProperties();
		for (Property property : templateProp) {
			if (property.getName().equals(generateForTag)) {
				for (Property property2 : beanProp) {
					if (property2.getTag().equals(property.getValue()))
						return property2;
				}
			}
		}
		return null;
	}
	
	public static boolean isToGenerateBasedOnTargetPresence(Template template, GeneratorBean bean) {
		return isToGenerateBasedOnPropertyNamePresence(template, bean, "target-presence");
	}
	
	public static boolean isToGenerateBasedOnPropertyPresence(Template template, GeneratorBean bean) {
		return isToGenerateBasedOnPropertyNamePresence(template, bean, "property-presence");
	}
	
	public static boolean isToGenerateBasedOnPropertyPresenceValue(Template template, GeneratorBean bean) {
		Property property = retrievePropertyPresence(template, bean, "property-presence");
		if (property!=null) {
			String value = template.getPropertyValue("property-presence-value");
			if (value!=null)
				
				return (value.equals(property.getValue()));
		}
		return false;
	}

	public static Property retrievePropertyPresence(Template template, GeneratorBean bean, String name) {
		return template.getPropertyByName(template.getPropertyValue(name));
	}
	
	public static boolean isToGenerateBasedOnPropertyNamePresence(Template template, GeneratorBean bean, String name) {
		return (template.getPropertyValue(template.getPropertyValue(name))!=null)?true:false;
	}
	
	public static boolean isToGenerateBasedOnTagAndNotMany2Many(Template template, GeneratorBean bean) {
		if (bean instanceof Table) {
			Table table = (Table) bean;
			if (!table.isManyToMany()) {
				//return isToGenerateBasedOnTag(template, table);
				return true;
			}
		} 
		return false;
	}
	
	public static boolean isToGenerateBasedOnCompositePKNotMany2Many (Template template, GeneratorBean bean) {
		if (bean instanceof Table) {
			Table table = (Table) bean;
			return TableUtils.isCompositePrimaryKeyNotMany2Many(table);
		} 
		return false;		
	}
	
	public static boolean isToGenerateBasedOnNotMany2Many(Template template, GeneratorBean bean) {
		if (bean instanceof Table) {
			Table table = (Table) bean;
			if (!table.isManyToMany()) {
//				return isToGenerateBasedOnTag(template, table);
				return true;
			}
		} 
		else if (bean instanceof View)
			return true;
		return false;
	}
	
	public static Column getOtherColumn (Table table, Column column) {
		if (table!=null && column!=null)
			for (Column col : table.getColumns()) {
				if (!col.getName().equals(column.getName()))
					return col;
			}
		return null;
	}

}
