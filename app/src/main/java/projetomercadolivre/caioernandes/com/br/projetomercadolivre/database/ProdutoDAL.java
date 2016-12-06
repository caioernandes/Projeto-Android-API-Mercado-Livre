package projetomercadolivre.caioernandes.com.br.projetomercadolivre.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.Produto;


public class ProdutoDAL {

    private static ProdutoDAL instance;
    private Context mContext;

    public ProdutoDAL(Context context) {
        this.mContext = context;
    }

    public static synchronized ProdutoDAL getInstance(Context context) {
        if (instance == null) {
            instance = new ProdutoDAL(context);
        }

        return instance;
    }

    public long inserir(Produto produto) {

        long result = -1;

        if(produto != null) {
            ProdutoHelper helper = new ProdutoHelper(mContext);
            SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues values = valuesFromProduto(produto);

            try {
                result = db.insert(ProdutoContract.TABLE_NAME, null, values);
            } finally {
                db.close();
            }
        }

        return result;
    }

    public int atualizar(Produto produto) {

        int result = -1;

        if(produto != null) {
            ProdutoHelper helper = new ProdutoHelper(mContext);
            SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues values = valuesFromProduto(produto);

            try {
                result = db.update(ProdutoContract.TABLE_NAME, values, ProdutoContract._ID + " = ?",
                        new String[] {String.valueOf(produto.id)});
            } finally {
                db.close();
            }
        }

        return result;
    }

    public int excluir(Produto produto) {

        int result = -1;

        if(produto != null) {
        ProdutoHelper helper = new ProdutoHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

            try {
                result = db.delete(ProdutoContract.TABLE_NAME, ProdutoContract._ID + " = ? ",
                        new String[] {produto.id});
            } finally {
                db.close();
            }
        }

        return result;
    }

    public boolean isFavorito(Produto produto) {

        boolean existe = false;

        if (produto != null) {
            ProdutoHelper helper = new ProdutoHelper(mContext);
            SQLiteDatabase db = helper.getReadableDatabase();

            try {
                Cursor cursor = db.rawQuery("SELECT count(*) FROM " + ProdutoContract.TABLE_NAME
                        + " WHERE " + ProdutoContract._ID + " = ?", new String[] { produto.id });

                if (cursor != null) {
                    cursor.moveToNext();
                    existe = cursor.getInt(0) > 0;
                    cursor.close();
                }
            } finally {
                db.close();
            }
        }

        return existe;
    }

    public Produto getProduto(Produto produto) {
        Produto result = null;

        if (produto != null) {
            ProdutoHelper helper = new ProdutoHelper(mContext);
            SQLiteDatabase db = helper.getReadableDatabase();

            Cursor c = db.rawQuery("SELECT * FROM " + ProdutoContract.TABLE_NAME + " where " +
                    ProdutoContract._ID + " = ?", new String[] { produto.id });

            if (c.moveToFirst()) {
                result = getProdutoFromCursor(c);
            }
        }

        return result;
    }


    public List<Produto> listar() {
        List<Produto> produtos = new ArrayList<>();

        ProdutoHelper helper = new ProdutoHelper(mContext);
        SQLiteDatabase db = helper.getReadableDatabase();

        try {
            Cursor c = db.rawQuery("SELECT * FROM " + ProdutoContract.TABLE_NAME, null);

            while(c.moveToNext()) {
                produtos.add(getProdutoFromCursor(c));
            }
        } finally {
            db.close();
        }

        return produtos;
    }

    private ContentValues valuesFromProduto(Produto produto) {

        ContentValues values = null;

        if (produto != null) {
            values = new ContentValues();
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
        }

        return values;
    }

    public Produto getProdutoFromCursor(Cursor cursor) {
        int idxId = cursor.getColumnIndex(ProdutoContract._ID);
        int idxTitulo = cursor.getColumnIndex(ProdutoContract.TITULO);
        int idxPreco = cursor.getColumnIndex(ProdutoContract.PRECO);
        int idxCondicao = cursor.getColumnIndex(ProdutoContract.CONDICAO);
        int idxLinkCompra = cursor.getColumnIndex(ProdutoContract.LINK_COMPRA);
        int idxFoto = cursor.getColumnIndex(ProdutoContract.FOTO);
        int idxLatitude = cursor.getColumnIndex(ProdutoContract.LATITUDE);
        int idxLongitude = cursor.getColumnIndex(ProdutoContract.LONGITUDE);
        int idxEstado = cursor.getColumnIndex(ProdutoContract.ESTADO);
        int idxCidade = cursor.getColumnIndex(ProdutoContract.CIDADE);

        Produto produto = new Produto();
        produto.id = cursor.getString(idxId);
        produto.titulo = cursor.getString(idxTitulo);
        produto.preco = cursor.getDouble(idxPreco);
        produto.condicao = cursor.getString(idxCondicao);
        produto.linkCompra = cursor.getString(idxLinkCompra);
        produto.foto = cursor.getString(idxFoto);
        produto.endereco.latitude = cursor.getString(idxLatitude);
        produto.endereco.longitude = cursor.getString(idxLongitude);
        produto.endereco.estado.name = cursor.getString(idxEstado);
        produto.endereco.cidade.name = cursor.getString(idxCidade);

        return produto;
    }
}
