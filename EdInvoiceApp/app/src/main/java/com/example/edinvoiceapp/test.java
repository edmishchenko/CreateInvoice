package com.example.edinvoiceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class test extends AppCompatActivity {
    byte COUNT_OF_ITEMDETAILS_FIELD = 3;
    ListView item_list;
    TextView subtotal, sales_tax, total;
    EditText sales_tax_number;
    AutoCompleteTextView money;
    Intent intent;
    SharedPreferences sPref;
    String[] item = new String[COUNT_OF_ITEMDETAILS_FIELD];
    ArrayList<Item_details> item_details_list;
    ItemDetailsListAdapter adapter;
    int num_of_test;
    double subtotal_sum = 0, sales_tax_sum = 0, total_sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        item_list = (ListView) findViewById(R.id.item_list);
        money = (AutoCompleteTextView) findViewById(R.id.money);
        subtotal = (TextView) findViewById(R.id.subtotal);
        sales_tax = (TextView) findViewById(R.id.sales_tax);
        total = (TextView) findViewById(R.id.total);
        sales_tax_number = (EditText) findViewById(R.id.sales_tax_number);
        //AutoCompliteText showDropDown
        String[] moneyArray = getResources().getStringArray(R.array.money);
        ArrayAdapter<String> adapter_money = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, moneyArray);
        money.setAdapter(adapter_money);
        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                money.showDropDown();
            }
        });
        //EditText TextChanged
        sales_tax_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }//Dont use this part
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }//Dont use this part
            @Override
            public void afterTextChanged(Editable s) {
                if (!("").equals(sales_tax_number.getText().toString())) {
                    sales_tax_sum = (subtotal_sum * Double.parseDouble(sales_tax_number.getText().toString())) / 100;
                    total_sum = subtotal_sum + sales_tax_sum;
                    sales_tax.setText(String.valueOf(sales_tax_sum));
                    total.setText(String.valueOf(total_sum));
                }
                else {
                    sales_tax.setText(R.string.Zero);
                    total.setText(R.string.Zero);
                }
            }
        });
        //Create ArrayList<Item_details>
        item_details_list = new ArrayList<>();
        //Set up adapter
        adapter = new ItemDetailsListAdapter(this, R.layout.item_details_layout, item_details_list);
        item_list.setAdapter(adapter);

        item_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {                      //ЭТО НУЖНО ДОДЕЛАТЬ НЕ НАЖИМАЮТСЯ
                Toast.makeText(test.this, "click item"+position+" "+item_details_list.get(position).toString(), Toast.LENGTH_SHORT).show();
                //Intent intent1 = new Intent(test.this, ItemDetails.class);
                //startActivity(intent1);
            }
        });
    }
    public void receiveData_from_itemDetails() {
            for (byte i = 0; i < COUNT_OF_ITEMDETAILS_FIELD; i++) {
                item[i] = sPref.getString(String.valueOf(i), "");
            }
            addDatatoItemList();
            subtotal_sum += Double.parseDouble(item[2]);
    }
    public void addDatatoItemList(){
        //Add the Item object to an ArrayList
        item_details_list.add(new Item_details(item[0], item[1], item[2]));
        adapter.notifyDataSetChanged();
    }


    public void onClick(View view) {
        switch (view.getId()){
            case R.id.linearLayout_add_line:
                intent = new Intent(test.this, ItemDetails.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    @Override
    protected void onRestart() {
        sPref = getSharedPreferences("Saved_item_details", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        num_of_test = sPref.getInt("3", 0);
        if (num_of_test == 1) receiveData_from_itemDetails();
        ed.putInt("3", 0);
        ed.apply();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        subtotal.setText(String.valueOf(subtotal_sum));
        super.onResume();
    }
}