package com.example.ohno11.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.ohno11.backend.Funcionario;
import com.example.ohno11.backend.Servico;
import com.example.ohno11.dataBase.DataBase;

public class ServicoDAO {

    private DataBase dataBase;
    private SQLiteDatabase sqLiteDatabase;

    public ServicoDAO(Context context){
        //dataBase = new DataBase(context);
        //sqLiteDatabase = dataBase.getWritableDatabase();
    }
    public ServicoDAO(){
    }

    public long inserir(Servico servico){
        ContentValues values = new ContentValues();
        values.put("descricao", servico.getDescricao());
        values.put("tempo", servico.getTempoDuracao());
        values.put("valor",servico.getValor());
        values.put("placa",servico.getPlaca());

        return sqLiteDatabase.insert("servico", null, values);
    }
}
