package br.ufpi.easii.es.vendedistraido.control;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import br.ufpi.easii.es.vendedistraido.model.Imovel;
import br.ufpi.easii.es.vendedistraido.view.MainInterface;

/**
 * Created by Alexandre on 04/07/2016.
 * Classe responsavel por passar os parametros retornados pela requisicao do controle para a view para uma listagem de imoveis
 */
public class RespostaSucessoListaImovel implements Response.Listener<String> {
    private MainInterface mainInterface;
    private Context context;

    /**
     * Contrutor padra da classe
     * @param context contexto da aplicacao
     * @param mainInterface Interface que trata a resposta
     */
    public RespostaSucessoListaImovel(Context context, MainInterface mainInterface) {
        this.mainInterface = mainInterface;
        this.context = context;
    }

    /**
     * Metodo que faz o tratamento da resposta do servidor
     * @param response
     */
    @Override
    public void onResponse(String response) {
        Log.i("LOG", "response: " + response);
        List<Imovel> imoveis = new ArrayList<Imovel>();
        Gson gson = new Gson();
        try {
            JSONArray jsonArray = new JSONArray (response);
            for(int i = 0; i < jsonArray.length(); i++){
                imoveis.add(gson.fromJson(jsonArray.getString(i), Imovel.class));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mainInterface.dadosLidos(imoveis);
    }
}