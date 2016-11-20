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

import projetomercadolivre.caioernandes.com.br.projetomercadolivre.R;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.Produto;

/**
 * Created by Caio Ernandes on 20/11/2016.
 */

public class ProdutosAdapter extends ArrayAdapter<Produto> {
    public ProdutosAdapter(Context context, List<Produto> produtos) {
        super(context, 0, produtos);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Produto produto = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_produto, parent, false);
        }

        ImageView imgFoto = (ImageView) convertView.findViewById(R.id.item_produto_foto);
        TextView txtTitulo = (TextView) convertView.findViewById(R.id.item_produto_titulo);
        TextView txtPreco = (TextView) convertView.findViewById(R.id.item_produto_preco);

        Glide.with(getContext()).load(produto.thumbnail).into(imgFoto);
        txtTitulo.setText(produto.title);
        txtPreco.setText(produto.precoConvertido());

        return convertView;
    }
}
