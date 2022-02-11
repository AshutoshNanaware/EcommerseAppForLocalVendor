package com.ashu.capstone20;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryListAdaptor extends ArrayList<String> {

    private final Activity context;
    private final ArrayList maintitle;

    public CategoryListAdaptor(Activity context, ArrayList maintitle) {
        this.context = context;
        this.maintitle = maintitle;
    }


    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.categorylist, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.ProductCategory);




        titleText.setText((String) maintitle.get(position));



        return rowView;

    }


}
