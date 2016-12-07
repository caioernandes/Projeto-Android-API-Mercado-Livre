package projetomercadolivre.caioernandes.com.br.projetomercadolivre.model;

import projetomercadolivre.caioernandes.com.br.projetomercadolivre.database.ProdutoContract;


public class Constantes {

    public static final String URL_SEARCH = "https://api.mercadolibre.com/sites/MLB/search?q=%s&category=%s";
    public static final String URL_SEARCH_ID = "https://api.mercadolibre.com/items/%s";
    public static final String CATEGORY_INFORMATICA = "MLA1648";
    public static final String PRODUTO_ID = "produtoId";

    public static final String CREATE_TB_PRODUTOS = "CREATE TABLE " + ProdutoContract.TABLE_NAME + " (" +
                        ProdutoContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        ProdutoContract.TITULO + " TEXT NOT NULL, " +
                        ProdutoContract.PRECO + " INTEGER, " +
                        ProdutoContract.CONDICAO + " TEXT NOT NULL, " +
                        ProdutoContract.FOTO + " TEXT NOT NULL, " +
                        ProdutoContract.LINK_COMPRA + " TEXT NOT NULL, " +
                        ProdutoContract.ACEITA_MERCADO_PAGO + " INTEGER, " +
                        ProdutoContract.QTD_DISPONIVEL + " INTEGER, " +
                        ProdutoContract.LATITUDE + " TEXT NOT NULL, " +
                        ProdutoContract.LONGITUDE + " TEXT NOT NULL, " +
                        ProdutoContract.ESTADO + " TEXT NOT NULL, " +
                        ProdutoContract.CIDADE + " TEXT NOT NULL)";
}