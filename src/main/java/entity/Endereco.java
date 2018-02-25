package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Endereco implements Serializable{

	private static final long serialVersionUID = 1L;

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
	
	@OneToMany (mappedBy = "EndFK")
	private List<Denuncia> denuncias;

	public List<Denuncia> getDenuncias() {
		return denuncias;
	}

	public void setDenuncias(List<Denuncia> denuncias) {
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

