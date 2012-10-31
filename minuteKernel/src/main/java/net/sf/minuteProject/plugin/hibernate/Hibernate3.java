package net.sf.minuteProject.plugin.hibernate;

import net.sf.minuteProject.configuration.bean.enumeration.DATABASEenum;
import net.sf.minuteProject.configuration.bean.model.data.Database;
import net.sf.minuteProject.configuration.bean.model.data.Table;

public class Hibernate3 {
	
	public static String getMany2ManyReadOnly(Table table) {
		return "read-only";
	}

    // src: http://docs.jboss.org/hibernate/orm/3.5/api/org/hibernate/dialect/package-summary.html
	public static String getDialect (Database database) {
        DATABASEenum type = database.getType();
		if (DATABASEenum.ORACLE==type)
			return "org.hibernate.dialect.Oracle10gDialect";
		else if (DATABASEenum.HSQLDB==type)
			return "org.hibernate.dialect.HSQLDialect";
		else if (DATABASEenum.MYSQL==type)
			return "org.hibernate.dialect.MySQLDialect";		
		else if (DATABASEenum.POSTGRESQL==type)
			return "org.hibernate.dialect.PostgreSQLDialect";		
		else if (DATABASEenum.DERBY==type)
			return "org.hibernate.dialect.DerbyDialect";
        else if (DATABASEenum.H2==type)
            return "org.hibernate.dialect.H2Dialect";
		return "wrongDialect";
	}

	public static String getDialect () {
		return "wrongDialect";
	}

}
