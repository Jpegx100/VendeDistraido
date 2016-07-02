package br.ufpi.easii.es.vendedistraido.view.cliente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.ufpi.easii.es.vendedistraido.R;

public class ClienteActivity extends AppCompatActivity {

    public static final String ID_CLIENTE = "id_cliente";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
    }
}