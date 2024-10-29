package model;

public class Usuario {

	private int id; // Tornar o id privado
	private String nome;
	private String email;
	private String senha;
	private int role;

	// Construtor com ID
	public Usuario(int id, String nome, String email, String senha, int role) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.role = role;
	}

	// Construtor sem ID (para criação de novo usuário)
	public Usuario(String nome, String email, String senha, int role) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.role = role;
	}

	// Construtor para login
	public Usuario(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	// Getter e Setter para o ID
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRole() {
		return role;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
