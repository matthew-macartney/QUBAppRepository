package com.example.matthew.qubapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Matthew on 24/08/2015.
 */
public class PreviousOfferActivity extends Activity {

    TextView offerName;
    TextView offerShop;
    TextView offerDateReceived;
    TextView offerExpiry;
    ImageView icon;
    PreviousOfferDataSource myPreviousOfferDB;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_offer);
        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");

        offerName = (TextView)findViewById(R.id.previousOfferName2);
        offerShop = (TextView)findViewById(R.id.textViewPreviousShop);
        offerExpiry = (TextView)findViewById(R.id.textViewPreviousExpiry);
        icon = (ImageView)findViewById(R.id.imageViewPreviousIcon);

//        setOfferDetails(name);
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

    public void setBeaconOfferDetails(BeaconOffer beaconOffer){

        offerName.setText(beaconOffer.getDescription());
        offerShop.setText(beaconOffer.getStore());
        offerExpiry.setText(beaconOffer.getExpiry());
        icon.setImageResource(Integer.valueOf(beaconOffer.getIcon()));

    }
}
