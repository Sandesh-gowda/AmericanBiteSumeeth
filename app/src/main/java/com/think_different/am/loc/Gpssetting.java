package com.think_different.am.loc;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.think_different.am.Controller;
import com.think_different.am.MainScreen;
import com.think_different.am.R;
import com.think_different.am.Registration;
import com.think_different.am.Signin;

public class Gpssetting extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    private int REQUEST_LOCATIONS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpssetting);
        getSupportActionBar().hide();
        checkForLocation();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                final Intent mainIntent = new Intent(Gpssetting.this, Registration.class);
                //  final Intent mainIntent = new Intent(SplashScreen.this, LocFinder.class);
                Gpssetting.this.startActivity(mainIntent);
                Gpssetting.this.finish();
            }
        }, 5000);
    }


    private void checkForLocation() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if (Controller.getInstance().IsInternetConnected()) {
            if (ConnectionResult.SUCCESS == resultCode) {
                if (MyLocation.checkSelfPermissions(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        && MyLocation.checkSelfPermissions(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    if (MyLocation.isLocationServiceAvailable(this)) {
                        Intent intent = new Intent(getApplicationContext(), LocationService.class);
                        startService(intent);
                        Toast.makeText(getApplicationContext(), "Service started", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Location service unavalable", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    requestLocationPermissionsNoDialog("Request for Access", "We would like to access your location to provide better suggestions",
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATIONS);
                    Toast.makeText(getApplicationContext(), "Location service psermission denied", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "google play service unavalable", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Requests permission. If the permission has been denied previously, a dialog box will prompt the user to grant the permission, otherwise it is requested directly.
     */
    public void requestLocationPermissionsNoDialog(final String title, final String message, final @NonNull String[] permissions, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle(title).setMessage(message)
                    .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (dialog != null) {
                                dialog.dismiss();
                                ActivityCompat.requestPermissions(Gpssetting.this, permissions, requestCode);
                            }
                        }
                    }).setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                        }
                    }).setCancelable(true);

            Dialog dialog = builder.create();
            dialog.show();
        } else {
            ActivityCompat.requestPermissions(this, permissions, requestCode);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATIONS) {
            /**
             * Permission got received for Location Permissions
             */
            if (MyLocation.verifyPermissions(grantResults)) {
                checkForLocation();
            } else {
                Toast.makeText(Gpssetting.this, "Permission denied", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
