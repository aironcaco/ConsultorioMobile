package com.oliveira.airon.consultoriomobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Airon on 24/01/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "clientesManager";

    private static final String TABLE_CLIENTES = "clientes";

    private static final String KEY_ID = "id";
    private static final String KEY_NOME = "nomeCompleto";
    private static final String KEY_CPF = "cpf";
    private static final String KEY_SEXO = "sexo";
    private static final String KEY_DATA_NASCIMENTO = "dataNascimento";
    private static final String KEY_CELULAR = "celular";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_SENHA = "senha";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CRIAR_TABELA_CLIENTES = "CREATE TABLE " + TABLE_CLIENTES + "(" +
                KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_NOME + " TEXT, " +
                KEY_CPF + " TEXT, " +
                KEY_SEXO + " TEXT, " +
                KEY_DATA_NASCIMENTO + " TEXT, " +
                KEY_CELULAR + " TEXT, " +
                KEY_EMAIL + " TEXT, " +
                KEY_SENHA + " TEXT " + ")";
        db.execSQL(CRIAR_TABELA_CLIENTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTES);
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    void addCliente(Cliente cliente) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(KEY_NOME, cliente.getNomeCompleto());
        valores.put(KEY_CPF, cliente.getCpf());
        valores.put(KEY_SEXO, String.valueOf(cliente.getSexo()));
        valores.put(KEY_DATA_NASCIMENTO, cliente.getDataNascimento());
        valores.put(KEY_CELULAR, cliente.getCelular());
        valores.put(KEY_EMAIL, cliente.getEmail());
        valores.put(KEY_SENHA, cliente.getSenha());

        db.insert(TABLE_CLIENTES, null, valores);
    }

    Cliente getCliente(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CLIENTES, new String[]{KEY_ID, KEY_NOME, KEY_CPF, KEY_SEXO,
                        KEY_DATA_NASCIMENTO, KEY_EMAIL, KEY_SENHA}, KEY_CPF + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Cliente cliente = new Cliente(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2),Sexo.valueOf( cursor.getString(3)), cursor.getString(4),
                cursor.getString(5), cursor.getString(6),cursor.getString(7));
        return cliente;
    }

    public List<Cliente> getAllClientes() {
        List<Cliente> listaClientes = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CLIENTES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Cliente cliente = new Cliente();
                cliente.setId(Integer.parseInt(cursor.getString(0)));
                cliente.setNomeCompleto(cursor.getString(1));
                cliente.setCpf(cursor.getString(2));
                cliente.setSexo(Sexo.valueOf(cursor.getString(3)));
                cliente.setDataNascimento(cursor.getString(4));
                cliente.setCelular(cursor.getString(5));
                cliente.setEmail(cursor.getString(6));
                cliente.setSenha(cursor.getString(7));
                listaClientes.add(cliente);
            } while (cursor.moveToNext());
        }
        return listaClientes;
    }

    public void deleteCliente(Cliente cliente) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CLIENTES, KEY_ID + " = ?", new String[]{cliente.getCpf()});
        db.close();
    }

    public int getQuantidadeClientes() {
        String contQuery = "SELECT * FROM " + TABLE_CLIENTES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(contQuery, null);
        cursor.close();
        return cursor.getCount();
    }
}
