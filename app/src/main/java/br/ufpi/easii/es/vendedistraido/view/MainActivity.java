package br.ufpi.easii.es.vendedistraido.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.ufpi.easii.es.vendedistraido.R;

public class MainActivity extends AppCompatActivity {

    private EditText email, senha;
    private Button btnEntrar, btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.main_edt_email);
        senha = (EditText) findViewById(R.id.main_edt_senha);

        btnCadastrar = (Button) findViewById(R.id.main_btn_cadastrar);
        btnEntrar = (Button) findViewById(R.id.main_btn_entrar);

        btnCadastrar.setOnClickListener(onClickCadastrar());

        btnEntrar.setOnClickListener(onClickEntrar());

    }

    private View.OnClickListener onClickEntrar(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        };
    }

    private View.OnClickListener onClickCadastrar(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        };
    }


    public EditText getEmail() {
        return email;
    }

    public void setEmail(EditText email) {
        this.email = email;
    }

    public EditText getSenha() {
        return senha;
    }

    public void setSenha(EditText senha) {
        this.senha = senha;
    }

    public Button getBtnEntrar() {
        return btnEntrar;
    }

    public void setBtnEntrar(Button btnEntrar) {
        this.btnEntrar = btnEntrar;
    }

    public Button getBtnCadastrar() {
        return btnCadastrar;
    }

    public void setBtnCadastrar(Button btnCadastrar) {
        this.btnCadastrar = btnCadastrar;
    }
}