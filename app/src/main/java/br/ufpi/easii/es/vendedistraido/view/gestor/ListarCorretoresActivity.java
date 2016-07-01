package br.ufpi.easii.es.vendedistraido.view.gestor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.StringTokenizer;

import br.ufpi.easii.es.vendedistraido.R;
import br.ufpi.easii.es.vendedistraido.control.CorretorControle;
import br.ufpi.easii.es.vendedistraido.control.GestorControle;
import br.ufpi.easii.es.vendedistraido.model.Corretor;
import br.ufpi.easii.es.vendedistraido.model.Gestor;
import br.ufpi.easii.es.vendedistraido.view.corretor.CorretorActivity;

public class ListarCorretoresActivity extends AppCompatActivity {
    private ListView list_corretores;
    private Button btn_cadastrar;
    private long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_corretor);
        list_corretores = (ListView)findViewById(R.id.listar_corretor_list_corretores);
        btn_cadastrar = (Button)findViewById(R.id.listar_corretor_btn_cadastrar);
        btn_cadastrar.setOnClickListener(OnClickCadastrar());
        Intent intent = getIntent();
        if(intent.hasExtra(GestorActivity.ID_GESTOR)){
            id = intent.getLongExtra(GestorActivity.ID_GESTOR, -1);
            if(id!=-1){
                Log.i("LOGIN", "Gestor id="+id+" logado");
                //Fazer consulta somente com ID do Gestor no CorretorControle.listar(id_gestor)
                Gestor gestor = GestorControle.pesquisar(id);
                ArrayList<String> list = new ArrayList<String>();
                for(Corretor corretor:gestor.getCorretores()){
                    list.add(corretor.getNome());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
                list_corretores.setAdapter(adapter);
            }
        }
    }

    private View.OnClickListener OnClickCadastrar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CadastraCorretorActivity.class);
                intent.putExtra(CorretorActivity.ID_CORRETOR, id);
                startActivity(intent);
            }
        };
    }

    private Context getContext(){
        return this;
    }

}