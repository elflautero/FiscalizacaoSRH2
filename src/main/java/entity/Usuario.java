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
	@JoinColumn (name = "us_End_Codigo")
	private Endereco us_End_Codigo;
	
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

}
