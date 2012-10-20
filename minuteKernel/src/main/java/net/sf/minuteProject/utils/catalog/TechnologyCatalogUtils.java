package net.sf.minuteProject.utils.catalog;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.loader.catalog.technologycatalog.node.Framework;
import net.sf.minuteProject.loader.catalog.technologycatalog.node.Technology;
import net.sf.minuteProject.utils.parser.ParserUtils;

public class TechnologyCatalogUtils extends CatalogUtils{

	private static List<Technology> technologies;
	
	public static Technology getPublishedTechnology(String name, String catalogDir) {
		for (Technology technology : getPublishedTechnologies (catalogDir)) {
			if (technology.getName().equals(name))
				return technology;
		}		
		return null;
	}
	
	public static List<Technology> getPublishedTechnologies (String catalogDir) {
		if (technologies==null)
			technologies = getPublishedTechnologyCatalogHolder(catalogDir).getTechnologyCatalog().getTechnologies().getTechnologys();
		return technologies;
	}
	
	public static String[] getPublishedTechnologyNames(String catalogDir) {
		List<String> list = new ArrayList<String>();
		for (Technology technology : getPublishedTechnologies(catalogDir)) {
			if (technology.isGenerable())
				list.add(technology.getName());
		}
		return (String[])list.toArray(new String[list.size()]);
	}
	
	public static List<Technology> getDependentTechnologies (Technology technology, String catalogDir) {
		List<Technology> list = new ArrayList<Technology>();
		List<String> targets = ParserUtils.getList(technology.getDependsOnTargets());
		for (String targetName : targets) {
			Technology tech = getTechnologyByTargetName(targetName, catalogDir);
			if (tech!=null)
				list.add(tech);
		}
		return list;
	}

	public static Technology getTechnologyByTargetName(String name, String catalogDir) {
		for (Technology technology : getPublishedTechnologies(catalogDir)) {
			if (technology.getTargetName().equals(name))
				return technology;
		}		
		return null;
	}

	public static List<Technology> getAllRelatedTechnologies (Technology technologyRoot, String catalogDir) {
		List<Technology> list = new ArrayList<Technology>();
		List<Technology> allTechnos = getPublishedTechnologies(catalogDir);
		List<Technology> dependentTechnologies = getDependentTechnologies (technologyRoot, catalogDir);
		for (Technology technology2 : allTechnos) {
			if (isDependent(technologyRoot, technology2, dependentTechnologies)) {
				technology2.getProperties().addAll(technologyRoot.getProperties());
				list.add(getDependentTechnology(technology2, true));
			} else {
				list.add(getDependentTechnology(technology2, false));
			}
		}
		return list;
	}

	private static boolean isDependent(Technology technologyroot, Technology technology, List<Technology> dependentTechnologies) {
		if (technologyroot==technology) return true;
		for (Technology technology2 : dependentTechnologies) {
			if (technology.equals(technology2))
				return true;
		}
		return false;
	}

	private static Technology getDependentTechnology(Technology technology2, boolean isGenerable) {
//
//		if (technology2.isGenerable() && !isGenerable)
//			technology2.setIsGenerable(false);		
		if (!isGenerable)
			technology2.setIsGenerable(false);
		else
			technology2.setIsGenerable(true);
		return technology2;
	}
	
	public static void resetTechnologies () {
		technologies = null;
		CatalogUtils.resetTechnologycatalogHolder();
	}

	public static String[][] getFrameworkDependency(Technology technology, String catalogDir) {
		List<Framework> frameworks = getAllLinkedFrameworkDependencies(technology, catalogDir);
		String [][] array = new String [frameworks.size()][2];
		int i=0;
		for (Framework framework : frameworks) {
			array[i][0]=framework.getName();
			array[i][1]=framework.getVersion();
			i++;
		}
		return array;
	}
	
	public static List<Framework> getAllLinkedFrameworkDependencies(Technology technology, String catalogDir) {
		List<Framework> frameworks = new ArrayList<Framework>();
		frameworks.addAll(getFrameworkDependencies(technology));
		frameworks.addAll(getRelatedFrameworkDependencies(technology, catalogDir));
		return frameworks;
	}
	
	public static List<Framework> getFrameworkDependencies(Technology technology) {
		List<Framework> frameworks = new ArrayList<Framework>();
		for (Framework framework : technology.getFrameworks().getFrameworks()) {
			frameworks.add(framework);
		}
		return frameworks;
	}
	
	public static List<Framework> getRelatedFrameworkDependencies(Technology technology, String catalogDir) {
		List<Framework> frameworks = new ArrayList<Framework>();
		List<Technology> technos = getDependentTechnologies(technology, catalogDir);
		for (Technology technology2 : technos) {
			frameworks.addAll(getFrameworkDependencies(technology2));
		}
		return frameworks;
	}
	
}
