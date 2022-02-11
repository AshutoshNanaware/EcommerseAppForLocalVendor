package com.ashu.capstone20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.generated.model.Cart;
import com.amplifyframework.datastore.generated.model.Customer;
import com.amplifyframework.datastore.generated.model.Inventory;
import com.amplifyframework.datastore.generated.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemPageForCustomer extends AppCompatActivity {
    String productImageUrl;
    String itemID;
    String categoryID;
    TextView itemName,itemPrise,itemQuantity;
    ImageView itemImage,increase,decrease;
    Button addToCart;
    String SellerID="";
    EditText InputQuantity;
    MultiAutoCompleteTextView itemDetails;
    SwipeRefreshLayout swipeRefreshLayout;
    String name,prise,quantity;
    double Item_Quantity_for_Cart=0;
    String User;
    Spinner spinner;
    String UserID;
    boolean InCart=false;
    boolean IsAvalible=false;
    Double item_quantity;
    String InventoryID;
    String detils;
    Double InventoryUpdatedQuantity;
    ArrayList<Integer> FootwareSize =new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_page_for_customer);
        categoryID=getIntent().getStringExtra("categoryID");
        itemID    =getIntent().getStringExtra("itemID");
        itemName=findViewById(R.id.textVewItemName);
        itemPrise=findViewById(R.id.textViewPrise);
        itemDetails=findViewById(R.id.detail_area);

        itemQuantity=findViewById(R.id.textViewQuantity);
        InputQuantity=findViewById(R.id.inputQuantity);
        itemImage=findViewById(R.id.ItemImage);
        //setting default input quantity
        InputQuantity.setText("0.0");
        addToCart=findViewById(R.id.AddToCart);
        decrease=findViewById(R.id.decrease);
        increase=findViewById(R.id.increase);
        spinner=findViewById(R.id.spinner_Footware_size);
        if(!categoryID.equals("Footware")){
            spinner.setVisibility(View.INVISIBLE);
        }else{
            //conditional rendering for footware category
            // get the size and create the size spinner for footware
            GetFootwareSize();
            CreateSpinner();
        }
        // change the input quantity field to whole number insted of decimal number if it is not grocery product
        if(!categoryID.equals("Grocery")){
            InputQuantity.setInputType(InputType.TYPE_CLASS_NUMBER);
        }


        //get user details
        FindUserID();
        GetItemDetails();
        Runnable a2=new Runnable() {
            @Override
            public void run() {

                // get product details

            }
        };
      Handler h2=new Handler();

      h2.postDelayed(a2,1000);
        Runnable a=new Runnable() {
            @Override
            public void run() {
             itemName.setText(name);
             itemPrise.setText(prise);
             itemQuantity.setText(""+item_quantity);
                itemDetails.setText(""+detils);

                Picasso.get().load(""+productImageUrl).into(itemImage);
            }
        };

        Handler h=new Handler();
        h.postDelayed(a,1000);


        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item_Quantity_for_Cart=Double.parseDouble((InputQuantity.getText().toString()));
                InventoryUpdatedQuantity=item_quantity-Item_Quantity_for_Cart;
                if(Item_Quantity_for_Cart<=0){
                    Toast.makeText(getApplicationContext(),"quantity can't 0",Toast.LENGTH_SHORT).show();
                }else{
                    addToCartListner();
                }


            }
        });


        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseListner();
            }
        });
        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseListner();
            }
        });

    }
    private void decreaseListner(){
        Item_Quantity_for_Cart=Double.parseDouble((InputQuantity.getText().toString()));
        if(!(Item_Quantity_for_Cart==0) ){

            Item_Quantity_for_Cart= Item_Quantity_for_Cart-1;
        }
        InputQuantity.setText(""+Item_Quantity_for_Cart);

    }
    private void increaseListner(){
        Item_Quantity_for_Cart=Double.parseDouble((InputQuantity.getText().toString()));
        if(Item_Quantity_for_Cart< item_quantity){

            Item_Quantity_for_Cart= Item_Quantity_for_Cart+1;
        }
        InputQuantity.setText(""+Item_Quantity_for_Cart);



    }
    private void addToCartListner(){


        // check item is in cart or not
        InCart =false;
        IsAvalible=false;
        CheckIsItemIsInCart();
        CheckItemQuantityInInventory();
        // if item is not in cart then add to cart
        Runnable a1=new Runnable() {
            @Override
            public void run() {

                if(!InCart && IsAvalible){

                    AddItemTOCart();
                    UpdateInventory();

                }else{
                    Toast.makeText(getApplicationContext(),"Item is in cart",Toast.LENGTH_SHORT).show();

                }

            }
        };
        Handler cartDealy=new Handler();
        cartDealy.postDelayed(a1,2000);
    }

    private void CheckItemQuantityInInventory() {
        Amplify.DataStore.query(
                Inventory.class,
                items -> {
                    while (items.hasNext()) {
                        Inventory item = items.next();
                        if(InventoryID.equals(item.getId()) && item_quantity<=item.getQuantity()){
                            IsAvalible=true;
                        }
                        Log.i("Amplify", "Id " + item.getId());
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );
    }

    private void UpdateInventory() {
        Amplify.DataStore.query(Inventory.class, Where.id(InventoryID),
                matches -> {
                    if (matches.hasNext()) {
                        Inventory original = matches.next();
                        Inventory edited = original.copyOfBuilder()
                                .quantity(InventoryUpdatedQuantity)
                                .build();
                        Amplify.DataStore.save(edited,
                                updated -> Log.i("MyAmplifyApp", "Updated a post."),
                                failure -> Log.e("MyAmplifyApp", "Update failed.", failure)
                        );
                    }
                },
                failure -> Log.e("MyAmplifyApp", "Query failed.", failure)
        );
    }

    private void CheckIsItemIsInCart() {
        Amplify.DataStore.query(
                Cart.class,
                items -> {
                    while (items.hasNext()) {
                        Cart item = items.next();
                        if(itemID.equals(item.getProductid()) && UserID.equals(item.getCustomerid()) ){
                            InCart=true;

                        }
                        Log.i("Amplify", "Id " + item.getId());
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );
    }

    private void AddItemTOCart() {
        Cart item = Cart.builder()
                .productid(itemID)
                .customerid(UserID)
                .sellerid(SellerID)
                .quantity(Item_Quantity_for_Cart)
                .productname(name)
                .build();
        Amplify.DataStore.save(
                item,
                success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
                error -> Log.e("Amplify", "Could not save item to DataStore", error)
        );
    }

    private void CreateSpinner() {

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,FootwareSize);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);
    }

    private void GetFootwareSize() {

        FootwareSize.add(6);
        FootwareSize.add(7);
        FootwareSize.add(8);
        FootwareSize.add(9);
        FootwareSize.add(10);


    }

    private void GetItemDetails() {
        Amplify.DataStore.query(
                Product.class,
                items -> {
                    while (items.hasNext()) {
                        Product item = items.next();
                        if(itemID.equals(item.getId())){
                            name=item.getName();
                            quantity=item.getQuantity();
                            prise=item.getPrise();
                            SellerID=item.getSellershopid();
                            productImageUrl =item.getProductimg();
                            detils=item.getDetails();
                            Log.i("Amplify item is present with name  ", "  " + item.getName());
                        }

                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );


        //get inventory Details

        Amplify.DataStore.query(
                Inventory.class,
                items -> {
                    while (items.hasNext()) {
                        Inventory item = items.next();
                        if(itemID.equals(item.getProductid())){
                                    InventoryID=item.getId();
                                    item_quantity=item.getQuantity();
                        }
                        Log.i("Amplify", "Id " + item.getId());
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );
    }

    private void FindUserID(){
// get the value stored in shared pref while login
        User="";
        SharedPreferences prefs = getSharedPreferences("capstone20", MODE_PRIVATE);
        User = prefs.getString("CustomerID", "none");//"No name defined

        Amplify.DataStore.query(
                Customer.class,
                items -> {
                    while (items.hasNext()) {
                        Customer item = items.next();
                        if(User.equals(item.getUsername())){
                            UserID=item.getId();
                        }
                        Log.i("Amplify", "Id " + item.getId());
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );
    }

}