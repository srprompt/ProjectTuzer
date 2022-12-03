package com.example.james_mark2.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.james_mark2.mData.Usuario;

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
    public long add(String name, String url, String descricao,String local, String categoria){
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constante.NOME, name);
            cv.put(Constante.URL, url);
            cv.put(Constante.DESCRICAO,descricao);
            cv.put(Constante.LOCAL,local);
            cv.put(Constante.CATEGORIA,categoria);

            db.insert(Constante.TB_NOME,Constante.ROW_ID,cv);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    //RETRIEVE
    public Cursor getPasseios(){
        String[] colunas = {Constante.ROW_ID, Constante.NOME, Constante.URL, Constante.DESCRICAO,Constante.LOCAL, Constante.CATEGORIA};

        return db.query(Constante.TB_NOME, colunas, null, null, null, null, null);
    }

    public Cursor getPasseiosCategoria(int categoriaId){
        String[] colunas = {Constante.ROW_ID, Constante.NOME, Constante.URL, Constante.DESCRICAO, Constante.CATEGORIA};

        return db.query(Constante.TB_NOME,colunas, String.valueOf(categoriaId),null,null,null,null);
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

    public Cursor getPerfil(){
        String[] colunas = {Constante.ID_USUARIO, Constante.NOME_USUARIO, Constante.URL, Constante.EMAIL, Constante.DATA_NASC,Constante.CIDADE, Constante.ESTADO, Constante.SEXO};

        Usuario usuario = new Usuario();
        return db.query(Constante.TB_NOME_USUARIO, colunas, null, null, null, null, null);
    }

    //add favorito
    public long addFavorito(Integer idPasseio, Integer idUsuario){
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constante.ROW_ID, idPasseio);
            cv.put(Constante.ID_USUARIO, idUsuario);

            db.insert((Constante.TB_NOME_FAVORITO),Constante.ID_FAVORITO,cv);

            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public Cursor getFavoritos(){
        String[] colunas = {Constante.ID_FAVORITO, Constante.ROW_ID, Constante.ID_USUARIO};

        return db.query(Constante.TB_NOME_FAVORITO, colunas, null, null, null, null, null);
    }
}

