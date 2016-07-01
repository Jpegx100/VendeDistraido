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

/**
 * Created by Irvayne Matheus on 30/06/2016.
 */
public class UsuarioControle {

    private static final String SEND_URL = "http://10.28.15.49/VendeDistraido/main/BuscarUsuario.php";


    public static Usuario pesquisar(final Usuario usuario, Context context) throws ExcecaoDeUsuarioInexistente {

        final Gson gson = new Gson();
        final String jsonUsuario = gson.toJson(usuario);
        final String type;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SEND_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                         Usuario usuarioLogado;
                        String[] rep = response.split("!=>");
                        if(rep[1].equals("cl")){

                            usuarioLogado = gson.fromJson(rep[0],Cliente.class);

                            usuario.setId(usuarioLogado.getId());
                            usuario.setNome(usuarioLogado.getNome()+"!=>cl");
                            usuario.setEmail(usuarioLogado.getEmail());
                            usuario.setSenha(usuarioLogado.getSenha());
                            usuario.setTelefone(usuarioLogado.getTelefone());


                        }
                        if(rep[1].equals("co")){
                            usuarioLogado = gson.fromJson(rep[0],Corretor.class);

                            usuario.setId(usuarioLogado.getId());
                            usuario.setNome(usuarioLogado.getNome()+"!=>co");
                            usuario.setEmail(usuarioLogado.getEmail());
                            usuario.setSenha(usuarioLogado.getSenha());
                            usuario.setTelefone(usuarioLogado.getTelefone());
                        }
                       if(rep[1].equals("ge")){
                            usuarioLogado = gson.fromJson(rep[0],Gestor.class);

                            usuario.setId(usuarioLogado.getId());
                            usuario.setNome(usuarioLogado.getNome()+"!=>ge");
                            usuario.setEmail(usuarioLogado.getEmail());
                            usuario.setSenha(usuarioLogado.getSenha());
                            usuario.setTelefone(usuarioLogado.getTelefone());
                        }
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
                params.put("objetoCliente", jsonUsuario);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

            if(usuario.getNome().contains("!=>cl")){
                return new Cliente(usuario.getId(),usuario.getNome(),usuario.getEmail(),usuario.getSenha(),usuario.getTelefone(),null);
            }
            if(usuario.getNome().contains("!=>co")){
                return new Corretor(usuario.getId(),usuario.getNome(),usuario.getEmail(),usuario.getSenha(),usuario.getTelefone(),null);
            }
            if(usuario.getNome().contains("!=>ge")){
                return new Gestor(usuario.getId(),usuario.getNome(),usuario.getEmail(),usuario.getSenha(),usuario.getTelefone(),null);
            }
        throw new ExcecaoDeUsuarioInexistente();
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
