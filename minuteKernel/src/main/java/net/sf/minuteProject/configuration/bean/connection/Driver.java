package net.sf.minuteProject.configuration.bean.connection;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;

public class Driver extends AbstractConfiguration{
	
	private String version, groupId, artifactId;

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	
}
