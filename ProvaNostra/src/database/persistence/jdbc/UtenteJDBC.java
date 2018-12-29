package database.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import database.model.Utente;
import database.persistence.dao.UtenteDAO;
import database.persistence.exception.PersistenceException;
import utilis.Configuration;
import utilis.Utils;

public class UtenteJDBC implements UtenteDAO {

	private BasicDataSource basicDataSource;

	public UtenteJDBC(BasicDataSource basicDataSource) {
		this.basicDataSource = basicDataSource;
	}

	@Override
	public void registra(Utente utente) throws Exception {

		Connection conn = null;
		PreparedStatement statement = null;

		try {

			conn = basicDataSource.getConnection();

			Configuration config = (Configuration) Utils.getJsonFile(Configuration.class, Utils.DB_PATH_QUERY);
			statement = conn.prepareStatement(config.insertUser);

			statement.setString(1, utente.getEmail());
			statement.setString(2, utente.getNickName());
			statement.setString(3, utente.getTelefonNumber());
			statement.setString(4, utente.getPathImage());
			statement.setBoolean(5, utente.isAdministrator());

			statement.executeUpdate();

		} catch (SQLException e) {

			if (e.getSQLState().equals("23505")) {
				throw new PersistenceException(10006L);
			} else {
				throw e;
			}

		} finally {
			try {
				if (statement != null)
					statement.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}

	}

	@Override
	public Utente findByEmail(String email) throws Exception {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {

			connection = basicDataSource.getConnection();

			Configuration config = (Configuration) Utils.getJsonFile(Configuration.class, Utils.DB_PATH_QUERY);

			statement = connection.prepareStatement(config.findByEmail);
			statement.setString(1, email);
			resultSet = statement.executeQuery();

			Utente user = null;

			while (resultSet.next()) {

				if (user == null) {
					user = new Utente();
				}
				
				user.setEmail(resultSet.getString("email"));
				user.setNickName(resultSet.getString("nick_name"));
				user.setPathImage(resultSet.getString("image_path"));
				user.setTelefonNumber(resultSet.getString("phone_number"));

			}

			return user;

		} catch (SQLException e) {
			throw e;
		} catch (ParseException e) {
			throw e;
		} finally {
			if (resultSet != null)
				resultSet.close();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}

	}

	@Override
	public void deleteUtente(String email) throws Exception {
		Connection conn = null;
		PreparedStatement statement = null;

		try {
			
			conn = basicDataSource.getConnection();

			Configuration config = (Configuration) Utils.getJsonFile(Configuration.class, Utils.DB_PATH_QUERY);

			statement = conn.prepareStatement(config.deleteUtenteByEmail);
			statement.setString(1, email);

			statement.executeUpdate();

		} catch (SQLException e) {

			if (e.getSQLState().equals("23505")) {
				throw new PersistenceException(10006L);
			} else {
				throw e;
			}

		} finally {
			try {
				if (statement != null)
					statement.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
	}

}
