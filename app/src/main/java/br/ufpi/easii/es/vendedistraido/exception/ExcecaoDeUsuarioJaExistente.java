package br.ufpi.easii.es.vendedistraido.exception;

import br.ufpi.easii.es.vendedistraido.model.Usuario;

/**
 * Created by Irvayne Matheus on 30/06/2016.
 */
public class ExcecaoDeUsuarioJaExistente extends  Exception {
    public ExcecaoDeUsuarioJaExistente(String mensagem) {
        super(mensagem);
    }
}
