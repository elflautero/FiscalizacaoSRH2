package tabela;

import entity.Ato;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class VistoriaTabela {
	
	private final SimpleIntegerProperty visCodigo;
	
	private final SimpleObjectProperty <Ato> visAtoFK;
	
	private final SimpleStringProperty visObjeto;
	
	private final SimpleStringProperty visApresentacao;
	
	private final SimpleStringProperty visRelato;
	
	private final SimpleStringProperty visRecomendacoes;
	

	public VistoriaTabela (
			
			int visCodigo,
			Ato visAtoFK,
			String visObjeto,
			String visApresentacao,
			String visRelato,
			String  visRecomendacoes
			
			) {
		
			super();
			
			this.visCodigo = new SimpleIntegerProperty(visCodigo);
			this.visAtoFK = new SimpleObjectProperty<>(visAtoFK);
			this.visObjeto = new SimpleStringProperty(visObjeto);
			this.visApresentacao = new SimpleStringProperty(visApresentacao);
			this.visRelato = new SimpleStringProperty(visRelato);
			this.visRecomendacoes = new SimpleStringProperty(visRecomendacoes);
	
	}
	
	public int getVisCodigo () {
		return visCodigo.get();
	}
	
	public Ato getVisAtoFK () {
		return visAtoFK.get();
	}
	
	public String getVisObjeto () {
		return visObjeto.get();
	}
	
	public String getVisApresentacao () {
		return visApresentacao.get();
	}

	public String getVisRelato () {
		return visRelato.get();
	}
	
	public String getVisRecomendacoes () {
		return visRecomendacoes.get();
	}
}
