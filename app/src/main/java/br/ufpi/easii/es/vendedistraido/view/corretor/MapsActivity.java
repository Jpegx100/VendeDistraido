package br.ufpi.easii.es.vendedistraido.view.corretor;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import br.ufpi.easii.es.vendedistraido.R;
import br.ufpi.easii.es.vendedistraido.control.ImovelControle;
import br.ufpi.easii.es.vendedistraido.exception.ExcecaoImovelJaExistente;
import br.ufpi.easii.es.vendedistraido.model.Corretor;
import br.ufpi.easii.es.vendedistraido.model.Imovel;
import br.ufpi.easii.es.vendedistraido.util.Constantes;

/**
 * Tela que mostra o mapa na visao do corretor e implementa as funcionalidades de cadastro de imovel
 */
public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMapClickListener,
        View.OnClickListener {
    private GoogleMap mMap;
    private double longitude;
    private double latitude;
    private Button buttonSave;
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonSave) {
            //getCurrentLocation();
            //moveMap();
            Intent intent = getIntent();
            if (intent.hasExtra(Constantes.IMOVEL_TITULO)) {

                String titulo = intent.getStringExtra(Constantes.IMOVEL_TITULO);
                String end = intent.getStringExtra(Constantes.IMOVEL_ENDERECO);

                Bundle extras = getIntent().getExtras();
                byte[] byteArray = extras.getByteArray("foto");
                Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

                Corretor corretor = usuarioLogado();
                Imovel imovel = new Imovel(latitude + "", longitude + "", end, corretor);

                try {
                    Log.i("LATLONG", latitude + "|" + longitude);
                    ImovelControle.inserir(imovel, bmp, corretor, getContext());
                    // ImovelControle.inserirImagem(foto,imovel,);
                    finish();
                } catch (ExcecaoImovelJaExistente excecaoImovelJaExistente) {
                    excecaoImovelJaExistente.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        mMap.clear();
        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .draggable(true));
        latitude = latLng.latitude;
        longitude = latLng.longitude;
        Log.i("LATLONG", latitude + "|" + longitude);
    }

    @Override
    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    //Getting current location
    private void getCurrentLocation() {
        mMap.clear();
        //Creating a location object
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (location != null) {
            //Getting longitude and latitude
            longitude = location.getLongitude();
            latitude = location.getLatitude();

            //moving the map to location
            moveMap();
        }
    }

    //Function to move the map
    private void moveMap() {
        String msg = latitude + ", " + longitude;
        LatLng latLng = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions()
                .position(latLng) //setting position
                .draggable(true) //Making the marker draggable
                .title("Current Location")); //Adding a title
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(latLng).draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.setOnMarkerDragListener(this);
        mMap.setOnMapClickListener(this);
    }

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

    @Override
    public void onMarkerDragEnd(Marker marker) {
        //Getting the coordinates
        latitude = marker.getPosition().latitude;
        longitude = marker.getPosition().longitude;

        //Moving the map
        moveMap();
    }

    @Override
    public void onConnected(Bundle bundle) {
        getCurrentLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    private Context getContext() {
        return this;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }
}