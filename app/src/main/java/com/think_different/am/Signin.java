package com.think_different.am;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.think_different.am.util.OkHttp_Class;

import org.json.JSONObject;

public class Signin extends AppCompatActivity {
private EditText mobilenumber;
    private EditText password ;
    Button signin;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        getSupportActionBar().hide();
        initView();
    }


    void initView(){

        mobilenumber = (EditText)findViewById(R.id.phone_signin);
        password = (EditText)findViewById(R.id.password_signin);

        signin=(Button) findViewById(R.id.btn_signin);



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidation1();
            }
        });

    }


    private void checkValidation1() {


        String mobile_number  = mobilenumber.getText().toString().trim();
        String passwords  = password.getText().toString().trim();



                new login(mobile_number, passwords).execute();


    }



    private class login extends AsyncTask<Void, Void, JSONObject> {
        private String mobile_number, password;


        public login(String mobile_number, String password) {
            this.mobile_number = mobile_number;
            this.password = password;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(Signin.this);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setMessage("Connecting...");
            dialog.show();
        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            JSONObject json = null;
            try {
                json = new OkHttp_Class().loginUser(mobile_number, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            if (dialog.isShowing())
                dialog.dismiss();
            try {

                if (jsonObject != null) {


                    Toast.makeText(Signin.this, "Success", Toast.LENGTH_SHORT).show();

                    //   MyApplication.getInstance().getPrefManager(Constants.Login_Preferences).storeUser(new User(mobile_number, tripid));
                  //  String tripid = mobilenumber.getText(mobile_number);

                  //  String mobile_number = jsonObject.getString("phonenumber");

                            Intent intent = new Intent(Signin.this,MainActivity.class);

                            startActivity(intent);


                    // this finish call will kill the login page to invoke when it is pressed back.
                    //  finish();

                   /* } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(Signin.this, message, Toast.LENGTH_SHORT).show();
                    }*/

                } else
                    Toast.makeText(Signin.this, "Failed to login right now. Try again.", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(Signin.this, "Unknown error occurred. Try again.", Toast.LENGTH_SHORT).show();


            }
        }


    }
    }
