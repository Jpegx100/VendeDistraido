package br.ufpi.easii.es.vendedistraido.view.corretor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
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
    private Button btn_cadastrar, btn_carregar_imagens;
    private Corretor corretor;
    private static int RESULT_LOAD_IMAGE = 1;
    String fotoString = null;
    Bitmap bmp = null;
    byte[] byteArray = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_imovel);
        edt_titulo = (EditText) findViewById(R.id.cadastra_imovel_edt_titulo);
        edt_endereco = (EditText) findViewById(R.id.cadastra_imovel_edt_endereco);
        btn_cadastrar = (Button) findViewById(R.id.cadastra_imovel_btn_cadastrar);
        btn_cadastrar.setOnClickListener(onClickCadastrar());
        btn_carregar_imagens = (Button) findViewById(R.id.cadastra_imovel_btn_imagens);
        btn_carregar_imagens.setOnClickListener(onClickCarregar());
        corretor = usuarioLogado();
    }

   //abre a galeria ou a camera pra selecionar a imagem...
    private View.OnClickListener onClickCarregar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);

            }
        };
    }
// trata a foto e salva
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            try {
                bmp = getBitmapFromUri(selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byteArray = stream.toByteArray();

            Toast.makeText(this, "Imagem Selecionada", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "ERRO", Toast.LENGTH_LONG).show();
        }


    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    private View.OnClickListener onClickCadastrar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = edt_titulo.getText().toString();
                String end = edt_endereco.getText().toString();
                Intent intent = new Intent(getContext(), MapsActivity.class);
                intent.putExtra(Constantes.IMOVEL_TITULO, titulo);
                intent.putExtra(Constantes.IMOVEL_ENDERECO, end);
                intent.putExtra("foto", byteArray);
                startActivity(intent);
                finish();
            }
        };
    }

    /**
     * Metodo que transforma os dados do arquivo de preferencias no objeto Corretor logado na sessao.
     *
     * @return retorna o corretor logado ou null casso nao haja alguem logado
     */
    private Corretor usuarioLogado() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constantes.USER, Context.MODE_PRIVATE);
        if (sharedPreferences == null) return null;
        Corretor corretor = new Corretor(sharedPreferences.getLong(Constantes.USER_LOGIN_ID, -1),
                sharedPreferences.getString(Constantes.USER_LOGIN_NOME, "-1"),
                sharedPreferences.getString(Constantes.USER_LOGIN_EMAIL, "-1"),
                sharedPreferences.getString(Constantes.USER_LOGIN_SENHA, "-1"),
                sharedPreferences.getString(Constantes.USER_LOGIN_TELEFONE, "-1"),
                //Pegar LISTA de IMOVEIS
                new ArrayList<Imovel>());
        return corretor;
    }

    private Context getContext() {
        return this;
    }
}