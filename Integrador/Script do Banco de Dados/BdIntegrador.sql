CREATE DATABASE integrador;
USE integrador;

CREATE TABLE usuario(
id_usuario BIGINT NOT NULL AUTO_INCREMENT UNIQUE,
PRIMARY KEY (id_usuario),
email VARCHAR(50) NOT NULL,
senha VARCHAR(20) NOT NULL,
nome VARCHAR(30) NOT NULL,
cpf BIGINT NOT NULL UNIQUE


);
CREATE TABLE cliente(
id_cliente  BIGINT NOT NULL AUTO_INCREMENT UNIQUE,
PRIMARY KEY(id_cliente),
rua VARCHAR(50) not null,
cidade VARCHAR(30) not null,
bairro VARCHAR(35) not null,
numero VARCHAR(6) not null,
complemento varchar(20),
id_usu BIGINT NOT NULL UNIQUE,
FOREIGN KEY cliente(id_usu) REFERENCES usuario(id_usuario)
);
CREATE TABLE restaurante(
id_restaurante BIGINT NOT NULL AUTO_INCREMENT UNIQUE,
PRIMARY KEY (id_restaurante),
nome VARCHAR(30)NOT NULL,
rua VARCHAR(50) NOT NULL,
cidade VARCHAR(30) NOT NULL,
bairro VARCHAR(30) NOT NULL,
numero VARCHAR(6) NOT NULL,
id_usu BIGINT NOT NULL UNIQUE,
FOREIGN KEY restaurante(id_usu) REFERENCES usuario(id_usuario)
);
CREATE TABLE cardapio(
id_cardapio BIGINT NOT NULL AUTO_INCREMENT UNIQUE,
PRIMARY KEY(id_cardapio), 
nome VARCHAR(20) NOT NULL,
preco DOUBLE NOT NULL,
descricao VARCHAR(200) NOT NULL,
id_res BIGINT NOT NULL UNIQUE,
FOREIGN KEY cardapio(id_res) REFERENCES restaurante(id_restaurante)
);
CREATE TABLE cartao (
    id_cartao BIGINT NOT NULL AUTO_INCREMENT UNIQUE,
    PRIMARY KEY (id_cartao),
    nome VARCHAR(40) NOT NULL,
    numero VARCHAR(16) NOT NULL,
    cvv BIGINT,
    validade VARCHAR(5),
    id_cli BIGINT NOT NULL UNIQUE,
    FOREIGN KEY (id_cli)
        REFERENCES cliente (id_cliente)
);


