package br.ufpi.easii.es.vendedistraido.view.corretor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.ufpi.easii.es.vendedistraido.R;

public class CadastraImovelActivity extends AppCompatActivity {
    private EditText edt_titulo, edt_latitude, edt_longitude, edt_endereco;
    private Button btn_cadastrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_imovel);
        edt_titulo = (EditText)findViewById(R.id.cadastra_imovel_edt_titulo);
        edt_latitude = (EditText)findViewById(R.id.cadastra_imovel_edt_latitude);
        edt_longitude = (EditText)findViewById(R.id.cadastra_imovel_edt_longitude);
        edt_endereco = (EditText)findViewById(R.id.cadastra_imovel_edt_endereco);
        btn_cadastrar = (Button)findViewById(R.id.cadastra_imovel_btn_cadastrar);
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
