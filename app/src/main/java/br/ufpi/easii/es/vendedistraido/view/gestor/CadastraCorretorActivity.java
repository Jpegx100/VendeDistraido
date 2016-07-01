package br.ufpi.easii.es.vendedistraido.view.gestor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import br.ufpi.easii.es.vendedistraido.R;
import br.ufpi.easii.es.vendedistraido.control.CorretorControle;
import br.ufpi.easii.es.vendedistraido.control.GestorControle;
import br.ufpi.easii.es.vendedistraido.exception.ExcecaoDeErroDeConexao;
import br.ufpi.easii.es.vendedistraido.exception.ExcecaoDeUsuarioJaExistente;
import br.ufpi.easii.es.vendedistraido.model.Cliente;
import br.ufpi.easii.es.vendedistraido.model.Corretor;
import br.ufpi.easii.es.vendedistraido.model.Gestor;
import br.ufpi.easii.es.vendedistraido.model.Imovel;

import static br.ufpi.easii.es.vendedistraido.control.CorretorControle.*;

public class CadastraCorretorActivity extends AppCompatActivity {

    private EditText edt_nome, edt_telefone, edt_email, edt_senha, edt_conf_senha;
    private Button btn_cadastrar;
    private Gestor gestor;
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

        Intent intent = getIntent();
        if(intent.hasExtra(GestorActivity.ID_GESTOR)){
            gestor = GestorControle.pesquisar(intent.getLongExtra(GestorActivity.ID_GESTOR,-1));
        }
    }

    private View.OnClickListener onClickCadastrar(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edt_nome.getText().toString();
                String email = edt_email.getText().toString();
                String senha = edt_senha.getText().toString();
                String telefone = edt_telefone.getText().toString();
                Corretor corretor = new Corretor(-1, nome, email, senha, telefone, null);
                try {
                    CorretorControle.inserir(corretor);
                } catch (ExcecaoDeErroDeConexao excecaoDeErroDeConexao) {
                    excecaoDeErroDeConexao.printStackTrace();
                } catch (ExcecaoDeUsuarioJaExistente excecaoDeUsuarioJaExistente) {
                    excecaoDeUsuarioJaExistente.printStackTrace();
                }
            }
        };
    }
}