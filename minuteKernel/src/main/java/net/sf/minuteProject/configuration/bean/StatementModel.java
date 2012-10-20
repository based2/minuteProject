package net.sf.minuteProject.configuration.bean;

import net.sf.minuteProject.configuration.bean.model.data.Database;
import net.sf.minuteProject.configuration.bean.model.statement.Composites;
import net.sf.minuteProject.configuration.bean.model.statement.Queries;

import org.apache.log4j.Logger;

public class StatementModel {
	
	private static Logger logger = Logger.getLogger(StatementModel.class);
	private Queries queries;
	private Composites composites;
	private SDDPackage sddPackage;
	
	private Model model;
	
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public Queries getQueries() {
		if (queries==null) {
			queries = new Queries();
		}
		return queries;
	}
	public Composites getComposites() {
		if (composites==null)
			composites = new Composites();
		return composites;
	}
	public SDDPackage getSddPackage() {
		if (sddPackage==null) sddPackage = new SDDPackage(this);
		return sddPackage;
	}
	public void setSddPackage(SDDPackage sddPackage) {
		this.sddPackage = sddPackage;
	}
	public void setQueries(Queries queries) {
		queries.setStatementModel(this);
		this.queries = queries;
	}
	public void setComposites(Composites composites) {
		composites.setStatementModel(this);
		this.composites = composites;
	}
	
	public void complementStatement() {
		Database database = model.getDataModel().getDatabase();
		getSddPackage().setPackages(model, database);
	}
	
	public boolean hasQueries () {
		return (getQueries().getQueries().size()>0)?true:false;
	}
	
	public boolean hasComposites () {
		return (getComposites().getComposites().size()>0)?true:false;
	}
}
