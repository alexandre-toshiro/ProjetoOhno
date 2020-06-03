package com.example.ohno11.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.ohno11.backend.Cliente;
import com.example.ohno11.backend.Funcionario;
import com.example.ohno11.dataBase.DataBase;

public class FuncionarioDAO {


    private DataBase dataBase;
    private SQLiteDatabase sqLiteDatabase;

    public FuncionarioDAO(Context context){
        //dataBase = new DataBase(context);
        //sqLiteDatabase = dataBase.getWritableDatabase();
    }

    public long inserir(Funcionario funcionario){
        ContentValues values = new ContentValues();
        values.put("nome", funcionario.getNome());
        values.put("cpf", funcionario.getCpf());
        values.put("foreignKeyEndereco",funcionario.getForeignKeyEndereco());
        values.put("telefone", funcionario.getTelefone());
        values.put("email",funcionario.getEmail());
        values.put("foreignKeyCargo", funcionario.getForeignKeyCargo());
        values.put("salario", funcionario.getSalario());

        return sqLiteDatabase.insert("funcionario", null, values);
    }

}
