package br.ufpi.easii.es.vendedistraido.view.cliente;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import br.ufpi.easii.es.vendedistraido.R;
import br.ufpi.easii.es.vendedistraido.control.ImovelControle;
import br.ufpi.easii.es.vendedistraido.model.Imovel;
import br.ufpi.easii.es.vendedistraido.view.MainInterface;

public class MapaClienteActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMapClickListener,
        MainInterface{

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_cliente);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ImovelControle.pesquisar(this, MapaClienteActivity.this);
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng the = new LatLng(-5.092,  -42.8038);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(the));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
    }

    @Override
    public void onMapClick(LatLng latLng) {
    }

    @Override
    public void dadosLidos(Object dados) {
        //ArrayList<String> imoveis = new ArrayList<String>();
        if((dados instanceof ArrayList) && (((ArrayList) dados).size()>0)){
            if(((ArrayList) dados).get(0) instanceof Imovel){
                for(Imovel i:(ArrayList<Imovel>)dados){
                    LatLng latLng = new LatLng(Double.parseDouble(i.getLatitude()), Double.parseDouble(i.getLongitude()));
                    Log.i("LATLONG",latLng.toString());
                    mMap.addMarker(new MarkerOptions()
                            .position(latLng) //setting position
                            .draggable(false) //Making the marker draggable
                            .title(i.getEndereco())); //Adding a title
                    //imoveis.add(i.getEndereco());
                }
            }
        }
    }

    @Override
    public void dadosNaoLidos(Exception e) {
    }
}
