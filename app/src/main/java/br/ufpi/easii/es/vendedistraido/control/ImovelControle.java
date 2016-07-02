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
import java.util.List;
import java.util.Map;

import br.ufpi.easii.es.vendedistraido.exception.ExcecaoDeImovelInexistente;
import br.ufpi.easii.es.vendedistraido.exception.ExcecaoImovelJaExistente;
import br.ufpi.easii.es.vendedistraido.model.Cliente;
import br.ufpi.easii.es.vendedistraido.model.Corretor;
import br.ufpi.easii.es.vendedistraido.model.Gestor;
import br.ufpi.easii.es.vendedistraido.model.Imovel;
import br.ufpi.easii.es.vendedistraido.model.Usuario;

/**
 * Created by Alexandre on 01/07/2016.
 */
public class ImovelControle {
    private static final String SEND_URL_INSERIR = "http://10.28.15.49/VendeDistraido/main/AdicionaImovel.php";
    private static final String SEND_URL_REMOVER = "http://10.28.15.49/VendeDistraido/main/RemoveImovel.php";
    private static final String SEND_URL_EDITAR = "http://10.28.15.49/VendeDistraido/main/EditaImovel.php";
    private static final String SEND_URL_LISTAR = "http://10.28.15.49/VendeDistraido/main/ListaImovel.php";

    public static void inserir(final Imovel imovel, long idDoCorretor,Context context) throws ExcecaoImovelJaExistente{
        final Gson gson = new Gson();
        final String jsonImovel = gson.toJson(imovel);
        final String id = Long.toString(idDoCorretor);

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
                params.put("idCorretor", id);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public static void remover(final Imovel imovel, Context context) {
        final String id = Long.toString(imovel.getId());

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
                params.put("idImovel", id);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

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

    public static Imovel pesquisar(final Imovel imovel, Context context) throws ExcecaoDeImovelInexistente{
        final Gson gson = new Gson();
        final String jsonImovel = gson.toJson(imovel);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SEND_URL_LISTAR,
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

    //-1 carrega todos e o id carrega apenas os imoveis do corretor
    public static void listar(long idDoCorretor, Context context){
        final String id = Long.toString(idDoCorretor);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SEND_URL_LISTAR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("LOG", "response: " + response);
                        Gson gson = new Gson();

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
                params.put("idCorretor", id);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}