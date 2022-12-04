package com.example.james_mark2.fragments;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.icu.util.ULocale;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.james_mark2.BuildConfig;
import com.example.james_mark2.DetailsActivity;
import com.example.james_mark2.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class ExploreFragment extends Fragment {


    private GoogleMap googleMap;
    private LatLng iflandia = new LatLng(-23.552485, -46.632933);
    private int cont=0;
    private String aux;
    private String aux2;
    Toast toastAlerta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String apiKey = BuildConfig.MAPS_API_KEY;

        //Inicializa o SDK
        Places.initialize(getContext(), apiKey);

        // Initialize view
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        // Initialize map fragment
        SupportMapFragment supportMapFragment=(SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        //----------------------------------GOOGLE-MAPS-----------------------------------//
        // Async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                // When map is loaded
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(iflandia, 8));
                /*googleMap.addMarker(new MarkerOptions()
                        .position(iflandia)
                        .title("Katiau Inc.")
                        .snippet("O início de um sonho")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                */

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        // When clicked on map
                        // Initialize marker options
                        MarkerOptions markerOptions=new MarkerOptions();
                        // Set position of marker
                        markerOptions.position(latLng);
                        // Set title of marker
                        markerOptions.title(latLng.latitude+" : "+latLng.longitude);
                        // Remove all marker
                        googleMap.clear();
                        // Animating to zoom the marker
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                        // Add marker on map
                        googleMap.addMarker(markerOptions);
                    }

                });

                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(@NonNull Marker marker) {
                        String markerTitle = marker.getTitle();
                        aux = markerTitle;
                        cont++;

                        toastAlerta = Toast.makeText(getActivity(), "Dê mais um clique para acessar", Toast.LENGTH_SHORT);
                        toastAlerta.show();

                        if(cont==1 && markerTitle.equals(aux2)) {
                            //toastAlerta.cancel();
                            Intent i = new Intent(getContext(), DetailsActivity.class);
                            i.putExtra("title", markerTitle);
                            startActivity(i);

                            cont=0;

                        }else if(!markerTitle.equals(aux2)){
                            cont = 0;
                        }
                        aux2 = aux;
                        return false;
                    }
                });

                //----------------------------------AUTO-COMPLETE-----------------------------------//

                if (!Places.isInitialized()) {
                    Places.initialize(getContext(), apiKey);
                }


                // Inicializa o AutocompleteSupportFragment.

                AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                        getChildFragmentManager().findFragmentById(R.id.autocomplete_fragment);


                // Specify the types of place data to return.
                autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));


                autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
                    @Override
                    public void onPlaceSelected(Place place) {
                        // TODO: Get info about the selected place.
                        Log.i(TAG, "Place: " + place.getName() + ", " +  place.getLatLng());
                        iflandia = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);

                        googleMap.clear();
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(iflandia, 13));
                        /*googleMap.addMarker(new MarkerOptions()
                                .position(iflandia)
                                .title(place.getName())
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));*/

                        //----------------------------------DATABASE-----------------------------------//
                        //id, cidade, nome_evento, categoria, lat, lon
                        String evento [][] = {
                                                {"1", "Piracicaba", "Curso Boviplan de Gestão Agropecuária", "Evento", "-22.715558167441234", "-47.64980722872309"},
                                                {"2", "Piracicaba", "Porto Certo", "Gastronomia", "-22.722838397542084", "-47.65596406028102"},
                                                {"3", "Piracicaba", "Alto do Mirante", "Turismo", "-22.713849564796504", "-47.65112820630401"},
                                                {"4", "Piracicaba", "Zoológico", "Turismo", "-22.69503513557547", "-47.65183755132581"},
                                                {"5", "Piracicaba", "Várzea Cup", "Evento", "-22.69285082843774", "-47.66172175823422"},
                                                {"6", "Piracicaba", "TAMO AQUI", "TAMO AQUI","-22.69338485527287", "-47.62550358946037"},
                                                {"7", "Piracicaba", "McDonald's", "Gastronomia","-22.729672314113348", "-47.64803649179834"},

                                                {"8", "Passos", "Dianelli Massas e Doces", "Gastronomia", "-20.72101017949072", "-46.60643463679768"},
                                                {"9", "Passos", "Kubanos Lounge e Bar", "Gastronomia", "-20.728869165727815", "-46.61007820245232"},
                                                {"10", "Passos", "Mangutti Sorvetes", "Gastronomia", "-20.717945264365873", "-46.61157418639631"},
                                                {"11", "Passos", "Praça da Matriz", "Turismo", "-20.718274029822144", "-46.610768428723084"},
                                                {"12", "Passos", "Borogodó Botequim", "Evento", "-20.727067673805713", "-46.60542049988647"},

                                                {"13", "São Paulo", "Museu do Futebol", "Turismo", "-23.546473639757743", "-46.665216948410844"},
                                                {"14", "São Paulo", "Museu de Arte de São Paulo Assis Chateaubriand", "Evento", "-23.559932070172042", "-46.65634683873395"},
                                                {"15", "São Paulo", "Ponte Estaiada", "Turismo", "-23.51843489125664", "-46.637826836676155"},
                                                {"16", "São Paulo", "Coco Bambu - JK", "Gastronomia", "-23.58250633349179", "-46.67097893892147"},
                                                {"17", "São Paulo", "Mercado Municipal de São Paulo", "Turismo", "-23.5415570672105", "-46.63006024472805"},
                                                {"18", "São Paulo", "O Mágico de Oz", "Evento", "-23.55778226078768", "-46.64110133123751"}

                        };


                        int cont=0;
                        for(int i=0; i<evento.length;i++){
                            if((evento[i] != null)){
                                cont++;
                            }
                        }

                    for(int i=0; i<cont; i++){
                        if(Objects.equals(place.getName(), evento[i][1])){
                            for(int j=0; j<cont; j++){
                                iflandia = new LatLng(Double.parseDouble(evento[j][4]), Double.parseDouble(evento[j][5]));
                                    if (evento[j][3] == "Evento") {
                                        googleMap.addMarker(new MarkerOptions()
                                                .position(iflandia)
                                                .title(evento[j][2])
                                                .snippet("Categoria: " + evento[j][3])
                                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                                    }else if (evento[j][3] == "Turismo") {
                                    googleMap.addMarker(new MarkerOptions()
                                            .position(iflandia)
                                            .title(evento[j][2])
                                            .snippet("Categoria:" + evento[j][3])
                                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                                    }else if (evento[j][3] == "Gastronomia") {
                                        googleMap.addMarker(new MarkerOptions()
                                                .position(iflandia)
                                                .title(evento[j][2])
                                                .snippet("Categoria: " + evento[j][3])
                                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                                    }else{
                                        googleMap.addMarker(new MarkerOptions()
                                                .position(iflandia)
                                                .title(evento[j][2])
                                                .snippet("Categoria: " + evento[j][3])
                                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
                                    }

                                }

                            }
                        }
                    }
                    //--------------------------------------------------------------------------------//


                    @Override
                    public void onError(Status status) {
                        // TODO: Handle the error.
                        Log.i(TAG, "An error occurred: " + status);
                    }
                });


            }

        });

        // Return view
        return view;


    }






    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}