package net.sf.minuteProject.configuration.bean.model.data;

import java.util.List;

import org.apache.ddlutils.model.Column;

import net.sf.minuteProject.configuration.bean.model.data.impl.DDLUtils.TableDDLUtils;
import net.sf.minuteProject.utils.ComponentUtils;
import junit.framework.TestCase;

public class ComponentTest extends TestCase{

	//create components from table (with column)
	public void testCreateComponentFromTable() {
		org.apache.ddlutils.model.Table tableOrig = new org.apache.ddlutils.model.Table();
		Column columnA = new Column();
		columnA.setName("A");
		Column columnA_B = new Column();
		columnA_B.setName("B__C");		
		tableOrig.addColumn(columnA);
		tableOrig.addColumn(columnA_B);
		tableOrig.setName("TEST");
		Table table = new TableDDLUtils(tableOrig);
		List<Component> list = ComponentUtils.getComponent(table);
		assertTrue(list.size()==2);
		Component component = list.get(0);
		assertTrue(component.getName().equals("Test"));
		assertTrue(component.getColumns().length==1);
		net.sf.minuteProject.configuration.bean.model.data.Column comp0col0 = component.getColumns()[0];
		assertTrue(comp0col0.getName().equals("A"));
		component = list.get(1);
		assertTrue(component.getColumns().length==1);
		net.sf.minuteProject.configuration.bean.model.data.Column comp1col0 = component.getColumns()[0];
		assertTrue(component.getName().equals("B"));
		assertTrue(comp1col0.getName().equals("C"));
	}
}
