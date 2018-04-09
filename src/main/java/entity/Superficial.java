package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Superficial implements Serializable{

	private static final long serialVersionUID = -2898246100522194510L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column 
	private int sup_Codigo;
	
	@Column (columnDefinition="varchar(15)")
	private String sup_Captacao; // gravidade, bombeamento, outro
	
	@Column (columnDefinition="varchar(15)")
	private String sup_Local; //-- () canal () rio () reservatório () lago natural () nascente
	
	@Column (columnDefinition="varchar(30)")
	private String sup_Bomba; // marca
	
	@Column (columnDefinition="varchar(10)")
	private String sup_Potencia; // em cv - cavalos
	
	@Column (columnDefinition="varchar(10)")
	private String sup_Area; /// em ha - hectares
	
	@Column (columnDefinition="varchar(15)")
	private String sup_Data;  // data de operação
	
	@Column (columnDefinition="varchar(1)")
	private String sup_Caesb;  // tem caesb () sim () não
	
	
	//-- getters and setters --//

	public int getSup_Codigo() {
		return sup_Codigo;
	}

	public void setSup_Codigo(int sup_Codigo) {
		this.sup_Codigo = sup_Codigo;
	}

	public String getSup_Captacao() {
		return sup_Captacao;
	}

	public void setSup_Captacao(String sup_Captacao) {
		this.sup_Captacao = sup_Captacao;
	}

	public String getSup_Local() {
		return sup_Local;
	}

	public void setSup_Local(String sup_Local) {
		this.sup_Local = sup_Local;
	}

	public String getSup_Bomba() {
		return sup_Bomba;
	}

	public void setSup_Bomba(String sup_Bomba) {
		this.sup_Bomba = sup_Bomba;
	}

	public String getSup_Potencia() {
		return sup_Potencia;
	}

	public void setSup_Potencia(String sup_Potencia) {
		this.sup_Potencia = sup_Potencia;
	}

	public String getSup_Area() {
		return sup_Area;
	}

	public void setSup_Area(String sup_Area) {
		this.sup_Area = sup_Area;
	}

	public String getSup_Data() {
		return sup_Data;
	}

	public void setSup_Data(String sup_Data) {
		this.sup_Data = sup_Data;
	}

	public String getSup_Caesb() {
		return sup_Caesb;
	}

	public void setSup_Caesb(String sup_Caesb) {
		this.sup_Caesb = sup_Caesb;
	}
	
	
	
	
	
}
