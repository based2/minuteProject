package net.sf.minuteProject.configuration.bean.enrichment.convention;

import net.sf.minuteProject.configuration.bean.Configuration;
import net.sf.minuteProject.configuration.bean.Target;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.TemplateTarget;

public class TargetConvention extends KernelConvention{
	
	public static final String ENABLE_UPDATABLE_CODE = "enable-updatable-code-feature";

	@Override
	public void apply(Configuration configuration) {
		if (ENABLE_UPDATABLE_CODE.equals(type))
			applyEnableUpdatableCode(configuration);
	}

	private void applyEnableUpdatableCode(Configuration configuration) {
		Target target = configuration.getTarget();
		applyEnableUpdatableCode(target);
		for (Target t : configuration.getTargets().getTargets())
			applyEnableUpdatableCode(t);
	}

	private void applyEnableUpdatableCode(Target target) {
		// TODO Auto-generated method stub
		for (TemplateTarget tt : target.getTemplateTargets()) {
			for (Template t : tt.getTemplates())
				applyEnableUpdatableCode(t);
		}
	}

	private void applyEnableUpdatableCode(Template t) {
		t.setUpdatable(true);
	}
}
