package net.sf.minuteProject.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.TemplateTarget;
import net.sf.minuteProject.configuration.bean.model.data.Reference;

public class TemplateTargetUtils {
	
	public static List <TemplateTarget> getDistinctTemplateTargetDirs (Model model) {
		List list = new ArrayList<TemplateTarget>();
		for (Iterator<TemplateTarget> iter = model.getConfiguration().getTarget().getTemplateTargets().iterator(); iter.hasNext();) {
			TemplateTarget templateTarget = (TemplateTarget)iter.next();
			addTemplateTarget(list, templateTarget);
		}
		return list;
	}
	
	
    private static void addTemplateTarget (List list, TemplateTarget templateTarget) {
    	if (list==null) return;
    	boolean isAlreadyPresent = false;
    	for (Iterator<Reference> iter = list.iterator(); iter.hasNext();) {
    		if (((TemplateTarget)iter.next()).getOutputdir().equals(templateTarget.getOutputdir())) {
    		    isAlreadyPresent = true;
    		    break;
    		}
    	}
    	if (!isAlreadyPresent)
    		list.add(templateTarget);
    }
    
}
