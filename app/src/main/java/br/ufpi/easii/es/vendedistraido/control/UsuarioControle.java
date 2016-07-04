package br.ufpi.easii.es.vendedistraido.control;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import br.ufpi.easii.es.vendedistraido.exception.ExcecaoDeUsuarioInexistente;
import br.ufpi.easii.es.vendedistraido.model.Usuario;
import br.ufpi.easii.es.vendedistraido.util.Constantes;
import br.ufpi.easii.es.vendedistraido.view.MainInterface;

/**
 * Created by Irvayne Matheus on 30/06/2016.
 * Classe responsavel por controlar o fluxo de dados dos usuarios
 */
public class UsuarioControle {

    private static final String SEND_URL = Constantes.SERVER_URL+"PesquisarUsuario.php";

    /**
     * Metodo responsavel por fazer uma requisicao ao servidor e retornar um usuario
     * @param usuario usuario passado como parametro para ser procurado no bando de dados
     * @param context contexto da view
     * @param mainInterface Interface utilizada para implementar as classe responsaveis por passar os dados retornados no controle para a visao
     * @throws ExcecaoDeUsuarioInexistente excecao que disparada caso o usuario passado como parametro nao exista no bando de dados
     */
    public static void pesquisar(final Usuario usuario, Context context, MainInterface mainInterface) throws ExcecaoDeUsuarioInexistente {
        final Gson gson = new Gson();
        //Converte o objeto para JSON
        final String jsonUsuario = gson.toJson(usuario);
        Usuario usr = new Usuario(-1,"","","","");
        /*Inicializacaoo da StringRequest, tendo como parametros: o metodo a ser utilizado(POST);
        * a URL da requisicao(SEND_URL); o objeto que implementa a Interface Response.Listener que ira tratar
        * o resultado da requisicao em caso de sucesso; e uma instancia da classe a ser utilizada em caso de erro.
         */
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SEND_URL,
                //Chama os metodos de sucesso e erro passando o contexto e a instancia da interface necessitada
                new RespostaSucessoPesquisa(context, mainInterface),
                new RespostaErroPesquisa(context, mainInterface)) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("objetoUsuario", jsonUsuario);
                return params;
            }
        };
        //A RequestQueue eh criada e tem a stringRequest adicionada
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}