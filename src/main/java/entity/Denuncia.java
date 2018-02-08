package entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import tabela.DenunciaTabela;

@Entity
public class Denuncia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	@Id
	private int Cod_Denuncia;
	
	@Column (name="Documento_Denuncia", nullable = false)
	private String Documento_Denuncia;
	
	@Column (name="Documento_SEI_Denuncia", nullable = false)
	private String Documento_SEI_Denuncia;
	
	@Column (name="Processo_SEI_Denuncia", nullable = false)
	private String Processo_SEI_Denuncia;
	
	@Column (name="Descricao_Denuncia", nullable = false)
	private String Descricao_Denuncia;
	
	
	//CONSTRUTOR PADRÃO
	public Denuncia () {
		
	}
	
	//CONSTRUTOR DE EDITAR DOCUMENTO
	public Denuncia(DenunciaTabela denunciaTabela) {
		this.Cod_Denuncia = denunciaTabela.getCod_Denuncia();
		this.Documento_Denuncia = denunciaTabela.getDocumento_Denuncia();
		this.Documento_SEI_Denuncia = denunciaTabela.getDocumento_SEI_Denuncia();
		this.Processo_SEI_Denuncia = denunciaTabela.getProcesso_SEI_Denuncia();
	}
	
	
	// GETTERS AND SETTERS
	public int getCod_Denuncia() {
		return Cod_Denuncia;
	}

	public void setCod_Denuncia(int cod_Denuncia) {
		Cod_Denuncia = cod_Denuncia;
	}

	public String getDocumento_Denuncia() {
		return Documento_Denuncia;
	}

	public void setDocumento_Denuncia(String documento_Denuncia) {
		Documento_Denuncia = documento_Denuncia;
	}

	public String getDocumento_SEI_Denuncia() {
		return Documento_SEI_Denuncia;
	}

	public void setDocumento_SEI_Denuncia(String documento_SEI_Denuncia) {
		Documento_SEI_Denuncia = documento_SEI_Denuncia;
	}

	public String getProcesso_SEI_Denuncia() {
		return Processo_SEI_Denuncia;
	}

	public void setProcesso_SEI_Denuncia(String processo_SEI_Denuncia) {
		Processo_SEI_Denuncia = processo_SEI_Denuncia;
	}

	public String getDescricao_Denuncia() {
		return Descricao_Denuncia;
	}

	public void setDescricao_Denuncia(String descricao_Denuncia) {
		Descricao_Denuncia = descricao_Denuncia;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}