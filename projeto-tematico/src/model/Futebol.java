package model;

public class Futebol {

    private int id; 
    private String nome;
    private String timeA;
    private int pontoA;
    private String timeB;
    private int pontoB;

    // Construtor com ID
	public Futebol(int id, String nome, String timeA, String timeB, int pontoA, int pontoB) {
		this.id = id;
		this.nome = nome;
		this.timeA = timeA;
		this.timeB = timeB;
		this.pontoA = pontoA;
		this.pontoB = pontoB;
	}
    
	// Construtor sem ID (para criação de novo usuário)
	public Futebol(String nome, String timeA, String timeB, int pontoA, int pontoB) {
        this.nome = nome;
		this.timeA = timeA;
		this.timeB = timeB;
		this.pontoA = pontoA;
        this.pontoB = pontoB;
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

    // Getter e Setter para 'timeA'
    public String getTimeA() {
        return timeA;
    }

    public void setTimeA(String timeA) {
        this.timeA = timeA;
    }

    // Getter e Setter para 'pontoA'
    public int getPontoA() {
        return pontoA;
    }

    public void setPontoA(int pontoA) {
        this.pontoA = pontoA;
    }

    // Getter e Setter para 'timeB'
    public String getTimeB() {
        return timeB;
    }

    public void setTimeB(String timeB) {
        this.timeB = timeB;
    }

    // Getter e Setter para 'pontoB'
    public int getPontoB() {
        return pontoB;
    }

    public void setPontoB(int pontoB) {
        this.pontoB = pontoB;
    }
}
