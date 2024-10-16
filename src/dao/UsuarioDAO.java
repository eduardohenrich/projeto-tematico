package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;

public class UsuarioDAO {

	private final Connection connection;

	
	
	public UsuarioDAO (Connection connection) {
		this.connection = connection;
	}
	
	
	
	public void insert(Usuario usuario) throws SQLException{

        String sql = "INSERT INTO usuario(nome, email, senha) VALUES (?,?,?)";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, usuario.getNome());
        stm.setString(2, usuario.getEmail());
        stm.setString(3, usuario.getSenha());
        stm.execute();
        
        stm.close();
        connection.close();
       

	}
	
	public Usuario consultaUsuario(Usuario usuario) throws SQLException {
	    String sql = "SELECT nome, email, senha FROM usuario WHERE nome = ? AND email = ? AND senha = ?";

	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	    	stmt.setString(1, usuario.getNome());
	        stmt.setString(2, usuario.getEmail());
	        stmt.setString(3, usuario.getSenha());

	        try (ResultSet rs = stmt.executeQuery()) {
	        	String nome = null, email = null, senha = null;
	            if (rs.next()) {
	                nome = rs.getString("nome");
	                email = rs.getString("email");
	                senha = rs.getString("senha");
	            }
	            usuario = new Usuario(nome, email, senha);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return usuario;
	}

	public Usuario consultaUsuarioLogin(Usuario usuario) throws SQLException {
	    String sql = "SELECT nome, email, senha FROM usuario WHERE email = ? AND senha = ?";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setString(1, usuario.getEmail());
	        stmt.setString(2, usuario.getSenha());

	        try (ResultSet rs = stmt.executeQuery()) {
	        	String nome = null, email = null, senha = null;
	            if (rs.next()) {
	                nome = rs.getString("nome");
	                email = rs.getString("email");
	                senha = rs.getString("senha");
	                
	                
	                
	            }
	            usuario = new Usuario(nome, email, senha);
	            
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return usuario;
	}
}


	
	
