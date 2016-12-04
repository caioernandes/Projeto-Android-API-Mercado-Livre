package projetomercadolivre.caioernandes.com.br.projetomercadolivre.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.Constantes;


public class ProdutoHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Produto";

    public ProdutoHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constantes.CREATE_TB_PRODUTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*if (oldVersion == 1) {
            db.execSQL("ALTER TABLE " + ProdutoContract.TABLE_NAME + " ADD COLUMN " + NEW COLUMN);
        }*/
    }
}
