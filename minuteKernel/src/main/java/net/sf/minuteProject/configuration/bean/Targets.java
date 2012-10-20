package net.sf.minuteProject.configuration.bean;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.system.Plugin;
import net.sf.minuteProject.configuration.bean.target.ImportTargets;
import net.sf.minuteProject.configuration.bean.target.TargetParams;
import net.sf.minuteProject.utils.io.FileUtils;

/**
 * @author Florian Adler
 *
 */
public class Targets extends AbstractConfiguration {
	
	private String outputdirRoot, templatedirRoot;
	private AbstractConfigurationRoot abstractConfigurationRoot;
	private List<Target> targets;

	public List<Target> getTargets() {
		if (targets==null)
			targets = new ArrayList<Target>();
		return targets;
	}

	public void setTargets(List<Target> targets) {
		this.targets = targets;
	}
	
	public AbstractConfigurationRoot getAbstractConfigurationRoot() {
		return abstractConfigurationRoot;
	}
	public void setAbstractConfigurationRoot(
			AbstractConfigurationRoot abstractConfigurationRoot) {
		this.abstractConfigurationRoot = abstractConfigurationRoot;
	}
	
	public void addTarget(Target target) {
		target.setTargets(this);
		target.setAbstractConfigurationRoot(getAbstractConfigurationRoot());
		getTargets().add(target);
	}

	public String getOutputdirRoot() {
		return outputdirRoot;
	}

	public void setOutputdirRoot(String outputdirRoot) {
		this.outputdirRoot = outputdirRoot;
	}

	public String getTemplatedirRoot() {
		return templatedirRoot;
	}

	public void setTemplatedirRoot(String templatedirRoot) {
		this.templatedirRoot = templatedirRoot;
	}
	
}
