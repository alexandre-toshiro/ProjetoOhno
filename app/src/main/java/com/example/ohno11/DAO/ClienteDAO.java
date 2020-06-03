package com.example.ohno11.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.ohno11.backend.Cliente;
import com.example.ohno11.backend.Veiculo;
import com.example.ohno11.dataBase.DataBase;

public class ClienteDAO {

    private DataBase dataBase;
    private SQLiteDatabase sqLiteDatabase;

    public ClienteDAO(Context context){
        //dataBase = new DataBase(context);
        //sqLiteDatabase = dataBase.getWritableDatabase();
    }

    public long inserir(Cliente cliente){
        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("cpf", cliente.getCpf());
        values.put("foreignKeyEndereco",cliente.getForeignKeyEndereco());
        values.put("telefone", cliente.getTelefone());
        values.put("email",cliente.getEmail());
        values.put("foreignKeyVeiculo", cliente.getForeignKeyveiculo());
        return sqLiteDatabase.insert("cliente", null, values);
    }

}
