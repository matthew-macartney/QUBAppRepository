package com.example.matthew.qubapp.GUI;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView productLink;

    public String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_product);
        getActionBar().setTitle(Html.fromHtml("<font color=\"#ffffff\"> Product found! </font>"));

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
        productLink = (TextView)findViewById(R.id.textViewProductLink);

        productLink.setClickable(true);
        productLink.setMovementMethod(LinkMovementMethod.getInstance());

        setLayoutValues(code);

    }


    public void setLayoutValues(String code){

        try {

                Product product = myProductDB.getProduct(code);

                productName.setText(product.getDescription());
                productRRP.setText(String.format("£%.2f RRP", product.getRRP()));
                productPrice.setText(String.format("Online price: £%.2f", product.getPrice()));
                productSaving.setText("Saving: " + product.getSaving() + "%");
                productImage.setImageResource(Integer.valueOf(product.getImage()));
                String text = "<a href='" + product.getLink()+ "'>" + product.getLinkName() + "</a>";
                productLink.setText(Html.fromHtml(text));


        }catch(Exception ex){
            Log.d("SQL Error", "Cannot retrieve product");
            Toast.makeText(this, "Product not found", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
}
