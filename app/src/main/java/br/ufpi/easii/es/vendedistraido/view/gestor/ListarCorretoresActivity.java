package br.ufpi.easii.es.vendedistraido.view.gestor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.ufpi.easii.es.vendedistraido.R;
import br.ufpi.easii.es.vendedistraido.model.Corretor;
import br.ufpi.easii.es.vendedistraido.model.Gestor;
import br.ufpi.easii.es.vendedistraido.util.Constantes;

public class ListarCorretoresActivity extends AppCompatActivity {
    private ListView list_corretores;
    private Button btn_cadastrar;
    private Gestor gestor;
    private long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_corretor);
        list_corretores = (ListView)findViewById(R.id.listar_corretor_list_corretores);
        btn_cadastrar = (Button)findViewById(R.id.listar_corretor_btn_cadastrar);
        btn_cadastrar.setOnClickListener(OnClickCadastrar());
        this.gestor = usuarioLogado();
        ArrayList<String> list = new ArrayList<String>();
        for(Corretor corretor:gestor.getCorretores()){
            list.add(corretor.getNome());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        list_corretores.setAdapter(adapter);
    }
    private Gestor usuarioLogado(){
        SharedPreferences sharedPreferences = getSharedPreferences(Constantes.USER, Context.MODE_PRIVATE);
        if(sharedPreferences == null) return null;
        Gestor gestor = new Gestor(sharedPreferences.getLong(Constantes.USER_LOGIN_ID,-1),
                sharedPreferences.getString(Constantes.USER_LOGIN_NOME,"-1"),
                sharedPreferences.getString(Constantes.USER_LOGIN_EMAIL,"-1"),
                sharedPreferences.getString(Constantes.USER_LOGIN_SENHA,"-1"),
                sharedPreferences.getString(Constantes.USER_LOGIN_TELEFONE,"-1"),
                //Pegar LISTA de Corretores
                new ArrayList<Corretor>());
        return gestor;
    }
    private View.OnClickListener OnClickCadastrar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CadastraCorretorActivity.class);
                startActivity(intent);
            }
        };
    }
    private Context getContext(){
        return this;
    }
}