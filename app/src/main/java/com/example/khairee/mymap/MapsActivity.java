package com.example.khairee.mymap;

import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        // Get LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);

        // Create a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Get the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Get Current Location

        Location myLocation = locationManager.getLastKnownLocation(provider);
        // Get latitude of the current location
        double latitude = myLocation.getLatitude();

        // Get longitude of the current location
        double longitude = myLocation.getLongitude();

        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("You are here!").snippet("Consider yourself located"));
        //  Log.d(+latitude" longitude " + longitude);



        Circle circle = mMap.addCircle(new CircleOptions()
                .center(new LatLng(latitude, longitude))
                .radius(3000)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));

        //hardcoded parking locations
        Location selected_location=new Location("locationA");
        selected_location.setLatitude(latitude);
        selected_location.setLongitude(longitude);

        Location near_locations=new Location("locationB");
        near_locations.setLatitude(latitude-1);
        near_locations.setLongitude(longitude);

        Location near_locations2=new Location("locationC");
        near_locations2.setLatitude(latitude-2);
        near_locations2.setLongitude(longitude);

        Location near_locations3=new Location("locationD");
        near_locations3.setLatitude(latitude-3);
        near_locations3.setLongitude(longitude);

        Location near_locations4=new Location("locationE");
        near_locations3.setLatitude(latitude-4);
        near_locations3.setLongitude(longitude);

        double distance=selected_location.distanceTo(near_locations);

        double distance2=selected_location.distanceTo(near_locations2);

        double distance3=selected_location.distanceTo(near_locations3);

        double distance4=selected_location.distanceTo(near_locations4);


        Log.i("distance", " value is " + distance);
        LatLng sydney = new LatLng(latitude-10, longitude);

        if( distance >= 3000 ){

            mMap.addMarker(new MarkerOptions().position(sydney).title("A"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);

        LatLng user1 = new LatLng( -34 + (1/110.574)*3, 151);

        LatLng sydney2 = new LatLng(-20, 147);
        LatLng sydney3 = new LatLng(-60, 130);
        LatLng sydney4 = new LatLng(-90, 110);

        mMap.addMarker(new MarkerOptions().position(sydney).title("A"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.addMarker(new MarkerOptions().position(sydney2).title("B"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney2));

        mMap.addMarker(new MarkerOptions().position(sydney3).title("C"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney3));

        mMap.addMarker(new MarkerOptions().position(sydney4).title("D"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney4));
*/

        // Enable MyLocation Layer of Google Map
        mMap.setMyLocationEnabled(true);

        // set map type
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        // Zoom in the Google Map
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
        // mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("You are here!").snippet("Consider yourself located"));
    }
}
