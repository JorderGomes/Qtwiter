package usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.Connection_factory;

public class Usuario_dao {
	private Connection connection;

	public Usuario_dao() {
	}

	public boolean addUser(Usuario usuario) {
		String sql = "INSERT INTO usuario(nome, email, senha) VALUES (?, ?, ?)";
		this.connection = new Connection_factory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getSenha());

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

	public boolean deleteUser(String email) {
		String sql = "DELETE FROM usuario WHERE email = ?";
		this.connection = new Connection_factory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, email);

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

	public boolean alterUser(Usuario usuario, String novoNome) {
		String sql = "UPDATE usuario SET nome = ? where email = ?";
		this.connection = new Connection_factory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, novoNome);
			stmt.setString(2, usuario.getEmail());

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

	public ArrayList<Usuario> getListUser() {
		String sql = "SELECT * FROM usuario;";
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();

		this.connection = new Connection_factory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String senha = rs.getString("senha");

				Usuario user = new Usuario(nome, email, senha);

				listaUsuarios.add(user);
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
		return listaUsuarios;
	}

	public ArrayList<Usuario> getListUserNome(String name) {
		String sql = "SELECT * FROM usuario where nome = ?;";
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
		this.connection = new Connection_factory().getConnection();

		try {

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String senha = rs.getString("senha");

				Usuario user = new Usuario(nome, email, senha);

				listaUsuarios.add(user);
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
		return listaUsuarios;
	}


}
