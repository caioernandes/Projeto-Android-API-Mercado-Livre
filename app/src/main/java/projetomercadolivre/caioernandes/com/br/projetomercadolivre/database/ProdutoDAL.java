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
            } catch (Exception e) {
                e.printStackTrace();
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
            } catch (Exception e) {
                e.printStackTrace();
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
            } catch (Exception e) {
                e.printStackTrace();
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
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.close();
            }
        }

        return existe;
    }

    public Produto getProduto(String produtoId) {
        Produto result = null;

        if (produtoId != null) {
            ProdutoHelper helper = new ProdutoHelper(mContext);
            SQLiteDatabase db = helper.getReadableDatabase();
            try {

                String sql = "SELECT * FROM " + ProdutoContract.TABLE_NAME + " where " + ProdutoContract._ID + " = ?";
                String[] params = {produtoId};

                Cursor c = db.rawQuery(sql, params);
                if (c.moveToFirst()) {
                    result = getProdutoFromCursor(c);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.close();
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return produtos;
    }

    public Produto getProdutoFromCursor(Cursor cursor) {
        Produto produto = new Produto();
        if (cursor != null) {
            produto.id = cursor.getString(cursor.getColumnIndex(ProdutoContract._ID));
            produto.titulo = cursor.getString(cursor.getColumnIndex(ProdutoContract.TITULO));
            produto.preco = cursor.getDouble(cursor.getColumnIndex(ProdutoContract.PRECO));
            produto.condicao = cursor.getString(cursor.getColumnIndex(ProdutoContract.CONDICAO));
            produto.linkCompra = cursor.getString(cursor.getColumnIndex(ProdutoContract.LINK_COMPRA));
            produto.quantidadeDisponivel = cursor.getInt(cursor.getColumnIndex(ProdutoContract.QTD_DISPONIVEL));
            produto.foto = cursor.getString(cursor.getColumnIndex(ProdutoContract.FOTO));
            produto.endereco.latitude = cursor.getString(cursor.getColumnIndex(ProdutoContract.LATITUDE));
            produto.endereco.longitude = cursor.getString(cursor.getColumnIndex(ProdutoContract.LONGITUDE));
            produto.endereco.estado.name = cursor.getString(cursor.getColumnIndex(ProdutoContract.ESTADO));
            produto.endereco.cidade.name = cursor.getString(cursor.getColumnIndex(ProdutoContract.CIDADE));
        }

        return produto;
    }

    private ContentValues valuesFromProduto(Produto produto) {

        ContentValues values = null;

        if (produto != null) {
            values = new ContentValues();
            values.put(ProdutoContract._ID, produto.id);
            values.put(ProdutoContract.TITULO, produto.titulo);
            values.put(ProdutoContract.PRECO, produto.precoConvertido());
            values.put(ProdutoContract.CONDICAO, produto.condicao);
            values.put(ProdutoContract.FOTO, produto.foto);
            values.put(ProdutoContract.LINK_COMPRA, produto.linkCompra);
            values.put(ProdutoContract.QTD_DISPONIVEL, produto.quantidadeDisponivel());
            values.put(ProdutoContract.LATITUDE, produto.foto);
            values.put(ProdutoContract.LONGITUDE, produto.foto);
            values.put(ProdutoContract.ESTADO, produto.foto);
            values.put(ProdutoContract.CIDADE, produto.foto);
        }

        return values;
    }
}
