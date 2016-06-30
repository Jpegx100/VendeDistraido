package br.ufpi.easii.es.vendedistraido.model;

/**
 * Created by Alexandre on 29/06/2016.
 */
public class Pessoa {
    private long id;
    private String nome;
    private String latitude;
    private String longitude;

    public Pessoa(String nome, String latitude, String longitude) {
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
}