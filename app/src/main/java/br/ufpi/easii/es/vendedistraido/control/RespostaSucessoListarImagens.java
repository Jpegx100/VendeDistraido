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
 * Created by Irvayne Matheus on 29/07/2016.
 */
public class RespostaSucessoListarImagens implements Response.Listener<String> {
    private MainInterface mainInterface;
    private Context context;

    /**
     * Contrutor padra da classe
     * @param context contexto da aplicacao
     * @param mainInterface Interface que trata a resposta
     */
    public RespostaSucessoListarImagens(Context context, MainInterface mainInterface) {
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
        List<String> imagens = new ArrayList<String>();
        Gson gson = new Gson();
        String json = gson.fromJson(response,String.class);
        //TODO
       // String [] list = json.split();


        mainInterface.dadosLidos(imagens);
    }
}