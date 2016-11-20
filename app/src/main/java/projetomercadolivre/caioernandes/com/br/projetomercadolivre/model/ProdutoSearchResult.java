package projetomercadolivre.caioernandes.com.br.projetomercadolivre.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Caio Ernandes on 17/11/2016.
 */

public class ProdutoSearchResult {

    @SerializedName("results")
    public List<Produto> produtos;
}
