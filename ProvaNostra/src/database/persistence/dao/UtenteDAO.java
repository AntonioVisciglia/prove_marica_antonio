package database.persistence.dao;

import database.model.Utente;

public interface UtenteDAO {

	public void registra(Utente utente) throws Exception;

	public void deleteUtente(String email) throws Exception;

	public Utente findByEmail(String email) throws Exception;
}
