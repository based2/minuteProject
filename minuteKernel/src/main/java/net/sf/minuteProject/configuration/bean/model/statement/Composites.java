package net.sf.minuteProject.configuration.bean.model.statement;

import java.util.ArrayList;
import java.util.List;

import net.sf.minuteProject.configuration.bean.AbstractConfiguration;
import net.sf.minuteProject.configuration.bean.StatementModel;

public class Composites extends AbstractConfiguration{

	public static final String DEFAULT_PACKAGE_NAME = "composite";

	private StatementModel statementModel;
	
	private List<Composite> composites;

	public StatementModel getStatementModel() {
		return statementModel;
	}

	public void setStatementModel(StatementModel statementModel) {
		this.statementModel = statementModel;
	}

	public List<Composite> getComposites() {
		if (composites==null)
			composites = new ArrayList<Composite>();
		return composites;
	}

	public void setComposites(List<Composite> composites) {
		this.composites = composites;
	}
	
	public void addComposite (Composite composite) {
		composite.setComposites(this);
		getComposites().add(composite);
	}
}
