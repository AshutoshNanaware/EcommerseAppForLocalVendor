package com.ashu.capstone20;



import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.generated.model.Order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SellerOrderAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList maintitle;
    private final ArrayList customer;
    private final ArrayList address;
    private final ArrayList prise;
    private final ArrayList quantity;
    private final ArrayList pin;
    private final ArrayList contact;
    private final ArrayList OrderID;

    public SellerOrderAdapter(Activity context, ArrayList maintitle, ArrayList customer, ArrayList prise, ArrayList quantity, ArrayList address, ArrayList pin, ArrayList contact,ArrayList OrderID) {
        super(context, R.layout.mylist, maintitle);
        this.context = context;
        this.maintitle = maintitle;
        this.customer = customer;
        this.address = address;
        this.prise = prise;
        this.quantity = quantity;
        this.pin=pin;
        this.contact = contact;
        this.OrderID=OrderID;
    }


    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.seller_order_layout, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.product_name);
        TextView customerNameText = (TextView) rowView.findViewById(R.id.customer_name);
        TextView addressText = (TextView) rowView.findViewById(R.id.Address_customer);
        TextView pinText = (TextView) rowView.findViewById(R.id.Pin);
        TextView quantityText = (TextView) rowView.findViewById(R.id.Quantity);
        TextView contactText=(TextView) rowView.findViewById(R.id.customer_contact);
        TextView prsiseText=(TextView) rowView.findViewById(R.id.price);

        Button btn_deliverd=(Button) rowView.findViewById(R.id.deliver_button);

        ImageView imageView=(ImageView) rowView.findViewById(R.id.image);


        // setting all data in listview layout
        titleText.setText((String) maintitle.get(position));
        prsiseText.setText("Prise :"+prise.get(position));
        customerNameText.setText("Name: "+(String)  customer.get(position));
        addressText.setText("Address: "+(String) address.get(position));
        pinText.setText("Pin: "+(String) pin.get(position));
        quantityText.setText("Quantity: "+(String) quantity.get(position));
        contactText.setText("contact: "+(String) contact.get(position));
        imageView.setImageResource(R.drawable.temp_logo);


        //diliverd button
        btn_deliverd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(),""+maintitle.get(position),Toast.LENGTH_LONG).show();
              ChangeTheDiliveryStatus(position,getDateTime());


            }
        });













        return rowView;

    }
    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private void ChangeTheDiliveryStatus(int position, String dateTime) {
        Amplify.DataStore.query(Order.class, Where.id((String) OrderID.get(position)),
                matches -> {
                    if (matches.hasNext()) {
                        Order original = matches.next();
                        Order edited = original.copyOfBuilder()
                                .productstatus("Deliverd")
                                .deliverydate(dateTime)
                                .build();
                        Amplify.DataStore.save(edited,
                                updated -> Log.i("MyAmplifyApp", "Updated a post."),
                                failure -> Log.e("MyAmplifyApp", "Update failed.", failure)
                        );
                    }
                },
                failure -> Log.e("MyAmplifyApp", "Query failed.", failure)
        );

    }


}
