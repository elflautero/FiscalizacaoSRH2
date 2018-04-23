package tabela;

import entity.Endereco;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class UsuarioTabela {
	
	private final SimpleIntegerProperty us_Codigo;
	private final SimpleStringProperty us_Nome;
	private final SimpleStringProperty us_CPFCNPJ;
	private final SimpleStringProperty us_Descricao_End;
	private final SimpleStringProperty us_RA;
	private final SimpleStringProperty us_Bairro;
	private final SimpleStringProperty us_Cidade;
	private final SimpleStringProperty us_Postal;
	private final SimpleStringProperty us_Telefone;
	private final SimpleStringProperty us_Celular;
	private final SimpleStringProperty us_Email;
	
	private SimpleObjectProperty <Endereco>  enderecoUsObjetoFK;
	
	public UsuarioTabela (
			
		int us_Codigo,
		
		String us_Nome,
		String us_CPFCNPJ,
		String us_Descricao_End,
		String us_RA,
		String us_Bairro,
		String us_Cidade,
		String us_Postal,
		String us_Telefone,
		String us_Celular,
		String us_Email,
		
		Endereco enderecoUsObjetoFK ) {
	
		super();
		
		this.us_Codigo = new SimpleIntegerProperty(us_Codigo);
		this.us_Nome = new SimpleStringProperty(us_Nome);
		this.us_CPFCNPJ = new SimpleStringProperty(us_CPFCNPJ);
		this.us_Descricao_End = new SimpleStringProperty(us_Descricao_End);
		this.us_RA = new SimpleStringProperty(us_RA);
		this.us_Bairro = new SimpleStringProperty(us_Bairro);
		this.us_Cidade = new SimpleStringProperty(us_Cidade);
		this.us_Postal = new SimpleStringProperty(us_Postal);
		this.us_Telefone = new SimpleStringProperty(us_Telefone);
		this.us_Celular = new SimpleStringProperty(us_Celular);
		this.us_Email = new SimpleStringProperty(us_Email);
		
		this.enderecoUsObjetoFK = new SimpleObjectProperty<>(enderecoUsObjetoFK);
		
		
		}
		
		
	public int getUsCodigo () {
		return us_Codigo.get();
	}
	
	public String getUs_Nome () {
		return us_Nome.get();
	}
	
	public String getUs_CPFCNPJ () {
		return us_CPFCNPJ.get();
	}
	
	public String getUs_Descricao_End () {
		return us_Descricao_End.get();
	}
	
	public String getUs_RA () {
		return us_RA.get();
	}
	
	public String getUs_Bairro () {
		return us_Bairro.get();
	}
	
	public String getUs_Cidade () {
		return us_Cidade.get();
	}
	
	public String getUs_Postal () {
		return us_Postal.get();
	}
	
	public String get () {
		return us_Telefone.get();
	}
	
	public String getUs_Celular () {
		return us_Celular.get();
	}
	public String getUs_Email () {
		return us_Email.get();
	}
	
	//-- objeto foreign  key --//
	public Endereco getenderecoUsObjetoTabelaFK () {
		return enderecoUsObjetoFK.get();
	}
	
}
