package com.example.james_mark2.fragments;

import static android.content.ContentValues.TAG;

import android.icu.util.ULocale;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.james_mark2.BuildConfig;
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


public class ExploreFragment extends Fragment {


    private GoogleMap googleMap;
    private LatLng iflandia = new LatLng(-22.693469, -47.625500);


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
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(iflandia, 15));
                googleMap.addMarker(new MarkerOptions()
                        .position(iflandia)
                        .title("Katiau Inc.")
                        .snippet("O início de um sonho")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));


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
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(iflandia, 10));
                        googleMap.addMarker(new MarkerOptions()
                                .position(iflandia)
                                .title(place.getName())
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                    }

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