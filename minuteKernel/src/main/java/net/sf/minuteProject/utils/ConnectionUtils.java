package net.sf.minuteProject.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

import net.sf.minuteProject.configuration.bean.DataModel;

public class ConnectionUtils {

	public static Connection getConnection (DataModel dataModel) {
		BasicDataSource basicDataSource = dataModel.getBasicDataSource();
	    String driver = basicDataSource.getDriverClassName();
	    String url = basicDataSource.getUrl();
	    String username = basicDataSource.getUsername();
	    String password = basicDataSource.getPassword();

	    try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username, password);
			return conn;
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // load Oracle driver
	    catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return null;		
	}
}
