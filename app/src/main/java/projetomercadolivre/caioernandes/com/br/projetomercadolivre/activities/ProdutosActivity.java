package projetomercadolivre.caioernandes.com.br.projetomercadolivre.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import projetomercadolivre.caioernandes.com.br.projetomercadolivre.fragments.DetalheProdutoFragment;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.interfaces.OnProdutoClick;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.R;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.Constantes;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.Produto;

public class ProdutosActivity extends AppCompatActivity implements OnProdutoClick {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);
    }

    @Override
    public void onProdutoClick(Produto produto) {
        if (getResources().getBoolean(R.bool.phone)) {
            Intent it = new Intent(this, DetalheProdutoActivity.class);
            it.putExtra(Constantes.PRODUTO_ID, produto.id);
            startActivity(it);
        } else {
            //carregando fragment em tempo de execução
            DetalheProdutoFragment dmf = DetalheProdutoFragment.newInstance(produto.id);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_produto_detalhe, dmf, "detalhe").commit();
        }
    }
}
