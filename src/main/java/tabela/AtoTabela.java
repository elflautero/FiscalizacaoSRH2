package tabela;

import entity.Endereco;
import entity.Vistoria;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class AtoTabela {
	
	private final SimpleIntegerProperty atoCodigo;
	
	private final SimpleObjectProperty<Endereco> atoEndCodigo;
	private final SimpleObjectProperty<Vistoria>atoVistoriaFK;
	
	private final SimpleStringProperty atoTipo;
	private final SimpleStringProperty atoIdentificacao;
	private final SimpleStringProperty atoSEI;
	private final SimpleStringProperty atoCaracterizacao;
	private final SimpleStringProperty atoInfracao;
	private final SimpleStringProperty atoAtenuante;
	private final SimpleStringProperty atoAgravante;
	private final SimpleStringProperty atoDataFiscalizacao;
	private final SimpleStringProperty atoDataCriacao;
	
	
	
	
	public AtoTabela (
			
			int atoCodigo,
			Endereco atoEndCodigo,
			Vistoria atoVistoriaFK,
			String atoTipo,
			String atoIdentificacao,
			String atoSEI,
			String atoCaracterizacao,
			String atoInfracao,
			String atoAtenuante,
			String atoAgravante,
			String atoDataFiscalizacao,
			String atoDataCriacao
			
			) {
		
		super();
		
		this.atoCodigo = new SimpleIntegerProperty(atoCodigo);
		
		this.atoEndCodigo = new SimpleObjectProperty<>(atoEndCodigo);
		this.atoVistoriaFK = new SimpleObjectProperty<>(atoVistoriaFK);
		
		this.atoTipo = new SimpleStringProperty(atoTipo);
		this.atoIdentificacao = new SimpleStringProperty(atoIdentificacao);
		this.atoSEI = new SimpleStringProperty(atoSEI);
		this.atoCaracterizacao = new SimpleStringProperty(atoCaracterizacao);
		this.atoInfracao = new SimpleStringProperty(atoInfracao);
		this.atoAtenuante = new SimpleStringProperty(atoAtenuante);
		this.atoAgravante = new SimpleStringProperty(atoAgravante);
		this.atoDataFiscalizacao = new SimpleStringProperty(atoDataFiscalizacao);
		this.atoDataCriacao = new SimpleStringProperty(atoDataCriacao);
		
	}
	
	public int getAtoCodigo () {
		return atoCodigo.get();
	}
	
	public Endereco getAtoEndCodigo () {
		return atoEndCodigo.get();
	}
	
	public Vistoria getAtoVistoriaFK () {
		return atoVistoriaFK.get();
	}
	
	public String getAtoTipo () {
		return atoTipo.get();
	}
	
	public String getAtoIdentificacao () {
		return atoIdentificacao.get();
	}
	
	public String getAtoSEI () {
		return atoSEI.get();
	}
	
	public String getAtoCaracterizacao () {
		return atoCaracterizacao.get();
	}
	
	public String getAtoInfracao () {
		return atoInfracao.get();
	}
	
	public String getAtoAtenuante () {
		return atoAtenuante.get();
	}
	
	public String getAtoAgravante () {
		return atoAgravante.get();
	}
	
	public String getAtoDataFiscalizacao () {
		return atoDataFiscalizacao.get();
	}
	
	public String getAtoDataCriacao () {
		return atoDataCriacao.get();
	}
}
