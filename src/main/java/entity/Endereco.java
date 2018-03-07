package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import tabela.EnderecoTabela;

@Entity
public class Endereco implements Serializable{

	private static final long serialVersionUID = -4904823824516549278L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column 
	private int Cod_Endereco;
	
	@Column (columnDefinition="varchar(90)")
	private String Desc_Endereco;
	
	@Column (columnDefinition="varchar(15)")
	private String RA_Endereco;
	
	@Column (columnDefinition="varchar(15)")
	private String CEP_Endereco;
	
	@Column (columnDefinition="varchar(15)")
	private String Cid_Endereco;
	
	@Column (columnDefinition="varchar(2)")
	private String UF_Endereco;
	
	@OneToMany (mappedBy = "enderecoFK", cascade = CascadeType.MERGE,
			fetch = FetchType.EAGER, targetEntity = Denuncia.class)
	private List<Denuncia> denuncias = new ArrayList<Denuncia>();
	
	//-- Construtor padrão -- //
	public Endereco () {
		
	}
	
	//-- Construtor --//
	public Endereco (EnderecoTabela enderecoTabela) {
		
		this.Cod_Endereco = enderecoTabela.getCod_Endereco();
		this.Desc_Endereco = enderecoTabela.getDesc_Endereco();
		this.RA_Endereco = enderecoTabela.getRA_Endereco();
		this.CEP_Endereco = enderecoTabela.getCEP_Endereco();
		this.Cid_Endereco = enderecoTabela.getCid_Endereco();
		this.UF_Endereco = enderecoTabela.getUF_Endereco();
	}
	
	// -- GETTERS AND SETTERS - //

	public List<Denuncia> getListDenuncias() {
		return denuncias;
	}

	public void setListDenuncias(List<Denuncia> denuncias) {
		this.denuncias = denuncias;
	}

	// getters and setters
	public int getCod_Endereco() {
		return Cod_Endereco;
	}

	public void setCod_Endereco(int cod_Endereco) {
		Cod_Endereco = cod_Endereco;
	}

	public String getDesc_Endereco() {
		return Desc_Endereco;
	}

	public void setDesc_Endereco(String desc_Endereco) {
		Desc_Endereco = desc_Endereco;
	}

	public String getRA_Endereco() {
		return RA_Endereco;
	}

	public void setRA_Endereco(String rA_Endereco) {
		RA_Endereco = rA_Endereco;
	}

	public String getCEP_Endereco() {
		return CEP_Endereco;
	}

	public void setCEP_Endereco(String cEP_Endereco) {
		CEP_Endereco = cEP_Endereco;
	}

	public String getCid_Endereco() {
		return Cid_Endereco;
	}

	public void setCid_Endereco(String cid_Endereco) {
		Cid_Endereco = cid_Endereco;
	}

	public String getUF_Endereco() {
		return UF_Endereco;
	}

	public void setUF_Endereco(String uF_Endereco) {
		UF_Endereco = uF_Endereco;
	}
	
}

