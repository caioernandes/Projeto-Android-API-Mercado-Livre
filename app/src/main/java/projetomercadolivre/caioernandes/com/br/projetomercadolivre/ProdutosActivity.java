package projetomercadolivre.caioernandes.com.br.projetomercadolivre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.http.ProdutosSearchTask;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.Constantes;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.Produto;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.ui.adapter.ProdutosAdapter;

public class ProdutosActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Produto>>, SearchView.OnQueryTextListener, AdapterView.OnItemClickListener {

    @BindView(R.id.listProdutos) ListView mListProdutos;
    LoaderManager mLoaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);
        ButterKnife.bind(this);

        mListProdutos.setOnItemClickListener(this);
        mLoaderManager = getSupportLoaderManager();
        mLoaderManager.initLoader(0, null, this);
    }

    @Override
    public Loader<List<Produto>> onCreateLoader(int id, Bundle args) {
        String query = args != null ? args.getString("q") : null;
        return new ProdutosSearchTask(this, query);
    }

    @Override
    public void onLoadFinished(Loader<List<Produto>> loader, List<Produto> data) {
        if (data != null) {
            mListProdutos.setAdapter(new ProdutosAdapter(ProdutosActivity.this, data));
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Produto>> loader) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Bundle bundle = new Bundle();
        bundle.putString("q", query);
        mLoaderManager.restartLoader(0, bundle, this);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Produto produto = (Produto)mListProdutos.getItemAtPosition(position);
        Intent it = new Intent(this, DetalheProdutoActivity.class);
        it.putExtra(Constantes.PRODUTO_ID, produto.id);
        startActivity(it);
    }
}
