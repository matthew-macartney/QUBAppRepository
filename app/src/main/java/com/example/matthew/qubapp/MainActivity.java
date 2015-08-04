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
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Toast;

import com.dm.zbar.android.scanner.ZBarConstants;
import com.dm.zbar.android.scanner.ZBarScannerActivity;
import com.jaalee.sdk.utils.L;


public class MainActivity extends Activity {

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
        findViewById(R.id.imageButtonBeacon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListBeaconActivity.class);
                intent.putExtra(ListBeaconActivity.EXTRAS_TARGET_ACTIVITY, NotifyDemoActivity.class.getName());
                startActivity(intent);
            }
        });
        myDBHelper = new DatabaseHelper(this, null, null, 6);
        queryDatabase();
        populateListView();

        L.enableDebugLogging(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        MenuItem mun1 = menu.add(0, -1, 0, "More");
        {
            mun1.setIcon(android.R.drawable.ic_menu_search);
            mun1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }

        menu.addSubMenu(1, Menu.FIRST, 0, "Jaalee");
        menu.addSubMenu(1, Menu.FIRST + 10, 1, "Buy Beacon");
        menu.addSubMenu(1, Menu.FIRST + 20, 2, "Get Source-Code");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case -1:
                Uri uri0 = Uri.parse("https://www.jaalee.com/store");
                startActivity(new Intent(Intent.ACTION_VIEW, uri0));
                break;
            case Menu.FIRST:
                Uri uri = Uri.parse("http://www.jaalee.com/index_en.html");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
                break;
            case Menu.FIRST + 10:
                Uri url1 = Uri.parse("https://www.jaalee.com/store");
                startActivity(new Intent(Intent.ACTION_VIEW, url1));
                break;
            case Menu.FIRST + 20:
                Uri url2 = Uri.parse("http://www.jaalee.com/contact_en.html");
                startActivity(new Intent(Intent.ACTION_VIEW, url2));
                break;
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

//    public void iBeaconButtonOnClick(View v){
//        Intent intent = new Intent(this, NotifyDemoActivity.class);
//        startActivity(intent);
//    }

}