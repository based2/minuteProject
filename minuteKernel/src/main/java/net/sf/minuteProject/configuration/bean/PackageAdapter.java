package net.sf.minuteProject.configuration.bean;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.enrichment.group.FieldGroup;
import net.sf.minuteProject.configuration.bean.enrichment.group.Group;
import net.sf.minuteProject.configuration.bean.model.data.Column;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.utils.ColumnUtils;

public abstract class PackageAdapter<T extends Group, E extends GeneratorBean> extends AbstractConfiguration{

	protected List<T> groups;
	private List<List<E>> groupsList;
	
	public void setGroups(List<T> groups) {
		this.groups = groups;
	}
	
	public void setDefaultGroup (T group) {
		getGroups().add(group);
	}
	
	public abstract T getDefaultGroup ();
	
//	public abstract List<T> getGroups();
	public List<T> getGroups() {
		if (groups==null) 
			groups=new ArrayList<T>();
		return groups;
	}
	
	public List<List<E>> getGroupsList () {
		if (groupsList==null) {
			groupsList = new ArrayList<List<E>>();
			List<E> es = new ArrayList<E>();
			for (E e : getElements()) {
				if (!contains(es, e)) {
					List<E> eGroup = getElementGroup(e);
					groupsList.add(eGroup);
					es.addAll(eGroup);
				}
			}
		}
		return groupsList;
	}

	public abstract List<E> getElements() ;

	private List<E> getElementGroup(E e) {
		List<E> es = new ArrayList<E>();
		for (T t : getGroups()) {
			if (groupContainsElement(t, e)) {
				return convertGroupToElement(t);
			}
		}
		es.add(e);
		return es;
	}
	
	protected abstract List<E> convertGroupToElement(T t); 
	
	private boolean groupContainsElement(T t, E e) {
		for (String element : t.getList()) {
			if (element.equals(e.getName())) {
				return true;
			}
		}
		return false;
	}
	
	private boolean contains(List<E> es, E e) {
		for (E ee : es) {
			if (ee.getName().equals(e.getName()))
				return true;
		}
		return false;
	}
	
}
