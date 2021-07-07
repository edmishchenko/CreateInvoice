package com.example.edinvoiceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

public class Your_company_details extends AppCompatActivity {
    byte COUNT_OF_FIELD_IN_CLIENT_DETAILS = 7;
    byte COUNT_OF_FIELD_AUTO_COMPLETE_TEXT_VIEW = 3;
    EditText[] your_company_ed = new EditText[COUNT_OF_FIELD_IN_CLIENT_DETAILS];
    AutoCompleteTextView[] your_company_act = new AutoCompleteTextView[COUNT_OF_FIELD_AUTO_COMPLETE_TEXT_VIEW];
    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_company_details);

        //Set variebles
        your_company_act[0] = (AutoCompleteTextView) findViewById(R.id.company_details_date_format);
        your_company_act[1] = (AutoCompleteTextView) findViewById(R.id.company_details_symbol);
        your_company_act[2] = (AutoCompleteTextView) findViewById(R.id.company_details_language);
        your_company_ed[0] = (EditText) findViewById(R.id.company_details_company_name);
        your_company_ed[1] = (EditText) findViewById(R.id.company_details_your_name);
        your_company_ed[2] = (EditText) findViewById(R.id.company_details_comapany_address);
        your_company_ed[3] = (EditText) findViewById(R.id.company_details_city);
        your_company_ed[4] = (EditText) findViewById(R.id.company_details_state);
        your_company_ed[5] = (EditText) findViewById(R.id.company_details_country);
        your_company_ed[6] = (EditText) findViewById(R.id.company_details_zip);

        String[] dateFormatArray = getResources().getStringArray(R.array.date_format);
        String[] symbols = getResources().getStringArray(R.array.operators);
        String[] languages = getResources().getStringArray(R.array.languages);

        //Set edittext and autocompletetextview
        loadEditTextValues();

        ArrayAdapter<String> adapter_date = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, dateFormatArray);
        your_company_act[0].setAdapter(adapter_date);

        your_company_act[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                your_company_act[0].showDropDown();
            }
        });

        ArrayAdapter<String> adapter_symbol = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, symbols);
        your_company_act[1].setAdapter(adapter_symbol);

        your_company_act[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                your_company_act[1].showDropDown();
            }
        });

        ArrayAdapter<String> adapter_languages = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, languages);
        your_company_act[2].setAdapter(adapter_languages);

        your_company_act[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                your_company_act[2].showDropDown();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.btn_save, menu);
        return true;
    }

    public void sendData(MenuItem item) {
        //Save all edittext text in layout
        saveText();
        finish();
    }
    private void saveText(){
        sPref = getSharedPreferences("Saved_clients_details", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        for (byte i = 0; i < COUNT_OF_FIELD_IN_CLIENT_DETAILS; i++){
            ed.putString(String.valueOf(i), your_company_ed[i].getText().toString());
        }
        for (byte i = 0; i < COUNT_OF_FIELD_AUTO_COMPLETE_TEXT_VIEW; i++){
            int index = i + COUNT_OF_FIELD_IN_CLIENT_DETAILS;
            ed.putString(String.valueOf(index), your_company_act[i].getText().toString());
        }
        ed.apply();
    }
    private void loadEditTextValues() {
        sPref = getSharedPreferences("Saved_clients_details", MODE_PRIVATE);
        for (byte i = 0; i < COUNT_OF_FIELD_IN_CLIENT_DETAILS; i++){
            your_company_ed[i].setText(sPref.getString(String.valueOf(i), ""));
        }
        for (byte i = 0; i < COUNT_OF_FIELD_AUTO_COMPLETE_TEXT_VIEW; i++){
            int index = i + COUNT_OF_FIELD_IN_CLIENT_DETAILS;
            your_company_act[i].setText(sPref.getString(String.valueOf(index), ""));
        }
    }
}