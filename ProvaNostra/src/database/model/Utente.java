package database.model;

public class Utente {

	private String nickName;
	private String email;
	private String telefonNumber;
	private String pathImage;


	public Utente() {
		
	}

	public Utente(String nickName, String email, String telefonNumber, String pathImage) {
		this.nickName = nickName;
		this.email = email;
		this.telefonNumber = telefonNumber;
		this.pathImage = pathImage;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefonNumber() {
		return telefonNumber;
	}

	public void setTelefonNumber(String telefonNumber) {
		this.telefonNumber = telefonNumber;
	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}

	
	
}