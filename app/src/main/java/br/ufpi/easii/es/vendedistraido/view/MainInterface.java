package br.ufpi.easii.es.vendedistraido.view;

import br.ufpi.easii.es.vendedistraido.model.Usuario;

/**
 * Created by jpegx on 7/3/2016.
 * Interface que define os metodo a tratar dos dados lidos ou nao lidos.
 */
public interface MainInterface {
    public void dadosLidos(Usuario dados);
    public void dadosNaoLidos();
}
