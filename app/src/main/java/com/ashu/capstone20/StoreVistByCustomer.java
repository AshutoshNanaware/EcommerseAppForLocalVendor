package com.ashu.capstone20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Product;

import java.util.ArrayList;

public class StoreVistByCustomer extends AppCompatActivity {
    String ShopID;
     ArrayList<String> itemNameList =new ArrayList<>();
    ArrayList<String> itemImage =new ArrayList<>();
     ArrayList<String> itemPriseList=new ArrayList<>();
     ArrayList<String>  inventory_category=new ArrayList<>();
     ListView list;
     ArrayList<String> itemID=new ArrayList<>();
     SwipeRefreshLayout swipeRefreshLayout;
     TextView textView;
   
    String categoryID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_vist_by_customer);
        ShopID=getIntent().getStringExtra("ShopID");
        categoryID=getIntent().getStringExtra("categoryID");
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout_for__customer_store_item);
        list=findViewById(R.id.list_customer_product_by_store);
        swipeRefreshLayout.setRefreshing(true);
       textView=findViewById(R.id.text_view_empty_text);
        textView.setVisibility(View.INVISIBLE);
        GetShopProduct();  //get product list form daatabase for current shop

        Runnable  a=new Runnable() {  //wait till database load the data for 2 sec
            @Override
            public void run() {
              
                RealoadAdapterView();

               

                swipeRefreshLayout.setRefreshing(false);

            }
        };


        Handler h=new Handler();
        h.postDelayed(a,1000);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetShopProduct();
                //wait till database load the data for 2 sec
                Runnable  a=new Runnable() {
                    @Override
                    public void run() {
                      
                        RealoadAdapterView();
                        swipeRefreshLayout.setRefreshing(false);

                    }
                };


                Handler h=new Handler();
                h.postDelayed(a,1000);
            }
        });



        //list item click listner
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent Go_to_item_page=new Intent(StoreVistByCustomer.this,ItemPageForCustomer.class);
                Go_to_item_page.putExtra("itemID",itemID.get(position));
                Go_to_item_page.putExtra("categoryID",inventory_category.get(position));
                startActivity(Go_to_item_page);

            }
        });
    }

    private void RealoadAdapterView() {
        if(itemNameList.size()==0){
            textView.setVisibility(View.VISIBLE);
            textView.setText("No Result found in "+categoryID+" ");
        }
        ItemByStoreListForCustomerAdapter adapter=new ItemByStoreListForCustomerAdapter(this, itemNameList,itemPriseList,inventory_category,itemImage);
        list.setAdapter(adapter);
    }
//get list of product from database
    private void GetShopProduct() {
itemNameList.clear();
itemPriseList.clear();
inventory_category.clear();
itemID.clear();
        Amplify.DataStore.query(
                Product.class,

                items -> {
                    while (items.hasNext()) {
                        Product item = items.next();

                        if(ShopID.equals(item.getShopid()) && categoryID.equals(item.getCategoryid())){
                            itemNameList.add(item.getName());
                            itemPriseList.add(item.getPrise());
                            inventory_category.add(item.getCategoryid());
                            itemImage.add(item.getProductimg());
                            itemID.add(item.getId());
                            Log.i("Amplify user of theis account is ", "Product name is  "+  itemNameList.get(0));
                        }

                    }

                },

                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );

    }
 

}