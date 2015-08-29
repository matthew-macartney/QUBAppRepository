package com.example.matthew.qubapp.GUI;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.matthew.qubapp.Database.ProductDataSource;
import com.example.matthew.qubapp.Model.Product;
import com.example.matthew.qubapp.R;

import java.sql.SQLException;

public class BarcodeProductActivity extends Activity {

    private ProductDataSource myProductDB;
    TextView productName;
    TextView productRRP;
    TextView productPrice;
    TextView productSaving;
    ImageView productImage;

    public String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_product);
        Intent intent = getIntent();
        code = intent.getStringExtra("Code");
        Log.d("BARCODE", code);

        myProductDB = new ProductDataSource(getApplicationContext());
        try {
            myProductDB.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        productName = (TextView)findViewById(R.id.textViewProductName);
        productRRP = (TextView)findViewById(R.id.textViewProductRRP);
        productPrice = (TextView)findViewById(R.id.textViewProductPrice);
        productSaving = (TextView)findViewById(R.id.textViewProductSaving);
        productImage = (ImageView)findViewById(R.id.imageViewProduct);

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

        Product product = myProductDB.getProduct(code);

            productName.setText(product.getDescription());
            productRRP.setText("£"+ product.getRRP() + " RRP");
            productPrice.setText("Online price: £" + product.getPrice());
            productSaving.setText("Saving: "+ product.getSaving()+" B");
            productImage.setImageResource(Integer.valueOf(product.getImage()));

        //.setText(String.format("MAC: %s (%.2fm)", beacon.getMacAddress(), Utils.computeAccuracy(beacon)))

    }
}
