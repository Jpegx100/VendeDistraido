package br.ufpi.easii.es.vendedistraido.view.cliente;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufpi.easii.es.vendedistraido.R;
import br.ufpi.easii.es.vendedistraido.control.ImovelControle;
import br.ufpi.easii.es.vendedistraido.model.Cliente;
import br.ufpi.easii.es.vendedistraido.model.Imovel;
import br.ufpi.easii.es.vendedistraido.util.CarregarImagem;
import br.ufpi.easii.es.vendedistraido.util.Constantes;
import br.ufpi.easii.es.vendedistraido.view.MainInterface;

/**
 * Tela que mostra o Imovel no perfil do cliente
 */
public class ImovelClienteActivity extends AppCompatActivity implements MainInterface{
    private Boolean click = false;
    private Long id;
    private Imovel imovel;
    private ImageView casa;
    private Button gostar;
    private TextView endereco, preco, desc, titulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imovel_cliente);
        setTitle("Imovel");
        endereco = (TextView) findViewById(R.id.imovel_cliente_txt_endereco);
        preco = (TextView) findViewById(R.id.imovel_cliente_txt_preco);
        gostar = (Button) findViewById(R.id.imovel_cliente_btn_gostar);
        casa = (ImageView)findViewById(R.id.imovel_cliente_img_principal);
        desc = (TextView) findViewById(R.id.imovel_cliente_txt_descricao);
        titulo = (TextView) findViewById(R.id.imovel_cliente_txt_titulo);

        gostar.setOnClickListener(onClickInteresse());

        Bundle args = getIntent().getExtras();
        id = args.getLong(Constantes.IMOVEL_ID);

        Imovel i = new Imovel();
        i.setId(id);
        ImovelControle.pesquisar(i, getContext(), ImovelClienteActivity.this);
    }
    private Context getContext(){
        return this;
    }

    private View.OnClickListener onClickInteresse() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gostar.setBackgroundResource(R.color.default_btn_off);
                gostar.setClickable(false);
                if(!click) {
                    Imovel i = new Imovel();
                    i.setId(id);
                    ImovelControle.interesse(usuarioLogado(), i, getContext());
                }
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

    @Override
    public void dadosLidos(Object dados) {
        if(dados instanceof Imovel){
            imovel = (Imovel) dados;
            casa.setImageBitmap(CarregarImagem.StringToBitMap(imovel.getFoto()));
            titulo.setText(imovel.getTitulo());
            desc.setText(imovel.getDescricao());
            endereco.setText(imovel.getEndereco());
            preco.setText("R$ "+imovel.getValor());
        }
    }
    @Override
    public void dadosNaoLidos(Exception e) {

    }
}
