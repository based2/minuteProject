package net.sf.minuteProject.configuration.bean.enrichment.convention;

import net.sf.minuteProject.configuration.bean.BusinessModel;

public abstract class ModelConvention extends Convention{

	public abstract void apply(BusinessModel model) ;
	
}
