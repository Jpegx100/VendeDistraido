package br.ufpi.easii.es.vendedistraido.control;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import br.ufpi.easii.es.vendedistraido.view.MainInterface;

/**
 * Created by Alexandre on 04/07/2016.
 * Classe responsavel por informar a view quando ouver um erro na requisicao dos dados para uma listagem de imoveis
 */
public class RespostaErroListarImovel implements Response.ErrorListener {
    private MainInterface mainInterface;
    private Context context;

    public RespostaErroListarImovel(MainInterface mainInterface, Context context) {
        this.mainInterface = mainInterface;
        this.context = context;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("LOG", "RESPOSTA-ERROR-LISTAR-IMOVEL: " + error.getMessage().toString());
    }
}