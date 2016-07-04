package br.ufpi.easii.es.vendedistraido.control;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;

import br.ufpi.easii.es.vendedistraido.model.Cliente;
import br.ufpi.easii.es.vendedistraido.model.Corretor;
import br.ufpi.easii.es.vendedistraido.model.Gestor;
import br.ufpi.easii.es.vendedistraido.model.Usuario;
import br.ufpi.easii.es.vendedistraido.view.MainInterface;
import cz.msebera.android.httpclient.Header;

/**
 * Created by jpegx on 7/2/2016.
 */
public class Rec implements Response.Listener<String>{
    private Usuario usuario;
    private MainInterface mainInterface;
    private Context context;
    public  Rec(Usuario usuario, Context context, MainInterface mainInterface){
        this.usuario = usuario;
        this.mainInterface = mainInterface;
        this.context = context;
        Log.i("NOSSO","\nTU"+this.usuario+"\n|U"+usuario);
    }
    @Override
    public void onResponse(String response) {
        Log.i("LOG", "response: " + response);
        Gson gson = new Gson();
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
        Log.i("LOG", "response: " + usuario.getNome());
        mainInterface.dadosLidos(usuario);
        Toast t = Toast.makeText(context, usuario.getNome(), Toast.LENGTH_LONG);
        t.show();
    }
}
