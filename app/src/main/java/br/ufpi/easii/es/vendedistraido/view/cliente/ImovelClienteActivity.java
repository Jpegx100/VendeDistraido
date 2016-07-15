package br.ufpi.easii.es.vendedistraido.view.cliente;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufpi.easii.es.vendedistraido.R;
import br.ufpi.easii.es.vendedistraido.control.ImovelControle;
import br.ufpi.easii.es.vendedistraido.model.Cliente;
import br.ufpi.easii.es.vendedistraido.model.Imovel;
import br.ufpi.easii.es.vendedistraido.util.Constantes;

public class ImovelClienteActivity extends AppCompatActivity {

    Long id;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imovel_cliente);

        TextView endereco = (TextView) findViewById(R.id.imovel_cliente_txt_endereco);
        TextView preco = (TextView) findViewById(R.id.imovel_cliente_txt_preco);
        ImageView gostar = (ImageView) findViewById(R.id.imovel_cliente_img_interesse);

        gostar.setOnClickListener(onClickInteresse());

        Bundle args = getIntent().getExtras();

        context = this;
        String end = args.getString(Constantes.IMOVEL_ENDERECO);
        String valor = String.valueOf(args.getDouble(Constantes.IMOVEL_VALOR));
        id = args.getLong(Constantes.IMOVEL_ID);

        endereco.setText(end);
        preco.setText(valor);
    }

    private View.OnClickListener onClickInteresse() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Imovel imovel = new Imovel();
                imovel.setId(id);
                ImovelControle.interesse(usuarioLogado(),imovel,context);
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
}
