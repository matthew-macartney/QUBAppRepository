package com.example.matthew.qubapp.GUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;

import com.example.matthew.qubapp.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Matthew on 21/08/2015.
 */
public class SimpleScannerActivity extends Activity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    public static final String TAG = "Barcode: ";

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);// Set the scanner view as the content view
        getActionBar()/* or getSupportActionBar() */.setTitle(Html.fromHtml("<font color=\"#ffffff\"> Scanning... </font>"));
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {

        String code = rawResult.getText();
        Intent intent = new Intent(this, BarcodeProductActivity.class);
        intent.putExtra("Code", code);
        startActivity(intent);

    }
}
