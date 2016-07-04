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

import br.ufpi.easii.es.vendedistraido.exception.ExcecaoDeImovelInexistente;
import br.ufpi.easii.es.vendedistraido.exception.ExcecaoImovelJaExistente;
import br.ufpi.easii.es.vendedistraido.model.Corretor;
import br.ufpi.easii.es.vendedistraido.model.Imovel;
import br.ufpi.easii.es.vendedistraido.util.Constantes;
import br.ufpi.easii.es.vendedistraido.view.MainInterface;


/**
 * Created by Alexandre on 01/07/2016.
 * Classe responsavel por controlar o fluxo de dados para objetos do tipo Imovel
 */
public class ImovelControle {
    /**
     * Definicao das URLs de conexao com o servidor e o banco de dados
     */
    private static final String SEND_URL_INSERIR = Constantes.SERVER_URL+"AdicionaImovel.php";
    private static final String SEND_URL_REMOVER = Constantes.SERVER_URL+"RemoveImovel.php";
    private static final String SEND_URL_EDITAR = Constantes.SERVER_URL+"EditaImovel.php";
    private static final String SEND_URL_PESQUISAR_POR_CORRETOR = Constantes.SERVER_URL+"ListarImoveisPorCorretor.php";
    private static final String SEND_URL_PESQUISAR_TODOS = Constantes.SERVER_URL+"ListarImoveis.php";
    private static final String SEND_URL_PESQUISAR = Constantes.SERVER_URL+"PesquisaImovel.php";

    /**
     * Metodo responsavel por fazer requisicao ao servidor para que possa adicionar um novo imovel, representa o fluxo de insercao de imovel
     * @param imovel Imovel vindo da visao e que sera inserido no banco de dados
     * @param corretor Corretor que sera relacionado ao novo imovel que sera adicionado
     * @param context Contexto da view
     * @throws ExcecaoImovelJaExistente Excecao que sera disparada caso o imovel que esteja sendo inserido ja exista no banco de dados
     */
    public static void inserir(final Imovel imovel, Corretor corretor,Context context) throws ExcecaoImovelJaExistente{
        final Gson gson = new Gson();
        final String jsonImovel = gson.toJson(imovel);
        final String jsonCorretor = gson.toJson(corretor.getId());

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
                        Log.i("LOG", "erro: " + error.getMessage().toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("objetoImovel", jsonImovel);
                params.put("idCorretor", jsonCorretor);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    /**
     * Metodo responsavel por remover um imovel passado como parametro do banco de dados atraves de uma requisicao para o servidor
     * @param imovel Imovel vindo da visao para ser removido
     * @param context Contexto da view
     */
    public static void remover(final Imovel imovel, Context context) {
        final Gson gson = new Gson();
        final String jsonImovel = gson.toJson(imovel);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SEND_URL_REMOVER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("LOG", "response: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("LOG", "erro: " + error.getMessage().toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("objetoImovel", jsonImovel);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    /**
     * Metodo que faz requisicao com o servidor com um objeto e atualiza esse objeto no banco de dados caso o memso ja exista
     * @param imovel Imovel vindo da visao para ser atualizado
     * @param context Contexto da view
     */
    public static void editar(final Imovel imovel, Context context){
        final Gson gson = new Gson();
        final String jsonImovel = gson.toJson(imovel);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SEND_URL_EDITAR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("LOG", "response: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("LOG", "erro: " + error.getMessage().toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("objetoImovel", jsonImovel);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    /**
     * Metodo responsavel por fazer uma requisicao com o servidor e retornar um objeto do tipo imovel de acordo com os parametros passados
     * @param imovel Imovel vindo da visao para que possa ser pesquisado
     * @param context Contexto da view
     * @return O metodo retorna um objeto do tipo Imovel
     * @throws ExcecaoDeImovelInexistente Excecao disparada caso o imovel pesquisado ainda nao exista no banco de dados
     */
    public static Imovel pesquisar(final Imovel imovel, Context context) throws ExcecaoDeImovelInexistente{
        final Gson gson = new Gson();
        final String jsonImovel = gson.toJson(imovel);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SEND_URL_PESQUISAR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Imovel imovelPesquisado;
                        imovelPesquisado = gson.fromJson(response, Imovel.class);

                        imovel.setId(imovelPesquisado.getId());
                        imovel.setEndereco(imovelPesquisado.getEndereco());
                        imovel.setLatitude(imovelPesquisado.getLatitude());
                        imovel.setLongitude(imovelPesquisado.getLongitude());

                        Log.i("LOG", "response: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Toast.makeText(ClienteControle.this, "Verifique sua conexão!", Toast.LENGTH_LONG).show();
                        Log.i("LOG", "erro: " + error.getMessage().toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("objetoImovel", jsonImovel);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

        return imovel;
    }

    /**
     * Metodo responsavel por retornar a lista de imoveis que estao relacionados a um determinado corretor
     * @param corretor parametro necessario para pesquisar os imoveis deste corretor
     * @param context Contexto da view
     * @param mainInterface Interface utilizada para fazer a passagem dos dados recebidos do controle para a view
     */
    public static void pesquisar(Corretor corretor, Context context, MainInterface mainInterface){
        final Gson gson = new Gson();
        final String jsonCorretor = gson.toJson(corretor.getId());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SEND_URL_PESQUISAR_POR_CORRETOR,
                new RespostaSucessoListaImovel(context, mainInterface),
                new RespostaErroPesquisa(context, mainInterface)) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("idCorretor", jsonCorretor);

                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    /**
     * Metodo responsavel por retornar a lista de todos os imoveis cadastrados para que possam ser vistos pelo cliente
     * @param context Contexto da view
     * @param mainInterface Inteface utilizada para fazer a passagem dos dados recebidos do controle para a view
     */
    public static void pesquisar(Context context, MainInterface mainInterface){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SEND_URL_PESQUISAR_TODOS,
                new RespostaSucessoListaImovel(context, mainInterface),
                new RespostaErroPesquisa(context, mainInterface)) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}