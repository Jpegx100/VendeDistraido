package br.ufpi.easii.es.vendedistraido.control;

import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.*;

import br.ufpi.easii.es.vendedistraido.exception.ExcecaoDeErroDeConexao;
import br.ufpi.easii.es.vendedistraido.exception.ExcecaoDeUsuarioJaExistente;
import br.ufpi.easii.es.vendedistraido.model.Cliente;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Irvayne Matheus on 30/06/2016.
 */
public class ClienteControle {

    private static final String SEND_URL = "";

    public void inserir(final Cliente cliente) throws ExcecaoDeErroDeConexao, ExcecaoDeUsuarioJaExistente{
        final Gson gson = new Gson();
        String jsonCliente = gson.toJson(cliente);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("objetoCliente", jsonCliente);

        final Cliente[] clienteResposta = new Cliente[1];

        client.post(SEND_URL, params, new TextHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String res) {
                        // called when response HTTP status is "200 OK"
                         clienteResposta[0] = gson.fromJson(res, Cliente.class);

                        Log.i("LOG", "OK" + res);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)

                        Log.i("LOG","D:"+res);
                    }
                }
        );


        //insere no servidor

        //redireciona para tela apropriada
    }
}
