package br.com.ProjChat.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public static Connection getConnection() {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = null;

			con = DriverManager.getConnection("jdbc:mysql:" + "//localhost/dbrobo", "root", "1234");

			return con;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
