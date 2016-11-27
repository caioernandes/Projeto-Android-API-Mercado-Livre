package projetomercadolivre.caioernandes.com.br.projetomercadolivre;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.ButterKnife;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.Constantes;

public class DetalheProdutoActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_produto);
        ButterKnife.bind(this);



        String produtoId = getIntent().getStringExtra(Constantes.PRODUTO_ID);
        DetalheProdutoFragment dmf = DetalheProdutoFragment.newInstance(produtoId);

        //carregando fragment em tempo de execução
        getSupportFragmentManager().beginTransaction().replace(R.id.content_produto_detalhe, dmf, "detalhe").commit();
    }
}
