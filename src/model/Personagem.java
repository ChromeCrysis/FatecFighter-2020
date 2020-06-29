package model;

public class Personagem {
	private int ID_PERSONAGEM;
	private String NOME;
	private String SEXO;
	private int FK_ID_JOGADOR;
	private String ID_REFERENCIA;
	public int getID_PERSONAGEM() {
		return ID_PERSONAGEM;
	}
	public void setID_PERSONAGEM(int iD_PERSONAGEM) {
		ID_PERSONAGEM = iD_PERSONAGEM;
	}
	public String getNOME() {
		return NOME;
	}
	public void setNOME(String nOME) {
		NOME = nOME;
	}
	public String getSEXO() {
		return SEXO;
	}
	public void setSEXO(String sEXO) {
		SEXO = sEXO;
	}
	public int getFK_ID_JOGADOR() {
		return FK_ID_JOGADOR;
	}
	public void setFK_ID_JOGADOR(int fK_ID_JOGADOR) {
		FK_ID_JOGADOR = fK_ID_JOGADOR;
	}
	public String getID_REFERENCIA() {
		return ID_REFERENCIA;
	}
	public void setID_REFERENCIA(String iD_REFERENCIA) {
		ID_REFERENCIA = iD_REFERENCIA;
	}
	@Override
	public String toString() {
		return "Personagem [ID_PERSONAGEM=" + ID_PERSONAGEM + ", NOME=" + NOME + ", SEXO=" + SEXO + ", FK_ID_JOGADOR="
				+ FK_ID_JOGADOR + ", ID_REFERENCIA=" + ID_REFERENCIA + "]";
	}
	
	
}
