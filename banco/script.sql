CREATE DATABASE IF NOT EXISTS java_secretaria;
USE java_secretaria;

CREATE TABLE IF NOT EXISTS aluno (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nomeAluno VARCHAR(100) NOT NULL,
    CPF CHAR(11) NOT NULL,
    dataNascimento DATE NOT NULL,
    genero CHAR(1) NOT NULL,
    nomeResponsavel VARCHAR(100) NOT NULL,
    afro BOOLEAN NOT NULL,
    escolaridadePublica BOOLEAN NOT NULL,
);
