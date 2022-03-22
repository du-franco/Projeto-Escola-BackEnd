package soulCode.escola.model;

public enum StatusBoleto {
	
	PENDENTE("Pendente"),
	RECEBIDO("Recebido"),
	CANCELADO("Cancelado");
	
	private String descricao;
	
	StatusBoleto(String descricao){
		this.descricao = descricao;
		
	}
	
	public String getDescricao() {
		return descricao;
	}

}
