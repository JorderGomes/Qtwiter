package postagem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.ArrayList;

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
			stmt.setTime(2, post.getHora());
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

	public boolean deletarPostagem(String texto, String email) {
		String sql = "DELETE FROM postagem WHERE texto = ? and email_usuario = ?";
		this.connection = new Connection_factory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, texto);
			stmt.setString(2, email);
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

	public boolean altPostagem() {
		return false;
	}

	public boolean verPostagem() {
		return false;
	}

	// specifc
	public ArrayList<Postagem> list_my_Posts(String email) {
		String sql = "SELECT * FROM postagem where email_usuario = ?;";
		ArrayList<Postagem> minhas_postagens = new ArrayList<Postagem>();
		this.connection = new Connection_factory().getConnection();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Date data = rs.getDate("Data");
				java.sql.Time hora = rs.getTime("Hora");
				String texto = rs.getString("texto");
				Postagem post = new Postagem(data, hora, texto, email);
				minhas_postagens.add(post);
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return minhas_postagens;
	}

	public boolean list_Postagem_seguido() {
		return false;
	}

	public ArrayList<Postagem> feed(String email_segue) {
		String sql = "select * from Postagem P where P.email_usuario in ( select email_seguido from folow where email_segue = ?);";
		ArrayList<Postagem> feed = new ArrayList<Postagem>();
		this.connection = new Connection_factory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, email_segue);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Date data = rs.getDate("Data");
				java.sql.Time hora = rs.getTime("Hora");
				String texto = rs.getString("texto");
				String email = rs.getString("email_usuario");
				Postagem post = new Postagem(data, hora, texto, email);
				feed.add(post);
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return feed;
	}

	public ArrayList<Postagem> pesqPostByTex(String texto, String email_user) {
		ArrayList<Postagem> base = this.list_my_Posts(email_user);
		ArrayList<Postagem> result = new ArrayList<Postagem>();
		for (Postagem postagem : base) {
			if (postagem.getTexto().contains(texto)) {
				result.add(postagem);
			}
		}
		return result;
	}
	
	
	public boolean alterTextPost(String texto, String novoTexto, String email) {
		String sql = "UPDATE postagem SET texto = ? where texto = ? and email_usuario = ?";
		this.connection = new Connection_factory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, novoTexto);
			stmt.setString(2, texto);
			stmt.setString(3, email);

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
	
}
