package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subterranea implements Serializable {
	
	private static final long serialVersionUID = 8669422759075749625L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column 
	private int sub_Codigo;
	
	@Column (columnDefinition="varchar (10)")
	private String sub_Poco;
	
	@Column (columnDefinition="varchar(1)")
	private String sub_Caesb;  // tem caesb () sim () não
	
	@Column (columnDefinition="varchar(10)")
	private String sub_Sistema;
	
	@Column (columnDefinition="varchar(5)")
	private String sub_Estatico;  // em metros
	
	@Column (columnDefinition="varchar(5)")
	private String sub_Dinamico;  // em metros
	
	@Column (columnDefinition="varchar(5)")
	private String sub_Vazao;  // em l/h - litros por hora
	
	@Column (columnDefinition="varchar(5)")
	private String sub_Profundidade;  // em metros
	
	@Column (columnDefinition="varchar (20)")
	private String sub_Data;


}
