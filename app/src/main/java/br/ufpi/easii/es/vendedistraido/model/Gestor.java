package br.ufpi.easii.es.vendedistraido.model;

import java.util.List;

/**
 * Created by Alexandre on 30/06/2016.
 */
public class Gestor extends Usuario{

    private List<Corretor> corretores;

    public Gestor(long id, String nome, String email, String senha, String telefone, List<Corretor> corretores) {
        super(id, nome, email, senha, telefone);
        this.corretores = corretores;
    }

    public List<Corretor> getCorretores() {
        return corretores;
    }

    public void setCorretores(List<Corretor> corretores) {
        this.corretores = corretores;
    }
}
