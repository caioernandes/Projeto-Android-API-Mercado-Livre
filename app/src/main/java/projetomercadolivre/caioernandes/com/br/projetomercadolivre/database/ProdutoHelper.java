package projetomercadolivre.caioernandes.com.br.projetomercadolivre.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ProdutoHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Produto";

    public ProdutoHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ProdutoContract.TABLE_NAME + " (" +
                ProdutoContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ProdutoContract.TITULO + " TEXT NOT NULL, " +
                ProdutoContract.PRECO + " REAL NOT NULL, " +
                ProdutoContract.CONDICAO + " TEXT NOT NULL, " +
                ProdutoContract.FOTO + " TEXT NOT NULL, " +
                ProdutoContract.ACEITA_MERCADO_PAGO + " INTEGER NOT NULL, " +
                ProdutoContract.LATITUDE + " TEXT NOT NULL, " +
                ProdutoContract.LONGITUDE + " TEXT NOT NULL, " +
                ProdutoContract.ESTADO + " TEXT NOT NULL, " +
                ProdutoContract.CIDADE + " TEXT NOT NULL, " +
                ProdutoContract.QTD_DISPONIVEL + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*if (oldVersion == 1) {
            db.execSQL("ALTER TABLE " + ProdutoContract.TABLE_NAME + " ADD COLUMN " + NEW COLUMN);
        }*/
    }
}
