package br.ufpi.easii.es.vendedistraido.control;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import br.ufpi.easii.es.vendedistraido.exception.ExcecaoDeUsuarioInexistente;
import br.ufpi.easii.es.vendedistraido.model.Usuario;
import br.ufpi.easii.es.vendedistraido.view.MainInterface;

/**
 * Created by jpegx on 7/3/2016.
 */
public class RespostaErroPesquisa implements Response.ErrorListener {
    private Usuario usuario;
    private MainInterface mainInterface;
    private Context context;
    public RespostaErroPesquisa(Context context, MainInterface mainInterface) {
        this.usuario = new Usuario(-1, "","","","");
        this.mainInterface = mainInterface;
        this.context = context;
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("LOG", "erro: " + error.getMessage().toString());
        //Chama o metodo utilizado para tratar erro na leitura dos dados
        mainInterface.dadosNaoLidos(new ExcecaoDeUsuarioInexistente());
    }
}
