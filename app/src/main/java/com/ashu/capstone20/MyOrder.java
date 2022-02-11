package com.ashu.capstone20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.generated.model.Order;

import java.util.ArrayList;

public class MyOrder extends AppCompatActivity {
    ListView list;
    SwipeRefreshLayout swipeRefreshLayout;
    String UserID;
    ArrayList Product_Title=new ArrayList();
    ArrayList Diliver_Status=new ArrayList();
    ArrayList Dilivery_Date=new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        UserID=getIntent().getStringExtra("UserID");
        list=findViewById(R.id.listview_customer_order);
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout_customer_order);
        swipeRefreshLayout.setRefreshing(true);
     //   Toast.makeText(getApplicationContext(),""+UserID,Toast.LENGTH_LONG).show();

        getOrderDetails();



        Runnable a=new Runnable() {
            @Override
            public void run() {
                if(Product_Title.size()==0){
                    list.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),"Don't have any order",Toast.LENGTH_LONG).show();
                    swipeRefreshLayout.setRefreshing(false);
                }else{


                    ReloadAdapter();
                    swipeRefreshLayout.setRefreshing(false);



                }

            }
        };
        Handler h=new Handler();
        h.postDelayed(a,1000);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Runnable a=new Runnable() {
                    @Override
                    public void run() {

                        ReloadAdapter();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                };
                Handler h=new Handler();
                h.postDelayed(a,1000);


            }
        });

    }

    public void ReloadAdapter() {
        MyOrderAdapter adapter=new MyOrderAdapter(
                this,
                Product_Title,
                Diliver_Status,
                Dilivery_Date
        );
        list.setAdapter(adapter);
    }

    private void getOrderDetails() {

        Amplify.DataStore.query(
                Order.class,
                Where.matches(Order.CUSTOMERID.eq(UserID)),
                items -> {
                    while (items.hasNext()) {
                        Order item = items.next();

                        Product_Title.add(item.getProductname());
                        Diliver_Status.add(item.getProductstatus());
                        Dilivery_Date.add(item.getDeliverydate());


                        Log.i("Amplify", "Id "  );

                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );
    }
}