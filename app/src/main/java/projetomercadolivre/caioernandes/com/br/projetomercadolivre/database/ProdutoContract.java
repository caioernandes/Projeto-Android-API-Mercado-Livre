package projetomercadolivre.caioernandes.com.br.projetomercadolivre.database;

import android.provider.BaseColumns;


public interface ProdutoContract extends BaseColumns {

    String TABLE_NAME = "Produto";

    String TITULO = "titulo";
    String PRECO = "preco";
    String CONDICAO = "condicao";
    String LINK_COMPRA = "link_compra";
    String FOTO = "foto";
    String ACEITA_MERCADO_PAGO = "aceita_mercado_pago";
    String QTD_DISPONIVEL = "qtd_disponivel";
    String LATITUDE = "latitude";
    String LONGITUDE = "longitude";
    String ESTADO = "estado";
    String CIDADE = "cidade";
}
