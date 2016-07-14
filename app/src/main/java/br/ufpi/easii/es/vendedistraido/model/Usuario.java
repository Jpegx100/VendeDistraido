package br.ufpi.easii.es.vendedistraido.model;

/**
 * Classe que modela o objeto Usuario
 * Created by Alexandre on 30/06/2016.
 */
public class Usuario {
    private long id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;

    /**
     * Construtor default para classe do tipo Usuario
     */
    public Usuario(){
    }

    /**
     * Contrutor padrao do objeto Usuario
     * @param id = identificador unico do objeto Usuario
     * @param nome = nome do objeto Usuario
     * @param email = email do objeto Usuario
     * @param senha = senha do objeto Usuario
     * @param telefone = telefone do objeto Usuario
     */
    public Usuario(long id, String nome, String email, String senha, String telefone){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }
    /**
     * Contrutor alternativo do objeto Usuario
     * @param nome = nome do objeto Usuario
     * @param email = email do objeto Usuario
     * @param senha = senha do objeto Usuario
     * @param telefone = telefone do objeto Usuario
     */
    public Usuario(String nome, String email, String senha, String telefone) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }

    /**
     *
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getSenha() {
        return senha;
    }

    /**
     *
     * @param senha
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     *
     * @return
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     *
     * @param telefone
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}