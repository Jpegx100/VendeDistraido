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


import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
;


import br.ufpi.easii.es.vendedistraido.exception.ExcecaoDeErroDeConexao;
import br.ufpi.easii.es.vendedistraido.exception.ExcecaoDeUsuarioJaExistente;
import br.ufpi.easii.es.vendedistraido.model.Corretor;
import br.ufpi.easii.es.vendedistraido.model.Gestor;
import br.ufpi.easii.es.vendedistraido.util.Constantes;


/**
 * Created by Irvayne Matheus on 30/06/2016.
 * Classe responsavel pelo controle do fluxo dos usuarios do tipo corretor
 */
public class CorretorControle {
    private static final String SEND_URL_INSERIR = Constantes.SERVER_URL+"AdicionaCorretor.php";
    private static final String SEND_URL_LISTAR = Constantes.SERVER_URL+"ListarCorretor.php";

    /**
     * Metodo responsavel pela insercao de um novo usuario do tipo corretor
     * @param corretor objeto do tipo corretor vindo da visao e que sera inserido no banco de dados
     * @param gestor objeto do tipo gestor vindo da visao e que sera relacionado com o corretor que esta sendo inserido
     * @param context contexto da view
     * @throws ExcecaoDeErroDeConexao excecao que sera disparada caso exista erro na conexao
     * @throws ExcecaoDeUsuarioJaExistente excecao que sera desparada caso o cliente passado como parametro ja exista no banco de dados
     */
    public static void inserir(Corretor corretor, Gestor gestor, Context context) throws ExcecaoDeErroDeConexao, ExcecaoDeUsuarioJaExistente {
        final Gson gson = new Gson();
        final String jsonCorretor = gson.toJson(corretor);
        final String jsonGestor = gson.toJson(gestor);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SEND_URL_INSERIR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("LOG", "response: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Toast.makeText(ClienteControle.this, "Verifique sua conexo!", Toast.LENGTH_LONG).show();
                        Log.i("LOG", "erro: " + error.getMessage().toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("objetoCorretor", jsonCorretor);
                params.put("objetoGestor", jsonGestor);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    /**
     * Metodo responsavel pela listagem de todos os corretores que sao gerenciados pelo gestor que foi passado como parametro
     * @param idGestor identificador unico do gestor para que possa ser feito a listagem dos corretores que o mesmo gerencia
     * @param context contexto da view
     * @return retorna uma lista com todos os corretores encontrados para tais entradas
     */
    public List<Corretor> listar(long idGestor, Context context){
        final Gson gson = new Gson();
        final String jsonIdGestor = gson.toJson(idGestor);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SEND_URL_LISTAR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("LOG", "response: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Toast.makeText(ClienteControle.this, "Verifique sua conexo!", Toast.LENGTH_LONG).show();
                        Log.i("LOG", "erro: " + error.getMessage().toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("objetoCorretor", jsonIdGestor);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return new ArrayList<>();
    }
}