package net.sf.minuteProject.configuration.bean.environment;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.Configuration;

public class Environment extends AbstractConfiguration{

	private Configuration configuration;
	
	public enum environment {local, loc, test, tst, development, dev, acceptance, acc, production, prod}
	
	public boolean isLocal(String name) {
		return (environment.loc.toString().equals(name) ||
				environment.local.toString().equals(name));
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public boolean isOfType(String environmentName) {
		return (name!=null && name.equals(environmentName));
	}
	
}
