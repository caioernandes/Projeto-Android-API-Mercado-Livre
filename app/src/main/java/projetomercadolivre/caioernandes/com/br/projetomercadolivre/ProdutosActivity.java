package projetomercadolivre.caioernandes.com.br.projetomercadolivre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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
        Intent it = new Intent(this, DetalheProdutoActivity.class);
        it.putExtra(Constantes.PRODUTO_ID, produto.id);
        startActivity(it);
    }
}
