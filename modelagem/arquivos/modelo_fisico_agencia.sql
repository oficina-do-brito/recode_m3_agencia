CREATE DATABASE IF NOT EXISTS agencia;
USE agencia;
-- Geração de Modelo físico
-- Sql ANSI 2003 - brModelo.
CREATE TABLE IF NOT EXISTS Usuario (
idUsuario INTEGER AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(150),
email VARCHAR(150),
password VARCHAR(50),
telefone VARCHAR(50),
tipoUsuario INTEGER,
imagem TEXT,
dataLogin DATETIME,
fkEndereco INTEGER
);

CREATE TABLE IF NOT EXISTS Endereco (
idEndereco INTEGER AUTO_INCREMENT PRIMARY KEY,
CEP VARCHAR(9),
estado VARCHAR(2),
cidade VARCHAR(150),
bairro VARCHAR(150),
rua VARCHAR(150),
numero INTEGER
);

CREATE TABLE IF NOT EXISTS Fornecedor (
idFornecedor INTEGER AUTO_INCREMENT PRIMARY KEY,
CNPJ VARCHAR(14),
tipoServico INTEGER,
fkUsuario INTEGER,
FOREIGN KEY(fkUsuario) REFERENCES Usuario (idUsuario)
);

CREATE TABLE IF NOT EXISTS Administrador (
idAdministrador INTEGER AUTO_INCREMENT PRIMARY KEY,
nViagensRevisadas INTEGER,
fkUsuario INTEGER,
FOREIGN KEY(fkUsuario) REFERENCES Usuario (idUsuario)
);

CREATE TABLE IF NOT EXISTS Cliente (
idCliente INTEGER AUTO_INCREMENT PRIMARY KEY,
RG VARCHAR(7),
CPF VARCHAR(11),
numeroViagens INTEGER,
cartaoCredito VARCHAR(16),
fkUsuario INTEGER,
FOREIGN KEY(fkUsuario) REFERENCES Usuario (idUsuario)
);

CREATE TABLE IF NOT EXISTS CarrinhoCompra (
idCarrinho INTEGER AUTO_INCREMENT PRIMARY KEY,
valorTotal DECIMAL(5,2),
formaPagamento INTEGER,
quantItems INTEGER,
fkCliente INTEGER,
FOREIGN KEY(fkCliente) REFERENCES Cliente (idCliente)
);

CREATE TABLE IF NOT EXISTS Hospedagem (
idHospedagem INTEGER AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(50),
imagem TEXT,
diaria INTEGER,
preco DECIMAL(5,2),
fkOrigem INTEGER,
fkFornecedor INTEGER,
FOREIGN KEY(fkFornecedor) REFERENCES Fornecedor (idFornecedor)
);

CREATE TABLE IF NOT EXISTS Passagem (
idPassagem INTEGER AUTO_INCREMENT PRIMARY KEY,
titulo VARCHAR(150),
preco DECIMAL(5,2),
tipo INTEGER,
fkFornecedor INTEGER,
fkPacote INTEGER,
FOREIGN KEY(fkFornecedor) REFERENCES Fornecedor (idFornecedor)
);

CREATE TABLE IF NOT EXISTS PacoteViagem (
idPacote INTEGER AUTO_INCREMENT PRIMARY KEY,
titulo VARCHAR(150),
valorDesconto INTEGER,
precoTotal DECIMAL(5,2),
possuiHospedagem VARCHAR(50),
status VARCHAR(20),
meioTransporte VARCHAR(50),
imagem TEXT,
prazoCancelamento DATETIME,
dataViagem DATETIME,
fkOrigem INTEGER,
fkHospedagem INTEGER,
fkCarrinho INTEGER,
FOREIGN KEY(fkHospedagem) REFERENCES Hospedagem (idHospedagem),
FOREIGN KEY(fkCarrinho) REFERENCES CarrinhoCompra (idCarrinho)
);

CREATE TABLE IF NOT EXISTS OrigemDestino (
idOrigem INTEGER AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(150),
imagem TEXT,
descricao TEXT,
tipo INTEGER,
fkEndereco INTEGER,
FOREIGN KEY(fkEndereco) REFERENCES Endereco (idEndereco)
);

CREATE TABLE IF NOT EXISTS revisa (
fkAdministrador INTEGER,
fkPacote INTEGER,
FOREIGN KEY(fkAdministrador) REFERENCES Administrador (idAdministrador),
FOREIGN KEY(fkPacote) REFERENCES PacoteViagem (idPacote)
);

ALTER TABLE Usuario ADD FOREIGN KEY(fkEndereco) REFERENCES Endereco (idEndereco);
ALTER TABLE Hospedagem ADD FOREIGN KEY(fkOrigem) REFERENCES OrigemDestino (idOrigem);
ALTER TABLE Passagem ADD FOREIGN KEY(fkPacote) REFERENCES PacoteViagem (idPacote);
ALTER TABLE PacoteViagem ADD FOREIGN KEY(fkOrigem) REFERENCES OrigemDestino (idOrigem);