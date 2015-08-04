package com.example.matthew.qubapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.dm.zbar.android.scanner.ZBarConstants;
import com.dm.zbar.android.scanner.ZBarScannerActivity;


public class MainActivity extends ActionBarActivity {

    private static final int ZBAR_SCANNER_REQUEST = 0;
    private static final int ZBAR_QR_SCANNER_REQUEST = 1;

    TextView appName;
    DatabaseHelper myDBHelper;
    SQLiteDatabase db;
    ListView myListView;
    ImageButton barcode;
    TextView scanForm;
    TextView scanCont;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appName = (TextView) findViewById(R.id.textViewAppName);
        barcode = (ImageButton) findViewById(R.id.imageButtonBarcode);
        scanForm = (TextView) findViewById(R.id.textViewScanFrom);
        scanCont = (TextView) findViewById(R.id.textViewScanCont);
        myDBHelper = new DatabaseHelper(this, null, null, 6);
        queryDatabase();
        populateListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    public void queryDatabase() {
        String dbString = myDBHelper.databaseToString();
        appName.setText(dbString);

    }


    private void populateListView() {

        Cursor cursor = myDBHelper.getSomeRows();

        String[] fromFieldNames = new String[]{DatabaseHelper.PRODUCT_NAME, DatabaseHelper.PRODUCT_BRAND};
        int[] toViewIDs = new int[]{R.id.textViewProductDes, R.id.textViewBrand};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.offer_layout, cursor, fromFieldNames, toViewIDs, 0);
        myListView = (ListView) findViewById(R.id.listViewFromDB);
        myListView.setAdapter(myCursorAdapter);
    }



    public void barcodeButtonOnClick(View v) {
        if (isCameraAvailable()) {
            Intent intent = new Intent(this, ZBarScannerActivity.class);
            startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
        } else {
            Toast.makeText(this, "Rear Facing Camera Unavailable", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isCameraAvailable() {
        PackageManager pm = getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ZBAR_SCANNER_REQUEST:
            case ZBAR_QR_SCANNER_REQUEST:
                if (resultCode == RESULT_OK) {
                    String result = data.getStringExtra(ZBarConstants.SCAN_RESULT);
                    String product = myDBHelper.barcodeQueryDatabase(result);
                    Toast.makeText(this, "Product = " + product, Toast.LENGTH_SHORT).show();
                } else if(resultCode == RESULT_CANCELED && data != null) {
                    String error = data.getStringExtra(ZBarConstants.ERROR_INFO);
                    if(!TextUtils.isEmpty(error)) {
                        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

}