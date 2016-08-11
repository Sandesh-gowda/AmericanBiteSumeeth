package com.think_different.am.util;

import java.io.Serializable;

/**
 * Created by Dell on 4/6/2016.
 */
public class User implements Serializable {

    private String shop_id,address,distance;



    public User(String shop_id, String shop_address, String distance) {
        this.shop_id=shop_id;
        this.address=address;
        this.distance=distance;
    }


    public String getShop_id() {
        return shop_id;
    }

    public String getAddress(){
        return address;
    }

    public String getDistance() {
        return distance;
    }
}
