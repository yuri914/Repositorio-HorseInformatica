package br.com.horseInformatica.util.enumerations;

public enum EnumSexo {

	M("Masculino"),
	F("Feminino");
	
	private String descricao;

	private EnumSexo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
