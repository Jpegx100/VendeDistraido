package br.ufpi.easii.es.vendedistraido.control;

import br.ufpi.easii.es.vendedistraido.model.Cliente;
import br.ufpi.easii.es.vendedistraido.model.Corretor;
import br.ufpi.easii.es.vendedistraido.model.Gestor;
import br.ufpi.easii.es.vendedistraido.model.Usuario;

/**
 * Created by Irvayne Matheus on 30/06/2016.
 */
public class UsuarioControle {

    public void login(Usuario usuario){
        //faz a requisi�ao no servidor

        if(usuario instanceof Cliente){
            //direcina para alguma tela de cliente
            //cria a se��o do usu�rio
        }
        if(usuario instanceof Corretor){
            //direcina para alguma tela de corretor
            //cria a se��o do usu�rio
        }
        if(usuario instanceof Gestor){
            //direcina para alguma tela de gestor
            //cria a se��o do usu�rio
        }
    }
}
