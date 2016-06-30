package br.ufpi.easii.es.vendedistraido.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.ufpi.easii.es.vendedistraido.R;

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

            }
        };
    }

    private View.OnClickListener onClickCadastrar(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CadastroActivity.class);
                startActivity(intent);
            }
        };
    }

    private Context getContext(){
        return this;
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

    public Button getBtn_entrar() {
        return btn_entrar;
    }

    public void setBtn_entrar(Button btn_entrar) {
        this.btn_entrar = btn_entrar;
    }

    public Button getBtn_cadastrar() {
        return btn_cadastrar;
    }

    public void setBtn_cadastrar(Button btn_cadastrar) {
        this.btn_cadastrar = btn_cadastrar;
    }
}