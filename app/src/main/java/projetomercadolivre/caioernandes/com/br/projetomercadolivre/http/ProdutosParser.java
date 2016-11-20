package projetomercadolivre.caioernandes.com.br.projetomercadolivre.http;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.Produto;
import projetomercadolivre.caioernandes.com.br.projetomercadolivre.model.ProdutoSearchResult;


public class ProdutosParser {

    private static final String URL_SEARCH = "https://api.mercadolibre.com/sites/MLB/search?q=%s";

    public static List<Produto> searchByTitle(String q) throws IOException {

        //estabelece a conexão com o servidor
        OkHttpClient client = new OkHttpClient();

        //fazendo requisicao ao servidor
        Request request = new Request.Builder().url(String.format(URL_SEARCH, q)).build();

        //resposta do servidor
        Response response = client.newCall(request).execute();

        //verificando se não houve erro de conexão
        if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
            String json = response.body().string();

            //converter o result json em obj java
            Gson gson = new Gson();
            ProdutoSearchResult result = gson.fromJson(json, ProdutoSearchResult.class);

            if (result != null)
                return result.produtos;
        }

        return null;
    }
}
