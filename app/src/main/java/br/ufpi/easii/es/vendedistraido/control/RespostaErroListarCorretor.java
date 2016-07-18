package br.ufpi.easii.es.vendedistraido.control;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import br.ufpi.easii.es.vendedistraido.view.MainInterface;

/**
 * Classe para tratar erro na listagem de Corretores
 * Created by Irvayne Matheus on 04/07/2016.
 */
public class RespostaErroListarCorretor implements Response.ErrorListener {

    public RespostaErroListarCorretor(Context context, MainInterface mainInterface){
       //TODO
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("LOG", "ERROR EM LISTAR CORRETOR: " + error.getMessage().toString());
    }
}
