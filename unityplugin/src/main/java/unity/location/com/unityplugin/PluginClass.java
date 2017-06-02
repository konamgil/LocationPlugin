package unity.location.com.unityplugin;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

/**
 * Created by konamgil on 2017-06-02.
 */

public class PluginClass {
    private Double latiude;
    private Context context;
    private static PluginClass instance;

    public PluginClass() {
        this.instance = this;
    }

    public static PluginClass instance() {
        if (instance == null) {
            instance = new PluginClass();
        }
        return instance;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void showMessage(String message) {
        Toast.makeText(this.context, getLatiude() + message, Toast.LENGTH_SHORT).show();
    }

    public String getLatiude(){
        return String.valueOf(latiude);
    }

    public void getLocation() {
        LocationManager locationManager = (LocationManager) this.context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) this.context,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
            return;
        }
        locationManager.requestLocationUpdates(locationManager.getBestProvider(new Criteria(), true), 3000, 10, mListener);
    }

    LocationListener mListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latiude = location.getLatitude();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    public static String GetTextFromPlugin(int number){
        return "Number is " + number;
    }
}
