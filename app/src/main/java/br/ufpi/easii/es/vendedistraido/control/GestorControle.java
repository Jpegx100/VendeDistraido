package br.ufpi.easii.es.vendedistraido.control;

import br.ufpi.easii.es.vendedistraido.control.UsuarioControle;
import br.ufpi.easii.es.vendedistraido.model.Gestor;

/**
 * Created by jpegx on 7/1/2016.
 */
public class GestorControle {
    public static Gestor pesquisar(long id) {
        return (Gestor) UsuarioControle.pesquisar("email", "senha");
    }
}
