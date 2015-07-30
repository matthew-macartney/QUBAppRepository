package com.example.matthew.qubapp;

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


public class MainActivity extends ActionBarActivity {


    TextView appName;
    DatabaseHelper myDBHelper;
    SQLiteDatabase db;
    ListView myListView;
    ImageButton barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appName = (TextView) findViewById(R.id.textViewAppName);
        myDBHelper = new DatabaseHelper(this, null, null, 4);
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



    public void queryDatabase(){
        String dbString = myDBHelper.databaseToString();
        appName.setText(dbString);

    }


    private void populateListView(){

        Cursor cursor = myDBHelper.getSomeRows();

        String[] fromFieldNames = new String[]{DatabaseHelper.PRODUCT_NAME, DatabaseHelper.PRODUCT_BRAND};
        int[] toViewIDs = new int[]{R.id.textViewProductDes, R.id.textViewBrand};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.offer_layout, cursor, fromFieldNames, toViewIDs, 0);
        myListView = (ListView) findViewById(R.id.listViewFromDB);
        myListView.setAdapter(myCursorAdapter);
    }

    public void scanNow(View view) {
        Log.d("test", "button works!");
        Intent intent = new Intent("com.google.zxing.client.android.SCAN"); intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "QR_CODE_MODE"); startActivityForResult(intent, 0);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(requestCode == 0){
            if(resultCode == RESULT_OK){
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                Log.i("xZing", "contents: "+contents+" format: "+format); // Handle successful scan
            }
            else if(resultCode == RESULT_CANCELED){ // Handle cancel
                Log.i("xZing", "Cancelled");
            }
        }
    }




}
