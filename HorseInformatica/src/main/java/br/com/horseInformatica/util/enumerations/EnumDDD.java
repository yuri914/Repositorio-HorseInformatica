package br.com.horseInformatica.util.enumerations;

public enum EnumDDD {

	SAO_PAULO(11),
	ARARAQUARA(16),
	RIO_DE_JANERO(21),
	BELO_HORIZONTE(31),
	CANTA_GALO(42),
	FORTALEZA(85),
	MANAUS(97);
	
	private Integer codigo;	

	private EnumDDD(Integer codigo) {
		this.codigo = codigo;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
}
