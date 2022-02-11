package com.ashu.capstone20;

import androidx.appcompat.app.AppCompatActivity;

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
import com.amplifyframework.datastore.generated.model.Customer;
import com.amplifyframework.datastore.generated.model.Seller;

public class CustomerRegistration extends AppCompatActivity {
    Button btn_continue ;
    String country[]={"+91"};
    EditText text_name_Customer,text_number_Customer,text_mail_Customer,text_password_Customer,text_address_Customer,text_pincode__customer;
    String username;
    String user_mode="";
    EditText text_name_seller,text_number_seller,text_mail_seller,text_password_seller,text_address_seller,text_pincode_seller,text_store_name;
    boolean value_seter=true;
    boolean value_form_presentUser=false;
    static Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);
        Spinner spin = (Spinner) findViewById(R.id.spinner);


        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
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
                else{


                  //check user have an account allready
                PresentUser();

                        Runnable a=new Runnable() {
                            @Override
                            public void run() {

                                if(!value_seter){
                                    text_number_seller.setError("user of this phone number is already present");
//                                    Toast.makeText(getApplicationContext(),"alreadt preasent",Toast.LENGTH_SHORT).show();

                                }

                                else{
//                                    Toast.makeText(getApplicationContext()," Missing",Toast.LENGTH_SHORT).show();

                                    sendDataToVerify();

                                }
                            }
                        };
                    Handler h=new Handler();
                    h.postDelayed(a,2000);




                }
            }
        });
    }


    //check already registerd user
   private void  PresentUser(){
    value_seter=true;
            Amplify.DataStore.query(
                    Customer.class,
                    items -> {
                        while (items.hasNext()) {
                            Customer item = items.next();
                            Log.i("Amplify", "Id " + item.getId());
                            if(item.getUsername().equals(username))
                            {
                                value_seter=false;
                                break;
                            }
                        }
                    },
                    failure -> Log.e("Amplify", "Could not query DataStore", failure)
            );

        // Toast.makeText(getApplicationContext(),""+name_tempo,Toast.LENGTH_SHORT).show();

    }

    private void sendDataToVerify(){
         username="+91"+text_number_seller.getText().toString();
        String name=text_name_seller.getText().toString();
        String address=text_address_seller.getText().toString();
        String pincode=text_pincode_seller.getText().toString();
        String mail=text_mail_seller.getText().toString();
        String password=text_password_seller.getText().toString();

        // vreating intent and sending data to verficvation page
        Intent Go_to_Verify=new Intent(CustomerRegistration.this,Verify.class);
        Bundle bundle=new Bundle();
        bundle.putString("username",username);
        bundle.putString("name",name);
        bundle.putString("address",address);
        bundle.putString("pincode",pincode);
        bundle.putString("password",password);
        bundle.putString("mail",mail);
        bundle.putString("usermode",user_mode);
        Go_to_Verify.putExtras(bundle);
        Go_to_Verify.putExtra("activity","Customer_activity");
        startActivity(Go_to_Verify);

    }


}