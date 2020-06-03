package com.example.ohno11.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.ohno11.backend.Cargo;
import com.example.ohno11.backend.Veiculo;
import com.example.ohno11.dataBase.DataBase;

public class CargoDAO {
    private DataBase dataBase;
    private SQLiteDatabase sqLiteDatabase;

    public CargoDAO(Context context){
        //dataBase = new DataBase(context);
        //sqLiteDatabase = dataBase.getWritableDatabase();
    }


    public long inserir(Cargo cargo){
        ContentValues values = new ContentValues();
        values.put("cargo", cargo.getCargo());
        return sqLiteDatabase.insert("cargo", null, values);
    }

}
