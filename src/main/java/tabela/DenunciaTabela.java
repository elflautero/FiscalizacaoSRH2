package tabela;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DenunciaTabela {
	
	private final SimpleIntegerProperty Cod_Denuncia;
	private final SimpleStringProperty Documento_Denuncia;
	private final SimpleStringProperty Documento_SEI_Denuncia;
	private final SimpleStringProperty Processo_SEI_Denuncia;
	private final SimpleStringProperty Descricao_Denuncia;
	
	// CONSTRUTOR 
	
	public DenunciaTabela (int Cod_Denuncia, String Documento_Denuncia, String Documento_SEI_Denuncia, String Processo_SEI_Denuncia, String Descricao_Denuncia) {
		
		super();
		
		this.Cod_Denuncia = new SimpleIntegerProperty(Cod_Denuncia);
		this.Documento_Denuncia = new SimpleStringProperty(Documento_Denuncia);
		this.Documento_SEI_Denuncia = new SimpleStringProperty(Documento_SEI_Denuncia);
		this.Processo_SEI_Denuncia = new SimpleStringProperty(Processo_SEI_Denuncia);
		this.Descricao_Denuncia = new SimpleStringProperty(Descricao_Denuncia);
		
	}
	public int getCod_Denuncia () {
		return Cod_Denuncia.get();// esse get é para pegar apenas o que queremos do dado, senão ele retornaria uma string longa... 
	}
	public String getDocumento_Denuncia () {
		return Documento_Denuncia.get();
	}
	public String getDocumento_SEI_Denuncia () {
		return Documento_SEI_Denuncia.get();
	}
	public String getProcesso_SEI_Denuncia () {
		return Processo_SEI_Denuncia.get();
	}
	public String getDescricao_Denuncia () {
		return Descricao_Denuncia.get();
	}
		
}