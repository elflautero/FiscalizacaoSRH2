package entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import tabela.DenunciaTabela;

@Entity
public class Denuncia implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column 
	private int Cod_Denuncia;
	
	@Column (columnDefinition="varchar(80)")
	private String Doc_Denuncia;
	
	@Column (columnDefinition="varchar(20)")
	private String Doc_SEI_Denuncia;
	
	@Column (columnDefinition="varchar(20)")
	private String Proc_SEI_Denuncia;
	
	@Column (columnDefinition="varchar(200)")
	private String Desc_Denuncia;
	
	@ManyToOne 
	private Endereco EndFK;
	
	/*
	@Column (name="Data_Distribuicao", nullable = false)
	private String Data_Distribuicao;*/
	
	
	//CONSTRUTOR PADRÃO
	public Denuncia () {
		
	}
	
	//CONSTRUTOR DE EDITAR DOCUMENTO
	public Denuncia(DenunciaTabela denunciaTabela) {
		this.Cod_Denuncia = denunciaTabela.getCod_Denuncia();
		this.Doc_Denuncia = denunciaTabela.getDoc_Denuncia();
		this.Doc_SEI_Denuncia = denunciaTabela.getDoc_SEI_Denuncia();
		this.Proc_SEI_Denuncia = denunciaTabela.getProc_SEI_Denuncia();
	}
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// GETTERS AND SETTERS
	
	public int getCod_Denuncia() {
		return Cod_Denuncia;
	}

	public void setCod_Denuncia(int cod_Denuncia) {
		Cod_Denuncia = cod_Denuncia;
	}

	public String getDoc_Denuncia() {
		return Doc_Denuncia;
	}

	public void setDoc_Denuncia(String doc_Denuncia) {
		Doc_Denuncia = doc_Denuncia;
	}

	public String getDoc_SEI_Denuncia() {
		return Doc_SEI_Denuncia;
	}

	public void setDoc_SEI_Denuncia(String doc_SEI_Denuncia) {
		Doc_SEI_Denuncia = doc_SEI_Denuncia;
	}

	public String getProc_SEI_Denuncia() {
		return Proc_SEI_Denuncia;
	}

	public void setProc_SEI_Denuncia(String proc_SEI_Denuncia) {
		Proc_SEI_Denuncia = proc_SEI_Denuncia;
	}

	public String getDesc_Denuncia() {
		return Desc_Denuncia;
	}

	public void setDesc_Denuncia(String desc_Denuncia) {
		Desc_Denuncia = desc_Denuncia;
	}
	
	// depois tira, para o comit
}