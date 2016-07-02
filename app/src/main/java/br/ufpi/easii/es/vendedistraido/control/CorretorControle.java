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


/**
 * Created by Irvayne Matheus on 30/06/2016.
 */
public class CorretorControle {
    private static final String SEND_URL_INSERIR = "http://10.28.15.49/VendeDistraido/main/AdicionaCorretor.php";
    private static final String SEND_URL_LISTAR = "http://10.28.15.49/VendeDistraido/main/ListarCorretor.php";


    public static void inserir(Corretor corretor, Context context) throws ExcecaoDeErroDeConexao, ExcecaoDeUsuarioJaExistente {

        final Gson gson = new Gson();
        final String jsonCorretor = gson.toJson(corretor);


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

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        //insere no servidor

        //redireciona para tela apropriada
    }

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

/**
    public static List<Imovel> pesquisarImoveis(Corretor corretor){
        List<Imovel> imoveis = new ArrayList<Imovel>();


        imoveis.add(new Imovel(1, "1.0.2.3.4", "1.7.4.6.1", "Morada Nova", corretor));
        imoveis.add(new Imovel(2, "1.2.2.3.4", "1.2.4.6.1", "Morada Nova", corretor));
        imoveis.add(new Imovel(3, "1.4.2.3.4", "1.0.4.6.1", "Morada Nova", corretor));
        imoveis.add(new Imovel(4, "1.1.2.3.4", "1.5.4.6.1", "Morada Nova", corretor));
        imoveis.add(new Imovel(5, "1.4.2.3.4", "1.6.4.6.1", "Morada Nova", corretor));

        return imoveis;
    }
**/
}

