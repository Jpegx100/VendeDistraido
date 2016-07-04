package br.ufpi.easii.es.vendedistraido.control;

import br.ufpi.easii.es.vendedistraido.control.UsuarioControle;
import br.ufpi.easii.es.vendedistraido.model.Gestor;

/**
 * Created by jpegx on 7/1/2016.
 * Classe responsavel por controlar o fluxo de dados para os usuarios do tipo Gestor
 */
public class GestorControle {
    /**
     * Metodo que dispara uma requisicao para o servidor e retorna um usuario do tipo gestor
     * @param id identificador unico do usuario do tipo gestor passado como parametro
     * @return retorno do tipo gestor
     */
    public static Gestor pesquisar(long id) {
        //return (Gestor) UsuarioControle.pesquisar("email", "senha");
        return null;
    }
}
