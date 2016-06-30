package br.ufpi.easii.es.vendedistraido.model;

/**
 * Created by Alexandre on 30/06/2016.
 */
public class Imovel {
    private long id;
    private String latitude;
    private String longitude;
    private String endereco;
    private Corretor corretor;

    public Imovel(long id, String latitude, String longitude, String endereco, Corretor corretor) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.endereco = endereco;
        this.corretor = corretor;
    }

    public Imovel(String latitude, String longitude, String endereco, Corretor corretor) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.endereco = endereco;
        this.corretor = corretor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Corretor getCorretor() {
        return corretor;
    }

    public void setCorretor(Corretor corretor) {
        this.corretor = corretor;
    }
}