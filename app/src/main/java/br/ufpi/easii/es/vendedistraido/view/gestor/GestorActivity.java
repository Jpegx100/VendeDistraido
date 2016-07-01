package br.ufpi.easii.es.vendedistraido.view.gestor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import br.ufpi.easii.es.vendedistraido.R;
import br.ufpi.easii.es.vendedistraido.control.GestorControle;
import br.ufpi.easii.es.vendedistraido.model.Gestor;

public class GestorActivity extends AppCompatActivity {
    public static final String ID_GESTOR = "id_gestor";
    private Button btn_corretores, btn_imoveis, btn_relatorios;
    private Gestor gestor;
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
        Intent intent = getIntent();
        if(intent.hasExtra(ID_GESTOR)) {
            long id = intent.getLongExtra(ID_GESTOR, -1);
            if (id != -1) {
                Log.i("LOGIN", "Gestor id="+id+" logado.");
                gestor = GestorControle.pesquisar(id);
            }
        }else{
            //Retornar ao login!
        }
    }

    private View.OnClickListener onClickCorretores() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListarCorretoresActivity.class);
                intent.putExtra(ID_GESTOR, gestor.getId());
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener onClickImoveis() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListarImoveisActivity.class);
                intent.putExtra(ID_GESTOR, gestor.getId());
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener onClickRelatorios() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //RELATORIOS
            }
        };
    }

    private Context getContext(){
        return this;
    }
}