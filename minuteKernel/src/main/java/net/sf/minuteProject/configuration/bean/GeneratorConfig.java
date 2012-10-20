package net.sf.minuteProject.configuration.bean;

import java.util.ArrayList;
import java.util.List;

public class GeneratorConfig {
	private List configurations;

	public void addConfiguration(Configuration configuration) {
		if (configurations==null)
			configurations = new ArrayList();
		configurations.add(configuration);
	}
	
	public List getConfigurations() {
		return configurations;
	}

	public void setConfigurations(List configurations) {
		this.configurations = configurations;
	}
}
