package net.sf.minuteProject.configuration.bean;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.enrichment.convention.Convention;
import net.sf.minuteProject.configuration.bean.enrichment.convention.Conventions;
import net.sf.minuteProject.configuration.bean.enrichment.convention.KernelConvention;
import net.sf.minuteProject.configuration.bean.environment.Environment;
import net.sf.minuteProject.configuration.bean.presentation.Presentation;

public class Configuration extends AbstractConfigurationRoot{
	
	private String catalogDir;
	private Model model;
	private Presentation presentation;
	private Conventions conventions;
	private List<Environment> environments;
	
	public Environment getEnvironmentByName (String environmentName) {
		if (environmentName==null) return null;
		for (Environment env : getEnvironments()) {
			if (env.isOfType(environmentName))
				return env;
		}
		return null;
	}
	
	public void addEnvironment (Environment environment) {
		environment.setConfiguration(this);
		getEnvironments().add(environment);
	}
	
	public List<Environment> getEnvironments() {
		if (environments == null)
			initEnvironments();
		return environments;
	}

	private void initEnvironments() {
		environments = new ArrayList<Environment>();
	}
	
	public Presentation getPresentation() {
		return presentation;
	}

	public void setPresentation(Presentation presentation) {
		presentation.setConfiguration(this);
		this.presentation = presentation;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		model.setConfiguration(this);
		this.model = model;
	}
	
	public String getTechnicalPackage(Template template) {
		return template.getPackageRoot();
	}
	
	public String getName () {
		if (super.getName()==null)
			return model.getName();
		return super.getName();
	}

	public String getGeneratedBeanName() {
		return getName();
	}

	public String getCatalogDir() {
		return catalogDir;
	}

	public void setCatalogDir(String catalogDir) {
		this.catalogDir = catalogDir;
	}

	public void applyConventions() {
		for (KernelConvention convention : getConventions().getKernelConventions()) {
			convention.apply (this);
		}
	}

	public Conventions getConventions() {
		if (conventions==null) conventions = new Conventions();
		return conventions;
	}

	public void setConventions(Conventions conventions) {
		this.conventions = conventions;
	}
	
}
