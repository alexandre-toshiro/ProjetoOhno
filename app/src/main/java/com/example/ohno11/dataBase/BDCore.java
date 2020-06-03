package com.example.ohno11.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ohno11.backend.Veiculo;


public class BDCore extends SQLiteOpenHelper {
    private static final String NOME_BD = "BDOhno";
    private static final int VERSAO_BD = 1;
    Context ctxx;


    public BDCore(Context ctx){
        super(ctx, NOME_BD, null, VERSAO_BD);
        ctxx = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
