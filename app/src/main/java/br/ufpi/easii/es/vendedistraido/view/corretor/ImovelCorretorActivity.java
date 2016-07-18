package br.ufpi.easii.es.vendedistraido.view.corretor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufpi.easii.es.vendedistraido.R;
import br.ufpi.easii.es.vendedistraido.control.ClienteControle;
import br.ufpi.easii.es.vendedistraido.control.ImovelControle;
import br.ufpi.easii.es.vendedistraido.model.Cliente;
import br.ufpi.easii.es.vendedistraido.model.Imovel;
import br.ufpi.easii.es.vendedistraido.util.Constantes;
import br.ufpi.easii.es.vendedistraido.view.MainInterface;
import br.ufpi.easii.es.vendedistraido.view.cliente.AdapterListView;

public class ImovelCorretorActivity extends AppCompatActivity implements MainInterface{
    private Button btn_editar;
    private ListView list_clientes;
    private TextView edt_preco, edt_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imovel_corretor);
        btn_editar = (Button)findViewById(R.id.imovel_corretor_btn_editar);
        list_clientes = (ListView)findViewById(R.id.imovel_corretor_lst_clientes);
        edt_end = (TextView)findViewById(R.id.imovel_corretor_txt_endereco);
        edt_preco = (TextView)findViewById(R.id.imovel_corretor_txt_preco);

        Bundle args = getIntent().getExtras();
        String end = args.getString(Constantes.IMOVEL_ENDERECO);
        String valor = String.valueOf(args.getDouble(Constantes.IMOVEL_VALOR));
        long id = args.getLong(Constantes.IMOVEL_ID);

        edt_preco.setText(valor);
        edt_end.setText(end);
        Imovel imovel = new Imovel();
        imovel.setId(id);
        ClienteControle.pesquisar(imovel, getContext(), ImovelCorretorActivity.this);
    }

    @Override
    public void dadosLidos(Object dados) {
        final ArrayList<String> clientes = new ArrayList<>();
        if((dados instanceof ArrayList) && (((ArrayList) dados).size()>0)){
            if(((ArrayList) dados).get(0) instanceof Cliente){
                for(Cliente i:(ArrayList<Cliente>)dados){
                    clientes.add(i.getNome());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clientes);
                list_clientes.setAdapter(adapter);
            }
        }
    }

    @Override
    public void dadosNaoLidos(Exception e) {

    }

    public Context getContext() {return this;    }
}
