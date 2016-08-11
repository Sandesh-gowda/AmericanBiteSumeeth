package com.think_different.am;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.think_different.am.loc.Gpssetting;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ab_login);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


             //  final Intent mainIntent = new Intent(SplashScreen.this, Gpssetting.class);
            //   final Intent mainIntent = new Intent(SplashScreen.this, Registration.class);
              final Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
             // final Intent mainIntent = new Intent(SplashScreen.this, MainScreen.class);
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        }, 5000);

    }




  /*  if (MyApplication.getInstance().getPrefManager(Constants.Login_Preferences).getUser() != null) {
//            startService(new Intent(getBaseContext(), ScheduledService.class));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //if the user is already login take him to main screen
                final Intent mainIntent = new Intent(SplashScreen.this, Gpssetting.class);
              //  final Intent mainIntent = new Intent(SplashScreen.this, LocFinder.class);
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        }, 5000);


    }else{
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //if the user is not loged in take him to signin screen
                final Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        }, 5000);
    }
*/


//}
}
