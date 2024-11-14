package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;

public class UsuarioDAO {

	private final Connection connection;

	public UsuarioDAO(Connection connection) {
		this.connection = connection;
	}

	public void insert(Usuario usuario) throws SQLException {
		String sql = "INSERT INTO usuario(nome, email, senha, role) VALUES (?, ?, ?, ?)";
		PreparedStatement stm = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		stm.setString(1, usuario.getNome());
		stm.setString(2, usuario.getEmail());
		stm.setString(3, usuario.getSenha());
		stm.setInt(4, usuario.getRole());
		stm.execute();

		// Obtendo o ID gerado
		try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				usuario.setId(generatedKeys.getInt(1)); // Definindo o ID no objeto Usuario
			}
		}

		stm.close();
		connection.close();
	}

	public Usuario consultaUsuario(Usuario usuario) throws SQLException {
		String sql = "SELECT id, nome, email, senha, role FROM usuario WHERE email = ?"; // Corrigido o SQL

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, usuario.getEmail());

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					// Usando o ID na criação do objeto Usuario
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					String email = rs.getString("email");
					String senha = rs.getString("senha");
					int role = rs.getInt("role");

					usuario = new Usuario(id, nome, email, senha, role); // Passando o ID
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public Usuario consultaUsuarioId(int id) throws SQLException {
		String sql = "SELECT nome, email, senha, role FROM usuario WHERE id = ?"; // Corrigido o SQL
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, id);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					// Usando o ID na criação do objeto Usuario
					String nome = rs.getString("nome");
					String email = rs.getString("email");
					String senha = rs.getString("senha");
					int role = rs.getInt("role");

					return new Usuario(id, nome, email, senha, role); // Passando o ID
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Usuario consultaUsuarioLogin(Usuario usuario) throws SQLException {
		String sql = "SELECT id, nome, email, senha, role FROM usuario WHERE email = ? AND senha = ?"; // Ajustado para incluir role e ID

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getSenha());

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					// Usando o ID na criação do objeto Usuario
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					String email = rs.getString("email");
					String senha = rs.getString("senha");
					int role = rs.getInt("role");

					return usuario = new Usuario(id, nome, email, senha, role); // Passando o ID
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
