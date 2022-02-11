package com.ashu.capstone20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Inventory;
import com.amplifyframework.datastore.generated.model.Product;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class ShowInventory extends AppCompatActivity {
    String catageoryID;
    ArrayList<String> ProductImageRef=new ArrayList<String>();
    TextView t1,textView_null_text;
    ListView l;
    ArrayList inventory=new ArrayList();
    ArrayList item_quantity=new ArrayList();
    ArrayList ProductID=new ArrayList();
    Dictionary PrductQuantity=new Hashtable();
    String SellerID;
    ArrayList inventory_category=new ArrayList();
    SwipeRefreshLayout swipeRefreshLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_inventory);
        catageoryID=getIntent().getStringExtra("category");
        t1=findViewById(R.id.textView3);
        t1.setText(catageoryID);
        textView_null_text=findViewById(R.id.textView11);
        textView_null_text.setVisibility(View.INVISIBLE);
        l = findViewById(R.id.list);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        GetItemList();
        swipeRefreshLayout.setRefreshing(true);

        Runnable r2 = new Runnable() {
            @Override
            public void run(){
                if(ProductID.size()!=0){
                    GetInventoryData();

                }

            }
        };

        Handler h2 = new Handler();
        h2.postDelayed(r2, 1000); // <-- the "1000" is the delay time in miliseconds.


        Runnable r = new Runnable() {
            @Override
            public void run(){
            //   Toast.makeText(getApplicationContext(),""+PrductQuantity.get(ProductID.get(0)),Toast.LENGTH_LONG).show();

                 RealoadDataIntoArrayAdapter();
                swipeRefreshLayout.setRefreshing(false);

            }
        };

        Handler h = new Handler();
        h.postDelayed(r, 2000); // <-- the "1000" is the delay time in miliseconds.

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        //swipe refraesh actiuon listner
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                GetItemList();

                Runnable r3 = new Runnable() {
                    @Override
                    public void run(){
                        if(ProductID.size()!=0){
                            GetInventoryData();

                        }

                    }
                };

                Handler h3 = new Handler();
                h3.postDelayed(r3, 1000); // <-- the "1000" is the delay time in miliseconds.



                Runnable r = new Runnable() {
                    @Override
                    public void run(){
                        RealoadDataIntoArrayAdapter();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                };

                Handler h = new Handler();
                h.postDelayed(r, 3000); // <-- the "1000" is the delay time in miliseconds.



            }
        });


    }

    private void GetInventoryData() {
        item_quantity.clear();
 //       Toast.makeText(getApplicationContext(),""+ProductID.get(0),Toast.LENGTH_SHORT).show();
    for(int i=0;i<ProductID.size();i++)
    {

        int finalI = i;

        Amplify.DataStore.query(
                Inventory.class,

                items -> {
                    while (items.hasNext()) {
                        Inventory item = items.next();
                        if(ProductID.get(finalI).equals(item.getProductid())){
                            item_quantity.add(""+item.getQuantity());
                            PrductQuantity.put(ProductID.get(finalI),item.getQuantity());


                            Log.i("Amplify user data ----------------------->>>>> "+item.getQuantity(), "Id " + item.getId());

                        }





                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );


    }


    }

    private void RealoadDataIntoArrayAdapter() {

            // Shuffling the arraylist items on the basis of system time
//            Collections.shuffle(arrayList, new Random(System.currentTimeMillis()));
//            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
//            listView.setAdapter(arrayAdapter);
        if(inventory.size()>0){
            MyListAdapter adapter=new MyListAdapter(this,inventory, PrductQuantity,ProductImageRef,swipeRefreshLayout, ProductID,l);
            l.setAdapter(adapter);

        }else{
            textView_null_text.setText("   No item in the Inventory of "+catageoryID+" please add items in the inventory");
            textView_null_text.setVisibility(View.VISIBLE);
            l.setVisibility(View.INVISIBLE);
        }


    }


    private void GetItemList() {
        SharedPreferences prefs = getSharedPreferences("capstone20", MODE_PRIVATE);
        SellerID = prefs.getString("SellerID", "none");//"No name defined
        //here sellerID and shopeID are same
        inventory.clear();
        item_quantity.clear();

        Amplify.DataStore.query(
                Product.class,

                items -> {
                    while (items.hasNext()) {
                        Product item = items.next();
                        if(SellerID.equals(item.getShopid())&&catageoryID.equals(item.getCategoryid())){
                            ProductID.add(item.getId());
                            inventory.add(item.getName());
                            ProductImageRef.add(item.getProductimg());
                            Log.i("Amplify user of theis account is --------------"+SellerID, "Id "+  item.getProductimg());

                        }

                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );
    }

}