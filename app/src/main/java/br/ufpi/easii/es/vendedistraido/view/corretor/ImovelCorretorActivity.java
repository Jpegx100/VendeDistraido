package br.ufpi.easii.es.vendedistraido.view.corretor;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import br.ufpi.easii.es.vendedistraido.util.CarregarImagem;
import br.ufpi.easii.es.vendedistraido.util.Constantes;
import br.ufpi.easii.es.vendedistraido.view.MainInterface;

import static br.ufpi.easii.es.vendedistraido.util.CarregarImagem.StringToBitMap;

public class ImovelCorretorActivity extends AppCompatActivity implements MainInterface{
    private TextView txt_preco, txt_end, txt_desc, txt_titulo;
    private ImageView img_casa;

    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listNome;
    private HashMap<String, List<String>> listClientes;
    private Imovel imovel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imovel_corretor);
        setTitle("Imovel");
        //list_clientes = (ListView)findViewById(R.id.imovel_corretor);
        txt_end = (TextView)findViewById(R.id.imovel_corretor_txt_endereco);
        txt_preco = (TextView)findViewById(R.id.imovel_corretor_txt_preco);
        txt_desc = (TextView)findViewById(R.id.imovel_corretor_txt_descricao);
        txt_titulo = (TextView)findViewById(R.id.imovel_corretor_txt_titulo);
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        img_casa = (ImageView) findViewById(R.id.imovel_corretor_img_principal);

        Bundle args = getIntent().getExtras();
        long id = args.getLong(Constantes.IMOVEL_ID);

        Imovel i = new Imovel();
        i.setId(id);
        Log.i("ID_IMOVEL", id+"");
        ImovelControle.pesquisar(i, getContext(), ImovelCorretorActivity.this);
        ClienteControle.pesquisar(i, getContext(), ImovelCorretorActivity.this);
    }



    @Override
    public void dadosLidos(Object dados) {
        Log.i("Dados_LIDOS","OK");

        if((dados instanceof ArrayList) && (((ArrayList) dados).size()>0)){
            final ArrayList<String> clientes = new ArrayList<>();
            listClientes = new HashMap<String, List<String>>();
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
        }else{
            if(dados instanceof Imovel){
                this.imovel = (Imovel)dados;

                txt_desc.setText(imovel.getDescricao());
                txt_end.setText(imovel.getEndereco());
                txt_preco.setText("R$ "+imovel.getValor());
                txt_titulo.setText(imovel.getTitulo());

                img_casa.setImageBitmap(CarregarImagem.StringToBitMap(imovel.getFoto()));
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
