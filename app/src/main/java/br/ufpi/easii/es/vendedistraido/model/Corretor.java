package br.ufpi.easii.es.vendedistraido.model;

import java.util.List;

/**
 * Created by Alexandre on 30/06/2016.
 */
public class Corretor extends Usuario{
    private List<Imovel> imoveis;

    public Corretor(long id, String nome, String email, String senha, String telefone, List<Imovel> imoveis) {
        super(id, nome, email, senha, telefone);
        this.imoveis = imoveis;
    }

    public List<Imovel> getImoveis() {
        return imoveis;
    }

    public void setImoveis(List<Imovel> imoveis) {
        this.imoveis = imoveis;
    }
}
