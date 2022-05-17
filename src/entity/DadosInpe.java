package entity;


public class DadosInpe {
	
	private Long id;
	private String ano; //
	private String mes;
	private String km;
	
	public Long getId() { // retorna o valor da variavel
		return id;
	}
	public void setId(Long id) { // altera o valor da variavel
		this.id = id;
	}
	public String getAno() { // retorna o valor da variavel
		return ano;
	}
	public void setAno(String ano) {// altera o valor da variavel
		this.ano = ano;
	}
	public String getMes() { // retorna o valor da variavel
		return mes;
	}
	public void setMes(String mes) {// altera o valor da variavel
		this.mes = mes;
	}
	public String getKm() { // retorna o valor da variavel
		return km;
	}
	public void setKm(String km) {// altera o valor da variavel
		this.km = km;
	}
	@Override
	public String toString() {
		return "DadosINPE{" +
            "id=" + id +
            ", ano='" + ano + '\'' +
            ", mes='" + mes + '\'' +
            ", km='" + km + '\'' +
            '}';
	
	
	}

}
