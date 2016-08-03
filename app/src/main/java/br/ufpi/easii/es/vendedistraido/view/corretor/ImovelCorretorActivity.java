package br.ufpi.easii.es.vendedistraido.view.corretor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.ufpi.easii.es.vendedistraido.R;
import br.ufpi.easii.es.vendedistraido.control.ClienteControle;
import br.ufpi.easii.es.vendedistraido.control.ImovelControle;
import br.ufpi.easii.es.vendedistraido.model.Cliente;
import br.ufpi.easii.es.vendedistraido.model.Imovel;
import br.ufpi.easii.es.vendedistraido.util.Constantes;
import br.ufpi.easii.es.vendedistraido.view.MainInterface;
import br.ufpi.easii.es.vendedistraido.view.cliente.AdapterListView;

import static br.ufpi.easii.es.vendedistraido.util.CarregarImagem.StringToBitMap;

public class ImovelCorretorActivity extends AppCompatActivity implements MainInterface{
    private Button btn_editar;
    private ListView list_clientes;
    private TextView edt_preco, edt_end;
    private ImageView img_casa;

    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listNome;
    private HashMap<String, List<String>> listClientes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imovel_corretor);
        //list_clientes = (ListView)findViewById(R.id.imovel_corretor);
        edt_end = (TextView)findViewById(R.id.imovel_corretor_txt_endereco);
        edt_preco = (TextView)findViewById(R.id.imovel_corretor_txt_preco);
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        img_casa = (ImageView) findViewById(R.id.imovel_corretor_img_principal);

        Bundle args = getIntent().getExtras();
        String end = args.getString(Constantes.IMOVEL_ENDERECO);
        String valor = String.valueOf(args.getDouble(Constantes.IMOVEL_VALOR));
        String foto = args.getString(Constantes.IMOVEL_FOTO);
        long id = args.getLong(Constantes.IMOVEL_ID);

        edt_preco.setText(valor);
        edt_end.setText(end);
        img_casa.setImageBitmap(StringToBitMap(foto));

        Imovel imovel = new Imovel();
        imovel.setId(id);
        Log.i("ID_IMOVEL", id+"");
        ClienteControle.pesquisar(imovel, getContext(), ImovelCorretorActivity.this);
    }



    @Override
    public void dadosLidos(Object dados) {
        Log.i("Dados_LIDOS","OK");
        final ArrayList<String> clientes = new ArrayList<>();
        listClientes = new HashMap<String, List<String>>();

        if((dados instanceof ArrayList) && (((ArrayList) dados).size()>0)){
            if(((ArrayList) dados).get(0) instanceof Cliente){

                for(Cliente i:(ArrayList<Cliente>)dados){

                    ArrayList<String> dadosUsuario = new ArrayList<String>();
                    clientes.add(i.getNome());
                    dadosUsuario.add(i.getEmail());
                    dadosUsuario.add(i.getTelefone());
                    listClientes.put(i.getNome(), dadosUsuario);

                }

                Log.i("Dados_LIDOS", clientes.get(0));
                listAdapter = new ExpandableListAdapter(this, clientes, listClientes);
                expListView.setAdapter(listAdapter);
            }
        }
    }

    @Override
    public void dadosNaoLidos(Exception e) {

    }

    public Context getContext() {return this;    }

    public HashMap<String, List<String>> getListClientes() {
        return listClientes;
    }

    public void setListClientes(HashMap<String, List<String>> listClientes) {
        this.listClientes = listClientes;
    }

    public List<String> getListNome() {
        return listNome;
    }

    public void setListNome(List<String> listNome) {
        this.listNome = listNome;
    }

    public ExpandableListView getExpListView() {
        return expListView;
    }

    public void setExpListView(ExpandableListView expListView) {
        this.expListView = expListView;
    }

    public ExpandableListAdapter getListAdapter() {
        return listAdapter;
    }

    public void setListAdapter(ExpandableListAdapter listAdapter) {
        this.listAdapter = listAdapter;
    }
}
