package net.sf.minuteProject.application;

import java.util.Date;
import java.util.List;

import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.Model;
import net.sf.minuteProject.configuration.bean.Package;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.model.data.Component;
import net.sf.minuteProject.configuration.bean.model.data.Function;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.model.data.View;
import net.sf.minuteProject.exception.MinuteProjectException;
import net.sf.minuteProject.integration.bean.BasicIntegrationConfiguration;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;

/**
 * @author Florian Adler
 * 
 */
public class ModelViewGenerator extends ModelGenerator {

	private static Logger logger = Logger.getLogger(ModelGenerator.class);

	/**
	 * Constructs the generator with its configuration
	 * 
	 * @param configurationFile
	 */
	public ModelViewGenerator(String configurationFile) {
		super(configurationFile);
	}

	public ModelViewGenerator(BasicIntegrationConfiguration bic) {
		super(bic);
	}

	public static void main(String args[]) {
		String config;
		if (args.length < 1) {
			System.exit(1);
		}
		config = args[0];
		Date startDate = new Date();
		logger.info("start time = " + new Date());
		ModelViewGenerator generator = new ModelViewGenerator(config);

		try {
			generator.generate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Date endDate = new Date();
		logger.info("time taken : " + (endDate.getTime() - startDate.getTime())
				/ 1000 + "s.");
	}

	protected void loadModel(Model model) {
		super.loadModel(model);
		model.getBusinessModel().complementDataModelWithViews();
		model.getBusinessModel().complementService();
	}

	public Model getModel() {
		return super.getModel();
	}

	public void setModel(Model model) {
		super.setModel(model);
	}

	protected void generateArtifactsByEntity(Template template) throws MinuteProjectException {	
		super.generateArtifactsByEntity(template);
		for (View view :  getModel().getBusinessModel().getBusinessPackage().getViews()) {
			generateArtifactsByEntity (view, template);
		}
	}

	protected void generateArtifactsByField(Template template) throws MinuteProjectException {	
		super.generateArtifactsByField(template);
		for (View view :  getModel().getBusinessModel().getBusinessPackage().getViews()) {
			generateArtifactsByField(template, view);
		}
	}
	
	protected void generateArtifactsByPackage(Template template) throws MinuteProjectException {
		super.generateArtifactsByPackage(template);
		List<Package> packages = getModel().getBusinessModel().getBusinessPackage().getPackageViews();
		for (Package pack : packages) {
			writeTemplateResult(pack, template);
		}
	}
	
	protected void putCommonContextObject(VelocityContext context, Template template) {
		putStandardContextObject(context);
		putPluginContextObject(context, template);
		context.put("model", getModel());
	}

}
