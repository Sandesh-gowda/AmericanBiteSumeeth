package com.think_different.am;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.think_different.am.adapter.MenuAdapter;
import com.think_different.am.fragment.Hfragment;
import com.think_different.am.model.Menu;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Menu> arrayList;
    private MenuAdapter adapter;
    private FragmentManager fragmentManager;

    private String responseData;
    public String URLMENU = "http://appshoppee.in/americanbyte/API/today_sp_mnu.php";
    public String urlM = "http://appshoppee.in/americanbyte/API/menus_list.php";
    public String urlD = "http://appshoppee.in/americanbyte/API/deset_mnu.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initRecyclerView();

        fragmentManager = getSupportFragmentManager();

         //makeSearchCall();

        //At start set home fragment
        if (savedInstanceState == null) {
          //  navigationView.setCheckedItem(R.id.home);
         //   MenuItem item = navigationView.getMenu().findItem(R.id.home);
            Fragment homeFragment = fragmentManager.findFragmentByTag(getTitle().toString());
            if (homeFragment == null)
                fragmentManager.beginTransaction().replace(R.id.frame_container, new Hfragment(), getTitle().toString()).commit();
        }





    }

/*
This was a working code so that i need to make it work .

    private void initRecyclerView() {
        arrayList = new ArrayList<>();


        recyclerView = (RecyclerView)
                findViewById(R.id.menu_recycler_view);

        recyclerView
                .setLayoutManager(new GridLayoutManager(MainActivity.this, 1));// Here 2 is no. of columns to be displayed


    }


    private void makeSearchCall() {
       StringRequest jsonObjRequest = new StringRequest(Request.Method.GET,
               URLMENU,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        responseData = response;
                        setData(response);
                        VolleyLog.d("volley", "Error: " + response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("volley", "Error: " + error.getMessage());
                error.printStackTrace();

                Log.d("MenuFragment", "onErrorResponse : 103");
            }
        });
        Controller.getInstance().getRequestQueue().add(jsonObjRequest);

    }







    private void setData(String response) {
        try {
            Type listOfTestObject = new TypeToken<List<Menu>>() {
            }.getType();
            Gson gson = new Gson();
            ArrayList<Menu> Menulist = gson.fromJson(response,listOfTestObject);
            if (Menulist == null || Menulist.size() == 0) {
              //TODO
                */
/*
                * check whether it has some data or not ?
                *
                *
                * *//*




            } else {
                */
/*TODO:set the adapter of recyclerview .
                *
                *
                * *//*


               adapter = new MenuAdapter(Menulist,MainActivity.this);
               recyclerView.setAdapter(adapter);
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }
*/

}
