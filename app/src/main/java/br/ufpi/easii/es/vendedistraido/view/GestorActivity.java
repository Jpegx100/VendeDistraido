package br.ufpi.easii.es.vendedistraido.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.ufpi.easii.es.vendedistraido.R;

public class GestorActivity extends AppCompatActivity {
    private Button btn_corretores, btn_imoveis, btn_relatorios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestor);
        btn_corretores = (Button)findViewById(R.id.gestor_btn_corretores);
        btn_corretores.setOnClickListener(onClickCorretores());
        btn_imoveis = (Button)findViewById(R.id.gestor_btn_imoveis);
        btn_imoveis.setOnClickListener(onClickImoveis());
        btn_relatorios = (Button)findViewById(R.id.gestor_btn_relatorios);
        btn_relatorios.setOnClickListener(onClickRelatorios());
    }

    private View.OnClickListener onClickCorretores() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }

    private View.OnClickListener onClickImoveis() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }

    private View.OnClickListener onClickRelatorios() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }
}
