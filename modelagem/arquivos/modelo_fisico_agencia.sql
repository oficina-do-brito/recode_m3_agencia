CREATE DATABASE IF NOT EXISTS agencia;
USE agencia;
-- Geração de Modelo físico
-- Sql ANSI 2003 - brModelo.
CREATE TABLE IF NOT EXISTS Usuario (
id_usuario INTEGER PRIMARY KEY,
nome VARCHAR(150),
email VARCHAR(150),
password VARCHAR(50),
telefone VARCHAR(50),
tipo_usuario INTEGER,
imagem VARCHAR(500),
data_login DATETIME,
fk_endereco INTEGER
);

CREATE TABLE IF NOT EXISTS Endereco (
id_endereco INTEGER PRIMARY KEY,
CEP VARCHAR(9),
estado VARCHAR(2),
cidade VARCHAR(150),
bairro VARCHAR(150),
rua VARCHAR(150),
numero INTEGER
);

CREATE TABLE IF NOT EXISTS Fornecedor (
id_fornecedor INTEGER PRIMARY KEY,
CNPJ VARCHAR(14),
tipo_servico INTEGER,
fk_usuario INTEGER,
FOREIGN KEY(fk_usuario) REFERENCES Usuario (id_usuario)
);

CREATE TABLE IF NOT EXISTS Administrador (
id_administrador INTEGER PRIMARY KEY,
numero_viagem_revisadas INTEGER,
fk_usuario INTEGER,
FOREIGN KEY(fk_usuario) REFERENCES Usuario (id_usuario)
);

CREATE TABLE IF NOT EXISTS Cliente (
id_cliente INTEGER PRIMARY KEY,
RG VARCHAR(7),
CPF VARCHAR(11),
numero_viagens INTEGER,
cartao_credito VARCHAR(16),
fk_usuario INTEGER,
FOREIGN KEY(fk_usuario) REFERENCES Usuario (id_usuario)
);

CREATE TABLE IF NOT EXISTS CarrinhoCompra (
id_carrinho INTEGER PRIMARY KEY,
valor_total DECIMAL(2),
forma_pagamento INTEGER,
quant_items INTEGER,
fk_cliente INTEGER,
FOREIGN KEY(fk_cliente) REFERENCES Cliente (id_cliente)
);

CREATE TABLE IF NOT EXISTS Hospedagem (
id_hospedagem INTEGER PRIMARY KEY,
nome VARCHAR(10),
imagem VARCHAR(10),
diaria VARCHAR(10),
preco DECIMAL(2),
fk_origem INTEGER,
fk_fornecedor INTEGER,
FOREIGN KEY(fk_fornecedor) REFERENCES Fornecedor (id_fornecedor)
);

CREATE TABLE IF NOT EXISTS Passagem (
id_passagem INTEGER PRIMARY KEY,
titulo VARCHAR(150),
preco DECIMAL(2),
tipo INTEGER,
fk_fornecedor INTEGER,
fk_pacote INTEGER,
FOREIGN KEY(fk_fornecedor) REFERENCES Fornecedor (id_fornecedor)
);

CREATE TABLE IF NOT EXISTS PacoteViagem (
id_pacote INTEGER PRIMARY KEY,
titulo VARCHAR(150),
valor_desconto INTEGER,
preco_total DECIMAL(2),
possui_hospedagem VARCHAR(50),
status VARCHAR(20),
meio_transporte VARCHAR(50),
imagem VARCHAR(150),
prazo_cancelamento DATETIME,
data_viagem DATETIME,
fk_origem INTEGER,
fk_hospedagem INTEGER,
fk_carrinho INTEGER,
FOREIGN KEY(fk_hospedagem) REFERENCES Hospedagem (id_hospedagem),
FOREIGN KEY(fk_carrinho) REFERENCES CarrinhoCompra (id_carrinho)
);

CREATE TABLE IF NOT EXISTS OrigemDestino (
id_origem INTEGER PRIMARY KEY,
nome VARCHAR(150),
imagem VARCHAR(500),
descricao VARCHAR(500),
tipo INTEGER,
fk_endereco INTEGER,
FOREIGN KEY(fk_endereco) REFERENCES Endereco (id_endereco)
);

CREATE TABLE IF NOT EXISTS revisa (
fk_administrador INTEGER,
fk_pacote INTEGER,
FOREIGN KEY(fk_administrador) REFERENCES Administrador (id_administrador),
FOREIGN KEY(fk_pacote) REFERENCES PacoteViagem (id_pacote)
);

ALTER TABLE Usuario ADD FOREIGN KEY(fk_endereco) REFERENCES Endereco (id_endereco);
ALTER TABLE Hospedagem ADD FOREIGN KEY(fk_origem) REFERENCES OrigemDestino (id_origem);
ALTER TABLE Passagem ADD FOREIGN KEY(fk_pacote) REFERENCES PacoteViagem (id_pacote);
ALTER TABLE PacoteViagem ADD FOREIGN KEY(fk_origem) REFERENCES OrigemDestino (id_origem);
