package classes;

public enum Status {
	INICIO("INICIO"), 
	CONFIRMACAO("CONFIRMACAO"),
	PRONTO("PRONTO"),
	ENTREGUE("ENTREGUE");
	
	private String descricao;
	
    Status(String descricao) {
        this.descricao = descricao;
    }
 
    public String getDescricao() {
        return descricao;
    }
}
