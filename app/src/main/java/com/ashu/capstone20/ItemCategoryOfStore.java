package com.ashu.capstone20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.ProductCategory;
import com.amplifyframework.datastore.generated.model.Shop;

import java.util.ArrayList;

public class ItemCategoryOfStore extends AppCompatActivity {


    String ShopID;
    ListView list;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView textView_shopName,textView_contactOfShop,textView_address;
    String ShopName;
    ImageView imageView;
    String Address,Pin;
    ArrayList<String> tutorials=new ArrayList<>();

    ArrayList<String> catagory=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_category_of_store);

        ShopID=getIntent().getStringExtra("ShopID");
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout_for__customer_store_item);
        list=findViewById(R.id.list_customer_product_by_store);
        swipeRefreshLayout.setRefreshing(true);
        textView_contactOfShop=findViewById(R.id.textView_ContactOfShop);
        textView_shopName=findViewById(R.id.textView6);
        imageView=findViewById(R.id.imageView);
        textView_address=findViewById(R.id.textView_AddressOfShop2);
        // invisable till data is loaded into listview else only these view will be visable
        imageView.setVisibility(View.INVISIBLE);
        textView_shopName.setVisibility(View.INVISIBLE);
        textView_contactOfShop.setVisibility(View.INVISIBLE);
        textView_address.setVisibility(View.INVISIBLE);

    list.setBackgroundResource(R.drawable.custome_shape);
        tutorials.add("1");
        tutorials.add("1");
        tutorials.add("1");
        tutorials.add("1");
        tutorials.add("1");
        tutorials.add("1");
        GetShopName();
        GetCategory();
        Runnable  a=new Runnable() {  //wait till database load the data for 2 sec
            @Override
            public void run() {
                textView_shopName.setText(ShopName);
                textView_contactOfShop.setText("Contact :"+ ShopID);
                textView_address.setText("Address :" +Address+", Pin :"+Pin);
                RealoadAdapterView();

                //making other view visable which we invisabled intilly
                imageView.setVisibility(View.VISIBLE);
                textView_shopName.setVisibility(View.VISIBLE);
                textView_contactOfShop.setVisibility(View.VISIBLE);
                textView_address.setVisibility(View.VISIBLE);

                swipeRefreshLayout.setRefreshing(false);

            }
        };


        Handler h=new Handler();
        h.postDelayed(a,1000);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent go_to_Caategory_item=new Intent(ItemCategoryOfStore.this,StoreVistByCustomer.class);
                go_to_Caategory_item.putExtra("ShopID",ShopID);
                go_to_Caategory_item.putExtra("categoryID",catagory.get(position));
                startActivity(go_to_Caategory_item);


            }
        });


    }


    private void RealoadAdapterView() {

        ArrayAdapter<String> arr;
        arr
                = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                catagory);
        list.setAdapter(arr);

    }
    private  void GetShopName(){
        Amplify.DataStore.query(

                Shop.class,
                items ->{
                    while (items.hasNext()){
                        Shop item=items.next();
                        if(ShopID.equals(item.getUsername())){
                            Address=item.getAddress();
                            Pin=item.getShoppin();
                            ShopName=item.getShopname();
                            break;
                        }

                    }


                },

                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );


    }

    private void GetCategory() {

        Amplify.DataStore.query(
                ProductCategory.class,
                items -> {
                    while (items.hasNext()) {
                        ProductCategory item = items.next();
                        Log.i("Amplify", "Id " + item.getProductId());
                        catagory.add(item.getProductId());

                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );
    }
}