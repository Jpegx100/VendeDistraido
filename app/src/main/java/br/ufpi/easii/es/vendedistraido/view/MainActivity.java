package br.ufpi.easii.es.vendedistraido.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.ufpi.easii.es.vendedistraido.R;
import br.ufpi.easii.es.vendedistraido.control.UsuarioControle;
import br.ufpi.easii.es.vendedistraido.model.Cliente;
import br.ufpi.easii.es.vendedistraido.model.Corretor;
import br.ufpi.easii.es.vendedistraido.model.Gestor;
import br.ufpi.easii.es.vendedistraido.model.Usuario;
import br.ufpi.easii.es.vendedistraido.view.cliente.ClienteActivity;
import br.ufpi.easii.es.vendedistraido.view.corretor.CorretorActivity;
import br.ufpi.easii.es.vendedistraido.view.gestor.GestorActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edt_email, edt_senha;
    private Button btn_entrar, btn_cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_email = (EditText) findViewById(R.id.main_edt_email);
        edt_senha = (EditText) findViewById(R.id.main_edt_senha);
        btn_cadastrar = (Button) findViewById(R.id.main_btn_cadastrar);
        btn_entrar = (Button) findViewById(R.id.main_btn_entrar);
        btn_cadastrar.setOnClickListener(onClickCadastrar());
        btn_entrar.setOnClickListener(onClickEntrar());
    }

    private View.OnClickListener onClickEntrar(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String senha = edt_senha.getText().toString();
                String email = edt_email.getText().toString();
                Usuario usuario = UsuarioControle.pesquisar(email, senha);
                if(usuario instanceof Cliente){
                    Log.i("LOGIN", "Cliente Logado");
                    Intent intent = new Intent(getContext(), ClienteActivity.class);
                    intent.putExtra(ClienteActivity.ID_CLIENTE, usuario.getId());
                    startActivity(intent);
                }else if(usuario instanceof Gestor){
                    Log.i("LOGIN", "Gestor Logado");
                    Intent intent = new Intent(getContext(), GestorActivity.class);
                    intent.putExtra(GestorActivity.ID_GESTOR, usuario.getId());
                    startActivity(intent);
                }else if(usuario instanceof Corretor){
                    Log.i("LOGIN", "Gestor Logado");
                    Intent intent = new Intent(getContext(), CorretorActivity.class);
                    intent.putExtra(CorretorActivity.ID_CORRETOR, usuario.getId());
                    startActivity(intent);
                }
            }
        };
    }

    private View.OnClickListener onClickCadastrar(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CadastroClienteActivity.class);
                startActivity(intent);
            }
        };
    }
    private Context getContext(){
        return this;
    }
}