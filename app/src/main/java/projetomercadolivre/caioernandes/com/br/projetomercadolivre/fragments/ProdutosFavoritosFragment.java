package projetomercadolivre.caioernandes.com.br.projetomercadolivre.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.R;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.database.DatabaseEvent;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.database.ProdutoDAL;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.interfaces.OnProdutoClick;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.Produto;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.ui.adapter.ProdutosAdapter;


public class ProdutosFavoritosFragment extends Fragment {

    @BindView(R.id.list_produto)
    ListView mListProdutos;

    @BindView(R.id.no_favoritos)
    View mEmpty;

    List<Produto> mProdutos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_produtos_favoritos, container, false);
        ButterKnife.bind(this, layout);

        mListProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(getActivity() instanceof OnProdutoClick) {
                    Produto produto = (Produto) mListProdutos.getItemAtPosition(position);
                    ((OnProdutoClick)getActivity()).onProdutoClick(produto);
                }
            }
        });

        updateList();
        return layout;
    }

    private void updateList() {
        mProdutos = ProdutoDAL.getInstance(getActivity().getApplication().getApplicationContext()).listar();
        mListProdutos.setAdapter(new ProdutosAdapter(getActivity(), mProdutos));
        mListProdutos.setEmptyView(mEmpty);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DatabaseEvent event) {
        updateList();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
