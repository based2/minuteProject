package net.sf.minuteProject.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.minuteProject.configuration.bean.BusinessModel;
import net.sf.minuteProject.configuration.bean.FunctionModel;
import net.sf.minuteProject.configuration.bean.GenerationCondition;
import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.Package;
import net.sf.minuteProject.configuration.bean.Reference;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.condition.FunctionGenerationCondition;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Database;
import net.sf.minuteProject.configuration.bean.model.data.ForeignKey;
import net.sf.minuteProject.configuration.bean.model.data.Function;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.presentation.EntityBlock;
import net.sf.minuteProject.configuration.bean.presentation.EntityBlocks;
import net.sf.minuteProject.configuration.bean.presentation.Presentation;
import net.sf.minuteProject.configuration.bean.presentation.PresentationBlock;
import net.sf.minuteProject.configuration.bean.view.Service;
import net.sf.minuteProject.configuration.bean.view.View;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class ModelUtils {
	
	private static Logger logger = Logger.getLogger(ModelUtils.class);

	public static boolean isToGenerate(BusinessModel businessModel, Table table) {
		if (businessModel.getGenerationCondition()!=null)
			return isToGenerate(businessModel.getGenerationCondition(), table);//.areConditionsTrue(table.getName());
		return true;
	}
	
	public static boolean isToGenerate(FunctionModel functionModel, Function function) {
		FunctionGenerationCondition functionGC = functionModel.getFunctionGenerationCondition();
		if (functionGC!=null) {
			return isToGenerate (functionGC, function);
		}
		return true;
	}
	
	private static boolean isToGenerate(FunctionGenerationCondition functionGC, Function function) {
		return functionGC.areConditionsTrue(function.getName());
	}

	public static boolean isToGenerate(GenerationCondition generationCondition, Table table) {
		if (table.TABLE.equals(table.getType()) && generationCondition.isExcludeTables())
			return false;
		if (table.VIEW.equals(table.getType()) && generationCondition.isExcludeViews())
			return false;		
		return generationCondition.areConditionsTrue(table.getName());
	}
	
	public static boolean isSchemaNeeded(Model model) {
		if (model.getDataModel().hasSchema()) {
			String schemaName = model.getDataModel().getSchema();
			String username = model.getDataModel().getBasicDataSource().getUsername();
			if (schemaName!=null && username!=null) {
				schemaName = StringUtils.lowerCase(schemaName);
				username = StringUtils.lowerCase(username);
				if (schemaName.equals(username))
					return false;
				else 
					return true;
			}
		}
		return false;
	}
	
//	public static boolean isToGenerate(BusinessModel businessModel, net.sf.minuteProject.configuration.bean.model.data.impl.DDLUtils.TableDDLUtils table) {
//		if (businessModel.getGenerationCondition()!=null)
//			return businessModel.getGenerationCondition().areConditionsTrue(table.getName());
//		return true;
//	}	
	
	public static String getPackage(Model model, Template template, Table table) {
		return getPackage (table, template);	
	}
	
	public static String getPackage(Model model, Template template, Package pack) {
		StringBuffer sb = new StringBuffer(getTechnicalPackage(model, template));
		sb.append("."+pack.getName());
		return sb.toString();		
	}

	public static String getPackage(Model model, Template template) {
		//StringBuffer sb = new StringBuffer(getTechnicalPackage(model, template));
		return getTechnicalPackage(model, template);//sb.toString();		
	}

	public static String getPackage(GeneratorBean bean, Template template) {
		return bean.getTechnicalPackage(template);
	}
	
	public static String getTechnicalPackage(Model model, Template template) {
		StringBuffer sb = new StringBuffer(getPackageRoot(template));
		sb.append("."+model.getName());
		if (template.getTechnicalPackage()!=null && !template.getTechnicalPackage().equals(""))
			sb.append("."+template.getTechnicalPackage());
		return sb.toString();		
	}
	
	public static String getPackageRoot (Template template) {
		return (template.getPackageRoot()!=null)?template.getPackageRoot():"defaultroot";
	}
	
	public static String getTechnicalPackage(View view, Template template) {
		StringBuffer sb = new StringBuffer(template.getPackageRoot());
		sb.append("."+view.getProjectname());
		sb.append("."+template.getTechnicalPackage());
		sb.append("."+view.getName());
		return sb.toString();		
	}	
	
	public static String getTechnicalPackage(Service service, Template template) {
		StringBuffer sb = new StringBuffer(getTechnicalPackage(service.getView(), template));
		sb.append("."+service.getName());
		return sb.toString();		
	}		
	//TODO getPackage and technicalPackage must be in the configuration bean interface
	public static String getTechnicalPackage(net.sf.minuteProject.configuration.bean.view.Function function, Template template) {
		StringBuffer sb = new StringBuffer(getTechnicalPackage(function.getService(), template));
		sb.append("."+function.getName());
		return sb.toString();		
	}		
	
	public static String getPackageDir(Model model, Template template, Table table) {
		return FormatUtils.getDirFromPackage(getPackage(model, template, table));		
	}

	public static String getPackageDir(Model model, Template template, Package pack) {
		return FormatUtils.getDirFromPackage(getPackage(model, template, pack));		
	}
	
	public static String getTechnicalPackageDir(Model model, Template template) {
		return FormatUtils.getDirFromPackage(getTechnicalPackage(model, template));		
	}	
	
	public static List<Reference> getParents (Database database, Table table) {
		//return getParents(table);
		// Duplicated code
		List<Reference> list = new ArrayList<Reference>();
		net.sf.minuteProject.configuration.bean.model.data.Reference ref;
		Reference reference;
		ForeignKey [] foreignKeys = table.getForeignKeys();
		//logger.info("table = "+table.getName()+", count FK = "+foreignKeys.length);
		for (int i = 0; i < foreignKeys.length; i++) {
			ref = foreignKeys[i].getFirstReference();
			String tablename = foreignKeys[i].getForeignTableName();
			Table table2 = TableUtils.getTable(database,tablename);
			String columnName = null;
			if (ref!=null)
				columnName = ref.getForeignColumnName();
			Column column2 = ColumnUtils.getColumn (table2, columnName);
			//reference = new Reference(table2, ColumnUtils.getColumn(table2, ref.getLocalColumnName()), tablename, ref.getLocalColumnName());
			reference = new Reference(table2, column2, tablename, columnName);
			reference.setLocalColumn(ref.getLocalColumn());
			
			//logger.info("table = "+tablename+", columnName = "+columnName);
			addReference(list, reference);				
		}
		return list;
		
	}	
	
	private static void addReference (List list, Reference referenceToAdd) {
//		for (Iterator iter = list.iterator(); iter.hasNext();) {
//			Reference reference = (Reference)iter.next();
//			if (reference.equals(referenceToAdd))
//				return;
//		}
		list.add(referenceToAdd);
	}
	
	
	public static List getChildren (Database database, Table table) {
		List list = new ArrayList();
		String columnRef;
		net.sf.minuteProject.configuration.bean.model.data.Reference ref;
		Reference reference;
		//Table [] tables = database.getTables();
		//replaced with entities to find enriched FK in the views
		Table [] tables = database.getEntities();
    	for (int i = 0; i < tables.length; i++) {
    		ForeignKey [] fk = tables[i].getForeignKeys();
        	for (int j = 0; j < fk.length; j++) {
        		String tableName = fk[j].getForeignTableName();
        		if (tableName!=null && fk[j].isBidirectional()) {
	        		if (tableName.equals(table.getName())) {
	        			columnRef = new String();
	        			ref = fk[j].getReference(0);
	        			columnRef = ref.getLocalColumnName();
//	        			if (columnRef==null)
//	        				logger.info("- no localcolumnName for tab");
	        			Column column2 = ColumnUtils.getColumn (tables[i], ref.getLocalColumnName());
	        			reference = new Reference(tables[i], column2, tables[i].getName(), ref.getLocalColumnName());
	        			reference.setLocalColumnName(ref.getLocalColumnName());
	        			reference.setForeignColumnName(ref.getForeignColumnName());
	        			addReference(list, reference);
	        		}
        		}
        	}    	
        }		
    	return list;
	}
	
	public static boolean hasParentReferenceSymbols(Model model, Table table) {
		if (getParentReferenceSymbols(model,table)!=null)
			return true;
		return false;
	}
	
	public static boolean hasParentReferenceSymbols(EntityBlock entityBlock) {
		if (entityBlock!=null)
			return true;
		return false;
	}	
	
	public static EntityBlock getParentReferenceSymbols(Model model, Table table) {
		Presentation presentation = model.getConfiguration().getPresentation();
		for (Iterator iter = presentation.getPresentationBlocks().iterator(); iter.hasNext();) {
			for (Iterator iter2 = ((PresentationBlock)iter.next()).getEntityBlockss().iterator(); iter2.hasNext();)
				for (Iterator iter3 = ((EntityBlocks)iter2.next()).getEntityBlocks().iterator(); iter3.hasNext();) {
					EntityBlock entityBlock = (EntityBlock)iter3.next();
					if (entityBlock.getType()!= null && entityBlock.getName()!= null
						&&	entityBlock.getType().equals("entity-block-parent-reference") 
						&& entityBlock.getEntity().equals(table.getName()))
						return entityBlock;
				}
		}
		return null;
	}
	
	public static String getModelRootPackage (Model model) {
		return model.getPackageRoot()+"."+model.getName();
	}
	
}
