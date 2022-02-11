package com.ashu.capstone20;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.generated.model.Customer;
import com.amplifyframework.datastore.generated.model.Shop;

public class UpdateProfile extends AppCompatActivity {
    EditText ed_name,ed_address,ed_pincode;
    String ShopID;
    String nameText,addressText,pincodeText;
    String Updated_nameText, Updated_addressText, Updated_pincodeText;
    Button btn_update;
    ProgressDialog progressDialog;
    SharedPreferences prefs;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        
         prefs = getSharedPreferences("capstone20", MODE_PRIVATE);
        name = prefs.getString("name", "No");//"No name defined
     
        
        ShopID=getIntent().getStringExtra("ShopID");
        
        btn_update =findViewById(R.id.updateInformation);
        ed_name=findViewById(R.id.storeName);
        ed_address=findViewById(R.id.storeAddress);
        ed_pincode=findViewById(R.id.storePincode);
        Toast.makeText(getApplicationContext(),""+ShopID,Toast.LENGTH_SHORT).show();
        if(name.equals("seller")){
            getInfo();
        }else{
            getInfoCustomer();
        }
        
    Runnable a=new Runnable() {
        @Override
        public void run() {
    
           ed_name.setText(""+nameText);
           ed_address.setText(""+addressText);
           ed_pincode.setText(""+pincodeText);

        }
    };

        Handler h = new Handler();
        h.postDelayed(a, 1000); // <-- the "1000" is the delay time in miliseconds

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ed_name.getText().toString().equals("")){
                    ed_name.setError("Can't be empty");
                }else if(ed_address.getText().toString().equals("")){
                    ed_address.setError("Can't be empty");
                }else if(ed_pincode.getText().toString().equals("")){
                    ed_pincode.setError("Can't be empty");

                }   else if(!(ed_pincode.getText().toString().length()==6) ){
                    ed_pincode.setError("Invalid pincode need 6 digit");
                }
                else{ Updated_addressText=ed_address.getText().toString();
                    Updated_pincodeText=ed_pincode.getText().toString();
                    Updated_nameText=ed_name.getText().toString();

                    Runnable a2=new Runnable() {
                        @Override
                        public void run() {
                            if(name.equals("seller")){
                                updateInfo();
                            }else{
                                updateInfoCustomer();
                            }
                          
                            ed_pincode.setText("");
                            ed_address.setText("");
                            ed_name.setText("");
                            Toast.makeText(getApplicationContext(),"Information Updated",Toast.LENGTH_LONG).show();
                        }
                    };
                    Handler h2 = new Handler();
                    h2.postDelayed(a2, 2000); // <-- the "1000" is the delay time in miliseconds



                }


            }
        });




    }

    private void updateInfoCustomer() {
        Amplify.DataStore.query(Customer.class, Where.id(ShopID),
                matches -> {
                    if (matches.hasNext()) {
                        Customer original = matches.next();
                        Customer edited = original.copyOfBuilder()
                                .name(Updated_nameText)
                                .address(Updated_addressText)
                                .pin(Updated_pincodeText)
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

    private void getInfoCustomer() {
        Amplify.DataStore.query(
                Customer.class,
                Where.matches(Customer.ID.eq(ShopID)),
                items -> {
                    while (items.hasNext()) {

                        Customer item = items.next();
                        nameText=item.getName();
                        addressText=item.getAddress();
                        pincodeText=item.getPin();
                        Log.i("Amplify", "Id " + item.getId());
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );
    }

    private void getInfo() {
        Amplify.DataStore.query(
                Shop.class,
                Where.matches(Shop.ID.eq(ShopID)),
                items -> {

                    while (items.hasNext()) {
                        Shop item = items.next();
                        nameText=item.getShopname();
                        addressText=item.getAddress();
                        pincodeText=item.getShoppin();
                        Log.i("Amplify", "Id " + item.getId());
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );
    }

    private void updateInfo() {

        Amplify.DataStore.query(Shop.class, Where.id(ShopID),
                matches -> {
                    if (matches.hasNext()) {
                        Shop original = matches.next();
                        Shop edited = original.copyOfBuilder()
                                .shopname(Updated_nameText)
                                .address(Updated_addressText)
                                .shoppin(Updated_pincodeText)
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

}