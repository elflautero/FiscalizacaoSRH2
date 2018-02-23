package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fiscal implements Serializable {

	
	private static final long serialVersionUID = 1L;

	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column 
	private int Cod_Fiscal;
	
	@Column (columnDefinition="varchar(60)")
	private String Nome_Fiscal;
	
	@Column (columnDefinition="varchar(35)")
	private String Cargo_Fiscal;
	
	@Column (columnDefinition="varchar(20)")
	private String Matricula_Fiscal;
	
	@Column (columnDefinition="varchar(20)")
	private String Senha_Fiscal;
	
	// getter e setter - Nome do Fiscal
	public String getNome_Fiscal () {
		return Nome_Fiscal;
	}
	public void setNome_Fiscal (String Nome_Fiscal) {
		this.Nome_Fiscal = Nome_Fiscal;
		
	}
	// getters e setters - Cargo do Fiscal
	public String getCargo_Fiscal () {
		return Cargo_Fiscal;
	}
	public void setCargo_Fiscal (String Cargo_Fiscal) {
		this.Cargo_Fiscal = Cargo_Fiscal;
	}
	//getters e setters - Matricula do Fiscal
	public String getMatricula_Fiscal () {
		return Matricula_Fiscal;
	}
	public void setMatricula_Fiscal (String Matricula_Fiscal) {
		this.Matricula_Fiscal = Matricula_Fiscal;
	}
	//getters e setters  - Senha do Fiscal
	public String getSenha_Fiscal () {
		return Senha_Fiscal;
	}
	public void setSenha_Fiscal(String Senha_Fiscal) {
		this.Senha_Fiscal = Senha_Fiscal;
	}

}

/* BANCO DE DADOS
1 Cod_Fiscal int(11) AUTO_INCREMENT 

2 Nome_Fiscal varchar(50) 

3 Cargo_Fiscal varchar(25) 

4 Matricula_Fiscal varchar(15) 

5 Senha_Fiscal varchar(15) 

*/