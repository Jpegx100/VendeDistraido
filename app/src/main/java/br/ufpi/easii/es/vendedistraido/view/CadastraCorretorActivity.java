package br.ufpi.easii.es.vendedistraido.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.ufpi.easii.es.vendedistraido.R;
import br.ufpi.easii.es.vendedistraido.exception.ExcecaoDeErroDeConexao;
import br.ufpi.easii.es.vendedistraido.exception.ExcecaoDeUsuarioJaExistente;
import br.ufpi.easii.es.vendedistraido.model.Corretor;

public class CadastraCorretorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_corretor);
    }
}
