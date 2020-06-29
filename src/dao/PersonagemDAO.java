package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Personagem;

public class PersonagemDAO extends Personagem{
	public List<Personagem> listarPersonagens(){
		List<Personagem> personagens = new ArrayList<Personagem>();
		
		try {
			Connection con = new ConnectionFactory().getConnection();
			PreparedStatement smt = con.prepareStatement("SELECT * FROM PERSONAGEM");
			ResultSet rs = smt.executeQuery();
			
			while(rs.next()) {
				Personagem p = new Personagem();
				p.setID_PERSONAGEM(rs.getInt("ID_PERSONAGEM"));
				p.setNOME(rs.getString("NOME"));
				p.setSEXO(rs.getString("SEXO"));
				p.setFK_ID_JOGADOR(rs.getInt("FK_ID_JOGADOR"));
				p.setID_REFERENCIA(rs.getString("ID_REFERENCIA"));
				
				personagens.add(p);
			}
			
			rs.close();
			smt.close();
			con.close();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return personagens;
		
	}
}
