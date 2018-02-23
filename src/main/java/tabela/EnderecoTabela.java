package tabela;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class EnderecoTabela {
	
	private final SimpleIntegerProperty Cod_Endereco;
	
	private final SimpleStringProperty Desc_Endereco;
	private final SimpleStringProperty RA_Endereco;
	private final SimpleStringProperty CEP_Endereco;
	
	private final SimpleStringProperty Cid_Endereco;
	private final SimpleStringProperty UF_Endereco;
	
	public EnderecoTabela (int Cod_Endereco, String Desc_Endereco, String RA_Endereco,String CEP_Endereco,
			String Cid_Endereco, String UF_Endereco) {
		
		super();
		
		
		this.Cod_Endereco = new SimpleIntegerProperty(Cod_Endereco);
		
		this.Desc_Endereco = new SimpleStringProperty(Desc_Endereco);
		this.RA_Endereco = new SimpleStringProperty(RA_Endereco);
		this.CEP_Endereco = new SimpleStringProperty(CEP_Endereco);
		
		this.Cid_Endereco = new SimpleStringProperty(Cid_Endereco);
		this.UF_Endereco = new SimpleStringProperty(UF_Endereco);
	
	
	}

	public SimpleIntegerProperty getCod_Endereco() {
		return Cod_Endereco;
	}

	public SimpleStringProperty getDesc_Endereco() {
		return Desc_Endereco;
	}

	public SimpleStringProperty getRA_Endereco() {
		return RA_Endereco;
	}

	public SimpleStringProperty getCEP_Endereco() {
		return CEP_Endereco;
	}

	public SimpleStringProperty getCid_Endereco() {
		return Cid_Endereco;
	}

	public SimpleStringProperty getUF_Endereco() {
		return UF_Endereco;
	}
	
	
	
}
