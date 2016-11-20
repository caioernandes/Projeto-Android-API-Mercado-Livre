package projetomercadolivre.caioernandes.com.br.projetomercadolivre.model;

import java.text.NumberFormat;


public class Produto {

    public String id;
    public String title;
    public double price;
    public String condition;
    public String permalink;
    public String thumbnail;
    public Endereco seller_address;

    public String precoConvertido() {
        return NumberFormat.getCurrencyInstance().format(price);
    }

    public Produto() {
        seller_address = new Endereco();
    }

    public Produto(String id, String title, double price, String condition, String permalink, String thumbnailm, Endereco seller_address) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.condition = condition;
        this.permalink = permalink;
        this.thumbnail = thumbnail;
        this.seller_address = seller_address;
    }
}
