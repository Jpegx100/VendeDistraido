package br.ufpi.easii.es.vendedistraido.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import br.ufpi.easii.es.vendedistraido.R;
import br.ufpi.easii.es.vendedistraido.control.ClienteControle;
import br.ufpi.easii.es.vendedistraido.exception.ExcecaoDeErroDeConexao;
import br.ufpi.easii.es.vendedistraido.exception.ExcecaoDeUsuarioJaExistente;
import br.ufpi.easii.es.vendedistraido.model.Cliente;
import br.ufpi.easii.es.vendedistraido.util.Constantes;
import br.ufpi.easii.es.vendedistraido.view.cliente.ClienteActivity;
/**
 * Created by Jpegx.
 * Classe responsavel por exibir a tela de cadastro de Cliente.
 */
public class CadastroClienteActivity extends AppCompatActivity {

    private EditText edt_email, edt_senha,edt_nome, edt_telefone, edt_confirmaSenha;
    private Button btn_cadastrar;
    private Context context ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cadastro);
        context = this;

        edt_nome = (EditText) findViewById(R.id.cadastro_edt_nome);
        edt_email = (EditText) findViewById(R.id.cadastro_edt_email);
        edt_telefone = (EditText) findViewById(R.id.cadastro_edt_telefone);
        edt_senha = (EditText) findViewById(R.id.cadastro_edt_senha);
        edt_confirmaSenha = (EditText) findViewById(R.id.cadastro_edt_confirmeSenha);

        btn_cadastrar = (Button) findViewById(R.id.cadastro_btn_cadastrar);

        btn_cadastrar.setOnClickListener(onClickCadastro());
    }

    private View.OnClickListener onClickCadastro(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String nome, email, telefone, senha, csenha;
                nome = edt_nome.getText().toString();
                email = edt_email.getText().toString();
                telefone = edt_telefone.getText().toString();
                senha = edt_senha.getText().toString();
                csenha = edt_confirmaSenha.getText().toString();
                Cliente cliente = new Cliente(nome, email, senha, telefone, null);

                try {
                    ClienteControle.inserir(cliente,context);
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences(Constantes.USER, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(Constantes.USER_LOGIN_EMAIL, (cliente.getEmail()));
                    editor.putString(Constantes.USER_LOGIN_NOME, (cliente.getNome()));
                    editor.putString(Constantes.USER_LOGIN_SENHA, (cliente.getSenha()));
                    editor.putString(Constantes.USER_LOGIN_TELEFONE, (cliente.getTelefone()));
                    editor.putLong(Constantes.USER_LOGIN_ID, (cliente.getId()));
                    editor.apply();
                    Intent intent = new Intent(getContext(), ClienteActivity.class);
                    startActivity(intent);
                } catch (ExcecaoDeErroDeConexao excecaoDeErroDeConexao) {
                    excecaoDeErroDeConexao.printStackTrace();
                } catch (ExcecaoDeUsuarioJaExistente excecaoDeUsuarioJaExistente) {
                    excecaoDeUsuarioJaExistente.printStackTrace();
                }
            }//fim botao
        };
    }

    private Context getContext() {
        return this.context;
    }

    public Button getBtn_cadastrar() {
        return btn_cadastrar;
    }

    public void setBtn_cadastrar(Button btn_cadastrar) {
        this.btn_cadastrar = btn_cadastrar;
    }

    public EditText getEdt_email() {
        return edt_email;
    }

    public void setEdt_email(EditText edt_email) {
        this.edt_email = edt_email;
    }

    public EditText getEdt_senha() {
        return edt_senha;
    }

    public void setEdt_senha(EditText edt_senha) {
        this.edt_senha = edt_senha;
    }

    public EditText getEdt_nome() {
        return edt_nome;
    }

    public void setEdt_nome(EditText edt_nome) {
        this.edt_nome = edt_nome;
    }

    public EditText getEdt_telefone() {
        return edt_telefone;
    }

    public void setEdt_telefone(EditText edt_telefone) {
        this.edt_telefone = edt_telefone;
    }

    public EditText getEdt_confirmaSenha() {
        return edt_confirmaSenha;
    }

    public void setEdt_confirmaSenha(EditText edt_confirmaSenha) {
        this.edt_confirmaSenha = edt_confirmaSenha;
    }
}
