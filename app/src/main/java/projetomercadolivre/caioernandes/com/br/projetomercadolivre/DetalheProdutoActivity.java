package projetomercadolivre.caioernandes.com.br.projetomercadolivre;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.http.ProdutosByIdTask;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.Constantes;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.Produto;

public class DetalheProdutoActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Produto> {

    @BindView(R.id.text_titulo) TextView textTitulo;
    @BindView(R.id.text_preco) TextView textPreco;
    @BindView(R.id.text_qtd) TextView textQuantidade;
    @BindView(R.id.image_foto) ImageView imageFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_produto);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO adicionar aos favoritos
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        getSupportLoaderManager().initLoader(1, getIntent().getExtras(), this);
    }

    @Override
    public Loader<Produto> onCreateLoader(int id, Bundle args) {
        String produtoId = args.getString(Constantes.PRODUTO_ID);
        return new ProdutosByIdTask(this, produtoId);
    }

    @Override
    public void onLoadFinished(Loader<Produto> loader, Produto data) {
        if (data != null) {
            textTitulo.setText(data.titulo);
            textPreco.setText(data.precoConvertido());
            textQuantidade.setText(data.quantidadeDisponivel());
            Glide.with(this).load(data.foto).into(imageFoto);
        } else {
            Toast.makeText(this, "Erro ao carregar informações.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<Produto> loader) {

    }
}
