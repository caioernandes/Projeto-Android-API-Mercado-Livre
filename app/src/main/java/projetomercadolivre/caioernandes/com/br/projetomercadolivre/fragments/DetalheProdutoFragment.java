package projetomercadolivre.caioernandes.com.br.projetomercadolivre.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.R;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.http.ProdutosByIdTask;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.Constantes;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.Produto;


public class DetalheProdutoFragment extends Fragment implements LoaderManager.LoaderCallbacks<Produto> {

    @BindView(R.id.text_titulo) TextView textTitulo;
    @BindView(R.id.text_preco) TextView textPreco;
    @BindView(R.id.text_qtd) TextView textQuantidade;
    @Nullable @BindView(R.id.image_foto) ImageView imageFoto;

    private Unbinder unbinder;

    public static DetalheProdutoFragment newInstance(String produtoId) {
        Bundle bundle = new Bundle();
        bundle.putString(Constantes.PRODUTO_ID, produtoId);
        DetalheProdutoFragment dmf = new DetalheProdutoFragment();
        dmf.setArguments(bundle);

        return dmf;
    }

    public DetalheProdutoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalhe_produto, container, false);
        unbinder = ButterKnife.bind(this, view);

        if(getResources().getBoolean(R.bool.phone)) {
            Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
            ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
            if (actionBar != null)
                actionBar.setDisplayHomeAsUpEnabled(true);

            FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO adicionar aos favoritos
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

        getLoaderManager().initLoader(1, getArguments(), this);
        return view;
    }

    @Override
    public Loader<Produto> onCreateLoader(int id, Bundle args) {
        String produtoId = args.getString(Constantes.PRODUTO_ID);
        return new ProdutosByIdTask(getActivity(), produtoId);
    }

    @Override
    public void onLoadFinished(Loader<Produto> loader, Produto data) {
        if (data != null) {

            if (getResources().getBoolean(R.bool.phone)) {
                CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) getView().findViewById(R.id.toolbar_layout);
                appBarLayout.setTitle(data.titulo);
            }

            textTitulo.setText(data.titulo);
            textPreco.setText(data.precoConvertido());
            textQuantidade.setText(data.quantidadeDisponivel());

            if (imageFoto != null)
                Glide.with(getActivity()).load(data.foto).into(imageFoto);
        } else {
            Toast.makeText(getActivity(), "Erro ao carregar informações.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<Produto> loader) {

    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
