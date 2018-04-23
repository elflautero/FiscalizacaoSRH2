package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 3804791055346363735L;
	
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column 
	private int us_Codigo;
	
	@ManyToOne (fetch = FetchType.EAGER) 
	@JoinColumn (name = "us_End_CodigoFK")
	private Endereco us_End_CodigoFK;
	
	@Column (columnDefinition="varchar(80)")
	private String us_Nome;
	
	@Column (columnDefinition="varchar(5)")
	private String us_CPFCNPJ;
	
	@Column (columnDefinition="varchar (80)")
	private String us_Descricao_End;
	
	@Column (columnDefinition="varchar (20)")
	private String us_RA;
	
	@Column (columnDefinition="varchar (20)")
	private String us_Bairro;

	@Column (columnDefinition="varchar (20)")
	private String us_Cidade;
	
	@Column (columnDefinition="varchar (20)")
	private String us_Postal;
	
	@Column (columnDefinition="varchar (20)")
	private String us_Telefone;
	
	@Column (columnDefinition="varchar (20)")
	private String us_Celular;
	
	@Column (columnDefinition="varchar (70)")
	private String us_Email;
	
	//-- construtor padrão --//
	public Usuario () {
		
	}

	
	
	//-- getters and setters --//
	
	public int getUs_Codigo() {
		return us_Codigo;
	}

	public void setUs_Codigo(int us_Codigo) {
		this.us_Codigo = us_Codigo;
	}

	public Endereco getUs_End_CodigoFK() {
		return us_End_CodigoFK;
	}

	public void setUs_End_CodigoFK(Endereco us_End_Codigo) {
		this.us_End_CodigoFK = us_End_Codigo;
	}

	public String getUs_Nome() {
		return us_Nome;
	}

	public void setUs_Nome(String us_Nome) {
		this.us_Nome = us_Nome;
	}

	public String getUs_CPFCNPJ() {
		return us_CPFCNPJ;
	}

	public void setUs_CPFCNPJ(String us_CPFCNPJ) {
		this.us_CPFCNPJ = us_CPFCNPJ;
	}

	public String getUs_Descricao_End() {
		return us_Descricao_End;
	}

	public void setUs_Descricao_End(String us_Descricao_End) {
		this.us_Descricao_End = us_Descricao_End;
	}

	public String getUs_RA() {
		return us_RA;
	}

	public void setUs_RA(String us_RA) {
		this.us_RA = us_RA;
	}

	public String getUs_Bairro() {
		return us_Bairro;
	}

	public void setUs_Bairro(String us_Bairro) {
		this.us_Bairro = us_Bairro;
	}

	public String getUs_Cidade() {
		return us_Cidade;
	}

	public void setUs_Cidade(String us_Cidade) {
		this.us_Cidade = us_Cidade;
	}

	public String getUs_Postal() {
		return us_Postal;
	}

	public void setUs_Postal(String us_Postal) {
		this.us_Postal = us_Postal;
	}

	public String getUs_Telefone() {
		return us_Telefone;
	}

	public void setUs_Telefone(String us_Telefone) {
		this.us_Telefone = us_Telefone;
	}

	public String getUs_Celular() {
		return us_Celular;
	}

	public void setUs_Celular(String us_Celular) {
		this.us_Celular = us_Celular;
	}

	public String getUs_Email() {
		return us_Email;
	}

	public void setUs_Email(String us_Email) {
		this.us_Email = us_Email;
	}
}
