package database.gui_prova;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import database.model.Utente;
import database.persistence.PostgreDAOFactory;
import database.persistence.dao.UtenteDAO;

public class Main {

	public static void main(String[] args) {
		try {
			PostgreDAOFactory postgreDAOFactory = new PostgreDAOFactory();
			UtenteDAO utenteDAO = postgreDAOFactory.getUtenteDAO();
			
			utenteDAO.deleteUtente("antonio01.visciglia@tagete.net");

			Utente utente = new Utente("antonio01.visciglia@tagete.net", "Antonio", "+393275407014", "resources/images/a4.jpg");
			utenteDAO.registra(utente);

			Utente ute = utenteDAO.findByEmail("antonio01.visciglia@tagete.net");

			Image im = ImageIO.read(new File(ute.getPathImage()));
			System.out.println(im.getWidth(null));

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
