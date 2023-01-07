package ma.projet.gestionville_zone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnMapsSdkInitializedCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.TravelMode;

import ma.projet.gestionville_zone.beans.PharmacieVilleZone;
import ma.projet.gestionville_zone.databinding.ActivityMapsBinding;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback , OnMapsSdkInitializedCallback {

    private static final Object YOUR_API_KEY = "AIzaSyBunlP_aMD48JGoX71w5w29yLVZnVuuQ-o";
    private GoogleMap mMap;
    private PharmacieVilleZone pharmacieVilleZone;
    Location userlocation ;
    double lati ;
    double longi;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();
        pharmacieVilleZone = (PharmacieVilleZone) intent.getSerializableExtra("pharmacie");


        FusedLocationProviderClient locationClient = LocationServices.getFusedLocationProviderClient(this);
        locationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                // Utilisez la latitude et la longitude de l'utilisateur ici
                lati = location.getLatitude();
                longi = location.getLongitude();
                userlocation = new Location(location);
                Toast.makeText((Context) MapsActivity.this, userlocation.getLongitude() +"", Toast.LENGTH_SHORT).show();
            }
        });

        MapView mapView = (MapView) findViewById(R.id.map);
        MapsInitializer.initialize(getApplicationContext(), MapsInitializer.Renderer.LATEST, this);

        GoogleMapOptions options = new GoogleMapOptions().liteMode(true);

        mapView.onCreate(null);
        mapView.getMapAsync(this);


    }

    @Override
    public void onMapsSdkInitialized(MapsInitializer.Renderer renderer) {
        switch (renderer) {
            case LATEST:
                Log.d("MapsDemo", "The latest version of the renderer is used.");
                break;
            case LEGACY:
                Log.d("MapsDemo", "The legacy version of the renderer is used.");
                break;
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(pharmacieVilleZone.getLatitude(), pharmacieVilleZone.getLongitude());
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        LatLng userloc = new LatLng(lati, longi);
        // Demandez à l'API Google Maps de trouver un itinéraire entre la pharmacie et l'utilisateur
        String url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + sydney.latitude + "," + sydney.longitude + "&destination=" + userloc.latitude + "," + userloc.longitude + "&key=" + YOUR_API_KEY;


        mMap.addMarker(new MarkerOptions().position(sydney).title(pharmacieVilleZone.getAdresse()));
        mMap.addMarker(new MarkerOptions().position(userloc).title("Votre Position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}