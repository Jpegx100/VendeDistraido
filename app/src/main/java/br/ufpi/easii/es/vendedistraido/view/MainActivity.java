package br.ufpi.easii.es.vendedistraido.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufpi.easii.es.vendedistraido.R;
import br.ufpi.easii.es.vendedistraido.control.UsuarioControle;
import br.ufpi.easii.es.vendedistraido.exception.ExcecaoDeUsuarioInexistente;
import br.ufpi.easii.es.vendedistraido.model.Cliente;
import br.ufpi.easii.es.vendedistraido.model.Corretor;
import br.ufpi.easii.es.vendedistraido.model.Gestor;
import br.ufpi.easii.es.vendedistraido.model.Usuario;
import br.ufpi.easii.es.vendedistraido.view.cliente.ClienteActivity;
import br.ufpi.easii.es.vendedistraido.view.corretor.CorretorActivity;
import br.ufpi.easii.es.vendedistraido.view.gestor.GestorActivity;

public class MainActivity extends AppCompatActivity implements MainInterface{

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
                String email = edt_email.getText().toString();
                try {
                    //Chama o metodo de Pesquisa passando o usuario a ser pesquisado; o contexto; e a instacia da activity
                    UsuarioControle.pesquisar(new Usuario(-1, "", email, "", ""), getContext(), MainActivity.this);
                } catch (ExcecaoDeUsuarioInexistente excecaoDeUsuarioInexistente) {
                    excecaoDeUsuarioInexistente.printStackTrace();
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

    @Override
    public void dadosLidos(Object dados) {
        Log.i("USUARIO", dados.getClass().getName());
        if(dados instanceof Usuario) {
            /*if(((Usuario) dados).getSenha() != edt_senha.getText().toString()){
                edt_senha.setText("");
                edt_email.setText("");
                Toast t = Toast.makeText(getContext(), "Senha Incorreta!", Toast.LENGTH_LONG);
            }else {*/
                SharedPreferences sharedPreferences = getContext().getSharedPreferences(Constantes.USER, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Constantes.USER_LOGIN_EMAIL, ((Usuario) dados).getEmail());
                editor.putString(Constantes.USER_LOGIN_NOME, ((Usuario) dados).getNome());
                editor.putString(Constantes.USER_LOGIN_SENHA, ((Usuario) dados).getSenha());
                editor.putString(Constantes.USER_LOGIN_TELEFONE, ((Usuario) dados).getTelefone());
                editor.putLong(Constantes.USER_LOGIN_ID, ((Usuario) dados).getId());
                editor.apply();
                if (dados instanceof Cliente) {
                    Intent intent = new Intent(getContext(), ClienteActivity.class);
                    startActivity(intent);
                    Log.i("TYPE", "Cliente");
                }
                if (dados instanceof Corretor) {
                    Intent intent = new Intent(getContext(), CorretorActivity.class);
                    startActivity(intent);
                    Log.i("TYPE", "Corretor");
                }
                if (dados instanceof Gestor) {
                    Intent intent = new Intent(getContext(), GestorActivity.class);
                    startActivity(intent);
                    Log.i("TYPE", "Gestor");
                }
            //}
        }
    }
    @Override
    public void dadosNaoLidos(Exception e){
        Log.e("ERROR", "Usuario NAO ENCONTRADO");
    }
}