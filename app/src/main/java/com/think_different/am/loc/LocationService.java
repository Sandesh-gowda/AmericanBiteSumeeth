package com.think_different.am.loc;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.think_different.am.Controller;
import com.think_different.am.VolleyMultipartRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LocationService extends Service implements ConnectionCallbacks, OnConnectionFailedListener, LocationListener, Runnable {

    public static final int UPDATE_INTERVAL_IN_SECONDS = 300; // Update frequency in seconds
    protected static final int MILLISECONDS_PER_SECOND = 1000; // Milliseconds per second
    protected static final long UPDATE_INTERVAL = MILLISECONDS_PER_SECOND * UPDATE_INTERVAL_IN_SECONDS; // Update frequency in milliseconds
    protected static final int FASTEST_INTERVAL_IN_SECONDS = 60; // The fastest update frequency, in seconds
    // A fast frequency ceiling in milliseconds
    protected static final long FASTEST_INTERVAL = MILLISECONDS_PER_SECOND * FASTEST_INTERVAL_IN_SECONDS;
    private GoogleApiClient mLocationClient;
    private LocationRequest mLocationRequest;


    private static Handler handler = new Handler();
    private Location currentLocation;
    private String tripid;


    @Override
    public void onCreate() {
        Log.d("LocationService", "onCreate : 49");
        super.onCreate();
       // tripid = MyApplication.getInstance().getPrefManager(Constants.Login_Preferences).getUser().getTripid();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("LocationService", "onStartCommand : 57");
        setupLocation();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onConnected(Bundle arg0) {
        Log.d("LocationService", "onConnected : 70");
        try {
            if (mLocationClient != null && mLocationClient.isConnected()) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    LocationServices.FusedLocationApi.requestLocationUpdates(mLocationClient, mLocationRequest, this);
                }
            } else {
                setupLocation();
            }
        } catch (Exception e) {
            setupLocation();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult arg0) {
        Log.d("LocationService", "onConnectionFailed : 86");
        Toast.makeText(getApplicationContext(), "Failed to connect play services.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("LocationService", "onConnectionSuspended : 92");
        mLocationClient.connect();
    }


    /**
     * Builds a GoogleApiClient. Uses the addApi() method to request the LocationServices API.
     */
    protected synchronized void setupLocation() {
        Log.d("LocationService", "setupLocation : 98");
        mLocationClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        mLocationClient.connect();
        mLocationRequest = (LocationRequest.create());
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); // Use high accuracy
        mLocationRequest.setInterval(UPDATE_INTERVAL); // Setting the update interval to 5mins
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL); // Set the fastest update interval to 1 min
    }


    @Override
    public void onLocationChanged(Location currentLocation) {
        Log.d("LocationService", "onLocationChanged : 109");
        if (this.currentLocation == null) {
            handler.removeCallbacks(this);
            handler.post(this);
        }
        this.currentLocation = currentLocation;
    }


    private int getBatteryPercent() {
        try {
            IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent batteryStatus = getApplicationContext().registerReceiver(null, ifilter);

            int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

            int batteryPct = level / scale;
            return level;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
/*code has been removed so that it will not call every 5min */

    @Override
    public void run() {
        handler.removeCallbacks(this);
        //code changed for 5 mim
      // handler.postDelayed(this, 1 * 60 * 1000);
       // handler.postDelayed(this, 5 * 60 * 1000);
        Log.d("LocationService", "run : 141");
        if (currentLocation != null) {
            int batteryPercent = getBatteryPercent();
            double latitude = currentLocation.getLatitude();
            double longitude = currentLocation.getLongitude();
            Log.d("LocationService", "battery:" + batteryPercent + " lat: " + latitude + " lang: " + longitude);
        //    Toast.makeText(getApplicationContext(),"battery:" + batteryPercent + " lat: " + latitude + " lang: " + longitude,Toast.LENGTH_LONG).show();
         //   new ContactUsTask(tripid, String.valueOf(latitude), String.valueOf(longitude), String.valueOf(batteryPercent)).execute();
//Toast.makeText(getApplicationContext(),"lat: " + latitude + " lang: " + longitude,Toast.LENGTH_LONG).show();
            callLocation(String.valueOf(latitude),String.valueOf(longitude));

        }
    }


    @Override
    public void onDestroy() {
        Log.d("LocationService", "onDestroy : 85");
        super.onDestroy();
        try {
            if (mLocationClient.isConnected()) {
                LocationServices.FusedLocationApi.removeLocationUpdates(mLocationClient, this);
                mLocationClient.disconnect();
            }
        } catch (Exception e) {
        }
    }



public void callLocation(final String lat , final String logn){

    String url = "http://appshoppee.in/americanbyte/API/search_nearby.php";
    VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url, new Response.Listener<NetworkResponse>() {
        @Override
        public void onResponse(NetworkResponse response) {
            String resultResponse = new String(response.data);
            try {
                /*TODO:use try and catch block to work */

                JSONObject result = new JSONObject(resultResponse);
                Log.d("R:", "onResponse String " + result);

            } catch (JSONException e) {
                e.printStackTrace();

            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            NetworkResponse networkResponse = error.networkResponse;
            String errorMessg = "Unknown error";
            if (networkResponse == null){
                if(error.getClass().equals(TimeoutError.class)){
                    errorMessg= "RequestTimeOut";
                }
            }else {
                String result = new String(networkResponse.data);
                try{
                    JSONObject response = new JSONObject(result);
                    String status = response.getString("shop_id");

                }catch (JSONException e){
                    e.printStackTrace();

                }
            }
        }
    }){
        @Override
        protected Map<String,String> getParams(){
            Map<String,String> params = new HashMap<>();

          //  params.put("latitude",lat);
          //  params.put("longitude",logn);

            params.put("latitude","13.015904");
            params.put("longitude","77.637862");

            return params;

        }


    };
    Controller.getInstance().addToRequestQueue(multipartRequest);
}

  /*  private class ContactUsTask extends AsyncTask<Void, Void, JSONObject> {
        private String tripid, lan, lat, battery;


        public ContactUsTask(String tripid, String lan, String lat, String battery) {
            this.tripid = tripid;

            this.lan = lan;
            this.lat = lat;
            this.battery = battery;


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            Log.d("ContactUsTask", "doInBackground : 189");
            JSONObject json = null;
            try {
                json = new OkHttp_Class().find_location(tripid, lan, lat, battery);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            Log.d("ContactUsTask", "onPostExecute :" + jsonObject);
            try {
                if (jsonObject != null) {

                } else
                    Toast.makeText(getApplicationContext(), "Failed to Connect right now", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Unknown error occurred. Try again.", Toast.LENGTH_SHORT).show();

            }
        }
    }*/
}
