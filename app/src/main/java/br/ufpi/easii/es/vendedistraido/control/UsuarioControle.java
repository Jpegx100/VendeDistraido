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

import br.ufpi.easii.es.vendedistraido.exception.ExcecaoDeUsuarioInexistente;
import br.ufpi.easii.es.vendedistraido.model.Cliente;
import br.ufpi.easii.es.vendedistraido.model.Corretor;
import br.ufpi.easii.es.vendedistraido.model.Gestor;
import br.ufpi.easii.es.vendedistraido.model.Imovel;
import br.ufpi.easii.es.vendedistraido.model.Usuario;
import br.ufpi.easii.es.vendedistraido.view.MainActivity;
import br.ufpi.easii.es.vendedistraido.view.MainInterface;

/**
 * Created by Irvayne Matheus on 30/06/2016.
 */
public class UsuarioControle {

    private static final String SEND_URL = "http://10.0.0.103/VendeDistraido/main/Pesquisar.php";


    public static void pesquisar(final Usuario usuario, Context context, MainInterface mainInterface) throws ExcecaoDeUsuarioInexistente {
        final Gson gson = new Gson();
        final String jsonUsuario = gson.toJson(usuario);
        final String type;
        Usuario usr = new Usuario(-1,"","","","");

        /*Inicializacaoo da StringRequest, tendo como parametros: o metodo a ser utilizado(POST);
        * a URL da requisicao(SEND_URL); o objeto que implementa a Interface Response.Listener que ira tratar
        * o resultado da requisicao em caso de sucesso; e uma instancia da classe a ser utilizada em caso de erro.
         */
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SEND_URL,
                new Rec(usr, context, mainInterface),
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
                params.put("objetoUsuario", jsonUsuario);
                return params;
            }
        };
        //A RequestQueue eh criada e tem a stringRequest adicionada
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
/**
 public static Usuario pesquisar(String email, String senha) {
 ArrayList<Corretor> corretores = new ArrayList<Corretor>();
 corretores.add(new Corretor(9, "corretor","em","s","w",new ArrayList<Imovel>()));
 corretores.add(new Corretor(19, "corretor","em","s","w",new ArrayList<Imovel>()));
 corretores.add(new Corretor(29, "corretor","em","s","w",new ArrayList<Imovel>()));
 corretores.add(new Corretor(39, "corretor","em","s","w",new ArrayList<Imovel>()));
 corretores.add(new Corretor(49, "corretor","em","s","w",new ArrayList<Imovel>()));
 Gestor gestor = new Gestor(1,"nome",email, senha,"999",corretores);
 return gestor;
 }**/
}