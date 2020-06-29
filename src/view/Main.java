package view;

import dao.PersonagemDAO;
import model.Personagem;

public class Main {

	public static void main(String[] args) {
		PersonagemDAO personagem_dao = new PersonagemDAO();
		
		for(Personagem p : personagem_dao.listarPersonagens()) {
			System.out.println(p.toString());
		}

	}

}
