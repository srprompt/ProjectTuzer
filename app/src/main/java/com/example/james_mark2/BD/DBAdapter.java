package com.example.james_mark2.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {
    Context c;
    SQLiteDatabase db;
    DBHelper helper;

    public DBAdapter(Context c){
        this.c = c;
        helper = new DBHelper(c);
    }
    //OPEN
    public DBAdapter openDB(){
        try {
            db=helper.getWritableDatabase();
        }catch (Exception e){
            e.printStackTrace();
        }

        return this;
    }
    //CLOSE
    public void closeDB(){
        try {
            helper.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    //SAVE
    public long add(String name, String url, String descricao){
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constante.NOME, name);
            cv.put(Constante.URL, url);
            cv.put(Constante.DESCRICAO,descricao);

            db.insert(Constante.TB_NOME,Constante.ROW_ID,cv);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    //RETRIEVE
    public Cursor getPasseios(){
        String[] colunas = {Constante.ROW_ID, Constante.NOME, Constante.URL, Constante.DESCRICAO};

        return db.query(Constante.TB_NOME, colunas, null, null, null, null, null);
    }

    //Salva perfil
    public long addPerfil(String name, String url, String dataNasc, String cidade, Integer estado, String email, Integer sexo){
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constante.NOME_USUARIO, name);
            cv.put(Constante.URL, url);
            cv.put(Constante.DATA_NASC,dataNasc);
            cv.put(Constante.CIDADE,cidade);
            cv.put(Constante.ESTADO,estado);
            cv.put(Constante.EMAIL,email);
            cv.put(Constante.SEXO,sexo);

            db.insert(Constante.TB_NOME_USUARIO,Constante.ID_USUARIO,cv);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }
}
