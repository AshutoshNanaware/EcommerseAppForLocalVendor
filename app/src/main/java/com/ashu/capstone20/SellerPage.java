package com.ashu.capstone20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.Navigation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.AndroidException;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.generated.model.ProductCategory;
import com.amplifyframework.datastore.generated.model.Shop;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class SellerPage extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    GridView gridView;
    TextView textView;
    String UserID;
    NavigationView navigationView;
    String prevStarted = "yes";
    String ShopID;
    TextView headerName;

    ArrayList<String> catagory=new ArrayList<String>();
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_page);

        GetCategory();
        //get user info
        FindUserID();
        GetSellerID();
        // navigation drower code
        NaigationSetter();
        GridViewSetter();

        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);

        navigationView=(NavigationView)findViewById(R.id.nav_view) ;


     //   Toast.makeText(getApplicationContext(),""+UserID,Toast.LENGTH_LONG).show();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               int ID=(int) item.getItemId();
              if(ID==R.id.nav_logout){
                  logoutFun();
              }
              else if(ID==R.id.nav_account){
                  //
                  Intent Go_to_update=new Intent(SellerPage.this,UpdateProfile.class);
                  Go_to_update.putExtra("ShopID",ShopID);
                  startActivity( Go_to_update);

              }else if(ID==R.id.nav_settings){

                  // TEMP ACTIVITY
               Toast.makeText(getApplicationContext(),"Setting",Toast.LENGTH_LONG).show();
                  //
              }else if(ID==R.id.nav_orders){
                  //order page
                  Intent Go_to_Seller_Order=new Intent(SellerPage.this,SellerOrders.class);
                    Go_to_Seller_Order.putExtra("ShopID",ShopID);
                  startActivity(Go_to_Seller_Order);


              }
              else if(ID==R.id.nav_Add_item){
                  GetCategory();
                    Intent Go_to_add_item=new Intent(SellerPage.this,AddItem.class);

                    startActivity(Go_to_add_item);
              }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });







    }

    private void GetSellerID() {

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


    @Override
    public void onBackPressed() {
        // super.onBackPressed();
     //   Toast.makeText(getApplicationContext(),"There is no back action",Toast.LENGTH_LONG).show();
        finishAffinity();
        finish();
        return;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
          //  Toast.makeText(getApplicationContext(),"There is no back action",Toast.LENGTH_LONG).show();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

 private void logoutFun(){
     SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
     SharedPreferences.Editor editor = sharedpreferences.edit();
     editor.putBoolean(prevStarted, Boolean.FALSE);
     editor.putString("SellerID","");
     editor.apply();
     Intent Go_to_Login_page =new Intent(SellerPage.this,MainActivity.class);
     startActivity(Go_to_Login_page);
     finish();
 }

    private void FindUserID(){

        SharedPreferences prefs = getSharedPreferences("capstone20", MODE_PRIVATE);
     UserID = prefs.getString("SellerID", "none");//"No name defined
        Amplify.DataStore.query(
                Shop.class,
                Where.matches(Shop.USERNAME.eq(UserID)),
                items -> {
                    while (items.hasNext()) {

                        Shop item = items.next();
                        Log.i("Amplify", "Id ");
                        ShopID=item.getId();

                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );


    }

    private void NaigationSetter(){


        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    private void GridViewSetter(){
        gridView = (GridView)findViewById(R.id.gridView);
        textView = (TextView)findViewById(R.id.textView);
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,R.layout.grid_item,R.id.textView,catagory);

        gridView.setAdapter(arrayAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent GO_to_inventory=new Intent(SellerPage.this,ShowInventory.class);
                GO_to_inventory.putExtra("category",catagory.get(position));
                startActivity(GO_to_inventory);
//                Toast.makeText(getApplicationContext(),""+catagory.get(position)+id,Toast.LENGTH_SHORT).show();





            }
        });
    }



}