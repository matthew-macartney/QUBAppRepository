package com.example.matthew.qubapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class BarcodeProductActivity extends Activity {

    ProductDatabase myProductDB;
    TextView productName;
    TextView productRRP;
    TextView productPrice;
    TextView productSaving;

    public String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_product);
        Intent intent = getIntent();
        code = intent.getStringExtra("Code");

        myProductDB = ProductDatabase.getInstance(this);

        productName = (TextView)findViewById(R.id.textViewProductName);
        productRRP = (TextView)findViewById(R.id.textViewRRP);
        productPrice = (TextView)findViewById(R.id.textViewPrice);
        productSaving = (TextView)findViewById(R.id.textViewSaving);

        setLayoutValues(code);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_barcode_product, menu);
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

    public void setLayoutValues(String code){

        ArrayList<String> product = myProductDB.barcodeQueryDatabase(code);

            productName.setText(product.get(0));
            productRRP.setText(product.get(1));
            productPrice.setText(product.get(2));
            productSaving.setText(product.get(3));


    }
}
