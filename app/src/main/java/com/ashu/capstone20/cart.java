package com.ashu.capstone20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Cart;
import com.amplifyframework.datastore.generated.model.Customer;
import com.amplifyframework.datastore.generated.model.Order;
import com.amplifyframework.datastore.generated.model.Product;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;

public class cart extends AppCompatActivity {
    ListView list;
    Button btn_checkout;
    TextView text_quaantity,text_ammount;
    ArrayList <String> product_title=new ArrayList<>();
    ArrayList <Double> product_quantity=new ArrayList<>();
    ArrayList <Double> item_quantity=new ArrayList<>();
    ArrayList<String> ProductID=new ArrayList<>();
    String date;
    Dictionary details = new Hashtable();
    Dictionary ProductPrise = new Hashtable();
    Dictionary Product_name = new Hashtable();
    Dictionary Product_Order_Seller = new Hashtable();
    Dictionary Product__Order_date = new Hashtable();
    Dictionary Product_Order_ammount = new Hashtable();
    ArrayList<String > Cart_ProduCt_ID=new ArrayList<>();


    double total_amount=0;
    String User,UserID,
            Product_Order_Pincode,
            Product_Order_Contact,
            Product_Order_Name,
            Product_Order_Shopping_Address;
    ArrayList<String> category=new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        UserID=getIntent().getStringExtra("UserID");
        list=findViewById(R.id.list);
        btn_checkout=findViewById(R.id.checkout_button);
        text_quaantity=findViewById(R.id.item_Quantity_textview);
        text_ammount=findViewById(R.id.ammount_text);

      //  swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout_cart_view);
// get details form database

     //  swipeRefreshLayout.setRefreshing(true);

       //
       // Toast.makeText(getApplicationContext(),UserID,Toast.LENGTH_LONG).show();
       //

        getCartDetails(UserID);


        Runnable a5=new Runnable() {
            @Override
            public void run() {
                if(Cart_ProduCt_ID.size()<=0){

                    Toast.makeText(getApplicationContext(),"Cart is Empty",Toast.LENGTH_LONG).show();
                        btn_checkout.setVisibility(View.INVISIBLE);
                }else {

                    Runnable a2=new Runnable() {
                        @Override
                        public void run() {
                            getProductDetails(ProductID);

                        }
                    };
                    Handler h1=new Handler();
                    h1.postDelayed(a2,1000);

                    Runnable a3=new Runnable() {
                        @Override
                        public void run() {
                            //    Toast.makeText(getApplicationContext(),"Total "+item_quantity.get(0)+ " "+ product_title.get(0),Toast.LENGTH_SHORT).show();
                            RealoadAdapter();
                            getTotalAmount();


                            text_quaantity.setText(""+ProductID.size());
                            text_ammount.setText(""+total_amount);
                            //    swipeRefreshLayout.setRefreshing(false);
                        }
                    };
                    Handler h3=new Handler();
                    h3.postDelayed(a3,3000);



                }

            }
        };
        Handler h5=new Handler();
        h5.postDelayed(a5,1000);


        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTotalAmount();

                   // Toast.makeText(getApplicationContext()," "+product_quantity.get(0)+ " real uant " + ProductID.get(0)+" "+ProductPrise.get(0)+ " name "+product_title.get(0),Toast.LENGTH_LONG).show();

                    // get details of customer for order
                FindUserID();
                    //get order time
                 date=getDateTime();

