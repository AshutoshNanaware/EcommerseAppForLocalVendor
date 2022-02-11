package com.ashu.capstone20;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.datastore.generated.model.Product;

import java.util.ArrayList;
import java.util.Dictionary;

public class CartListViewAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList maintitle;
    private  ArrayList quantity;
    private  Dictionary details,prise;
    private ArrayList category;
    private ArrayList ProductID;
    private Dictionary Product_name ;
    public CartListViewAdapter(Activity context, ArrayList maintitle,Dictionary Product_name, ArrayList quantity, Dictionary prise, ArrayList category, ArrayList ProductID) {


        super(context, R.layout.cart_listview_layout, maintitle);
        this.context=context;
        this.maintitle=maintitle;
        this.quantity=quantity;
        this.prise=prise;
        this.ProductID=ProductID;
        this.category =category;
    this.Product_name=Product_name;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.cart_listview_layout, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.caer_product_title);
        TextView quantityText=(TextView) rowView.findViewById(R.id.cart_listview_quantity_textview);
        ImageView add_icon=(ImageView) rowView.findViewById(R.id.cart_listview_add_icon);
        ImageView remove_icon=(ImageView) rowView.findViewById(R.id.cart_listview_remove_icon);
        ImageView Product_image=(ImageView) rowView.findViewById(R.id.cart_list_icon);

        add_icon.setImageResource(R.drawable.ic_add);
        remove_icon.setImageResource(R.drawable.ic_remove);

        Product_image.setImageResource(R.drawable.storage1);
        titleText.setText((String)  Product_name.get(ProductID.get(position))+" Prise :"+prise.get(ProductID.get(position)));
        if(category.get(position).equals("Grocery")){

            quantityText.setText(""+quantity.get(position)+" Kg");
        }else{
            quantityText.setText(""+quantity.get(position)+" Unit");
        }


        return rowView;

    }
}
