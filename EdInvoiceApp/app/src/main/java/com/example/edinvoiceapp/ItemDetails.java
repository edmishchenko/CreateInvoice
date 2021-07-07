package com.example.edinvoiceapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class ItemDetails extends AppCompatActivity {
    Intent intent;
    SharedPreferences sPref;
    EditText item_details_item_name, item_details_quantity, item_details_unit_cost;
    double total = 0;
    String quantity_and_unit_cost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        item_details_item_name = (EditText) findViewById(R.id.item_details_item_name);
        item_details_quantity = (EditText) findViewById(R.id.item_details_quantity);
        item_details_unit_cost = (EditText) findViewById(R.id.item_details_unit_cost);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.btn_save, menu);
        return true;
    }

    private void convertData(){
        total = Double.parseDouble(item_details_quantity.getText().toString()) * Double.parseDouble(item_details_unit_cost.getText().toString());
        quantity_and_unit_cost = item_details_quantity.getText().toString() + " x " + item_details_unit_cost.getText().toString();
    }
    public void sendData(MenuItem item) {
        convertData();
        sPref = getSharedPreferences("Saved_item_details", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("0", item_details_item_name.getText().toString());
        ed.putString("1", quantity_and_unit_cost);
        ed.putString("2", String.valueOf(total));
        ed.putInt("3", 1);
        ed.apply();
        finish();
    }

}