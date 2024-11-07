package model;

public class Corrida {

    private int id; 
    private String nome;
    private String primeiro;
    private String segundo;
    private String terceiro;


    // Construtor com ID
	public Corrida(int id, String nome, String primeiro, String segundo, String terceiro) {
		this.id = id;
		this.nome = nome;
		this.primeiro = primeiro;
		this.segundo = segundo;
		this.terceiro = terceiro;
	}

	// Construtor sem ID (para criação de novo usuário)
	public Corrida(String nome, String primeiro, String segundo, String terceiro) {
		this.nome = nome;
		this.primeiro = primeiro;
		this.segundo = segundo;
		this.terceiro = terceiro;
	}

    // Getter e Setter para 'id'
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter e Setter para 'nome'
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter para 'primeiro'
    public String getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(String primeiro) {
        this.primeiro = primeiro;
    }

    // Getter e Setter para 'segundo'
    public String getSegundo() {
        return segundo;
    }

    public void setSegundo(String segundo) {
        this.segundo = segundo;
    }

    // Getter e Setter para 'terceiro'
    public String getTerceiro() {
        return terceiro;
    }

    public void setTerceiro(String terceiro) {
        this.terceiro = terceiro;
    }
}
