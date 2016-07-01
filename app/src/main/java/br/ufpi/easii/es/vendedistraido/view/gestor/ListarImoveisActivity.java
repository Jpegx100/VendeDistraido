package br.ufpi.easii.es.vendedistraido.view.gestor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.ufpi.easii.es.vendedistraido.R;
import br.ufpi.easii.es.vendedistraido.control.CorretorControle;
import br.ufpi.easii.es.vendedistraido.control.GestorControle;
import br.ufpi.easii.es.vendedistraido.model.Corretor;
import br.ufpi.easii.es.vendedistraido.model.Gestor;
import br.ufpi.easii.es.vendedistraido.model.Imovel;

public class ListarImoveisActivity extends AppCompatActivity {

    private ListView list_imoveis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_imoveis);

        list_imoveis = (ListView) findViewById(R.id.listar_imovel_list_imoveis);

        listImoveis();
    }

    public void listImoveis(){
        Intent intent = getIntent();

        if (intent.hasExtra(GestorActivity.ID_GESTOR)){
            long id = intent.getLongExtra(GestorActivity.ID_GESTOR, -1);
            if (id != -1){
                Log.i("LOGIN", "Gestor id="+id+" logado");
                Gestor gestor = GestorControle.pesquisar(id);

                ArrayList<String> list = new ArrayList<String>();
                List<Imovel> listaImovel = new ArrayList<Imovel>();

                for (Corretor corretor: gestor.getCorretores()){
                    listaImovel.addAll(CorretorControle.pesquisarImoveis(corretor));
                }

                for (Imovel imovel: listaImovel){
                    list.add(imovel.getEndereco());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list);
                list_imoveis.setAdapter(adapter);
            }
        }
    }
}
