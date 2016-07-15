package br.ufpi.easii.es.vendedistraido.model;

import br.ufpi.easii.es.vendedistraido.util.Tipo;

/**
 * Classe que modela o objeto Imovel
 * Created by Alexandre on 30/06/2016.
 */
public class Imovel {

    private long id;
    private String latitude;
    private String longitude;
    private String endereco;
    private String descricao;
    private Corretor corretor;
    private Double valor;
    private Tipo tipo;

    /**
     * Construtor default para objetos do tipo Imovel
     */
    public Imovel(){
    }

    /**
     * Contrutor padrao da classe Imovel
     * @param id = identificador unico do objeto Imovel
     * @param latitude = latitude do objeto Imovel
     * @param longitude = longitude do objeto Imovel
     * @param endereco = endereco do objeto Imovel
     * @param corretor = objeto corretor que possui tal imovel cadastrado
     */
    public Imovel(long id, String latitude, String longitude, String endereco, Corretor corretor, String descricao, Double valor, Tipo tipo) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.endereco = endereco;
        this.corretor = corretor;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
    }

    /**
     * Contrutor alternativo da classe Imovel
     * @param latitude = latitude do objeto Imovel
     * @param longitude = longitude do objeto Imovel
     * @param endereco = endereco do objeto Imovel
     * @param corretor = objeto corretor que possui tal imovel cadastrado
     */
    public Imovel(String latitude, String longitude, String endereco, Corretor corretor) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.endereco = endereco;
        this.corretor = corretor;
    }

    /**
     * Metodo que retorna o id do objeto Imovel
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * Metodo que modifica o id do objeto Imovel
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Metodo que retorna a latitude do objeto Imovel
     * @return
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Metodo que modifica a latitude do objeto Imovel
     * @param latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * Metodo que retorna a longitude do objeto Imovel
     * @return
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Metodo que modifica a longitude do objeto Imovel
     * @param longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * Metodo que retorna o endereco do objeto Imovel
     * @return
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Metodo que modifica o endereco do objeto Imovel
     * @param endereco
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Metodo que retorna o Objeto Corretor que possui esse objeto Imovel
     * @return
     */
    public Corretor getCorretor() {
        return corretor;
    }

    /**
     * Metodo que modifica o objeto Corretor que possui esse objeto Imovel
     * @param corretor
     */
    public void setCorretor(Corretor corretor) {
        this.corretor = corretor;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}