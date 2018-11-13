package postagem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;

import jdbc.Connection_factory;

public class Postagem_dao {
	private Connection connection;

	// crud
	public Postagem_dao() {
	}

	public boolean insercaoPostagem(Postagem post) {
		// Postagem postagem = new Postagem(texto, email);
		String sql = "INSERT INTO postagem (data, hora, texto, email_usuario) VALUES(?,?,?,?);";
		this.connection = new Connection_factory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setDate(1, (Date) post.getData());
			stmt.setTime(2,  post.getHora());
			stmt.setString(3, post.getTexto());
			stmt.setString(4, post.getEmail_usuario());

			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			if (qtdRowsAffected > 0)
				return true;
			return false;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;

	}

	public boolean deletarPostagem() {
		return false;
	}

	public boolean altPostagem() {
		return false;
	}

	public boolean verPostagem() {
		return false;
	}

	// specifc
	public boolean list_my_Posts() {
		return false;
	}

	public boolean list_Postagem_seguido() {
		return false;
	}

	public boolean feed() {
		return false;
	}
}
