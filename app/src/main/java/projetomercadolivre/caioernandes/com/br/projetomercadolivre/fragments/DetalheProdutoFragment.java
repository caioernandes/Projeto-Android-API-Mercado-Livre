package projetomercadolivre.caioernandes.com.br.projetomercadolivre.fragments;


import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.R;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.database.DatabaseEvent;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.database.ProdutoDAL;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.http.ProdutosByIdTask;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.Constantes;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.Produto;


public class DetalheProdutoFragment extends Fragment implements LoaderManager.LoaderCallbacks<Produto> {

    CollapsingToolbarLayout appBarLayout;
    @BindView(R.id.text_titulo) TextView textTitulo;
    @BindView(R.id.text_preco) TextView textPreco;
    @BindView(R.id.text_qtd) TextView textQuantidade;
    @BindView(R.id.btn_comprar) Button btnComprar;
    @Nullable @BindView(R.id.image_foto) ImageView imageFoto;
    @BindView(R.id.fab) FloatingActionButton fab;

    MediaPlayer soundFavorite;
    MediaPlayer soundNoFavorite;

    private Unbinder unbinder;
    Produto produto;
    ProdutoDAL produtoDAL;

    public static DetalheProdutoFragment newInstance(String produtoId) {
        Bundle bundle = new Bundle();
        bundle.putString(Constantes.PRODUTO_ID, produtoId);
        DetalheProdutoFragment dmf = new DetalheProdutoFragment();
        dmf.setArguments(bundle);

        return dmf;
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

            appBarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.toolbar_layout);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveOrRemoveFavorite();
            }
        });

        produtoDAL = ProdutoDAL.getInstance(getActivity().getApplication().getApplicationContext());

        String produtoId = getArguments().getString(Constantes.PRODUTO_ID);
        produto = produtoDAL.getProduto(produtoId);
        if (produto == null) {
            getLoaderManager().initLoader(1, getArguments(), this);
        } else {
            updateUI(produto, true);
        }

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(produto.linkCompra));
                startActivity(browserIntent);
            }
        });

        return view;
    }

    public void saveOrRemoveFavorite() {
        Produto tempProduto = produtoDAL.getProduto(produto.id);
        if (tempProduto != null) {
            produtoDAL.excluir(produto);
            changeFloatingButton(false);
            tocarFavorite(false);
        } else {
            produtoDAL.inserir(produto);
            changeFloatingButton(true);
            tocarFavorite(true);
        }
        EventBus.getDefault().post(new DatabaseEvent());
    }

    public void changeFloatingButton(boolean isFavorite) {
        int resource = isFavorite ? R.drawable.is_favorite : R.drawable.is_no_favorite;
        fab.setImageResource(resource);
        fab.setBackgroundColor(Color.parseColor("#FCAF19"));
    }

    @Override
    public Loader<Produto> onCreateLoader(int id, Bundle args) {
        String produtoId = args.getString(Constantes.PRODUTO_ID);
        return new ProdutosByIdTask(getActivity(), produtoId);
    }

    @Override
    public void onLoadFinished(Loader<Produto> loader, Produto data) {
        if (data != null) {
            produto = data;
            updateUI(data, false);
        } else {
            Toast.makeText(getActivity(), "Erro ao carregar informações.", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI(Produto data, boolean isFavorite) {
        produto = data;
        if (getResources().getBoolean(R.bool.phone)) {
            appBarLayout.setTitle(data.titulo);
        }
        textTitulo.setText(data.titulo);
        textPreco.setText(data.precoConvertido());
        textQuantidade.setText(data.quantidadeDisponivel());

        if (imageFoto != null)
            Glide.with(getActivity()).load(data.foto).into(imageFoto);

        changeFloatingButton(isFavorite);
    }

    public void tocarFavorite(boolean isFavorite) {

        soundFavorite = MediaPlayer.create(getContext(), R.raw.favorite);
        soundNoFavorite = MediaPlayer.create(getContext(), R.raw.no_favorite);

        try {
            if (isFavorite) {
                if (soundNoFavorite.isPlaying()) {
                    soundNoFavorite.release();
                    soundNoFavorite.stop();
                }
                soundFavorite.start();
            } else {
                if (soundFavorite.isPlaying()) {
                    soundFavorite.release();
                    soundFavorite.stop();
                }
                soundNoFavorite.start();
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Erro ao executar som do Google Tradutor.", Toast.LENGTH_LONG).show();
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
