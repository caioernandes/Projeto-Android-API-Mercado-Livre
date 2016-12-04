package projetomercadolivre.caioernandes.com.br.projetomercadolivre.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.R;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.Produto;


public class ProdutosAdapter extends ArrayAdapter<Produto> {
    public ProdutosAdapter(Context context, List<Produto> produtos) {
        super(context, 0, produtos);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Produto produto = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_produto, parent, false);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (produto != null) {
            Glide.with(getContext()).load(produto.foto).into(viewHolder.imgFoto);
            viewHolder.txtTitulo.setText(produto.titulo);
            viewHolder.txtPreco.setText(produto.precoConvertido());
        }

        return convertView;
    }

    public static class ViewHolder {
        @BindView(R.id.item_produto_foto) ImageView imgFoto;
        @BindView(R.id.item_produto_titulo) TextView txtTitulo;
        @BindView(R.id.item_produto_preco) TextView txtPreco;

        public ViewHolder(View parent) {
            ButterKnife.bind(this, parent);
            parent.setTag(this);
        }
    }
}
