package com.ashu.capstone20;



import android.app.Activity;

import android.app.Notification;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.generated.model.Inventory;
import com.amplifyframework.datastore.generated.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Dictionary;

import static com.amplifyframework.datastore.generated.model.Product.*;

public class MyListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList maintitle;
    private final Dictionary subtitle;
    private final ArrayList<String> imgid;
    SwipeRefreshLayout swipeRefreshLayout;
    ListView l;
    ArrayList catageoryID;
    String item_id;
    String id ="";
    String InventoryID;
    String Inventory_ProductID;
    ArrayList<String> ID=new ArrayList<>();

    public MyListAdapter(Activity context, ArrayList maintitle, Dictionary subtitle, ArrayList imgid, SwipeRefreshLayout swipeRefreshLayout, ArrayList catageoryID, ListView l) {
        super(context, R.layout.mylist, maintitle);
        // TODO Auto-generated constructor stub


        this.context=context;
        this.maintitle=maintitle;
        this.subtitle=subtitle;
        this.imgid=imgid;
        this.swipeRefreshLayout=swipeRefreshLayout;
        this.l=l;
        this.catageoryID=catageoryID;
    }


    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);
        Button btn_update_item=(Button) rowView.findViewById(R.id.Update_item);
        Button btn_delete_item=(Button) rowView.findViewById(R.id.Delete_item);

        if(maintitle.size()!=0)
        {
            titleText.setText((String) maintitle.get(position));
            Picasso.get().load(""+imgid.get(position)).into(imageView);
            //  imageView.setImageResource(imgid.get(position));
            if (catageoryID.equals("Grocery")){

                subtitleText.setText((String) "Quantity: "+subtitle.get(catageoryID.get(position))+" Kg");


            }else{
                subtitleText.setText((String) "Quantity: "+subtitle.get(catageoryID.get(position))+" Unit");

            }


        }else
        {

            Toast.makeText(getContext(),"No item in inventory",Toast.LENGTH_SHORT).show();
        }


        btn_delete_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                item_id="";
                item_id= (String) maintitle.get(position);
                Inventory_ProductID= (String) catageoryID.get(position);
                String i=GetItemID();//get id of item  from database
               GetInventoryID();
                Runnable r = new Runnable() {
                    @Override
                    public void run(){
                        //delete the item from database
                         deletedata();
                        //Delete INventory Data
                         deleteInventoryData();
                        Toast.makeText(getContext(),""+catageoryID.get(position),Toast.LENGTH_LONG).show();
                    }
                };

                Handler h = new Handler();
                h.postDelayed(r, 1000); // <-- the "1000" is the delay time in miliseconds.



            }
        });
        btn_update_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_id="";
                item_id= (String) maintitle.get(position);

                GetItemID();//get id of an item which is udated

               // Toast.makeText(getContext(),"update"+ maintitle.get(position),Toast.LENGTH_SHORT).show();
                Runnable a=new Runnable() {
                    @Override
                    public void run() {



                    }
                };

                Handler h=new Handler();
                h.postDelayed(a,1000);


            }
        });


        return rowView;

    }

    private void UpdateItem() {
            Product ToUpdate=Product.justId(id);


    }

    private String GetItemID() {

        Amplify.DataStore.query(
                Product.class,
                Where.matches(NAME.eq(item_id)),
                items -> {
                    while (items.hasNext()) {
                        Product item = items.next();
                        if(item.getName().equals(item_id)){
                            id=item.getId();

                        }
                        Log.i("Amplify", "Id " + item.getId());
                        ID.add(item.getName());
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)

        );
        return id;
    }
    private void GetInventoryID(){

        Amplify.DataStore.query(
                Inventory.class,
                Where.matches(Inventory.PRODUCTID.eq(Inventory_ProductID)),
                items->{
                    while(items.hasNext()){
                        Inventory item=items.next();
                        if (id.equals(item.getProductid())){
                            InventoryID=item.getId();
                        }
                        Log.i("Amplify", "Id " + item.getId());
                    }

                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );



    }
    private void deleteInventoryData(){
        Inventory toDeleteItem=Inventory.justId(InventoryID);
        Amplify.DataStore.delete(toDeleteItem,
                deleted -> Log.i("Amplify", "Deleted item."),
                failure -> Log.e("Amplify", "Delete failed.", failure)
        );

    }
private  void deletedata(){

        Product toDeleteItem=Product.justId(Inventory_ProductID);
        Amplify.DataStore.delete(toDeleteItem,
                deleted -> Log.i("Amplify", "Deleted item."),
                failure -> Log.e("Amplify", "Delete failed.", failure)
        );

}


}