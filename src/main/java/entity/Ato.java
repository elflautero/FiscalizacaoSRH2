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
public class Ato implements Serializable{

	
	private static final long serialVersionUID = -3230783682956535548L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column 
	private int ato_Codigo;
	
	@ManyToOne (fetch = FetchType.EAGER) 
	@JoinColumn (name = "ato_End_Codigo")
	private Endereco ato_End_Codigo;
	
	@Column (columnDefinition="varchar(70)")
	private String ato_Identificacao;
	
	@Column (columnDefinition="varchar(20)")
	private String ato_Doc_SEI;
	
	@Column (columnDefinition="varchar(70)")
	private String ato_Observacao;
	
	
}
