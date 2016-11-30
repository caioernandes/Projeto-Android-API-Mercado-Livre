package projetomercadolivre.caioernandes.com.br.projetomercadolivre.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.Produto;


public class ProdutoDAL {

    private Context mContext;

    public ProdutoDAL(Context context) {
        this.mContext = context;
    }

    public long inserir(Produto produto) {
        ProdutoHelper helper = new ProdutoHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = valuesFromProduto(produto);
        long id = db.insert(ProdutoContract.TABLE_NAME, null, values);
        produto.id = Long.toString(id);

        db.close();

        return id;
    }

    public int atualizar(Produto produto) {
        ProdutoHelper helper = new ProdutoHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = valuesFromProduto(produto);
        int rowsAffected = db.update(ProdutoContract.TABLE_NAME, values, ProdutoContract._ID + " = ?",
                new String[] {String.valueOf(produto.id)});

        db.close();

        return rowsAffected;
    }

    public int excluir(Produto produto) {
        ProdutoHelper helper = new ProdutoHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        int rowsAffected = db.delete(ProdutoContract.TABLE_NAME, ProdutoContract.LINK_COMPRA + " = ?",
                new String[] {String.valueOf(produto.linkCompra)});

        db.close();

        return rowsAffected;
    }

    public boolean isFavorito(Produto produto) {
        ProdutoHelper helper = new ProdutoHelper(mContext);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT count(*) FROM " + ProdutoContract.TABLE_NAME
                        + " WHERE " + ProdutoContract.LINK_COMPRA + " = ?",
                new String[] { produto.linkCompra });
        boolean existe = false;
        if (cursor != null) {
            cursor.moveToNext();
            existe = cursor.getInt(0) > 0;
            cursor.close();
        }
        db.close();
        return existe;
    }

    /*/ Faltando apenas criar o método de listar  /**/

    private ContentValues valuesFromProduto(Produto produto) {
        ContentValues values = new ContentValues();
        values.put(ProdutoContract.TITULO, produto.titulo);
        values.put(ProdutoContract.PRECO, produto.precoConvertido());
        values.put(ProdutoContract.CONDICAO, produto.condicao);
        values.put(ProdutoContract.LINK_COMPRA, produto.linkCompra);
        values.put(ProdutoContract.FOTO, produto.foto);
        values.put(ProdutoContract.ACEITA_MERCADO_PAGO, produto.aceitaMercadoPago);
        values.put(ProdutoContract.QTD_DISPONIVEL, produto.quantidadeDisponivel());
        values.put(ProdutoContract.LATITUDE, produto.foto);
        values.put(ProdutoContract.LONGITUDE, produto.foto);
        values.put(ProdutoContract.ESTADO, produto.foto);
        values.put(ProdutoContract.CIDADE, produto.foto);

        return values;
    }
}
