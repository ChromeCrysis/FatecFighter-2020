package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Jogador;

import view.TelaCadastro;

public class JogadorDAO {
	TelaCadastro tela_cadastro = new TelaCadastro();
	
	Connection conn = new ConnectionFactory().getConnection();
	Jogador jogador = new Jogador();
	
	public void Salvar() {
		try {
			//PreparedStatement stm = conn.prepareStatement("insert into JOGADOR(LOGIN_JOGADOR, SENHA_JOGADOR) values ('"+ txtLogin.getText() +"', '"+ + "');")
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
