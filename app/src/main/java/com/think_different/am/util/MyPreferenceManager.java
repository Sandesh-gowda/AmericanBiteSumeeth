package com.think_different.am.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


public class MyPreferenceManager {

    private String TAG = MyPreferenceManager.class.getSimpleName();

    // Shared Preferences
    private SharedPreferences pref;

    // Editor for Shared preferences
    private SharedPreferences.Editor editor;

    // Context
    private Context _context;

    // Shared pref mode
    private int PRIVATE_MODE = 0;

    // Constructor
    public MyPreferenceManager(Context context, String pref_key_value) {
        this._context = context;
        pref = _context.getSharedPreferences(pref_key_value, PRIVATE_MODE);
        editor = pref.edit();
    }


   public void storeUser(User user) {
       editor.putString(Constants.shop_id, user.getShop_id()) ;

               editor.putString(Constants.shop_id, user.getShop_id());

        editor.commit();

        Log.e(TAG, "User is stored in shared preferences. " + user.getShop_id() + "");
    }

    public User getUser() {
        if (pref.getString(Constants.shop_id, null) != null) {
            String shop_id = pref.getString(Constants.shop_id, null);
            String address = pref.getString(Constants.address, null);
            String distance = pref.getString(Constants.distance, null);


            return new User(shop_id,address,distance);
        }
       return null;
   }

    public void logoutUser() {
        editor.clear();
        editor.commit();
    }
}
