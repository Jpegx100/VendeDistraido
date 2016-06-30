package br.ufpi.easii.es.vendedistraido.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.ufpi.easii.es.vendedistraido.R;

public class CadastroActivity extends AppCompatActivity {

    private EditText edt_email, edt_senha,edt_nome, edt_telefone, edt_confirmaSenha;
    private Button btn_cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

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

            }
        };
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
