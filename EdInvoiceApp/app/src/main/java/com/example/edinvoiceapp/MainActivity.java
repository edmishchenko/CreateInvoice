package com.example.edinvoiceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    Intent intent;
    byte COUNT_OF_FIELD_IN_CLIENT_DETAILS = 7;
    byte COUNT_OF_FIELD_IN_MY_COMPANY_DETAILS = 7;
    TextView clients_details_company_name, clients_details_company_address, clients_details_company_country, text_from_ItemDetails, digit_from_ItemDetails, my_company_name, my_company_address, my_company_country, clear_all_items;
    String[] clients_details_company = new String[COUNT_OF_FIELD_IN_CLIENT_DETAILS];
    String[] my_company_details = new String[COUNT_OF_FIELD_IN_MY_COMPANY_DETAILS];
    SharedPreferences sPref, sPref1;
    EditText invoice_details_invoice_date, invoice_details_due_date;
    LinearLayout linearLayout_add_line;
    ListView item_list;
    String item_details_item_name, item_details_quantity, item_details_unit_cost;

    final Calendar myCalendarInvoice = Calendar.getInstance();
    final Calendar myCalendarDue = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Variable initialization
        clients_details_company_name = (TextView) findViewById(R.id.clients_details_company_name);
        clients_details_company_address = (TextView) findViewById(R.id.clients_details_company_address);
        clients_details_company_country = (TextView) findViewById(R.id.clients_details_company_country);
        text_from_ItemDetails = (TextView) findViewById(R.id.text_from_ItemDetails);
        digit_from_ItemDetails = (TextView) findViewById(R.id.digit);
        my_company_name = (TextView) findViewById(R.id.my_company_name);
        my_company_address = (TextView) findViewById(R.id.my_company_address);
        my_company_country = (TextView) findViewById(R.id.my_company_country);
        clear_all_items = (TextView) findViewById(R.id.clear_all_items);
        invoice_details_invoice_date = (EditText) findViewById(R.id.invoice_details_invoice_date);
        invoice_details_due_date = (EditText) findViewById(R.id.invoice_details_due_date);
        linearLayout_add_line = (LinearLayout) findViewById(R.id.linearLayout_add_line);
        item_list = (ListView) findViewById(R.id.item_list);

        //DataPickerDialog in EditText
        final DatePickerDialog.OnDateSetListener date_of_myCalendarInvoice = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendarInvoice.set(year, month, dayOfMonth);
                updateLabel();
            }
        };

        invoice_details_invoice_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, date_of_myCalendarInvoice, myCalendarInvoice.get(Calendar.YEAR), myCalendarInvoice.get(Calendar.MONTH), myCalendarInvoice.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final DatePickerDialog.OnDateSetListener date_of_myCalendarDue = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendarDue.set(year, month, dayOfMonth);
                updateLabel();
            }
        };

        invoice_details_due_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, date_of_myCalendarDue, myCalendarDue.get(Calendar.YEAR), myCalendarDue.get(Calendar.MONTH), myCalendarDue.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd.MM.yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
        invoice_details_invoice_date.setText(sdf.format(myCalendarInvoice.getTime()));
        invoice_details_due_date.setText(sdf.format(myCalendarDue.getTime()));
    }


    private void receiveData_from_clients_details() {
        sPref = getSharedPreferences("Saved_clients_details", MODE_PRIVATE);
        for (byte i = 0; i < COUNT_OF_FIELD_IN_CLIENT_DETAILS; i++) {
            clients_details_company[i] = sPref.getString(String.valueOf(i), "");
        }
        //If clients_details_company is empty
        if (clients_details_company[2].equals("") && clients_details_company[3].equals("") && clients_details_company[4].equals("") && clients_details_company[6].equals("")) {
            clients_details_company_address.setText("");
        } else {
            clients_details_company_address.setText(String.format("%s, %s, %s, %s", clients_details_company[2], clients_details_company[3], clients_details_company[4], clients_details_company[6], ""));
        }
        clients_details_company_name.setText(clients_details_company[0]);
        clients_details_company_country.setText(clients_details_company[5]);

        sPref1 = getSharedPreferences("Saved_clients_details", MODE_PRIVATE);
        for (byte i = 0; i < COUNT_OF_FIELD_IN_MY_COMPANY_DETAILS; i++) {
            my_company_details[i] = sPref.getString(String.valueOf(i), "");
        }
        if (my_company_details[2].equals("") && my_company_details[3].equals("") && my_company_details[4].equals("") && my_company_details[6].equals("")) {
            my_company_address.setText("");
        } else {
            my_company_address.setText(String.format("%s, %s, %s, %s", my_company_details[2], my_company_details[3], my_company_details[4], my_company_details[6], ""));
            my_company_name.setText(my_company_details[0]);
            my_company_country.setText(my_company_details[5]);
        }
    }

    private void addNewItemDetail(){
        //TEST create item list
        Item_details item1 = new Item_details("Item1", "33x6", "198");
        Item_details item2 = new Item_details("Item2", "45x3", "1");
        Item_details item3 = new Item_details("Item3", "27x1", "8");
        Item_details item4 = new Item_details("Item4", "10x6", "18");

        //Add the Item object to an ArrayList
        ArrayList<Item_details> item_details_list = new ArrayList<>();
        item_details_list.add(item1);
        item_details_list.add(item2);
        item_details_list.add(item3);
        item_details_list.add(item4);

        ItemDetailsListAdapter adapter = new ItemDetailsListAdapter(this, R.layout.item_details_layout, item_details_list);
        item_list.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        updateLabel(); //Set InvoiceDate and DueDate
        receiveData_from_clients_details(); //Receive data when app is restarted
        //receiveData_from_item_details();
        super.onResume();
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.linearLayout_client_details:
                intent = new Intent(MainActivity.this, ClientDetails.class);
                startActivity(intent);
                break;
            case R.id.linearLayout_add_line:
                intent = new Intent(MainActivity.this, test.class);
                startActivity(intent);
                addNewItemDetail();
                break;
            case R.id.linearLayout_your_company_details:
                intent = new Intent(MainActivity.this, Your_company_details.class);
                startActivity(intent);
                break;
            case R.id.clear_all_items:
                break;
            default:
                break;
        }
    }
}