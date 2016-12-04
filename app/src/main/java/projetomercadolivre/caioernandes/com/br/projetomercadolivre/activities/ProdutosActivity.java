package projetomercadolivre.caioernandes.com.br.projetomercadolivre.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import projetomercadolivre.caioernandes.com.br.projetomercadolivre.R;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.fragments.DetalheProdutoFragment;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.fragments.ProdutoListFragment;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.fragments.ProdutosFavoritosFragment;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.interfaces.OnProdutoClick;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.Constantes;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.Produto;

public class ProdutosActivity extends AppCompatActivity implements OnProdutoClick {

    Toolbar toolbar;
    ViewPager mViewPager;
    TabLayout tabLayout;
    ProdutoListFragment produtoListFragment;
    ProdutosFavoritosFragment produtosFavoritosFragment;
    SelectorPageAdapter selectorPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        buildViewPager();
    }

    private void buildViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.container);

        selectorPageAdapter = new SelectorPageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(selectorPageAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
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

    public class SelectorPageAdapter extends FragmentPagerAdapter {
        public SelectorPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (produtoListFragment == null) {
                        produtoListFragment = new ProdutoListFragment();
                    }

                    return produtoListFragment;
                case 1:
                default:
                    if (produtosFavoritosFragment == null) {
                        produtosFavoritosFragment = new ProdutosFavoritosFragment();
                    }

                    return produtosFavoritosFragment;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Lista";
                case 1:
                default:
                    return "Favoritos";
            }
        }
    }
}
