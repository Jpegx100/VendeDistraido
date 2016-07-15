package br.ufpi.easii.es.vendedistraido.view.cliente;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.ufpi.easii.es.vendedistraido.R;
import br.ufpi.easii.es.vendedistraido.model.Imovel;

/**
 * Created by Otávio Cury on 15/07/2016.
 */
public class AdapterListView extends ArrayAdapter<Imovel> {

    public AdapterListView(Context context, int resource) {
        super(context, resource);
    }

    public AdapterListView(Context context, int resource, List<Imovel> imoveis) {
        super(context, resource, imoveis);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.item_lista, parent, false);
        }

        Imovel imovel = getItem(position);

        if(imovel != null){
            ImageView casa = (ImageView) v.findViewById(R.id.imgCasa);
            TextView endereco = (TextView) v.findViewById(R.id.edtEndereco);
            TextView preco = (TextView) v.findViewById(R.id.edtPreco);
            ImageView gostar = (ImageView) v.findViewById(R.id.imgGostar);

            if (casa != null){
                casa.setImageResource(R.mipmap.casa);
            }

            if (gostar != null){
                gostar.setImageResource(R.mipmap.gostar);
            }

            if(endereco != null){
                endereco.setText(imovel.getEndereco());
            }

            if(preco != null){
                preco.setText(String.valueOf(imovel.getValor()));
            }

        }

        return v;

    }
}
