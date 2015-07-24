package com.example.matthew.qubapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Database_Entry extends ActionBarActivity {

    DatabaseHelper myDB;
    EditText editName, editBrand, editCategory, editRRP, editPrice, editSaving;
    Button btnAddData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_entry);
        myDB = new DatabaseHelper(this);

        editName = (EditText)findViewById(R.id.editText_name);
        editBrand = (EditText)findViewById(R.id.editText_brand);
        editCategory = (EditText)findViewById(R.id.editText_category);
        editRRP = (EditText)findViewById(R.id.editText_RRP);
        editPrice = (EditText)findViewById(R.id.editText_price);
        editSaving = (EditText)findViewById(R.id.editText_saving);
        btnAddData = (Button)findViewById(R.id.button_add);
        AddData();
    }

    public void AddData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDB.insertData(editName.getText().toString(),editBrand.getText().toString(), editCategory.getText().toString(), editRRP.getText().toString(), editPrice.getText().toString(), editSaving.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(Database_Entry.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Database_Entry.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
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
}