package br.ufpi.easii.es.vendedistraido.view.corretor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import br.ufpi.easii.es.vendedistraido.R;
import br.ufpi.easii.es.vendedistraido.control.CorretorControle;
import br.ufpi.easii.es.vendedistraido.control.ImovelControle;
import br.ufpi.easii.es.vendedistraido.exception.ExcecaoImovelJaExistente;
import br.ufpi.easii.es.vendedistraido.model.Corretor;
import br.ufpi.easii.es.vendedistraido.model.Imovel;
import br.ufpi.easii.es.vendedistraido.util.Constantes;

/**
 * Created by Jpegx.
 * Classe responsavel por exibir a tela de cadastro de Imovel.
 */
public class CadastraImovelActivity extends AppCompatActivity {
    private EditText edt_titulo, edt_endereco;
    private Button btn_cadastrar;
    private Corretor corretor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_imovel);
        edt_titulo = (EditText)findViewById(R.id.cadastra_imovel_edt_titulo);
        edt_endereco = (EditText)findViewById(R.id.cadastra_imovel_edt_endereco);
        btn_cadastrar = (Button)findViewById(R.id.cadastra_imovel_btn_cadastrar);
        btn_cadastrar.setOnClickListener(onClickCadastrar());
        corretor = usuarioLogado();
    }
    private View.OnClickListener onClickCadastrar(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = edt_titulo.getText().toString();;
                String end = edt_endereco.getText().toString();
                Intent intent = new Intent(getContext(),MapsActivity.class);
                intent.putExtra(Constantes.IMOVEL_TITULO, titulo);
                intent.putExtra(Constantes.IMOVEL_ENDERECO, end);
                startActivity(intent);
                finish();
            }
        };
    }
    /**
     * Metodo que transforma os dados do arquivo de preferencias no objeto Corretor logado na sessao.
     * @return retorna o corretor logado ou null casso nao haja alguem logado
     */
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
    private Context getContext(){
        return this;
    }
}