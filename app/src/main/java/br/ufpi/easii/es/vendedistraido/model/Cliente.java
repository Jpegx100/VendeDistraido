package br.ufpi.easii.es.vendedistraido.model;

import java.util.List;

/**
 * Created by Alexandre on 30/06/2016.
 */
public class Cliente extends Usuario{
    private List<Imovel> imoveisInteresses;

    public Cliente(long id, String nome, String email, String senha, String telefone, List<Imovel> imoveisInteresses) {
        super(id, nome, email, senha, telefone);
        this.imoveisInteresses = imoveisInteresses;
    }

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