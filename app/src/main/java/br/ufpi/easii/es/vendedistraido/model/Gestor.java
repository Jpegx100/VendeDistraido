package br.ufpi.easii.es.vendedistraido.model;

import java.util.List;

/**
 * Classe que modela o ator Corretor
 * Created by Alexandre on 30/06/2016.
 */
public class Gestor extends Usuario{

    private List<Corretor> corretores;

    /**
     * Contrutor padrao da Classe de Gestor
     *
     * @param id = identificador unico do objeto Gestor
     * @param nome = nome do objeto Gestor
     * @param email = email do objeto Gestor
     * @param senha = senha do objeto Gestor
     * @param telefone = telefone do objeto Gestor
     * @param corretores = lista de todos corretores associados ao objeto Gestor
     */
    public Gestor(long id, String nome, String email, String senha, String telefone, List<Corretor> corretores) {
        super(id, nome, email, senha, telefone);
        this.corretores = corretores;
    }

    /**
     * Metodo que retorna a lista de todos os corretores associados ao objeto Gestor
     * @return
     */
    public List<Corretor> getCorretores() {
        return corretores;
    }

    /**
     * Metodo que seta a lista de todos os corretores associados ao objeto Gestor
     * @param corretores
     */
    public void setCorretores(List<Corretor> corretores) {
        this.corretores = corretores;
    }
}
