package com.think_different.am.util;

import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;


/**
 * Created by SONU on 13/10/15.
 */
public class OkHttp_Class {

    public static final String TAG = "OkHTTP";
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    public JSONObject loginUser(String phone, String password) throws IOException {
        try {

            RequestBody formBody;
            OkHttpClient client = new OkHttpClient();

            formBody = new FormEncodingBuilder()
                    .add("phone", phone)
                    .add("password", password)
                    .build();


            Request request = new Request.Builder()
                    .url(End_Points.login)
                    .post(formBody)
                    .build();
            Response responses = null;

            try {
                responses = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String jsonString = responses.body().string();
            Log.e(TAG, jsonString);
            JSONObject jsonData = new JSONObject(jsonString);
            //  Log.e(TAG, jsonData.toString());

            return jsonData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public JSONObject Register(String phone, String email,String password) throws IOException {
        try {

            RequestBody formBody;
            OkHttpClient client = new OkHttpClient();

            formBody = new FormEncodingBuilder()
                    .add("phone", phone)
                    .add("email",email)
                    .add("password", password)
                    .build();


            Request request = new Request.Builder()
                    .url(End_Points.register)
                    .post(formBody)
                    .build();
            Response responses = null;

            try {
                responses = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String jsonString = responses.body().string();
            Log.e(TAG, jsonString);
            JSONObject jsonData = new JSONObject(jsonString);
             Log.e(TAG, jsonData.toString());

            return jsonData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject locatintrace(String latitude, String longitude) throws IOException {
        try {

            RequestBody formBody;
            OkHttpClient client = new OkHttpClient();

            formBody = new FormEncodingBuilder()
                    .add("latitude", latitude)
                    .add("longitude",longitude)

                    .build();


            Request request = new Request.Builder()
                    .url(End_Points.locationtrack)
                    .post(formBody)
                    .build();
            Response responses = null;

            try {
                responses = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String jsonString = responses.body().string();
            Log.e(TAG, jsonString);
            JSONObject jsonData = new JSONObject(jsonString);
            Log.e(TAG, jsonData.toString());

            return jsonData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
