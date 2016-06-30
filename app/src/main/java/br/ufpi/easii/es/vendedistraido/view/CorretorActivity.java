package br.ufpi.easii.es.vendedistraido.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import br.ufpi.easii.es.vendedistraido.R;

public class CorretorActivity extends AppCompatActivity {
    private Button btn_cadastrar;
    private ListView lista_imoveis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corretor);
        btn_cadastrar = (Button)findViewById(R.id.corretor_btn_cadastar_imovel);
        btn_cadastrar.setOnClickListener(onClickCadastrar());
    }
    private View.OnClickListener onClickCadastrar(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }
}
