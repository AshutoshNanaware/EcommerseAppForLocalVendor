package com.ashu.capstone20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Seller;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class Register extends AppCompatActivity {
    Button btn_continue ;
    String country[]={"+91"};
    String user_mode="";
    EditText text_name_seller,text_number_seller,text_mail_seller,text_password_seller,text_address_seller,text_pincode_seller,text_store_name;
    String name_tempo="";
    boolean value_seter;
    String username;
    boolean value_form_presentUser=false;
    static Thread thread;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Bundle bundle=getIntent().getExtras();
        user_mode=bundle.getString("usermode");
        Toast.makeText(getApplicationContext(),user_mode,Toast.LENGTH_SHORT).show();
        Spinner spin = (Spinner) findViewById(R.id.spinner);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        //sheredpreferance for starting login activty

        // getting Id

        btn_continue=findViewById(R.id.button2);
        text_name_seller=findViewById(R.id.customer_name);
        text_number_seller=findViewById(R.id.Customer_phone_number);
        text_password_seller=findViewById(R.id.Customer_password);
        text_mail_seller=findViewById(R.id.mail);
        text_address_seller=findViewById(R.id.address);
        text_pincode_seller=findViewById(R.id.pincode);
        text_store_name=findViewById(R.id.store_name);
        // contuine to verfy proccess
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 username="+91"+text_number_seller.getText().toString();

                String password=text_password_seller.getText().toString();
                int pass_count=password.length();
                if(text_name_seller.getText().toString().equals("")){
                    text_name_seller.setError("can't be empty");
                }
                else if(text_number_seller.getText().toString().equals("")){
                    text_number_seller.setError("can't be empty");
                }
                else if(text_password_seller.getText().toString().equals("")){
                    text_password_seller.setError("can't be empty");
                }
                else if(pass_count<6){
                    text_password_seller.setError("At least 6 character");
                }
                else if(text_address_seller.getText().toString().equals("")){
                    text_address_seller.setError("can't be empty");
                }
                else if(text_pincode_seller.getText().toString().equals("")){
                    text_pincode_seller.setError("can't be empty");
                }
                else if(text_store_name.getText().toString().equals("")){
                    text_store_name.setError("can't be empty");
                }

                else {

                    PresentUser();
//                    Runnable r2 = new Runnable() {
//                        @Override
//                        public void run(){
//
//                        }
//                    };
//
//                    Handler h2 = new Handler();
//                    h2.postDelayed(r2, 2000); // <-- the "1000" is the delay time in miliseconds.
//


                    Runnable r = new Runnable() {
                        @Override
                        public void run(){
                         PushDataToVeryfyPage();
                        }
                    };

                    Handler h = new Handler();
                    h.postDelayed(r, 2000); // <-- the "1000" is the delay time in miliseconds.






                }
            }
        });
    }

    private void PushDataToVeryfyPage() {

       String username="+91"+text_number_seller.getText().toString();
        if(!value_seter){
            text_number_seller.setError("user of this phone number is already present");
            Toast.makeText(getApplicationContext(),"alreadt preasent",Toast.LENGTH_SHORT).show();

        }
        else{
            //Toast.makeText(getApplicationContext(),"Missing",Toast.LENGTH_SHORT).show();

            username="+91"+text_number_seller.getText().toString();
            String name=text_name_seller.getText().toString();
            String address=text_address_seller.getText().toString();
            String pincode=text_pincode_seller.getText().toString();
            String storeName=text_store_name.getText().toString();
            String mail=text_mail_seller.getText().toString();
            String password=text_password_seller.getText().toString();
            // vreating intent and sending data to verficvation page
            Intent Go_to_Verify=new Intent(Register.this,Verify.class);
            Bundle bundle=new Bundle();
            bundle.putString("username",username);
            bundle.putString("name",name);
            bundle.putString("address",address);
            bundle.putString("pincode",pincode);
            bundle.putString("password",password);
            bundle.putString("storeName",storeName);
            bundle.putString("mail",mail);
            bundle.putString("usermode",user_mode);
            Go_to_Verify.putExtras(bundle);
            Go_to_Verify.putExtra("activity","seller_activity");
            startActivity(Go_to_Verify);
        }
    }


    //check already registerd user
private void PresentUser(){
value_seter=true;

    Amplify.DataStore.query(
            Seller.class,
            items -> {
                while (items.hasNext()) {
                    Seller item = items.next();
                    Log.i("Amplify", "Id " + item.getId());
                    if(item.getUsername().equals(username))
                    {
                        value_seter=false;
                        Log.i("Amplify", "Id " +value_seter+item.getUsername());
                        break;


                    }

                }
            },
            failure -> Log.e("Amplify", "Could not query DataStore"+value_seter, failure)
    );


}


}