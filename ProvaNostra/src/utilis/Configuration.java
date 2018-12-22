package utilis;

/**
 * Classe delegata ad avere Stringhe utilizzate dalle classi del codice
 * per avere i valori del file .json
 * @author Antonio
 *
 */
public class Configuration {

	/*
	 * email
	 */
	public String userEmail;
	public String userPassword;

	/*
	 * sms
	 */
	public String baseUrlSms;
	public String messageQuality;
	public String userNameSms;
	public String userPasswordSms;
	public String messageBody;
	public String recipient;

	/*
	 * DB set
	 */
	public String jdbc;
	public String host;
	public String port;
	public String database;
	public String username;
	public String password;
	public String driver;

	/*
	 * DB query
	 */
	public String insertUser;
	public String findByEmail;
}