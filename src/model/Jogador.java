package model;

public class Jogador {
	private String ID_JOGADOR;
	private String LOGIN_JOGADOR;
	private String SENHA_JOGADOR;
	
	public String getID_JOGADOR() {
		return ID_JOGADOR;
	}
	public void setID_JOGADOR(String iD_JOGADOR) {
		ID_JOGADOR = iD_JOGADOR;
	}
	public String getLOGIN_JOGADOR() {
		return LOGIN_JOGADOR;
	}
	public void setLOGIN_JOGADOR(String lOGIN_JOGADOR) {
		LOGIN_JOGADOR = lOGIN_JOGADOR;
	}
	public String getSENHA_JOGADOR() {
		return SENHA_JOGADOR;
	}
	public void setSENHA_JOGADOR(String sENHA_JOGADOR) {
		SENHA_JOGADOR = sENHA_JOGADOR;
	}
	
	@Override
	public String toString() {
		return "Jogador [ID_JOGADOR=" + ID_JOGADOR + ", LOGIN_JOGADOR=" + LOGIN_JOGADOR + ", SENHA_JOGADOR="
				+ SENHA_JOGADOR + "]";
	}
	
	
	
}
