package db;

public class DbTable {
	public static String getDDLCreateFornecedor() {
		return "CREATE TABLE IF NOT EXISTS Fornecedor (" + "id INTEGER AUTO_INCREMENT PRIMARY KEY," + "CNPJ VARCHAR(14),"
				+ "tipo_servico INTEGER," + "id_usuario INTEGER," + "FOREIGN KEY(id_usuario) REFERENCES Usuario (id)"
				+ ");";
	}

	public static String getDDLCreateCliente() {
		return "CREATE TABLE IF NOT EXISTS Cliente (" + "id INTEGER  AUTO_INCREMENT PRIMARY KEY," + "RG VARCHAR(7),"
				+ "CPF VARCHAR(11)," + "numero_viagens INTEGER," + "cartao_credito VARCHAR(16)," + "id_usuario INTEGER"
				+ ");";
	}

	public static String getDDLCreateHospedagem() {
		return " CREATE TABLE IF NOT EXISTS Hospedagem (" + "id INTEGER  AUTO_INCREMENT PRIMARY KEY," + "nome VARCHAR(100),"
				+ "imagem VARCHAR(500)," + "diaria INTEGER," + "preco DECIMAL(2)," + "id_origem_destino INTEGER,"
				+ "id_fornecedor INTEGER );";
	}

	public static String getDDLCreatePacoteViagem() {
		return "CREATE TABLE IF NOT EXISTS PacoteViagem (" + "id INTEGER  AUTO_INCREMENT PRIMARY KEY," + "titulo VARCHAR(150),"
				+ "valor_desconto INTEGER," + "preco_total DECIMAL(2)," + "possui_hospedagem VARCHAR(50),"
				+ "status VARCHAR(20)," + "meio_transporte VARCHAR(50)," + "imagem VARCHAR(150),"
				+ "prazo_cancelamento DATETIME," + "data_viagem DATETIME," + "id_origem_destino INTEGER,"
				+ "id_hospedagem INTEGER," + "id_carrinho_compra INTEGER" + ");";
	}

	public static String getDDLCreateUsuario() {
		return "CREATE TABLE IF NOT EXISTS Usuario (" + "id INTEGER  AUTO_INCREMENT PRIMARY KEY," + "nome VARCHAR(150),"
				+ "email VARCHAR(150)," + "password VARCHAR(50)," + "telefone VARCHAR(50)," + "imagem Text,"
				+ "tipo_usuario INTEGER," + "data_login DATETIME," + "id_endereco INTEGER" + ");";
	}

	public static String getDDLCreateEndereco() {
		return "CREATE TABLE IF NOT EXISTS Endereco (" + "id INTEGER  AUTO_INCREMENT PRIMARY KEY," + "CEP VARCHAR(9),"
				+ "estado VARCHAR(2)," + "cidade VARCHAR(150)," + "bairro VARCHAR(150)," + "rua VARCHAR(150),"
				+ "numero INTEGER" + ");";
	}

	public static String getDDLCreateAdministrador() {
		return "CREATE TABLE IF NOT EXISTS Administrador (" + "id INTEGER  AUTO_INCREMENT PRIMARY KEY,"
				+ "numero_viagem_revisadas INTEGER," + "id_usuario INTEGER,"
				+ "FOREIGN KEY(id_usuario) REFERENCES Usuario (id));";
	}

	public static String getDDLCreateRevisa() {
		return "CREATE TABLE IF NOT EXISTS revisa (" + "id_administrador INTEGER," + "id_pacote_viagem INTEGER,"
				+ "FOREIGN KEY(id_administrador) REFERENCES Administrador (id),"
				+ "FOREIGN KEY(id_pacote_viagem) REFERENCES PacoteViagem (id));";

	}

	public static String getDDLCreateOrigemDestino() {
		return "CREATE TABLE IF NOT EXISTS OrigemDestino (" + "id INTEGER  AUTO_INCREMENT PRIMARY KEY," + "nome VARCHAR(150),"
				+ "imagem VARCHAR(500)," + "descricao VARCHAR(500)," + "tipo INTEGER," + "id_endereco INTEGER,"
				+ "FOREIGN KEY(id_endereco) REFERENCES Endereco (id));";
	}

	public static String getDDLCreatePassagem() {
		return "CREATE TABLE IF NOT EXISTS Passagem (" + "id INTEGER  AUTO_INCREMENT PRIMARY KEY," + "titulo VARCHAR(150),"
				+ "preco DECIMAL(2)," + "tipo INTEGER," + "id_fornecedor INTEGER," + "id_pacote_viagem INTEGER,"
				+ "FOREIGN KEY(id_fornecedor) REFERENCES Fornecedor (id),"
				+ "FOREIGN KEY(id_pacote_viagem) REFERENCES PacoteViagem (id)" + ");";
	}

	public static String getDDLCreateCarrinhoCompra() {
		return "CREATE TABLE IF NOT EXISTS CarrinhoCompra (" + "id INTEGER  AUTO_INCREMENT PRIMARY KEY," + "valor_total DECIMAL(2),"
				+ "forma_pagamento INTEGER," + "quant_items INTEGER," + "id_cliente INTEGER,"
				+ "FOREIGN KEY(id_cliente) REFERENCES Cliente (id)" + ");";
	}

	public static String getDDLSetFornecedorFkUsuario() {
		return "ALTER TABLE Fornecedor ADD FOREIGN KEY(id_usuario) REFERENCES Usuario (id);";
	}

	public static String getDDLSetClienteFkUsuario() {
		return "ALTER TABLE Cliente ADD FOREIGN KEY(id_usuario) REFERENCES Usuario (id);";
	}

	public static String getDDLSetAdministradorFkUsuario() {
		return "ALTER TABLE Administrador ADD FOREIGN KEY(id_usuario) REFERENCES Usuario (id);";
	}

	public static String getDDLSetPacoteViagemFkOrigemDestino() {
		return "ALTER TABLE PacoteViagem ADD FOREIGN KEY(id_origem_destino) REFERENCES OrigemDestino (id);";
	}

	public static String getDDLSetPacoteViagemFkHospedagem() {
		return "ALTER TABLE PacoteViagem ADD FOREIGN KEY(id_hospedagem) REFERENCES Hospedagem (id);";
	}

	public static String getDDLSetPacoteViagemFkCarrinhoCompra() {
		return "ALTER TABLE PacoteViagem ADD FOREIGN KEY(id_carrinho_compra) REFERENCES CarrinhoCompra (id);";
	}

	public static String getDDLSetOrigemDestinoFkEndereco() {
		return "ALTER TABLE OrigemDestino ADD FOREIGN KEY(id_endereco) REFERENCES Endereco (id);";
	}

	public static String getDDLSetUsuarioFkEndereco() {
		return "ALTER TABLE Usuario ADD FOREIGN KEY(id_endereco) REFERENCES Endereco (id);";
	}

}
