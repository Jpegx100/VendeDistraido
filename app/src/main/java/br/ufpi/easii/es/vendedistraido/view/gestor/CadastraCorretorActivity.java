package br.ufpi.easii.es.vendedistraido.view.gestor;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.ufpi.easii.es.vendedistraido.R;
import br.ufpi.easii.es.vendedistraido.exception.ExcecaoDeErroDeConexao;
import br.ufpi.easii.es.vendedistraido.exception.ExcecaoDeUsuarioJaExistente;
import br.ufpi.easii.es.vendedistraido.model.Corretor;

public class CadastraCorretorActivity extends AppCompatActivity {

    private EditText edt_nome, edt_telefone, edt_email, edt_senha, edt_conf_senha;
    private Button btn_cadastrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_corretor);
        edt_nome = (EditText)findViewById(R.id.cadastra_corretor_edt_nome);
        edt_telefone = (EditText)findViewById(R.id.cadastra_corretor_edt_telefone);
        edt_email = (EditText)findViewById(R.id.cadastra_corretor_edt_email);
        edt_senha = (EditText)findViewById(R.id.cadastra_corretor_edt_senha);
        edt_conf_senha = (EditText)findViewById(R.id.cadastra_corretor_edt_confirme_senha);
        btn_cadastrar = (Button) findViewById(R.id.cadastra_corretor_btn_cadastrar);
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
