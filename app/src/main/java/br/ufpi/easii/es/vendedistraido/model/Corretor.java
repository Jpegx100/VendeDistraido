package br.ufpi.easii.es.vendedistraido.model;

import java.util.List;

/**
 * Classe que modela o ator Corretor
 * Created by Alexandre on 30/06/2016.
 */
public class Corretor extends Usuario{
    private List<Imovel> imoveis;

    /**
     * Contrutor padrao da Classe de Corretor
     *
     * @param id = identificador unico do objeto Corretor
     * @param nome = nome do objeto Corretor
     * @param email = email do objeto Corretor
     * @param senha = senha do objeto Corretor
     * @param telefone = telefone do objeto Corretor
     * @param imoveis = lista de todos imoveis pertencentes ao objeto Corretor
     */
    public Corretor(long id, String nome, String email, String senha, String telefone, List<Imovel> imoveis) {
        super(id, nome, email, senha, telefone);
        this.imoveis = imoveis;
    }

    /**
     * Metodo que retorna a lista de imoveis pertencentes ao Corretor
     * @return
     */
    public List<Imovel> getImoveis() {
        return imoveis;
    }

    /**
     *Metodo que seta a lista de imoveis pertencentes ao Corretor
     * @param imoveis
     */
    public void setImoveis(List<Imovel> imoveis) {
        this.imoveis = imoveis;
    }
}
