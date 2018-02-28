package tabela;

import java.util.Set;

import entity.Denuncia;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;

public class EnderecoTabela {
	
	private final SimpleIntegerProperty Cod_Endereco;
	
	private final SimpleStringProperty Desc_Endereco;
	private final SimpleStringProperty RA_Endereco;
	private final SimpleStringProperty CEP_Endereco;
	private final SimpleStringProperty Cid_Endereco;
	private final SimpleStringProperty UF_Endereco;
	private final SimpleSetProperty<Denuncia> denuncias;
	
	// coloquei  o set<Endereco>denuncias para atender o observable value
	
	public EnderecoTabela (int Cod_Endereco, String Desc_Endereco, String RA_Endereco,String CEP_Endereco,
			String Cid_Endereco, String UF_Endereco, Set<Denuncia> denuncias) {
			//Set<Denuncia> set
		super();
		
		this.Cod_Endereco = new SimpleIntegerProperty(Cod_Endereco);
		this.Desc_Endereco = new SimpleStringProperty(Desc_Endereco);
		this.RA_Endereco = new SimpleStringProperty(RA_Endereco);
		this.CEP_Endereco = new SimpleStringProperty(CEP_Endereco);	
		this.Cid_Endereco = new SimpleStringProperty(Cid_Endereco);
		this.UF_Endereco = new SimpleStringProperty(UF_Endereco);
		this.denuncias = new SimpleSetProperty<Denuncia>();
		
	
	}
	
	/*
	public EnderecoTabela (int Cod_Endereco, String Desc_Endereco, String RA_Endereco,String CEP_Endereco,
			String Cid_Endereco, String UF_Endereco, Set<Endereco> denuncias) {
		
		super();
		
		this.Cod_Endereco = new SimpleIntegerProperty(Cod_Endereco);
		this.Desc_Endereco = new SimpleStringProperty(Desc_Endereco);
		this.RA_Endereco = new SimpleStringProperty(RA_Endereco);
		this.CEP_Endereco = new SimpleStringProperty(CEP_Endereco);	
		this.Cid_Endereco = new SimpleStringProperty(Cid_Endereco);
		this.UF_Endereco = new SimpleStringProperty(UF_Endereco);
		this.denuncias = new SimpleSetProperty<Endereco>();
		
	
	}
	*/
	public int getCod_Endereco() {
		return Cod_Endereco.get();
	}

	public String getDesc_Endereco() {
		return Desc_Endereco.get();
	}

	public String getRA_Endereco() {
		return RA_Endereco.get();
	}

	public String getCEP_Endereco() {
		return CEP_Endereco.get();
	}

	public String getCid_Endereco() {
		return Cid_Endereco.get();
	}

	public String getUF_Endereco() {
		return UF_Endereco.get();
	}

	public SimpleSetProperty<Denuncia> getDenuncias() {
		return denuncias;
	}
	
	
}
