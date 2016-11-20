package projetomercadolivre.caioernandes.com.br.projetomercadolivre;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

import projetomercadolivre.caioernandes.com.br.projetomercadolivre.http.ProdutosParser;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.Produto;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.ui.adapter.ProdutosAdapter;

public class ProdutosActivity extends AppCompatActivity {

    ListView mListProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        mListProdutos = (ListView) findViewById(R.id.listProdutos);
        new ProdutosSearchTask().execute("informatica");
    }

    class ProdutosSearchTask extends AsyncTask<String, Void, List<Produto>> {

        @Override
        protected List<Produto> doInBackground(String... params) {
            try {
                List<Produto> produtos = ProdutosParser.searchByTitle(params[0]);
                return produtos;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Produto> produtos) {
            super.onPostExecute(produtos);

            if (produtos != null) {
                mListProdutos.setAdapter(new ProdutosAdapter(ProdutosActivity.this, produtos));
            }
        }
    }
}
