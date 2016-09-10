package br.edu.ifpi.eventos.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	 public Connection getConnection() {
	     try {
	    	 Class.forName("com.mysql.jdbc.Driver");
	         return DriverManager.getConnection(
	 "jdbc:mysql://localhost:3306/controleDeEventos", "root","root");
	     } catch (SQLException | ClassNotFoundException e) {
	    	 System.out.println("Deu errado!");
	         throw new RuntimeException(e);
	     }
	 }
	
}
