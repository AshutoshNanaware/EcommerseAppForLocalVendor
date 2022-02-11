package com.ashu.capstone20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.generated.model.Order;
import com.amplifyframework.datastore.generated.model.Seller;

import java.util.ArrayList;

public class SellerOrders extends AppCompatActivity {
    ListView list;
    ArrayList<String> ProductImageRef=new ArrayList<String>();
    private  ArrayList<String> maintitle=new ArrayList<>();
    private  ArrayList<String> customer=new ArrayList<>();
    private  ArrayList<String>address=new ArrayList<>();
    private  ArrayList<String> prise=new ArrayList<>();
    private  ArrayList<String>quantity=new ArrayList<>();
    private  ArrayList<String>pin=new ArrayList<>();
    private  ArrayList<String> contact=new ArrayList<>();
    private  ArrayList<String> OrderID=new ArrayList<>();

    private  ArrayList<String> Product_name=new ArrayList<>();
    private String ShopID;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_orders);
         ShopID=getIntent().getStringExtra("ShopID");

        list=findViewById(R.id.customListView);
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout_seller_order);

        swipeRefreshLayout.setRefreshing(true);
        getOrderDataFromDatabase();


            Runnable a1=new Runnable() {
                @Override
                public void run() {
              RealoadAdapterView();
                    //Toast.makeText(getApplicationContext(),""+ShopID,Toast.LENGTH_LONG).show();
              swipeRefreshLayout.setRefreshing(false);
                }
            };
        Handler h1=new Handler();
        h1.postDelayed(a1,2000);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getOrderDataFromDatabase();

                Runnable a2=new Runnable() {
                    @Override
                    public void run() {
                        RealoadAdapterView();
                 //       Toast.makeText(getApplicationContext(),""+ShopID,Toast.LENGTH_LONG).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                };
                Handler h2=new Handler();
                h2.postDelayed(a2,2000);
            }
        });


    }

    private void getOrderDataFromDatabase() {
        maintitle.clear();
        customer.clear();
        prise.clear();
        quantity.clear();
        address.clear();
        pin.clear();
        contact.clear();
        OrderID.clear();
        Product_name.clear();

        //database query

        Amplify.DataStore.query(
                Order.class,
                Where.matches(Order.SELLERID.eq(ShopID).and(Order.PRODUCTSTATUS.eq("Active"))),
                items -> {
                    while (items.hasNext()) {
                        Order item = items.next();
                        OrderID.add(item.getId());
                        prise.add(String.valueOf( item.getOrderammount()));
                        quantity.add(String.valueOf(item.getOrderquantity()));
                        address.add(item.getShippingaddress());
                        pin.add(item.getZipcode());
                        contact.add(item.getOrdermobilenumber());
                        maintitle.add(item.getOrdername());
                        customer.add(item.getOrdername());
                        Product_name.add(item.getProductname());

                        Log.i("Amplify", "Id "  );

                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );

    }

    public void RealoadAdapterView() {
         SellerOrderAdapter adapter=new SellerOrderAdapter(
                this,
                 Product_name,
                 customer,
                 prise,
                 quantity,
                 address,
                 pin,
                 contact,
                 OrderID
                );
         list.setAdapter(adapter);

    }
}