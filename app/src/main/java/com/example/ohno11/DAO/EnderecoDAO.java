package com.example.ohno11.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.ohno11.backend.Cliente;
import com.example.ohno11.backend.Endereco;
import com.example.ohno11.dataBase.DataBase;

public class EnderecoDAO {

    private DataBase dataBase;
    private SQLiteDatabase sqLiteDatabase;

    public EnderecoDAO(Context context){
        //dataBase = new DataBase(context);
        //sqLiteDatabase = dataBase.getWritableDatabase();
    }

    public long inserir(Endereco endereco){
        ContentValues values = new ContentValues();
        values.put("rua", endereco.getRua());
        values.put("complemento", endereco.getComplemento());
        values.put("cep",endereco.getCep());
        values.put("bairro", endereco.getBairro());
        values.put("cidade",endereco.getCidade());
        values.put("estado", endereco.getEstado());

        return sqLiteDatabase.insert("endereco", null, values);
    }

}
