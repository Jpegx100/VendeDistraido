package br.ufpi.easii.es.vendedistraido.view.corretor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufpi.easii.es.vendedistraido.R;
import br.ufpi.easii.es.vendedistraido.model.Corretor;
import br.ufpi.easii.es.vendedistraido.model.Imovel;
import br.ufpi.easii.es.vendedistraido.util.Constantes;

public class CorretorActivity extends AppCompatActivity {
    private Button btn_cadastrar;
    private ListView lista_imoveis;
    public static String ID_CORRETOR = "id_corretor";
    private Corretor corretor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corretor);
        Toast.makeText(this, "ClienteActivity", Toast.LENGTH_LONG);
        this.corretor = usuarioLogado();
        /*btn_cadastrar = (Button)findViewById(R.id.corretor_btn_cadastar_imovel);
        btn_cadastrar.setOnClickListener(onClickCadastrar());

        Intent intent = getIntent();
        if(intent.hasExtra(ID_CORRETOR)){
            long id = intent.getLongExtra(ID_CORRETOR, -1);
            corretor = CorretorControle.pesquisar(id);
            ArrayList<String> list = new ArrayList<String>();

            for(Imovel imovel:corretor.getImoveis()){
                list.add(imovel.getEndereco());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
            lista_imoveis.setAdapter(adapter);
        }else{
            onDestroy();
        }*/
    }
    private Corretor usuarioLogado(){
        SharedPreferences sharedPreferences = getSharedPreferences(Constantes.USER, Context.MODE_PRIVATE);
        if(sharedPreferences == null) return null;
        Corretor corretor = new Corretor(sharedPreferences.getLong(Constantes.USER_LOGIN_ID,-1),
                sharedPreferences.getString(Constantes.USER_LOGIN_NOME,"-1"),
                sharedPreferences.getString(Constantes.USER_LOGIN_EMAIL,"-1"),
                sharedPreferences.getString(Constantes.USER_LOGIN_SENHA,"-1"),
                sharedPreferences.getString(Constantes.USER_LOGIN_TELEFONE,"-1"),
                //Pegar LISTA de IMOVEIS
                new ArrayList<Imovel>());
        return corretor;
    }
    private View.OnClickListener onClickCadastrar(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CadastraImovelActivity.class);
                intent.putExtra(ID_CORRETOR, corretor.getId());
                startActivity(intent);
            }
        };
    }
    private Context getContext(){
        return this;
    }
}