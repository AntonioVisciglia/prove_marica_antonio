package database.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import database.persistence.exception.PersistenceException;

class DataSource {
	final private String dbURI;
	final private String userName;
	final private String password;

	public DataSource(String dbURI, String userName, String password) {
		this.dbURI = dbURI;
		this.userName = userName;
		this.password = password;
	}

	public Connection getConnection() throws PersistenceException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(dbURI, userName, password);
			return connection;
		} catch (SQLException e) {
			throw new PersistenceException(e.getErrorCode());
		}
	}

	
}