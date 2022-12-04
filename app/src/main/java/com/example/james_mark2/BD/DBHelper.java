package com.example.james_mark2.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context){
        super(context, Constante.DB_NOME, null, Constante.DB_VERSION);
    }

    //Cria tabelas do banco de dados
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(Constante.CREATE_TB_USUARIO);
            db.execSQL(Constante.CREATE_TB);
            db.execSQL(Constante.CREATE_TB_FAVORITO);
        }catch (Exception e){
            e.printStackTrace();
        }    }

    //Atualiza tabelas do banco de dados
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(Constante.UPGRADE_TB);
        db.execSQL(Constante.UPGRADE_TB_USUARIO);
        db.execSQL(Constante.UPGRADE_TB_FAVORITO);
    }
}
