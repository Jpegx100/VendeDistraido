package br.ufpi.easii.es.vendedistraido.view.cliente;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import br.ufpi.easii.es.vendedistraido.R;
import br.ufpi.easii.es.vendedistraido.control.ImovelControle;
import br.ufpi.easii.es.vendedistraido.model.Cliente;
import br.ufpi.easii.es.vendedistraido.model.Imovel;
import br.ufpi.easii.es.vendedistraido.util.Constantes;
import br.ufpi.easii.es.vendedistraido.view.MainInterface;

public class MapaClienteActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMapClickListener,
        MainInterface{

    private GoogleMap mMap;
    private Button btn_lista;
    private ArrayList<Imovel> imoveis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_cliente);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ImovelControle.pesquisar(this, MapaClienteActivity.this);

        btn_lista = (Button)findViewById(R.id.cliente_mapa_btn_lista);
        btn_lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ClienteActivity.class);
                startActivity(intent);
            }
        });
        imoveis = new ArrayList<Imovel>();
    }
    private Context getContext(){
        return this;
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
                    imoveis.add(i);
                    LatLng latLng = new LatLng(Double.parseDouble(i.getLatitude()), Double.parseDouble(i.getLongitude()));
                    Log.i("LATLONG",latLng.toString());
                    MarkerOptions marker = new MarkerOptions()
                            .position(latLng) //setting position
                            .draggable(false) //Making the marker draggable
                            .title(i.getEndereco()) //Adding a title
                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.token_casa));
                    mMap.addMarker(marker);
                }
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Log.i("Marker_Clicked",marker.getTitle());
                        for(Imovel i:imoveis){
                            if(i.getLatitude().equals(String.valueOf(marker.getPosition().latitude))
                                    && i.getLongitude().equals(String.valueOf(marker.getPosition().longitude))){
                                Intent intent = new Intent(getContext(), ImovelClienteActivity.class);
                                intent.putExtra(Constantes.IMOVEL_ENDERECO, i.getEndereco());
                                intent.putExtra(Constantes.IMOVEL_VALOR, i.getValor());
                                intent.putExtra(Constantes.IMOVEL_ID, i.getId());
                                startActivity(intent);
                                break;
                            }
                        }
                        return false;
                    }
                });
            }
        }
    }

    @Override
    public void dadosNaoLidos(Exception e) {
    }
}
