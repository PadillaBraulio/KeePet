package com.example.root.keepet;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback , ImageButton.OnClickListener{


    private static final String CLASSNAME = "MAINACTIVITY";
    private static final LatLng HOME = new LatLng(14.5411093, -90.4260691);
    private static final LatLng WALKER = new LatLng(14.540851714521105,-90.42498940194491);
    private static final LatLng [] UBICATION_ARRAY =
        {
                new LatLng(14.541587849086847,-90.42629420757294),
                new LatLng(14.54110139129484,-90.42663261283451),
                new LatLng(14.540670406418046,-90.42668625701481),
                new LatLng(14.54023942070038,-90.42677745212131),
                new LatLng(14.53985473505263,-90.42677253462898),
                new LatLng(14.539917046401229,-90.42757719733345),
                new LatLng(14.539989742952377,-90.42812436797249),
                new LatLng(14.540862099700234,-90.4279634354316),
                new LatLng(14.540664781213518,-90.42671889044868),
                new LatLng(14.541100958685167,-90.42657941557991),
                new LatLng(14.5411093,-90.4260691),
        };
    private GoogleMap mMap;
    private ImageButton iniciar;
    private ImageButton keepers;
    private Polyline mypolyline;
    private Marker myMarker;

    private int count = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        iniciar = (ImageButton) findViewById(R.id.iniciar);
        keepers = (ImageButton) findViewById(R.id.keepers);

        iniciar.setOnClickListener(this);
        keepers.setOnClickListener(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HOME, 17));

        mypolyline  = mMap.addPolyline(new PolylineOptions()
                .add(this.UBICATION_ARRAY[0])
                .color(Color.RED)
                .width(5f));

        addMarkers();

    }
    public void addMarkers()
    {
        // tag de el hogar
        mMap.addMarker(new MarkerOptions()
                        .position(HOME)
                        .title("Home")
                        .snippet("This is your ubication")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.user))
        );
        myMarker = mMap.addMarker(new MarkerOptions()
                .position(this.UBICATION_ARRAY[0])
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.dog)));
        mMap.addMarker(new MarkerOptions()
                        .position(WALKER)
                        .title("Walker Available")
                        .snippet("Braulio Padilla, Raiting 88")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.image))
        );

    }


    public void writePolylines()
    {

        if(count < this.UBICATION_ARRAY.length )
        {
            mypolyline.setPoints(Arrays.asList(this.UBICATION_ARRAY).subList(0, count + 1));
            myMarker.setPosition(this.UBICATION_ARRAY[count]);
            count = count + 1;

        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.iniciar:
                writePolylines();
                break;
            case R.id.keepers:

                break;

        }
    }

}
