package projetomercadolivre.caioernandes.com.br.projetomercadolivre.model;

import com.google.gson.annotations.SerializedName;

import java.text.NumberFormat;


public class Produto {

    @SerializedName("id")
    public String id;
    @SerializedName("title")
    public String titulo;
    @SerializedName("price")
    public double preco;
    @SerializedName("condition")
    public String condicao;
    @SerializedName("permalink")
    public String linkCompra;
    @SerializedName("thumbnail")
    public String foto;
    @SerializedName("seller_address")
    public Endereco endereco;
    @SerializedName("accepts_mercadopago")
    public Boolean aceitaMercadoPago;
    @SerializedName("available_quantity")
    public int quantidadeDisponivel;

    public String precoConvertido() {
        return NumberFormat.getCurrencyInstance().format(preco);
    }

    public String quantidadeDisponivel() {
        String retorno = null;

        if(quantidadeDisponivel > 1)
            retorno = Integer.toString(quantidadeDisponivel) + " produtos disponíveis.";
        else if (quantidadeDisponivel == 1)
            retorno = Integer.toString(quantidadeDisponivel) + " produto disponível.";

        return retorno;
    }

    public Produto() {
        endereco = new Endereco();
    }

    public Produto(String id, String titulo, double preco, String condicao, String linkCompra,
                   String foto, Endereco endereco, Boolean aceitaMercadoPago, int quantidadeDisponivel) {
        this.id = id;
        this.titulo = titulo;
        this.preco = preco;
        this.condicao = condicao;
        this.linkCompra = linkCompra;
        this.foto = foto;
        this.endereco = endereco;
        this.aceitaMercadoPago = aceitaMercadoPago;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }
}
