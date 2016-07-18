package br.ufpi.easii.es.vendedistraido.control;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import br.ufpi.easii.es.vendedistraido.model.Cliente;
import br.ufpi.easii.es.vendedistraido.model.Corretor;
import br.ufpi.easii.es.vendedistraido.view.MainInterface;

/***
 * Classe para tratar sucesso na listagem de Clientes
 * Created by Irvayne Matheus on 15/07/2016.
 */
public class RespostaSucessoListarCliente implements Response.Listener<String>{
    private MainInterface mainInterface;
    private Context context;

    /**
     * Construtor padrao da classe
     * @param context contexto da aplicacao
     * @param mainInterface interface que trata o sucesso da requisicao
     */
    public RespostaSucessoListarCliente(Context context, MainInterface mainInterface){
        this.mainInterface = mainInterface;
        this.context = context;
    }

    /**
     * Metodo que trata o sucesso da requisicao e chama o metodo correspondente na interface
     * @param response resposta do servidor
     */
    @Override
    public void onResponse(String response) {
        Log.i("RESPONSE",response);
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        Gson gson = new Gson();
        try {
            JSONArray jsonArray = new JSONArray(response);

            for (int i=0; i<jsonArray.length(); i++) {
                clientes.add(gson.fromJson(jsonArray.getString(i), Cliente.class));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mainInterface.dadosLidos(clientes);
    }
}
