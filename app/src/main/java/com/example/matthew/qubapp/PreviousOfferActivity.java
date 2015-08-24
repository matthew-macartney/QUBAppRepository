package com.example.matthew.qubapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Matthew on 24/08/2015.
 */
public class PreviousOfferActivity extends Activity {

    TextView offerName;
    TextView offerShop;
    TextView offerExpiry;
    OfferDatabase myOfferDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);
        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");


        offerName = (TextView)findViewById(R.id.textViewOfferName12);
        offerShop = (TextView)findViewById(R.id.textViewShop);
        offerExpiry = (TextView)findViewById(R.id.textViewExpiry);

        myOfferDB = OfferDatabase.getInstance(getApplicationContext());

        setOfferDetails(name);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_offer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setOfferDetails(String name){

        ArrayList<String> details = myOfferDB.getBeaconOfferDetails(name);

        offerName.setText(name);
        //offerShop.setText(offerDetails.get(0));
        offerShop.setText(details.get(0));
        offerExpiry.setText(details.get(1));

    }
}
