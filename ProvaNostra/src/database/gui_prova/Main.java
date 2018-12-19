package database.gui_prova;

import database.model.Utente;
import database.persistence.PostgreDAOFactory;
import database.persistence.dao.UtenteDAO;

public class Main {

	public static void main(String[] args) {
		try {
			PostgreDAOFactory postgreDAOFactory = new PostgreDAOFactory();
			UtenteDAO utenteDAO = postgreDAOFactory.getUtenteDAO();

			Utente utente = new Utente("Antonio", "antonio01.visciglia@tagete.net", "+393275407014", "boh/boh");

			utenteDAO.registra(utente);
		} catch (Exception e) {
			System.err.println("MMMM");
		}
	}
}
