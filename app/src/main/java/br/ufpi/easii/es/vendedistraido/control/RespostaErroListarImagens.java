package br.ufpi.easii.es.vendedistraido.control;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import br.ufpi.easii.es.vendedistraido.view.MainInterface;

/**
 * Created by Irvayne Matheus on 29/07/2016.
 */
public class RespostaErroListarImagens implements Response.ErrorListener {
    private MainInterface mainInterface;
    private Context context;

    public RespostaErroListarImagens(Context context, MainInterface mainInterface) {
        this.mainInterface = mainInterface;
        this.context = context;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("LOG", "RESPOSTA-ERROR-LISTAR-IMAGENS: " + error.getMessage().toString());
    }
}