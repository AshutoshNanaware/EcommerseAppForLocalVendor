package com.ashu.capstone20;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyOrderAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList maintitle;
    private final ArrayList OrderDate;
    private final ArrayList OrderStatus;


    public MyOrderAdapter(Activity context, ArrayList maintitle, ArrayList OrderStatus,ArrayList OrderDate ) {
        super(context, R.layout.mylist, maintitle);
        this.context = context;
        this.maintitle = maintitle;
        this.OrderDate = OrderDate;
       this.OrderStatus=OrderStatus;
    }


    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.my_order_layout, null, true);

        TextView titleText = (TextView) rowView.findViewById(R.id.product_name_customer);
        TextView OrderDateText = (TextView) rowView.findViewById(R.id.dilivery_date);


        ImageView imageView = (ImageView) rowView.findViewById(R.id.image_customer_order);


        // setting all data in listview layout
        titleText.setText((String) maintitle.get(position));

        if(OrderStatus.get(position).equals("Active")){

            OrderDateText.setText(" On The Way" );

        }else{

            OrderDateText.setText("Diliverd On  " + OrderDate.get(position));
        }
        imageView.setImageResource(R.drawable.temp_logo);


        return rowView;

    }
}
