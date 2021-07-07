package com.example.edinvoiceapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ClientDetails extends AppCompatActivity {
    byte COUNT_OF_FIELD_IN_CLIENT_DETAILS = 7;
    EditText[] clients_company = new EditText[COUNT_OF_FIELD_IN_CLIENT_DETAILS];
    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_details);

        //Find values
        clients_company[0] = (EditText) findViewById(R.id.clients_company_name);
        clients_company[1] = (EditText) findViewById(R.id.clients_company_bill_to);
        clients_company[2] = (EditText) findViewById(R.id.clients_company_address);
        clients_company[3] = (EditText) findViewById(R.id.clients_company_city);
        clients_company[4] = (EditText) findViewById(R.id.clients_company_state);
        clients_company[5] = (EditText) findViewById(R.id.clients_company_country);
        clients_company[6] = (EditText) findViewById(R.id.clients_company_zip);

        //Set values
        loadEditTextValues();

    }
    private void saveText() {
        sPref = getSharedPreferences("Saved_clients_details", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        for (byte i = 0; i < COUNT_OF_FIELD_IN_CLIENT_DETAILS; i++){
            ed.putString(String.valueOf(i), clients_company[i].getText().toString());
        }
        ed.apply();
    }

    private void loadEditTextValues() {
        sPref = getSharedPreferences("Saved_clients_details", MODE_PRIVATE);
        for (byte i = 0; i < COUNT_OF_FIELD_IN_CLIENT_DETAILS; i++){
            clients_company[i].setText(sPref.getString(String.valueOf(i), ""));
        }
    }

    @Override
    protected void onPause() {
        //Save values to SharedPreferences
        saveText();
        super.onPause();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.clear_all_items){
            dialogQuestion();
        }
    }

    private void dialogQuestion() {
        final AlertDialog aboutDialog = new AlertDialog.Builder(
                ClientDetails.this).setMessage("Do you want to clear all the fields?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Positive
                        sPref.edit().clear().apply();
                        loadEditTextValues();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Negative
                    }
                }).create();

        aboutDialog.show();
    }
}