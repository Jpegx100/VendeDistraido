package br.ufpi.easii.es.vendedistraido.control;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufpi.easii.es.vendedistraido.exception.ExcecaoDeErroDeConexao;
import br.ufpi.easii.es.vendedistraido.exception.ExcecaoDeUsuarioJaExistente;
import br.ufpi.easii.es.vendedistraido.model.Cliente;
import br.ufpi.easii.es.vendedistraido.model.Imovel;
import br.ufpi.easii.es.vendedistraido.util.Constantes;
import br.ufpi.easii.es.vendedistraido.view.MainInterface;

/**
 * Created by Irvayne Matheus on 30/06/2016.
 * Classe responsavel pelo controle do fluxo dos usuarios do tipo cliente
 */
public class ClienteControle {

    private static final String SEND_URL = Constantes.SERVER_URL+"AdicionaCliente.php";
    private static final String  SEND_URL_LISTAR_CLIENTES_POR_IMOVEL = Constantes.SERVER_URL+"ListarClientesPorImovel.php";

    /**
     * Metodo responsavel pela insercao de um novo usuario do tipo cliente
     * @param cliente objeto do tipo cliente vindo da visao e que sera inserido no banco de dados
     * @param context contexto da view
     * @throws ExcecaoDeErroDeConexao excecao que sera disparada caso exista erro na conexao
     * @throws ExcecaoDeUsuarioJaExistente excecao que sera disparada caso o cliente passado como parametro ja exista no banco de dados
     */
    public static void inserir(Cliente cliente, Context context) throws ExcecaoDeErroDeConexao, ExcecaoDeUsuarioJaExistente{

        final Gson gson = new Gson();
        final String jsonCliente = gson.toJson(cliente);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SEND_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
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
                params.put("objetoCliente", jsonCliente);

                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    /**
     * Retorna todos os Clientes interessados no Imovel
     * @param imovel - objeto imovel
     * @param context
     * @param mainInterface
     */
    public static void pesquisar(Imovel imovel, Context context, MainInterface mainInterface){
        final Gson gson = new Gson();
        final String jsonImovel = gson.toJson(imovel.getId());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SEND_URL_LISTAR_CLIENTES_POR_IMOVEL,
                new RespostaSucessoListarCliente(context, mainInterface),
                new RespostaErroPesquisa(context, mainInterface)) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("idImovel", jsonImovel);

                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }
}