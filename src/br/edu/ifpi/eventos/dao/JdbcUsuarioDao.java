package br.edu.ifpi.eventos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import br.edu.ifpi.eventos.modelo.Usuario;
public class JdbcUsuarioDao {
	Connection connection;
	
	public JdbcUsuarioDao(){
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void adiciona(Usuario usuario){
		String sql = "insert into usuario (nome, login, senha) " +
					"values (?, ?, ?)";
		
		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getLogin());
			stmt.setString(3, usuario.getSenha());
			
			stmt.execute();
			stmt.close();
		}catch (SQLException e){
			throw new RuntimeException (e);
		}
	}

}
