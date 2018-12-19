package database.persistence;


import org.apache.commons.dbcp2.BasicDataSource;

import database.persistence.dao.UtenteDAO;
import database.persistence.jdbc.UtenteJDBC;
import utilis.Configuration;
import utilis.Utils;

public class PostgreDAOFactory {

	private static BasicDataSource basicDataSource;

	static {
		try {
			Configuration config = (Configuration) Utils.getJsonFile(Configuration.class, Utils.CONFIG_PATH_DB);

			String url = String.format("jdbc:%s://%s:%s/%s", config.jdbc, config.host, config.port, config.database);

			String username = config.username;
			String password = config.password;
			String driver = config.driver;

			basicDataSource = new BasicDataSource();
			basicDataSource.setDriverClassName(driver);
			basicDataSource.setUrl(url);
			basicDataSource.setUsername(username);
			basicDataSource.setPassword(password);
			basicDataSource.setMaxIdle(0);
		} catch (Exception e) {
			
		}
	}

	/**
	 * Metodo fabbrica
	 * @return
	 */
	public UtenteDAO getUtenteDAO() {
		return new UtenteJDBC(basicDataSource);
	}

}
