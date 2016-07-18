package br.ufpi.easii.es.vendedistraido.control;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import br.ufpi.easii.es.vendedistraido.view.MainInterface;

/**
 * Classe para tratar erro na listagem de Clientes
 * Created by Irvayne Matheus on 15/07/2016.
 */
public class RespostaErroListarCliente implements Response.ErrorListener  {
    private MainInterface mainInterface;
    private Context context;

    /**
     * Construtor padrao da classe
     * @param mainInterface interface necessaria para o tratamento do erro
     * @param context Contexto da aplicacao
     */
    public RespostaErroListarCliente(MainInterface mainInterface, Context context) {
        this.mainInterface = mainInterface;
        this.context = context;
    }
    /**
     * Tratamento do erro
     * @param error
     */
    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("LOG", "RESPOSTA-ERROR-LISTAR-IMOVEL: " + error.getMessage().toString());
    }

}
