package projetomercadolivre.caioernandes.com.br.projetomercadolivre.model;


import com.google.gson.annotations.SerializedName;

public class Endereco {
    @SerializedName("id")
    public int id ;
    @SerializedName("latitude")
    public Object latitude;
    @SerializedName("longitude")
    public Object longitude;
    @SerializedName("state")
    public Estado estado;
    @SerializedName("city")
    public Cidade cidade;

    public Endereco() {
        estado = new Estado();
        cidade = new Cidade();
    }

    public Endereco(int id, Object latitude, Object longitude, Estado estado, Cidade cidade) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.estado = estado;
        this.cidade = cidade;
    }

    public class Estado
    {
        public String id;
        public String name;

        public Estado() {
        }
    }

    public class Cidade
    {
        public String id;
        public String name;

        public Cidade() {
        }
    }
}