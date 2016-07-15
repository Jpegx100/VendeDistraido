package br.ufpi.easii.es.vendedistraido.view.corretor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;

import br.ufpi.easii.es.vendedistraido.R;

public class ImovelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imovel);

        ArrayList<String> items = new ArrayList<String>();
        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");
        items.add("Item 4");
        ArrayAdapter<String> aItems = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        TwoWayView lvTest = (TwoWayView) findViewById(R.id.imovel_list_img_casa);
        lvTest.setAdapter(aItems);
    }
}
