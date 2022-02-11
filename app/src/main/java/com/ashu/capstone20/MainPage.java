package com.ashu.capstone20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.generated.model.Customer;
import com.amplifyframework.datastore.generated.model.Product;
import com.amplifyframework.datastore.generated.model.ProductCategory;
import com.amplifyframework.datastore.generated.model.Seller;
import com.amplifyframework.datastore.generated.model.Shop;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import kotlin.reflect.KCallable;

public class MainPage extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    Button btn_logout, temp_btn;
    TextView view, textView;
    String prevStarted = "yes";
    String alldata = "";
    String value = "";
    Handler handler;
    NavigationView navigationView;
    GridView gridView;
    String User;
    String UserPin;
    ProgressDialog progressBar;
    String temp1;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<String> catagory =new ArrayList<>();
    ArrayList<String> Owner =new ArrayList<>();
    ArrayList<String> ShopID =new ArrayList<>();
    ArrayList<String> ShopnName =new ArrayList<>();
    String UserID;
    ArrayList<String> itemList =new ArrayList<>();
    ArrayList<String> itemPriseList =new ArrayList<>();

    String PinCode;
    ListView list;
    ListView list_category;
    String tmep3="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        //get current user id
        list = (ListView) findViewById(R.id.list_customer_product);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout_customer);
        navigationView=(NavigationView)findViewById(R.id.nav_view_customer) ;

        swipeRefreshLayout.setRefreshing(true);

        FindUserID();

        GetCategory();
        GetUserPin();
        //  CreateProgressBar();
        Runnable a2 = new Runnable() {
            @Override
            public void run(){
                GetShopId();
            }
        };

        Runnable a = new Runnable() {
            @Override
            public void run(){
                GetItemList();
            }
        };

        Handler h = new Handler();
        h.postDelayed(a, 1000); // <-- the "1000" is the delay time in miliseconds.
        h.postDelayed(a2,1000);

        RealoadDataIntoArrayAdapter();
        Runnable a3 = new Runnable() {
            @Override
            public void run(){

                //Toast.makeText(getApplicationContext()," "+itemList.get(0),Toast.LENGTH_SHORT).show();
                RealoadDataIntoArrayAdapter();
                swipeRefreshLayout.setRefreshing(false);


            }
        };
        Handler h2 = new Handler();
        h2.postDelayed(a3, 2000); // <-- the "1000" is the delay time in miliseconds.


       //listView Listner function
list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


       // Toast.makeText(getApplicationContext()," "+ShopID.get(position),Toast.LENGTH_SHORT).show();
        Intent go_to_store_item=new Intent(MainPage.this,ItemCategoryOfStore.class);

        go_to_store_item.putExtra("ShopID",ShopID.get(position));

        startActivity(go_to_store_item);
    }
});


        // navigation view id for customer page

        //navigation setting method
        NaigationSetter();
        //Action Listner for navigation button
        NavigationButtonActionListner();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                       GetUserPin();


                        GetShopId();

                        GetItemList();

//                Runnable a2 = new Runnable() {
//                    @Override
//                    public void run(){
//
//                    }
//                };
//
//                Handler h1 = new Handler();
//                h1.postDelayed(a2, 1000); // <-- the "1000" is the delay time in miliseconds.

                //      Toast.makeText(getApplicationContext()," "+itemList.get(0),Toast.LENGTH_SHORT).show();

                Runnable a = new Runnable() {
                    @Override
                    public void run(){
                        RealoadDataIntoArrayAdapter();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                };

                Handler h = new Handler();
                h.postDelayed(a, 1000); // <-- the "1000" is the delay time in miliseconds.

            }
        });

    }





    private void   GetItemList() {
itemList.clear();
itemPriseList.clear();
Amplify.DataStore.query(
                Product.class,

                items -> {
                    while (items.hasNext()) {
                        Product item = items.next();
                        temp1=item.getShopid();
                        if(ShopID.contains(temp1)){
                        itemList.add(item.getName());
                        itemPriseList.add(item.getPrise());
                                  Log.i("Amplify user of theis account is ", "Product name is  "+  itemList.get(0));
                        }

                    }

                },

                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );


    }
        public void onTextDetected(String result){


        }
    private void RealoadDataIntoArrayAdapter() {
        ItemListAdapter adapter=new ItemListAdapter(this,ShopnName,ShopID);
        list.setAdapter(adapter);

    }

    private void GetShopId() {

   ShopID.clear();
   Owner.clear();
   ShopnName.clear();
        Amplify.DataStore.query(
                Shop.class,
                Where.matches(Shop.SHOPPIN.eq(PinCode)),
                items -> {
                    while (items.hasNext()) {
                        Shop item = items.next();
                        Log.i("Amplify", "Id " + item.getId());
                        if(PinCode.equals(item.getShoppin())){
                            ShopID.add(item.getUsername());
                            Owner.add(item.getAddress());
                            ShopnName.add(item.getShopname());
                        }

                    }
                },
                failure -> Log.e("Amplify erroe in shop data loading Pincode="+PinCode, "Could not query DataStore", failure)
        );
    }



    private void GetUserPin() {

        Amplify.DataStore.query(
                Customer.class,
                Where.matches(Customer.USERNAME.eq(User)),
                items -> {
                    while (items.hasNext()) {
                        Customer item = items.next();
                       if(item.getUsername().equals(User)){
                           Log.i("Amplify", "Pincode is "+item.getPin() +" Username is "+item.getName()+User);
                           PinCode=item.getPin();
                           UserID=item.getId();
                       }                 }
                },
                failure -> Log.e("Amplify getting user pin", "Could not query DataStore", failure)

        );

    }

    private void FindUserID(){
// get the value stored in shared pref while login
        User="";
        SharedPreferences prefs = getSharedPreferences("capstone20", MODE_PRIVATE);
        User = prefs.getString("CustomerID", "none");//"No name defined


    }
    private void GetCategory() {

        Amplify.DataStore.query(
                ProductCategory.class,

                items -> {
                    while (items.hasNext()) {
                        ProductCategory item = items.next();

                        Log.i("Amplify", "Id " + item.getId());
                        catagory.add(item.getProductId());
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );
    }

    private void NavigationButtonActionListner() {



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int ID=(int) item.getItemId();
                if(ID==R.id.nav_logout_customer){
                   // calls logout function
                    logoutFun();
                }
                else if(ID==R.id.nav_account_customer){
                    Intent Go_to_update=new Intent(MainPage.this,UpdateProfile.class);
                    Go_to_update.putExtra("ShopID",UserID);
                    startActivity( Go_to_update);

                    //
                }else if(ID==R.id.nav_settings_customer){
                    //
                }else if(ID==R.id.nav_myorder_customer){
                    Intent go_to_order=new Intent(MainPage.this,MyOrder.class);
                    go_to_order.putExtra("UserID",UserID);
                    startActivity(go_to_order);

                    //
                }else if(ID==R.id.nav_cart_customer){
                        Intent go_to_cart=new Intent(MainPage.this,cart.class);
                        go_to_cart.putExtra("UserID",UserID);
                        startActivity(go_to_cart);
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

    }


    private void NaigationSetter(){


        drawerLayout = findViewById(R.id.customer_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open_customer, R.string.nav_close_customer);
        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
         super.onBackPressed();
        finishAffinity();
        finish();
        return;
    }
    private void logoutFun(){
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(prevStarted, Boolean.FALSE);
        editor.putString("CustomerID","");
        editor.apply();
        Intent Go_to_Login_page =new Intent(MainPage.this,MainActivity.class);
        startActivity(Go_to_Login_page);
        finish();
    }


}