package br.com.matheusfarias.agendasqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBController {
    private SQLiteDatabase db;
    private DB banco;
    private String campos[] = {"_id", "nome", "fone"};
    private Cursor cursor;

    public DBController(Context context){
        banco = new DB(context);
    }

    public String inserir(String nome, String fone){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("fone", fone);

        resultado = db.insertOrThrow("contatos", null, valores);
        db.close();

        if (resultado == -1){
            return "Erro ao InserirActivity Registro!";
        }else {
            return "Registro Inserido com Sucesso.";
        }
    }
    public Cursor buscarTodos(){
        db = banco.getReadableDatabase();
        cursor = db.query("contatos", campos, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor buscarPoId(int id){
        db = banco.getReadableDatabase();
        cursor = db.query("contatos", campos, "_id = " + id, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public void alterar(int id, String nome, String fone){
        ContentValues valores;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("fone", fone);

        db.update("contatos", valores, "_id = " + id, null);
    }
    public void excluir(int id){
        db = banco.getReadableDatabase();
        db.delete("contatos", "_id = " + id, null);
    }
}
