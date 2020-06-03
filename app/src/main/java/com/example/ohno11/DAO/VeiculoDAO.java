package com.example.ohno11.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ohno11.backend.Veiculo;
import com.example.ohno11.dataBase.DataBase;


public class VeiculoDAO {

    private DataBase dataBase;
    private SQLiteDatabase sqLiteDatabase;

    public VeiculoDAO(Context context){
        //dataBase = new DataBase(context);
        //sqLiteDatabase = dataBase.getWritableDatabase();
    }
    public VeiculoDAO(){
    }


    public long inserir(Veiculo veiculo){
        ContentValues values = new ContentValues();
        values.put("modelo", veiculo.getModelo());
        values.put("ano", veiculo.getAno());
        values.put("placa",veiculo.getPlaca());
        values.put("marca", veiculo.getMarca());
        values.put("informacoesAdicionais",veiculo.getInformacoesAdicionais());
        values.put("foreignKeyCliente", veiculo.getForeignKeyCliente());
        values.put("nota", veiculo.getNotaservico());
            return sqLiteDatabase.insert("veiculo", null, values);
    }

    public Cursor selectVeiculoByCpf(String cpf){
        Veiculo veiculo = new Veiculo();
        String selectQuery = "select * from veiculo where foreignKeyCliente= ?";
        //SQLiteDatabase banco = dataBase.getWritableDatabase();

        //Cursor cursor = banco.rawQuery(selectQuery, new String[] { cpf });
        //return cursor;
        return null;
    }
}
