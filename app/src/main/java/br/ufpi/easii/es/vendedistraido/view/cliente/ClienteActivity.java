package br.ufpi.easii.es.vendedistraido.view.cliente;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import br.ufpi.easii.es.vendedistraido.R;
import br.ufpi.easii.es.vendedistraido.control.ImovelControle;
import br.ufpi.easii.es.vendedistraido.model.Cliente;
import br.ufpi.easii.es.vendedistraido.model.Imovel;
import br.ufpi.easii.es.vendedistraido.util.Constantes;
import br.ufpi.easii.es.vendedistraido.view.MainActivity;
import br.ufpi.easii.es.vendedistraido.view.MainInterface;

/**
 * Created by Jpegx.
 * Activity que implementa a interface MainInterface e e responsavel pela tela inicial do cliente.
 */
public class ClienteActivity extends AppCompatActivity implements MainInterface {

    public static final String ID_CLIENTE = "id_cliente";
    private Cliente cliente;
    private ListView listView;
    private Button btn_mapa, btn_lista;
    /**
     * Metodo padrao
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Imoveis");
        setContentView(R.layout.activity_cliente);
        Toast.makeText(this, "ClienteActivity", Toast.LENGTH_LONG);
        btn_mapa = (Button)findViewById(R.id.cliente_btn_mapa);
        btn_mapa.setOnClickListener(mapaClick());

        cliente = usuarioLogado();
        listView = (ListView)findViewById(R.id.cliente_list_imoveis);
        ImovelControle.pesquisar(this, ClienteActivity.this);
    }

    private View.OnClickListener mapaClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapaClienteActivity.class);
                startActivity(intent);
            }
        };
    }

    /**
     * Metodo que transforma os dados do arquivo de preferencias no objeto Cliente logado na sessao.
     * @return retorna o cliente logado ou null caso nao haja alguem logado
     */
    private Cliente usuarioLogado(){
        SharedPreferences sharedPreferences = getSharedPreferences(Constantes.USER, Context.MODE_PRIVATE);
        if(sharedPreferences == null) return null;
        Cliente cliente = new Cliente(sharedPreferences.getLong(Constantes.USER_LOGIN_ID,-1),
                sharedPreferences.getString(Constantes.USER_LOGIN_NOME,"-1"),
                sharedPreferences.getString(Constantes.USER_LOGIN_EMAIL,"-1"),
                sharedPreferences.getString(Constantes.USER_LOGIN_SENHA,"-1"),
                sharedPreferences.getString(Constantes.USER_LOGIN_TELEFONE,"-1"),
                //Pegar LISTA de IMOVEIS
                new ArrayList<Imovel>());
        return cliente;
    }

    /**
     * Metodo de view.MainInterface
     * @param dados
     */
    @Override
    public void dadosLidos(Object dados) {
        final ArrayList<Imovel> imoveis = new ArrayList<Imovel>();
        if((dados instanceof ArrayList) && (((ArrayList) dados).size()>0)){
            if(((ArrayList) dados).get(0) instanceof Imovel){
                for(Imovel i:(ArrayList<Imovel>)dados){
                    imoveis.add(i);
                }

                AdapterListView adapter = new AdapterListView(this, R.layout.item_lista, imoveis);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getContext(), ImovelClienteActivity.class);
                        intent.putExtra(Constantes.IMOVEL_ENDERECO, imoveis.get(position).getEndereco());
                        intent.putExtra(Constantes.IMOVEL_VALOR, imoveis.get(position).getValor());
                        intent.putExtra(Constantes.IMOVEL_ID, imoveis.get(position).getId());
                        //intent.putExtra(Constantes.IMOVEL_FOTO, imoveis.get(position).getFoto());
                        startActivity(intent);
                    }
                });
            }
        }
    }
    /**
     * Metodo de view.MainInterface
     * @param e
     */
    @Override
    public void dadosNaoLidos(Exception e) {
    }

    public Context getContext() {
        return this;
    }
}