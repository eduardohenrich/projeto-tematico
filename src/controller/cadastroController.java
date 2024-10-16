package controller;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JFrame;
import dao.Conexao;
import dao.UsuarioDAO;
import model.Usuario;
import view.telaCadastro;

public class cadastroController {

	private telaCadastro view;
	
	public cadastroController(telaCadastro view) {
		this.view = view;
		
	}
	
	
	public void salvaUsuario() {
		
		try {
			String nome = view.getTextFieldNome().getText();
			String email = view.getTextFieldEmail().getText();
			String senha = view.getTextFieldSenha().getText();
			
			Usuario usuarioAutenticar = new Usuario(nome, email, senha);
			
			Connection conexao = new Conexao().getConnection();
			UsuarioDAO user = new UsuarioDAO(conexao);
			Usuario usuario = user.consultaUsuario(usuarioAutenticar);
			
			if(usuarioAutenticar.getNome().equals(usuario.getNome()) && usuarioAutenticar.getEmail().equals(usuario.getEmail()) 
			&& usuarioAutenticar.getSenha().equals(usuario.getSenha())){
	            view.dispose();
				telaCadastro frame = new telaCadastro("       Usuário já está Cadastrado!");
	            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	            frame.setLocationRelativeTo(null);
	            frame.setVisible(true);
			}
			else{
	            user.insert(usuarioAutenticar);
				view.dispose();
				telaCadastro frame = new telaCadastro("           Usuário Cadastrado!");
	            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	            frame.setLocationRelativeTo(null);
	            frame.setVisible(true);
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		
	}
	
	
	
}
