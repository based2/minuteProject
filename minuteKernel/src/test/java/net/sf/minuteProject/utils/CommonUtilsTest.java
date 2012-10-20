package net.sf.minuteProject.utils;

import net.sf.minuteProject.configuration.bean.GeneratorBean;
import net.sf.minuteProject.configuration.bean.Package;
import net.sf.minuteProject.configuration.bean.Template;
import net.sf.minuteProject.configuration.bean.model.data.Table;
import net.sf.minuteProject.configuration.bean.model.data.impl.DDLUtils.TableDDLUtils;
import junit.framework.TestCase;
import static net.sf.minuteProject.utils.CommonUtils.*;

public class CommonUtilsTest extends TestCase{

	//TODO disable technical package, business package, model package
	//TODO convention packaging order (technical, business, model combination)
	public void testGetClassName() {
		String s = getClassName(getSampleTable(), getSampleDomainTemplate());
		System.out.println("s = "+s);
		assertNotNull(s);
	}

	private Template getSampleDomainTemplate() {
		Template t = new Template();
		t.setName("DomainObjectUML");
		
		return t;
	}

	private GeneratorBean getSampleTable() {
		Table t = new TableDDLUtils(getTableDDLUtils());
		t.setPackage(getPackage());
		return t;
	}

	private Package getPackage() {
		Package p = new Package();
	
		return p;
	}

	private org.apache.ddlutils.model.Table getTableDDLUtils() {
		org.apache.ddlutils.model.Table t = new org.apache.ddlutils.model.Table();
		t.setName("TEST");
		return t;
	}
}
