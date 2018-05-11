package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Vistoria implements Serializable{

	private static final long serialVersionUID = -8551878034448323133L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="vis_codigo")
	private int visCodigo;
	
	//-- OneToOne subterrâneo e interferência --//
			@OneToOne 
			@JoinColumn (name = "vis_atoFK")
			private Interferencia visAtoFK;
	
	@Column (name="vis_objecto", columnDefinition="varchar(350)")
	private String visObjeto;
	
	@Column (name="vis_apresentacao", columnDefinition="varchar(350)")
	private String visApresentacao;
	
	@Column (name="vis_relato", columnDefinition="varchar(6000)")
	private String visRelato;
	
	@Column (name="vis_recomendacoes", columnDefinition="varchar(600)")
	private String visRecomendacoes;
	
}
