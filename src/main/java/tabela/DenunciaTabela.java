package tabela;


import entity.Endereco;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class DenunciaTabela {
	
	private final SimpleIntegerProperty Cod_Denuncia;
	private final SimpleStringProperty Doc_Denuncia;
	private final SimpleStringProperty Doc_SEI_Denuncia;
	private final SimpleStringProperty Proc_SEI_Denuncia;
	private final SimpleStringProperty Desc_Denuncia;
	private SimpleObjectProperty<Endereco> enderecoFK;
	
	// CONSTRUTOR 
	
	public DenunciaTabela (int Cod_Denuncia, String Doc_Denuncia, String Doc_SEI_Denuncia, 
			String Proc_SEI_Denuncia, String Desc_Denuncia, Endereco enderecoFK) {
		
		super();
		
		this.Cod_Denuncia = new SimpleIntegerProperty(Cod_Denuncia);
		this.Doc_Denuncia = new SimpleStringProperty(Doc_Denuncia);
		this.Doc_SEI_Denuncia = new SimpleStringProperty(Doc_SEI_Denuncia);
		this.Proc_SEI_Denuncia = new SimpleStringProperty(Proc_SEI_Denuncia);
		this.Desc_Denuncia = new SimpleStringProperty(Desc_Denuncia);
		this.enderecoFK = new SimpleObjectProperty<>(enderecoFK);
		
	}
	
	public int getCod_Denuncia () {
		return Cod_Denuncia.get();// esse get é para pegar apenas o que queremos do dado, senão ele retornaria uma string longa... 
	}
	public String getDoc_Denuncia () {
		return Doc_Denuncia.get();
	}
	public String getDoc_SEI_Denuncia () {
		return Doc_SEI_Denuncia.get();
	}
	public String getProc_SEI_Denuncia () {
		return Proc_SEI_Denuncia.get();
	}
	public String getDesc_Denuncia () {
		return Desc_Denuncia.get();
	}
	// precisei mudar o get para tipo objeto Endereco, ao incluir  no construtor da denúncia  o
	
			//this.enderecoFK = denunciaTabela.getenderecoTabelaFK();
	
	public Endereco getenderecoTabelaFK () {
		return enderecoFK.get();
	}
	
	/*
	public Object getenderecoTabelaFK () {
		return enderecoFK.get();
	}
	*/
		
}