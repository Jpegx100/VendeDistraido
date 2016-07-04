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
public class RespostaSucessoPesquisa implements Response.Listener<String>{
    private Usuario usuario;
    private MainInterface mainInterface;
    private Context context;
    public RespostaSucessoPesquisa(Context context, MainInterface mainInterface){
        this.usuario = new Usuario(-1, "","","","");
        this.mainInterface = mainInterface;
        this.context = context;
    }
    @Override
    public void onResponse(String response) {
        Log.i("LOG", "response: " + response);
        Gson gson = new Gson();
        Usuario usuarioLogado = new Usuario(-1,"","","","");
        String[] rep = response.split("!=>");
        if(rep[1].equals("cl")){
            usuarioLogado = gson.fromJson(rep[0],Cliente.class);
        }
        if(rep[1].equals("co")){
            usuarioLogado = gson.fromJson(rep[0],Corretor.class);
        }
        if(rep[1].equals("ge")) {
            usuarioLogado = gson.fromJson(rep[0], Gestor.class);
        }
        //Utiliza a interface para chamar o metodo que trata do sucesso na leitura dos dados
        mainInterface.dadosLidos(usuarioLogado);
    }
}
