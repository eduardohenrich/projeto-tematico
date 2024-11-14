package controller;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JFrame;
import dao.Conexao;
import dao.UsuarioDAO;
import model.Usuario;
import view.Login;
import view.Login;
import view.Principal;

public class loginController {

	private final Login login;


	public loginController(Login login) {
		this.login = login;
	
	}

	public void autenticar() throws SQLException {
		
		String email = login.getTextUsuario().getText();
		String senha = login.getTextSenha().getText();
		
		Usuario usuarioAutenticar = new Usuario(email, senha);
		
		Connection conexao = new Conexao().getConnection();
		UsuarioDAO user = new UsuarioDAO(conexao);
		Usuario usuario = user.consultaUsuarioLogin(usuarioAutenticar);
		
		if (usuario != null && usuario.getEmail() != null && usuario.getEmail().equals(usuarioAutenticar.getEmail()) &&
		usuario.getSenha() != null && usuario.getSenha().equals(senha) && usuario.getId() != 0) {
			login.dispose();
			Principal frame = new Principal(usuario.getNome(), usuario.getRole());
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			
		}
		else{
			login.dispose();
			Login frame = new Login("             Usuário não Encontrado!");
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
		}
	}
}
