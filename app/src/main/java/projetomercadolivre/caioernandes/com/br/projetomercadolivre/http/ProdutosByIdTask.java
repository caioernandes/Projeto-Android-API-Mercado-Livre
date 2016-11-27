package projetomercadolivre.caioernandes.com.br.projetomercadolivre.http;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.Produto;

public class ProdutosByIdTask extends AsyncTaskLoader<Produto> {

    private Produto mProduto;
    private String mId;

    public ProdutosByIdTask(Context context, String id) {
        super(context);
        mId = id;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (mId == null) return;

        if (mProduto == null) {
            forceLoad();
        } else {
            deliverResult(mProduto);
        }
    }

    @Override
    public Produto loadInBackground() {
        try {
            mProduto = ProdutosParser.searchById(mId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mProduto;
    }
}
