package br.com.oobj.rest.jersey;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value = "notaFiscal")
public class NotaFiscal implements Serializable {

	private static final long serialVersionUID = -2219315908250642929L;

	private int id;
	private String empresa;
	private String cnpj;
	private Double valor;
	private String status;
	
	public NotaFiscal(int id, String empresa, String cnpj, Double valor, String status) {
		this.id = id;
		this.empresa = empresa;
		this.cnpj = cnpj;
		this.valor = valor;
		this.status = status;
	}
	
	public static NotaFiscal create() {
		return new NotaFiscal(10, "Oobj - TI", "4567898765", 199.0, null);
	}
	
	@Override
	public String toString() {
		return "NotaFiscal [empresa=" + empresa + ", status=" + status + "]";
	}

	public NotaFiscal() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Double getValor() {
//		for (Item item : getItens()) {
//			valor = valor + item.getValor();
//		}
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

//	public List<Item> getItens() {
//		if (itens == null) {
//			itens = new ArrayList<Item>();
//		}
//		return itens;
//	}
//
//	public void setItens(List<Item> itens) {
//		this.itens = itens;
//	}
}
