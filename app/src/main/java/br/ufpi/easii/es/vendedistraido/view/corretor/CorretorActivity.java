package br.ufpi.easii.es.vendedistraido.view.corretor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import br.ufpi.easii.es.vendedistraido.R;
import br.ufpi.easii.es.vendedistraido.control.CorretorControle;
import br.ufpi.easii.es.vendedistraido.model.Corretor;
import br.ufpi.easii.es.vendedistraido.model.Imovel;

public class CorretorActivity extends AppCompatActivity {
    private Button btn_cadastrar;
    private ListView lista_imoveis;
    public static String ID_CORRETOR = "id_corretor";
    public Corretor corretor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corretor);

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