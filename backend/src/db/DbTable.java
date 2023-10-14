package db;

public class DbTable {
	public static String getDDLCreateUsuario() {
		return "CREATE TABLE IF NOT EXISTS Usuario (idUsuario INTEGER AUTO_INCREMENT PRIMARY KEY,nome VARCHAR(150),email VARCHAR(150),password VARCHAR(50),telefone VARCHAR(50),tipoUsuario INTEGER,imagem VARCHAR(500),dataLogin DATETIME,fkEndereco INTEGER);";
	}
	public static String getDDLCreateEndereco() {
		return "CREATE TABLE IF NOT EXISTS Endereco (idEndereco INTEGER AUTO_INCREMENT PRIMARY KEY,CEP VARCHAR(9),estado VARCHAR(2),cidade VARCHAR(150),bairro VARCHAR(150),rua VARCHAR(150),numero INTEGER);";
	}

	public static String getDDLCreateFornecedor() {
		return "CREATE TABLE IF NOT EXISTS Fornecedor (idFornecedor INTEGER AUTO_INCREMENT PRIMARY KEY,CNPJ VARCHAR(14),tipoServico INTEGER,fkUsuario INTEGER,FOREIGN KEY(fkUsuario) REFERENCES Usuario (idUsuario));";
	}

	public static String getDDLCreateAdministrador() {
		return "CREATE TABLE IF NOT EXISTS Administrador (idAdministrador INTEGER AUTO_INCREMENT PRIMARY KEY,nViagensRevisadas INTEGER,fkUsuario INTEGER,FOREIGN KEY(fkUsuario) REFERENCES Usuario (idUsuario));";
	}

	public static String getDDLCreateCliente() {
		return "CREATE TABLE IF NOT EXISTS Cliente (idCliente INTEGER AUTO_INCREMENT PRIMARY KEY,RG VARCHAR(7),CPF VARCHAR(11),numeroViagens INTEGER,cartaoCredito VARCHAR(16),fkUsuario INTEGER,FOREIGN KEY(fkUsuario) REFERENCES Usuario (idUsuario));";
	}
	public static String getDDLCreateCarrinhoCompra() {
		return "CREATE TABLE IF NOT EXISTS CarrinhoCompra (idCarrinho INTEGER AUTO_INCREMENT PRIMARY KEY,valorTotal DECIMAL(2),formaPagamento INTEGER,quantItems INTEGER,fkCliente INTEGER,FOREIGN KEY(fkCliente) REFERENCES Cliente (idCliente));";
	}

	public static String getDDLCreateHospedagem() {
		return "CREATE TABLE IF NOT EXISTS Hospedagem (idHospedagem INTEGER AUTO_INCREMENT PRIMARY KEY,nome VARCHAR(10),imagem VARCHAR(10),diaria VARCHAR(10),preco DECIMAL(2),fkOrigem INTEGER,fkFornecedor INTEGER,FOREIGN KEY(fkFornecedor) REFERENCES Fornecedor (idFornecedor));";
	}

	public static String getDDLCreatePassagem() {
		return "CREATE TABLE IF NOT EXISTS Passagem (idPassagem INTEGER AUTO_INCREMENT PRIMARY KEY,titulo VARCHAR(150),preco DECIMAL(2),tipo INTEGER,fkFornecedor INTEGER,fkPacote INTEGER,FOREIGN KEY(fkFornecedor) REFERENCES Fornecedor (idFornecedor));";
	}

	public static String getDDLCreatePacoteViagem() {
		return "CREATE TABLE IF NOT EXISTS PacoteViagem (idPacote INTEGER AUTO_INCREMENT PRIMARY KEY,titulo VARCHAR(150),valorDesconto INTEGER,precoTotal DECIMAL(2),possuiHospedagem VARCHAR(50),status VARCHAR(20),meioTransporte VARCHAR(50),imagem VARCHAR(150),prazoCancelamento DATETIME,dataViagem DATETIME,fkOrigem INTEGER,fkHospedagem INTEGER,fkCarrinho INTEGER,FOREIGN KEY(fkHospedagem) REFERENCES Hospedagem (idHospedagem),FOREIGN KEY(fkCarrinho) REFERENCES CarrinhoCompra (idCarrinho));";
	}
	
	public static String getDDLCreateOrigemDestino() {
		return "CREATE TABLE IF NOT EXISTS OrigemDestino (idOrigem INTEGER AUTO_INCREMENT PRIMARY KEY,nome VARCHAR(150),imagem VARCHAR(500),descricao VARCHAR(500),tipo INTEGER,fkEndereco INTEGER,FOREIGN KEY(fkEndereco) REFERENCES Endereco (idEndereco));";
	}
	
	public static String getDDLCreateRevisa() {
		return "CREATE TABLE IF NOT EXISTS revisa (fkAdministrador INTEGER,fkPacote INTEGER,FOREIGN KEY(fkAdministrador) REFERENCES Administrador (idAdministrador),FOREIGN KEY(fkPacote) REFERENCES PacoteViagem (idPacote));";

	}
	/**
	 *  ALTER TABLE Usuario ADD FOREIGN KEY(fkEndereco) REFERENCES Endereco (idEndereco);
		ALTER TABLE Hospedagem ADD FOREIGN KEY(fkOrigem) REFERENCES OrigemDestino (idOrigem);
		ALTER TABLE Passagem ADD FOREIGN KEY(fkPacote) REFERENCES PacoteViagem (idPacote);
		ALTER TABLE PacoteViagem ADD FOREIGN KEY(fkOrigem) REFERENCES OrigemDestino (idOrigem);
	 */
	
	public static String getDDLSetUsuarioFkEndereco() {
		return "ALTER TABLE Usuario ADD FOREIGN KEY(fkEndereco) REFERENCES Endereco (idEndereco);";
	}

	public static String getDDLSetHospedagemFkOrigemDestino() {
		return "ALTER TABLE Hospedagem ADD FOREIGN KEY(fkOrigem) REFERENCES OrigemDestino (idOrigem);";
	}

	public static String getDDLSetPassagemFkPacoteViagem() {
		return "ALTER TABLE Passagem ADD FOREIGN KEY(fkPacote) REFERENCES PacoteViagem (idPacote);";
	}

	public static String getDDLSetPacoteViagemFkOrigemDestino() {
		return "ALTER TABLE PacoteViagem ADD FOREIGN KEY(fkOrigem) REFERENCES OrigemDestino (idOrigem);";
	}

}
