package br.ufpi.easii.es.vendedistraido.view;

import br.ufpi.easii.es.vendedistraido.model.Usuario;

/**
 * Created by jpegx on 7/3/2016.
 * Interface que define os metodo a tratar dos dados lidos ou nao lidos.
 */
public interface MainInterface {
    /**
     * Metodo a ser implementado pelas classes(que utilizarem a interface) para realizar o tratamento
     * dos dados a lidos.
     * @param dados objeto generico que devera ser tratado em cada classe de acordo com o resultado esperados.
     */
    public void dadosLidos(Object dados);

    /**
     * Metodo a ser implementado pelas classes(que utilizarem a interface) para realizar o tratamento
     * de erros na leitura dos dados.
     * @param e excecao gerada que devera ser tratada pela classe que implementar o metodo.
     */
    public void dadosNaoLidos(Exception e);
}
