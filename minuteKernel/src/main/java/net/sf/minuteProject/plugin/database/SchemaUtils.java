package net.sf.minuteProject.plugin.database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import net.sf.minuteProject.configuration.bean.model.data.Database;
import net.sf.minuteProject.configuration.bean.model.data.Reference;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.utils.TableUtils;

/**
 * @author florian
 *
 */
public class SchemaUtils {

	private List<Table> deleteOrder;
	private List<Table> insertOrder;
	private Map<Table, List<Table>> distinctDependencies;
	
	/**
	 * get the order of tables in delete order
	 * @param database
	 * @return List<Table>
	 */
	public List<Table> getTableDeleteOrder (Database database) {
		if (deleteOrder==null)
			deleteOrder = getTableDeleteOrderList(database);
		return deleteOrder;
	}
	
	private List<Table> getTableDeleteOrderList (Database database) {
		List<Table> workingSet = getTables(database); //copy of the tables + ref
		List<Table> deleteOrderList = getNonReferencedTable(workingSet);
//		int nbInDeleteOrderList = deleteOrderList.size();
		int deleteOrderListLastSize = 0;
		int twiceDeleteOrderListLastSize = 0;//init
		while (workingSet.size()>0 && deleteOrderList.size()>twiceDeleteOrderListLastSize) {
			deleteOrderListLastSize = deleteOrderList.size();
			getTableDeleteOrder(workingSet, deleteOrderList);
			if (deleteOrderListLastSize == deleteOrderList.size()) {
				// no further delete order where found for this pass 
				if (twiceDeleteOrderListLastSize<deleteOrderListLastSize)
					//so lets iterate again to be sure
					twiceDeleteOrderListLastSize = deleteOrderList.size();
				else
					twiceDeleteOrderListLastSize++;
			}
//			} else ;// add the last elements
				//addLastTables (workingSet, deleteOrderList);
		}
		if (workingSet.size()>0) {
			//recursivity
			
		}
		return deleteOrderList;
	}
	
	private void addLastTables (List<Table> workingSet, List<Table> deleteOrderList) {
		for (Table table : workingSet) {
			deleteOrderList.add(table);
		}
	}
	/**
	 * get the order of tables in insert order
	 * @param database
	 * @return List<Table>
	 */
	public List<Table> getTableInsertOrder (Database database) {
		if (insertOrder==null)
			insertOrder = getTableInsertOrderList(database);
		return insertOrder;		
	}
	
	private List<Table> getTableInsertOrderList (Database database) {
		List<Table> deleteOrder = getTableDeleteOrderList(database);
	    Collections.reverse(deleteOrder);
	    return deleteOrder;
	}	
	
	/**
	 * the list of tables that an input tables depends on
	 * @param table, database
	 * @return List<Table>
	 */
	public List<Table> getDistinctTransitiveDependencies (Table table) {
		if (table==null)
			return new ArrayList<Table>();
		if (!getDistinctDependencies().containsKey(table))
			addDistinctDependencies(table, getDistinctTransitiveDependenciesForTable(table));
		return getDistinctDependencies().get(table);
	}
	
	private List<Table> getDistinctTransitiveDependenciesForTable (Table table) {
		Map<String, Table> distinctDep = new Hashtable<String, Table>();
		populateDistinctTransitiveDependencies(table, distinctDep);
		return new ArrayList<Table>(distinctDep.values());
	}
	
	private void populateDistinctTransitiveDependencies (Table table, Map<String, Table> distinctDep) {
		List<Table> parents = TableUtils.getParents(table);
		for (Table parent : parents) {
			String parentName = parent.getName();
			if (!parentName.equals(table.getName()) && !distinctDep.containsKey(parentName)) {
				distinctDep.put(parentName, parent);
				populateDistinctTransitiveDependencies(parent, distinctDep);
			}
		}
	}
	/**
	 * @param workingSet
	 * @param deleteOrderList
	 */
	public void getTableDeleteOrder (List<Table> workingSet, List<Table> deleteOrderList) {
		List<Table> tablesToRemove = new ArrayList<Table>();
		for (Table table : workingSet) {
			if (table.getChildren()==null || table.getChildren().length==0 || areAllReferenceInDeleteOrderList(table, deleteOrderList)) {
				deleteOrderList.add(table);
				tablesToRemove.add(table);
			}
			
		}
		for (Table table : tablesToRemove) {
			workingSet.remove(table);
		}
//		return deleteOrderList;
	}
	
	private boolean areAllReferenceInDeleteOrderList (Table table, List<Table> deleteOrderList) {
		for (Reference reference : table.getChildren()) {
			if (isStillReferenceInWorkingSet(reference, deleteOrderList))
				return false;
		}
		return true;
	}
	
	private boolean isStillReferenceInWorkingSet (Reference reference, List<Table> deleteOrderList) {
		return !isNotReferenceInDeleteOrderList(reference, deleteOrderList);
	}
	
	private boolean isNotReferenceInDeleteOrderList (Reference reference, List<Table> deleteOrderList) {
		for (Table tableDelete : deleteOrderList) {
			if (reference.getForeignTableName().equals(tableDelete.getName()))
				return true;
		}		
		return false;
	}
	
	private List<Table> getTables (Database database) {
		List<Table> tables = new ArrayList<Table>();
		for (int i = 0; i < database.getTables().length; i++) {
			tables.add(database.getTables()[i]);
		}
		return tables;
	}
	
	private List<Table> getNonReferencedTable (List<Table> tables) {
		List<Table> nonReferencedTables = new ArrayList<Table>();
		List<Table> tablesToRemove = new ArrayList<Table>();

		for (Table table : tables) {
			if (table.getChildren()==null || table.getChildren().length==0) {
				nonReferencedTables.add(table);
				tablesToRemove.add(table);
			}
		}

		for (Table table : tablesToRemove) {
			tables.remove(table);
		}
		
		return nonReferencedTables;
	}

	public Map<Table, List<Table>> getDistinctDependencies() {
		if (distinctDependencies==null)
			distinctDependencies = new Hashtable<Table, List<Table>>();
		return distinctDependencies;
	}

	public void addDistinctDependencies(Table table, List<Table> distinctDependencies) {
		getDistinctDependencies().put(table, distinctDependencies);
	}

}
