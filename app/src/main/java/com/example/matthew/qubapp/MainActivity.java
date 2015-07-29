package com.example.matthew.qubapp;

import android.annotation.TargetApi;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {


    TextView appName;
    DatabaseHelper myDBHelper;
    SQLiteDatabase db;
    ListView myListView;

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

        String[] fromFieldNames = new String[]{DatabaseHelper.PRODUCT_ID, DatabaseHelper.PRODUCT_NAME};
        int[] toViewIDs = new int[]{R.id.textViewProductDes, R.id.textViewBrand};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.offer_layout, cursor, fromFieldNames, toViewIDs, 0);
        myListView = (ListView) findViewById(R.id.listViewFromDB);
        myListView.setAdapter(myCursorAdapter);
    }



}
