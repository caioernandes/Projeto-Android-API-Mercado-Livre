package projetomercadolivre.caioernandes.com.br.projetomercadolivre;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.http.ProdutosSearchTask;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.Produto;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.ui.adapter.ProdutosAdapter;


public class ProdutoListFragment extends Fragment implements AdapterView.OnItemClickListener, LoaderManager.LoaderCallbacks<List<Produto>>, SearchView.OnQueryTextListener {

    @BindView(R.id.listProdutos) ListView mListProdutos;
    LoaderManager mLoaderManager;

    private Unbinder unbinder;

    public ProdutoListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_produto_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        mListProdutos.setOnItemClickListener(this);
        mLoaderManager = getLoaderManager();
        mLoaderManager.initLoader(0, null, this);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public Loader<List<Produto>> onCreateLoader(int id, Bundle args) {
        String query = args != null ? args.getString("q") : null;
        return new ProdutosSearchTask(getActivity(), query);
    }

    @Override
    public void onLoadFinished(Loader<List<Produto>> loader, List<Produto> data) {
        if (data != null) {
            mListProdutos.setAdapter(new ProdutosAdapter(getActivity(), data));
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Produto>> loader) {

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

        if(getActivity() instanceof OnProdutoClick) {
            Produto produto = (Produto)mListProdutos.getItemAtPosition(position);
            ((OnProdutoClick)getActivity()).onProdutoClick(produto);
        }
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
