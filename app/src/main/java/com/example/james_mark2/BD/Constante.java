package com.example.james_mark2.BD;

public class Constante {
    //Colunas
    static final String ROW_ID="id";
    static final String NOME="nome";
    static final String URL="url";
    static final String DESCRICAO = "descricao";
    static final String LOCAL = "local";

    //DB Properties
    static final String DB_NOME="banco_tuzer_DB";
    static final String TB_NOME="passeio_TB";
    static final int DB_VERSION=1;

    //CREATE TABLE STMT
    static final String CREATE_TB= "CREATE TABLE passeio_TB (id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "nome TEXT NOT NULL, url TEXT NOT NULL, descricao TEXT NOT NULL, local TEXT);";

    //UPGRADE TB
    static final String UPGRADE_TB = "DROP TABLE IF EXISTS " + TB_NOME;
}
