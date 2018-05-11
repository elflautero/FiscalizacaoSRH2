package entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Ato implements Serializable{

	
	private static final long serialVersionUID = -3230783682956535548L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="ato_codigo")
	private int atoCodigo;
	
	//-- endereço --//
	@ManyToOne (fetch = FetchType.EAGER) 
	@JoinColumn (name = "ato_end_codigo")
	private Endereco atoEndCodigo;
	
	//-- vistoria --//
	@OneToOne (cascade = CascadeType.ALL, mappedBy = "visAtoFK")
	private Vistoria atoVistoriaFK;

	@Column (name="ato_tipo", columnDefinition="varchar(40)")  // auto de infração de advertência, relatório de vistoria
	private String atoTipo;
	
	@Column (name="ato_identificacao", columnDefinition="varchar(70)") // 15/2018
	private String atoIdentificacao;
	
	@Column (name="ato_SEI", columnDefinition="varchar(20)") // 3561241
	private String atoSEI;
	
	@Column (name="ato_Caracaterizacao", columnDefinition="varchar(100)") // texto
	private String atoCaracterizacao;
	
	@Column (name="ato_infracao", columnDefinition="varchar(20)") // vai ficar assim no  banco, incisos: 1,2,3,4,5,6,7,8,9
	private String atoInfracao;
	
	@Column (name="ato_atenuante", columnDefinition="varchar(20)")
	private String atoAtenuante;
	
	@Column (name="ato_agravante", columnDefinition="varchar(20)")
	private String atoAgravante;
	
	@Column (name="ato_data_fiscalizacao", columnDefinition="varchar(20)")
	private String atoDataFiscalizacao;
	
	@Column (name="ato_data_criacao", columnDefinition="varchar(20)")
	private String atoDataCriacao;
	
	//-- getters and setters --//
	public int getAtoCodigo() {
		return atoCodigo;
	}

	public Endereco getAtoEndCodigo() {
		return atoEndCodigo;
	}

	public Vistoria getAtoVistoriaFK() {
		return atoVistoriaFK;
	}

	public String getAtoTipo() {
		return atoTipo;
	}

	public String getAtoIdentificacao() {
		return atoIdentificacao;
	}

	public String getAtoSEI() {
		return atoSEI;
	}

	public String getAtoCaracterizacao() {
		return atoCaracterizacao;
	}

	public String getAtoInfracao() {
		return atoInfracao;
	}

	public String getAtoAtenuante() {
		return atoAtenuante;
	}

	public String getAtoAgravante() {
		return atoAgravante;
	}

	public String getAtoDataFiscalizacao() {
		return atoDataFiscalizacao;
	}

	public String getAtoDataCriacao() {
		return atoDataCriacao;
	}

	public void setAtoCodigo(int atoCodigo) {
		this.atoCodigo = atoCodigo;
	}

	public void setAtoEndCodigo(Endereco atoEndCodigo) {
		this.atoEndCodigo = atoEndCodigo;
	}

	public void setAtoVistoriaFK(Vistoria atoVistoriaFK) {
		this.atoVistoriaFK = atoVistoriaFK;
	}

	public void setAtoTipo(String atoTipo) {
		this.atoTipo = atoTipo;
	}

	public void setAtoIdentificacao(String atoIdentificacao) {
		this.atoIdentificacao = atoIdentificacao;
	}

	public void setAtoSEI(String atoSEI) {
		this.atoSEI = atoSEI;
	}

	public void setAtoCaracterizacao(String atoCaracterizacao) {
		this.atoCaracterizacao = atoCaracterizacao;
	}

	public void setAtoInfracao(String atoInfracao) {
		this.atoInfracao = atoInfracao;
	}

	public void setAtoAtenuante(String atoAtenuante) {
		this.atoAtenuante = atoAtenuante;
	}

	public void setAtoAgravante(String atoAgravante) {
		this.atoAgravante = atoAgravante;
	}

	public void setAtoDataFiscalizacao(String atoDataFiscalizacao) {
		this.atoDataFiscalizacao = atoDataFiscalizacao;
	}

	public void setAtoDataCriacao(String atoDataCriacao) {
		this.atoDataCriacao = atoDataCriacao;
	}
	
	
	
	
}
