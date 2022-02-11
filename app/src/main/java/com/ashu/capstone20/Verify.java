package com.ashu.capstone20;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Customer;
import com.amplifyframework.datastore.generated.model.Seller;
import com.amplifyframework.datastore.generated.model.Shop;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class Verify extends AppCompatActivity {
   Button btn_create_account;
   EditText Otp_bar;
    int random;
    String messege="";
    String username,password,name,pincode,address,storeName,mail;
    Bundle Data_bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        String acticity=getIntent().getStringExtra("activity");
       
        //DATA RECIVED FROM SELLER REGISTRATION PAGE
        if(acticity.equals("seller_activity")){
          //  Toast.makeText(getApplicationContext(),"it is seller",Toast.LENGTH_SHORT).show();
            Data_bundle=getIntent().getExtras();
            username=Data_bundle.getString("username");
            password=Data_bundle.getString("password");
            name=Data_bundle.getString("name");
            pincode=Data_bundle.getString("pincode");
            address=Data_bundle.getString("address");
            mail=Data_bundle.getString("mail");
            storeName=Data_bundle.getString("storeName");
        }//DATA FROM CUSTOMRE REGISTRARTION PAGE
        else{
            Data_bundle=getIntent().getExtras();
      //      Toast.makeText(getApplicationContext(),"it is customer",Toast.LENGTH_SHORT).show();
            username=Data_bundle.getString("username");
            password=Data_bundle.getString("password");
            name=Data_bundle.getString("name");
            pincode=Data_bundle.getString("pincode");
            address=Data_bundle.getString("address");
            mail=Data_bundle.getString("mail");
        }

        Otp_bar=findViewById(R.id.otp);
        btn_create_account=findViewById(R.id.createaccount);
        sendOtp();
        btn_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //generating OTP
                String otp_number=Otp_bar.getText().toString();
                int int_Otp=Integer.parseInt(otp_number);


        //verify OTP
                if(random==int_Otp){
                    if(acticity.equals("seller_activity")) {
                        CreateAccountForSeller();//store data into database
                        ShopeDatabaseEntry();

                    }else{
                        CreateAccountForCustomer();//for customer database information storing
                    }

                  //  Toast.makeText(getApplicationContext(),"verifyed",Toast.LENGTH_SHORT).show();
                    Intent Go_to_Login_page =new Intent(Verify.this,MainActivity.class);
                    startActivity(Go_to_Login_page);
                    finish();



                }
                else{
                    Toast.makeText(getApplicationContext(),"Enter valid OTP sent to your phone",Toast.LENGTH_SHORT).show();


                }


            }
        });
    }

    private void ShopeDatabaseEntry() {
        Shop item = Shop.builder()
                .username(username)
                .shoppin(pincode)
                .shopname(storeName)
                .address(address)
                .build();
        Amplify.DataStore.save(
                item,
                success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
                error -> Log.e("Amplify", "Could not save item to DataStore", error)
        );
    }

    void sendOtp(){
        random = new Random().nextInt(9000) + 1000;
        //opt message
        messege=""+" Dear "+username.substring(3)+", Your OTP for creating account  is "+random+" Use this code to complete your Verfication. Thank you";
      SmsManager smsManager=SmsManager.getDefault();
       smsManager.sendTextMessage(username.substring(3),null,messege,null,null);

    Toast.makeText(getApplicationContext(),""+messege,Toast.LENGTH_SHORT).show();
    }

    //aws data store for seller
    private void CreateAccountForSeller(){
        Seller item = Seller.builder()
                .username(username)
                .name(name)
                .mail(mail)
                .password(password)
                .build();
        Amplify.DataStore.save(
                item,
                success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
                error -> Log.e("Amplify", "Could not save item to DataStore", error)
        );
    }
    //aws data store for customer
    private  void CreateAccountForCustomer(){


        Customer item = Customer.builder()
                .username(username)
                .name(name)
                .address(address)
                .pin(pincode)
                .mail(mail)
                .password(password)
                .build();
        Amplify.DataStore.save(
                item,
                success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
                error -> Log.e("Amplify", "Could not save item to DataStore", error)
        );

    }





}
