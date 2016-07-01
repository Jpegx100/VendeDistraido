package br.ufpi.easii.es.vendedistraido.control;

import java.util.ArrayList;
import java.util.List;

import br.ufpi.easii.es.vendedistraido.model.Cliente;
import br.ufpi.easii.es.vendedistraido.model.Corretor;
import br.ufpi.easii.es.vendedistraido.model.Gestor;
import br.ufpi.easii.es.vendedistraido.model.Imovel;
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

    public static Usuario pesquisar(String email, String senha) {
        ArrayList<Corretor> corretores = new ArrayList<Corretor>();
        corretores.add(new Corretor(9, "corretor","em","s","w",new ArrayList<Imovel>()));
        corretores.add(new Corretor(19, "corretor","em","s","w",new ArrayList<Imovel>()));
        corretores.add(new Corretor(29, "corretor","em","s","w",new ArrayList<Imovel>()));
        corretores.add(new Corretor(39, "corretor","em","s","w",new ArrayList<Imovel>()));
        corretores.add(new Corretor(49, "corretor","em","s","w",new ArrayList<Imovel>()));
        Gestor gestor = new Gestor(1,"nome",email, senha,"999",new ArrayList<Corretor>());
        return gestor;
    }
}
