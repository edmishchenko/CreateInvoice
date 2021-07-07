package com.example.edinvoiceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ItemDetailsListAdapter extends ArrayAdapter<Item_details> {
    private Context mContext;
    int mResource;
    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */

    public ItemDetailsListAdapter(Context context, int resource, ArrayList<Item_details> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String text_from_ItemDetails = getItem(position).getText_from_ItemDetails();
        String digit = getItem(position).getDigit();
        String total_sum = getItem(position).getTotal_sum();

        //Create the Item details object with the information
        Item_details item_details = new Item_details(text_from_ItemDetails, digit, total_sum);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView  text_from_ItemDetails_tv = (TextView) convertView.findViewById(R.id.text_from_ItemDetails);
        TextView  digit_tv = (TextView) convertView.findViewById(R.id.digit);
        TextView  total_sum_tv = (TextView) convertView.findViewById(R.id.total_sum);

        text_from_ItemDetails_tv.setText(text_from_ItemDetails);
        digit_tv.setText(digit);
        total_sum_tv.setText(total_sum);

        return convertView;
    }
}
