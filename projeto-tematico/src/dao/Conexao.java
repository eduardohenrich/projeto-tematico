package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import config.Config;


public class Conexao {
		
	public Connection getConnection() throws SQLException {

		String host = Config.getDbHost();
		String port = Config.getDbPort();
		String dbName = Config.getDbName();
		String user = Config.getDbUser();
		String pass = Config.getDbPass();

		String url = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;

        return DriverManager.getConnection(url, user, pass);
	}
	
}