package com.example.matthew.qubapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class OfferActivity extends Activity {

    TextView offerName;
    TextView offerShop;
    TextView offerExpiry;
    GeneralOfferTable myOfferDB;
    Button button;

    public String offer;
    public String store;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);

        offerName = (TextView)findViewById(R.id.textViewOfferName12);
        offerShop = (TextView)findViewById(R.id.textViewShop);
        offerExpiry = (TextView)findViewById(R.id.textViewExpiry);

        myOfferDB = GeneralOfferTable.getInstance(getApplicationContext());

        Intent intent = getIntent();
        if(intent.getStringExtra("Name") != null) {
            offer = intent.getStringExtra("Name");
            setOfferDetails(offer);
        }else if(intent.getStringExtra("Offer")!= null){
            offer = intent.getStringExtra("Offer");
            setBeaconOfferDetails(offer);
        }
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

    public void onMapButtonClick(View v){

        Intent intent = new Intent(this, MapsActivity.class);

        intent.putExtra("Name", offerName.getText());
        //intent.putExtra("Store", store);
        startActivity(intent);

    }

    public void setOfferDetails(String offer){

        ArrayList<String> details = myOfferDB.getOfferDetails(offer);

        this.offer = offer;
        offerName.setText(offer);
        //offerShop.setText(offerDetails.get(0));
        store = details.get(0);
        offerShop.setText(details.get(0));
        offerExpiry.setText(details.get(1));

    }

    public void setBeaconOfferDetails(String offer){

        ArrayList<String> details = myOfferDB.getBeaconOfferDetails(offer);

        this.offer = offer;
        offerName.setText(offer);
        //offerShop.setText(offerDetails.get(0));
        store = details.get(0);
        offerShop.setText(details.get(0));
        offerExpiry.setText(details.get(1));

    }


}
