package br.ufpi.easii.es.vendedistraido.control;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import br.ufpi.easii.es.vendedistraido.model.Corretor;
import br.ufpi.easii.es.vendedistraido.view.MainInterface;

/**
 * Created by Irvayne Matheus on 04/07/2016.
 */
public class RespostaSucessoListarCorretor implements Response.Listener<String> {

    private MainInterface mainInterface;
    private Context context;

    public RespostaSucessoListarCorretor(Context context, MainInterface mainInterface){
        this.mainInterface = mainInterface;
        this.context = context;
    }
    @Override
    public void onResponse(String response) {
        Log.i("LOG", "responseZXX: " + response);
        ArrayList<Corretor> corretor = new ArrayList<Corretor>();
        Gson gson = new Gson();
        try {
            JSONArray jsonArray = new JSONArray(response);

            for (int i=0; i<jsonArray.length(); i++) {
                corretor.add(gson.fromJson(jsonArray.getString(i), Corretor.class));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mainInterface.dadosLidos(corretor);
    }
}
