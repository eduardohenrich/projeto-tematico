package controller;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JFrame;
import dao.Conexao;
import dao.UsuarioDAO;
import model.Usuario;
import view.Login;
import view.telaCadastro;

public class cadastroController {

	private telaCadastro view;
	
	public cadastroController(telaCadastro view) {
		this.view = view;
	}
	
	public void salvaUsuario() {
		try {
			String nome = view.getNomeField().getText();
			String email = view.getEmailField().getText();
			String senha = view.getSenhaField().getText();
			if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
				return ;
			}
			int role = 0;
			
			Usuario usuarioAutenticar = new Usuario(nome, email, senha, role);
			
			Connection conexao = new Conexao().getConnection();
			UsuarioDAO user = new UsuarioDAO(conexao);
			Usuario usuario = user.consultaUsuario(usuarioAutenticar);
			
			if (usuario != null) {
	            view.dispose();
				telaCadastro frame = new telaCadastro("Usuário já está Cadastrado!");
	            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	            frame.setLocationRelativeTo(null);
	            frame.setVisible(true);
			} else{
	            user.insert(usuarioAutenticar);
				view.dispose();
				Login frame = new Login("Usuário cadastrado!");
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			
		}
		
	}
}
