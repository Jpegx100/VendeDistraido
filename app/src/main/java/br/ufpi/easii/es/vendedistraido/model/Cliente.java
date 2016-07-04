package br.ufpi.easii.es.vendedistraido.model;

import java.util.List;

/**
 * Created by Alexandre on 30/06/2016.
 * Classe que modela o ator Cliente.
 */
public class Cliente extends Usuario{
    private List<Imovel> imoveisInteresses;

    /**
     * Construtor padrao da classe Cliente
     * @param id identificador unico do cliente
     * @param nome nome do cliente
     * @param email email do cliente
     * @param senha senha do cliente
     * @param telefone telefone do cliente
     * @param imoveisInteresses lista de imoveis para os quais o cliente manifestou interesse
     */
    public Cliente(long id, String nome, String email, String senha, String telefone, List<Imovel> imoveisInteresses) {
        super(id, nome, email, senha, telefone);
        this.imoveisInteresses = imoveisInteresses;
    }

    /**
     * Segundo construtor da classe Cliente, neste caso omitindo o parametro 'id', utilizado para criar objetos quando nao ha o `id` do cliente disponivel
     * @param nome nome do cliente
     * @param email email do cliente
     * @param senha senha do cliente
     * @param telefone telefone do cliente
     * @param imoveisInteresses lista de imoveis para os quais o cliente manifestou interesse
     */
    public Cliente(String nome, String email, String senha, String telefone, List<Imovel> imoveisInteresses) {
        super(nome, email, senha, telefone);
        this.imoveisInteresses = imoveisInteresses;
    }

    public List<Imovel> getImoveisInteresses() {
        return imoveisInteresses;
    }

    public void setImoveisInteresses(List<Imovel> imoveisInteresses) {
        this.imoveisInteresses = imoveisInteresses;
    }
}