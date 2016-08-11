package com.think_different.am;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {


    private EditText nameOfOrderPerson ;
    private EditText mobileNumberOfOrderPerson;
    private EditText deliveryAddress ;
    private TextView totalCost ;
    private Button  checkout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        getSupportActionBar().hide();
        intiView();
    }


    public void intiView(){

        nameOfOrderPerson = (EditText) findViewById(R.id.name_of_order_person);
        mobileNumberOfOrderPerson = (EditText) findViewById(R.id.phone_number_of_order_person);
        deliveryAddress = (EditText) findViewById(R.id.address_of_order_person);
        totalCost =(TextView)findViewById(R.id.toatal_cost);
        checkout = (Button)findViewById(R.id.btn_checkout);

    }


}
