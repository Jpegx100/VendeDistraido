package br.ufpi.easii.es.vendedistraido.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import br.ufpi.easii.es.vendedistraido.model.Pessoa;

/**
 * Created by Alexandre on 29/06/2016.
 */
public class PessoaDao extends SQLiteOpenHelper {

    private final String TAG = "sql";
    public static final String nomeDoBanco = "pessoa.sqlite";
    private static final int versaoDoBanco = 1;

    public PessoaDao(Context context) {
        super(context, nomeDoBanco, null, versaoDoBanco);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Criando a tabela pessoa...");
        db.execSQL("create table if not exists pessoa (_id integer primary key " +
                "autoincrement, nome text, latitude text, longitude text);");
        Log.d(TAG, "Tabela pessoa criada com sucesso.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Insere uma nova pessoa, ou atualiza se já existe
    public long inserir(Pessoa pessoa){
        long id = pessoa.getId();
        SQLiteDatabase db = getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put("nome", pessoa.getNome());
            values.put("latitude", pessoa.getLatitude());
            values.put("longitude", pessoa.getLongitude());
            if(id != 0){
                String _id = String.valueOf(pessoa.getId());
                String[] whereArgs = new String[]{_id};
                //update pessoa set values = ...where _id = ?
                int count = db.update("pessoa", values, "_id=?", whereArgs);
                return count;
            }else{
                //insert into carro values (...)
                id = db.insert("pessoa", "", values);
                return id;
            }
        }finally {
            db.close();
        }
    }

    //Pesquisa pessoa
    public Pessoa buscar(String nome){
        SQLiteDatabase db = getWritableDatabase();
        try{
            //"select * from pessoa where nome = ?"
            Cursor c = db.query("pessoa", null, "nome = '" + nome + "'", null, null, null, null);

            String nomePessoa = c.getString(0);
            String latitude = c.getString(1);
            String longitude = c.getString(2);

            Pessoa pessoa = new Pessoa(nomePessoa, latitude, longitude);

            return pessoa;

        }finally {
            db.close();
        }
    }
}
