package controler;

import view.login;

public class loginControler {

	private final login view;

	public loginControler(login view ) {
		this.view = view;
	}
	
	public void fizTarefa() {
		System.out.println("'Busquei algo do banco de dados");
	}
	
	public void entrarNoSistema() {
		String usuario = view.getUsuario();
		String senha = view.getSenha();
		System.out.println("Deu certo");
	}
}