                Runnable order_thred=new Runnable() {
                    @Override
                    public void run() {
                        PocessdToOrder(ProductID);
                        DeleteProductInCart();
                        ModifySellerDatabase();
                        Toast.makeText(getApplicationContext(),"Total "+product_title.get(0)+" PAY: "+total_amount,Toast.LENGTH_SHORT).show();
                    }
                };
                //process to order the products
                Handler orderdelay=new Handler();
                orderdelay.postDelayed(order_thred,2000);
               text_ammount.setText(""+total_amount);

            }
        });

    }

    private void DeleteProductInCart() {

        for(int i=0;i<Cart_ProduCt_ID.size();i++){

            Cart toDeleteItem=Cart.justId( Cart_ProduCt_ID.get(i));

            Amplify.DataStore.delete(toDeleteItem,
                    deleted -> Log.i("Amplify", "Deleted item."),
                    failure -> Log.e("Amplify", "Delete failed.", failure)
            );
        }



    }

    private void ModifySellerDatabase() {


    }

    private void PocessdToOrder(ArrayList<String> productID) {
        for(int i=0;i<productID.size();i++) {

            int finalI = i;
            Order item = Order.builder()
                    .productid(ProductID.get(finalI))
                    .sellerid(String.valueOf(Product_Order_Seller.get(ProductID.get(finalI))))
                    .customerid(UserID)
                    .productstatus("Active")
                    .shippingaddress(Product_Order_Shopping_Address)
                    .zipcode(Product_Order_Pincode)
                    .orderammount((Double) Product_Order_ammount.get(ProductID.get(finalI)))
                    .ordername(Product_Order_Name)
                    .ordermobilenumber(Product_Order_Contact)
                    .orderdate(date)
                    .orderquantity((Double) details.get(ProductID.get(finalI)))
                    .productname((String) Product_name.get(ProductID.get(finalI)))
                    .deliverydate("")
                    .build();
            Amplify.DataStore.save(
                    item,
                    success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
                    error -> Log.e("Amplify", "Could not save item to DataStore", error)
            );
        }
    }
    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    private void getTotalAmount() {
        total_amount=0;
        for(int i=0;i<ProductID.size();i++){
            Product_Order_ammount.put(ProductID.get(i),(Double.parseDouble((String) ProductPrise.get(ProductID.get(i)))*((Double)details.get(ProductID.get(i)))));
            total_amount=total_amount+(Double.parseDouble((String) ProductPrise.get(ProductID.get(i)))*((Double)details.get(ProductID.get(i))));

        }

    }

    private void getProductDetails(ArrayList<String> productid) {
        product_title.clear();

        category.clear();
        for(int i=0;i<productid.size();i++){

            int finalI = i;
            Amplify.DataStore.query(
                    Product.class,
                    items -> {
                        while (items.hasNext()) {
                            Product item = items.next();
                            if(productid.get(finalI).equals(item.getId())){
                                product_title.add(item.getName());
                                ProductPrise.put(item.getId(),item.getPrise());
                                category.add(item.getCategoryid());
                                details.put(item.getId(),item_quantity.get(finalI));
                                Product_name.put(item.getId(),item.getName());
                                Product_Order_Seller.put(item.getId(),item.getSellershopid());

                                Log.i("Amplify has product with an prise of", "   "+item.getName()+"|" + item.getPrise()+"|" +item_quantity.get(finalI));
                            }



                        }
                    },
                    failure -> Log.e("Amplify", "Could not query DataStore", failure)
            );
        }


    }

    private void RealoadAdapter(){

        CartListViewAdapter  adapter=new CartListViewAdapter(this,product_title,Product_name,product_quantity,ProductPrise,category,ProductID);
        list.setAdapter(adapter);

    }
    private void getCartDetails(String userID){
        product_quantity.clear();
        ProductID.clear();

        Amplify.DataStore.query(
                Cart.class,
                items -> {
                    while (items.hasNext()) {
                        Cart item = items.next();
                        if(userID.equals(item.getCustomerid()))
                        {
                            Cart_ProduCt_ID.add(item.getId());
                            product_quantity.add(item.getQuantity());
                            item_quantity.add(item.getQuantity());
                            ProductID.add(item.getProductid());

                            Log.i("Amplify has product with an prise of", "   "+item.getProductname()+"|" + item.getQuantity()+ "|"+item.getProductid());
                        }

                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );


    }
    private void FindUserID(){
// get the value stored in shared pref while login
//        User="";
//        SharedPreferences prefs = getSharedPreferences("capstone20", MODE_PRIVATE);
//        User = prefs.getString("CustomerID", "none");//"No name defined

        Amplify.DataStore.query(
                Customer.class,
                items -> {
                    while (items.hasNext()) {
                        Customer item = items.next();
                        if(UserID.equals(item.getId())){

                            Product_Order_Contact=item.getUsername();
                            Product_Order_Name=item.getName();
                            Product_Order_Pincode=item.getPin();
                            Product_Order_Shopping_Address=item.getAddress();
                        }
                        Log.i("Amplify", "Id " + item.getId());
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );
    }
}