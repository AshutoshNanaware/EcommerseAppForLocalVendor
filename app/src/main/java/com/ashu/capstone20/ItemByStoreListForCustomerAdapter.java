package com.ashu.capstone20;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemByStoreListForCustomerAdapter extends ArrayAdapter<String>{




        private final Activity context;
        private final ArrayList maintitle;
        private final ArrayList subtitle;
        private final ArrayList itemListCategory;
         private final ArrayList itemImage;


    public ItemByStoreListForCustomerAdapter(Activity context, ArrayList maintitle, ArrayList subtitle, ArrayList<String> itemListCategory, ArrayList<String> itemImage) {
            super(context, R.layout.activity_store_vist_by_customer, maintitle);
            // TODO Auto-generated constructor stub

            this.context=context;
            this.maintitle=maintitle;
            this.subtitle=subtitle;
            this.itemListCategory=itemListCategory;
            this.itemImage = itemImage;
    }

        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater=context.getLayoutInflater();
            View rowView=inflater.inflate(R.layout.item_by_store_for_customer, null,true);

            TextView titleText = (TextView) rowView.findViewById(R.id.title_product_customer);
            ImageView imageView=(ImageView) rowView.findViewById(R.id.customer_list_icon);
            TextView subtitleText=(TextView) rowView.findViewById(R.id.subtitle_prise_customer);
            Picasso.get().load(""+itemImage.get(position)).into(imageView);
            titleText.setText((String) maintitle.get(position));
            if(itemListCategory.get(position).equals("Grocery")){

                subtitleText.setText((String)"Rs :"+subtitle.get(position)+" /Kg");

            }else{

                subtitleText.setText((String)"Rs :"+subtitle.get(position));

            }


            return rowView;

        };
    }



