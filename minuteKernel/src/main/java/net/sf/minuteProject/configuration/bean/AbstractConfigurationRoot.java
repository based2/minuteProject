package net.sf.minuteProject.configuration.bean;

public abstract class AbstractConfigurationRoot extends AbstractConfiguration{
	
	private Target target;
	private String projectname;
	private Targets targets;
	
	public String getProjectname() {
		if (this.projectname == null)
			return getName();
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public Target getTarget() {
		return target;
	}

	public void setTarget(Target target) {
		setTarget(target, this);
//		target.setAbstractConfigurationRoot(this);
//		this.target = target;
	}
	
	protected void setTarget(Target target, AbstractConfigurationRoot abstractConfigurationRoot) {
		target.setAbstractConfigurationRoot(abstractConfigurationRoot);
		this.target = target;
	}

	public Targets getTargets() {
		return targets;
	}

	public void setTargets(Targets targets) {
		targets.setAbstractConfigurationRoot(this);
		this.targets = targets;
	}

	public void addTarget(Target target) {
		getTargets().addTarget(target);
	}
	
	public boolean hasTarget () {
		return (getTarget()==null)?false:true;
	}
	
	public boolean hasTargets () {
		return (getTargets()==null)?false:true;
	}
}
