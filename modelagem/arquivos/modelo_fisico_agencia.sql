CREATE DATABASE IF NOT EXISTS agencia;
USE agencia;

-- Geração de Modelo físico
-- Sql ANSI 2003 - brModelo.

CREATE TABLE IF NOT EXISTS Fornecedor (
id INTEGER AUTO_INCREMENT PRIMARY KEY,
CNPJ VARCHAR(14) NOT NULL UNIQUE,
tipo_servico INTEGER NOT NULL,
id_usuario INTEGER
);

CREATE TABLE IF NOT EXISTS Cliente (
id INTEGER AUTO_INCREMENT PRIMARY KEY,
RG VARCHAR(7) NOT NULL UNIQUE,
CPF VARCHAR(11) NOT NULL UNIQUE,
numero_viagens INTEGER,
cartao_credito VARCHAR(16),
id_usuario INTEGER
);

CREATE TABLE IF NOT EXISTS Administrador (
id INTEGER AUTO_INCREMENT PRIMARY KEY,
numero_viagem_revisadas INTEGER,
id_usuario INTEGER
);

CREATE TABLE IF NOT EXISTS PacoteViagem (
id INTEGER AUTO_INCREMENT PRIMARY KEY,
titulo VARCHAR(150),
valor_desconto INTEGER,
preco_total DECIMAL(2),
possui_hospedagem VARCHAR(50),
status VARCHAR(20),
meio_transporte VARCHAR(50),
imagem VARCHAR(150),
prazo_cancelamento DATETIME,
data_viagem DATETIME,
id_origem_destino INTEGER,
id_hospedagem INTEGER,
id_carrinho_compra INTEGER
);

CREATE TABLE IF NOT EXISTS OrigemDestino (
id INTEGER AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(150),
imagem VARCHAR(500),
descricao VARCHAR(500),
tipo INTEGER NOT NULL ,
id_endereco INTEGER
);

CREATE TABLE IF NOT EXISTS Hospedagem (
id INTEGER AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100),
imagem VARCHAR(500),
diaria INTEGER,
preco DECIMAL(2),
id_origem_destino INTEGER,
id_fornecedor INTEGER,
FOREIGN KEY(id_origem_destino) REFERENCES OrigemDestino (id),
FOREIGN KEY(id_fornecedor) REFERENCES Fornecedor (id)
);

CREATE TABLE IF NOT EXISTS Passagem (
id INTEGER AUTO_INCREMENT PRIMARY KEY,
titulo VARCHAR(150),
preco DECIMAL(2),
tipo INTEGER NOT NULL,
id_fornecedor INTEGER,
id_pacote_viagem INTEGER,
FOREIGN KEY(id_fornecedor) REFERENCES Fornecedor (id),
FOREIGN KEY(id_pacote_viagem) REFERENCES PacoteViagem (id)
);

CREATE TABLE IF NOT EXISTS Usuario (
id INTEGER AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(150),
email VARCHAR(150),
password VARCHAR(50),
telefone VARCHAR(50),
tipo_usuario INTEGER NOT NULL,
imagem VARCHAR(500),
data_login DATETIME,
id_endereco INTEGER
);

CREATE TABLE IF NOT EXISTS CarrinhoCompra (
id INTEGER AUTO_INCREMENT PRIMARY KEY,
valor_total DECIMAL(2),
forma_pagamento INTEGER,
quant_items INTEGER,
id_cliente INTEGER,
FOREIGN KEY(id_cliente) REFERENCES Cliente (id)
);

CREATE TABLE IF NOT EXISTS Endereco (
id INTEGER AUTO_INCREMENT PRIMARY KEY,
CEP VARCHAR(9),
estado VARCHAR(2),
cidade VARCHAR(150),
bairro VARCHAR(150),
rua VARCHAR(150),
numero INTEGER
);

CREATE TABLE IF NOT EXISTS revisa (
id_administrador INTEGER,
id_pacote_viagem INTEGER,
FOREIGN KEY(id_administrador) REFERENCES Administrador (id),
FOREIGN KEY(id_pacote_viagem) REFERENCES PacoteViagem (id)
);

ALTER TABLE Fornecedor ADD FOREIGN KEY(id_usuario) REFERENCES Usuario (id);
ALTER TABLE Cliente ADD FOREIGN KEY(id_usuario) REFERENCES Usuario (id);
ALTER TABLE Administrador ADD FOREIGN KEY(id_usuario) REFERENCES Usuario (id);
ALTER TABLE PacoteViagem ADD FOREIGN KEY(id_origem_destino) REFERENCES OrigemDestino (id);
ALTER TABLE PacoteViagem ADD FOREIGN KEY(id_hospedagem) REFERENCES Hospedagem (id);
ALTER TABLE PacoteViagem ADD FOREIGN KEY(id_carrinho_compra) REFERENCES CarrinhoCompra (id);
ALTER TABLE OrigemDestino ADD FOREIGN KEY(id_endereco) REFERENCES Endereco (id);
ALTER TABLE Usuario ADD FOREIGN KEY(id_endereco) REFERENCES Endereco (id);
