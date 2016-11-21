package projetomercadolivre.caioernandes.com.br.projetomercadolivre.http;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import java.util.List;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.Produto;

public class ProdutosSearchTask extends AsyncTaskLoader<List<Produto>> {

    List<Produto> mProdutos;
    String mQuery;

    public ProdutosSearchTask(Context context, String q) {
        super(context);
        mQuery = q;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (mQuery == null) return;

        if (mProdutos == null) {
            forceLoad();
        } else {
            deliverResult(mProdutos);
        }
    }

    @Override
    public List<Produto> loadInBackground() {
        try {
            mProdutos = ProdutosParser.searchByTitle(mQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mProdutos;
    }
}
