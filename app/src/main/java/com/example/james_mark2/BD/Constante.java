package com.example.james_mark2.BD;

public class Constante {
    //Colunas
    static final String ROW_ID="idPasseio";
    static final String NOME="nome";
    static final String URL="url";
    static final String DESCRICAO = "descricao";
    static final String LOCAL = "local";
    static final String CATEGORIA = "categoria";

    static final String ID_USUARIO="idUsuario";
    static final String NOME_USUARIO="nome";
    static final String EMAIL="email";
    static final String DATA_NASC="data_nasc";
    static final String CIDADE="cidade";
    static final String ESTADO="estado";
    static final String SEXO="sexo";

    static final String ID_FAVORITO="idFavorito";

    //DB Properties
    static final String DB_NOME="banco_tuzer_DB";
    static final String TB_NOME="passeio_TB";
    static final String TB_NOME_USUARIO="usuario_TB";
    static final String TB_NOME_FAVORITO="favorito_TB";
    static final int DB_VERSION=1;

    //CREATE TABLE STMT
    static final String CREATE_TB= "CREATE TABLE passeio_TB (idPasseio INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "nome TEXT NOT NULL, url TEXT, descricao TEXT NOT NULL, local TEXT, categoria TEXT);";

    static final String CREATE_TB_USUARIO= "CREATE TABLE usuario_TB (idUsuario INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "nome TEXT NOT NULL, email TEXT NOT NULL, data_nasc TEXT NOT NULL, cidade TEXT NOT NULL, estado INTEGER, sexo INTEGER, url TEXT);";

    static final String CREATE_TB_FAVORITO="CREATE TABLE favorito_TB (idFavorito INTEGER PRIMARY KEY AUTOINCREMENT,"
    + "idPasseio INTEGER NOT NULL UNIQUE, idUsuario INTEGER NOT NULL );";

    //UPGRADE TB
    static final String UPGRADE_TB = "DROP TABLE IF EXISTS " + TB_NOME;
    static final String UPGRADE_TB_USUARIO = "DROP TABLE IF EXISTS " + TB_NOME_USUARIO;
    static final String UPGRADE_TB_FAVORITO = "DROP TABLE IF EXISTS " + TB_NOME_FAVORITO;
}
