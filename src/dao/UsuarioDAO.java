package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
       

        System.out.println("Usuário inserido com sucesso!");

	}
}
	
	
