package br.ufpi.easii.es.vendedistraido.view.gestor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufpi.easii.es.vendedistraido.R;
import br.ufpi.easii.es.vendedistraido.control.GestorControle;
import br.ufpi.easii.es.vendedistraido.control.UsuarioControle;
import br.ufpi.easii.es.vendedistraido.exception.ExcecaoDeUsuarioInexistente;
import br.ufpi.easii.es.vendedistraido.model.Corretor;
import br.ufpi.easii.es.vendedistraido.model.Gestor;
import br.ufpi.easii.es.vendedistraido.model.Imovel;
import br.ufpi.easii.es.vendedistraido.model.Usuario;
import br.ufpi.easii.es.vendedistraido.view.Constantes;

public class GestorActivity extends AppCompatActivity {
    public static final String ID_GESTOR = "id_gestor";
    private Button btn_corretores, btn_imoveis, btn_relatorios;
    private Gestor gestor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestor);
        Toast.makeText(this, "ClienteActivity", Toast.LENGTH_LONG);
        btn_corretores = (Button)findViewById(R.id.gestor_btn_corretores);
        btn_corretores.setOnClickListener(onClickCorretores());
        btn_imoveis = (Button)findViewById(R.id.gestor_btn_imoveis);
        btn_imoveis.setOnClickListener(onClickImoveis());
        btn_relatorios = (Button)findViewById(R.id.gestor_btn_relatorios);
        btn_relatorios.setOnClickListener(onClickRelatorios());
        this.gestor = usuarioLogado();
    }

    private View.OnClickListener onClickCorretores() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListarCorretoresActivity.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener onClickImoveis() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListarImoveisActivity.class);
                intent.putExtra(ID_GESTOR, gestor.getId());
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener onClickRelatorios() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //RELATORIOS
            }
        };
    }

    private Context getContext(){
        return this;
    }
    private Gestor usuarioLogado(){
        SharedPreferences sharedPreferences = getSharedPreferences(Constantes.USER, Context.MODE_PRIVATE);
        if(sharedPreferences == null) return null;
        Gestor gestor = new Gestor(sharedPreferences.getLong(Constantes.USER_LOGIN_ID,-1),
                sharedPreferences.getString(Constantes.USER_LOGIN_NOME,"-1"),
                sharedPreferences.getString(Constantes.USER_LOGIN_EMAIL,"-1"),
                sharedPreferences.getString(Constantes.USER_LOGIN_SENHA,"-1"),
                sharedPreferences.getString(Constantes.USER_LOGIN_TELEFONE,"-1"),
                //Pegar LISTA de IMOVEIS
                new ArrayList<Corretor>());
        return gestor;
    }
}