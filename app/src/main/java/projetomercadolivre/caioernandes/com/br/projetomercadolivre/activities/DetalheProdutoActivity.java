package projetomercadolivre.caioernandes.com.br.projetomercadolivre.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.fragments.DetalheProdutoFragment;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.R;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.Constantes;

public class DetalheProdutoActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_produto);
        ButterKnife.bind(this);

        //carregando fragment em tempo de execução
        String produtoId = getIntent().getStringExtra(Constantes.PRODUTO_ID);
        DetalheProdutoFragment dmf = DetalheProdutoFragment.newInstance(produtoId);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_produto_detalhe, dmf, "detalhe").commit();
    }
}
