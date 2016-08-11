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

import com.think_different.am.util.OkHttp_Class;

import org.json.JSONObject;

public class Registration extends AppCompatActivity {

    private EditText mobilenumber,email;
    private EditText password ;
    Button signin,login;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();
        initView();
    }


    void initView(){

        mobilenumber = (EditText)findViewById(R.id.phone_signin);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password_signin);
login=(Button) findViewById(R.id.Register);
        signin=(Button) findViewById(R.id.btn_signin);



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidation1();
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Registration.this,Signin.class);
                startActivity(intent);
            }
        });

    }


    private void checkValidation1() {


        String mobile_number  = mobilenumber.getText().toString().trim();
        String email_id  = email.getText().toString().trim();
        String passwords  = password.getText().toString().trim();



        new login(mobile_number, email_id,passwords).execute();


    }



    private class login extends AsyncTask<Void, Void, JSONObject> {
        private String mobile_number, email,password;


        public login(String mobile_number, String email,String password) {
            this.mobile_number = mobile_number;
            this.email = email;
            this.password = password;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(Registration.this);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setMessage("Connecting...");
            dialog.show();
        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            JSONObject json = null;
            try {
                json = new OkHttp_Class().Register(mobile_number, email,password);
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
                    String message1 = jsonObject.getString("message");
                    if (message1 == "Everything worked as expected") {


                        Toast.makeText(Registration.this, "Success", Toast.LENGTH_SHORT).show();

                        //   MyApplication.getInstance().getPrefManager(Constants.Login_Preferences).storeUser(new User(mobile_number, tripid));


                               Intent intent = new Intent(Registration.this,MainScreen.class);

                               startActivity(intent);


                        // this finish call will kill the login page to invoke when it is pressed back.
                        finish();

                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(Registration.this, message, Toast.LENGTH_SHORT).show();
                    }

                } else
                    Toast.makeText(Registration.this, "Failed to login right now. Try again.", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(Registration.this, "Unknown error occurred. Try again.", Toast.LENGTH_SHORT).show();


            }
        }


    }

}
