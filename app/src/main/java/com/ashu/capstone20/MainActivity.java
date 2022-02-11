

package com.ashu.capstone20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Customer;
import com.amplifyframework.datastore.generated.model.Seller;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn_login,btn_register;
    String prevStarted = "yes";
    String user_mode="";
    RadioGroup rg1;
    RadioButton rd1_user_mode;
    RadioButton default_option;
    EditText username,password;
    SharedPreferences.Editor editor;
    String User_login_frm_database;
    String User_pass_frm_database;
    String SellerID,CustomerID;
    ArrayList<Boolean> LoginSuccesValue=new ArrayList<>(10);
    int cnt=0;
    boolean LoginSucces=false;
    static Thread thread;

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onResume() {
        super.onResume();

        //allow only one time to loging in app
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        if (!sharedpreferences.getBoolean(prevStarted, false)) {
            editor = sharedpreferences.edit();
            editor.putBoolean(prevStarted, Boolean.FALSE);
     ;
            editor.apply();

        } else {
            SharedPreferences prefs = getSharedPreferences("capstone20", MODE_PRIVATE);
            String name = prefs.getString("name", "No");//"No name defined
            if(name.equals("seller")){
                moveToMainPage();
            }
            //if you already login then it will go to main page
            else{
                move_to_customer_page();
            }
        }
    }

    private void move_to_customer_page() {
        Intent Go_to_main_page =new Intent(MainActivity.this,MainPage.class);
        startActivity(Go_to_main_page);

    }

    //go to main page if already login
    private void moveToMainPage() {

      //  Configure(); //aws conffiguration
        Intent Go_to_main_page =new Intent(MainActivity.this,SellerPage.class);
        startActivity(Go_to_main_page);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        //  id of elements
        btn_login=findViewById(R.id.login);
        btn_register=findViewById(R.id.register);
        rg1=(RadioGroup) findViewById(R.id.radiogroup);
        default_option=findViewById(R.id.seller);
        default_option.setChecked(true);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);


        //login button
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              LoginSuccesValue.clear();
                user_mode=((RadioButton)(findViewById(rg1.getCheckedRadioButtonId()))).getText().toString();
                //public static final String MY_PREFS_NAME = "MyPrefsFile";
                SharedPreferences.Editor editor = getSharedPreferences("capstone20", MODE_PRIVATE).edit();
                editor.putString("name", user_mode);
                editor.apply();

                if(user_mode.equals("seller")){
                    SellerDataBaseInfoCheck();
                    Runnable r = new Runnable() {
                        @Override
                        public void run(){
                            //this will wait 1 sec till dataload value
                        LoginMethodToImplement("seller");
                        }
                    };
                    Handler h = new Handler();
                    h.postDelayed(r, 2000); // <-- the "1000" is the delay time in miliseconds.
                }else{
                    //it will make sure that user had not login again again
                    CheckDataBaseInfoForCustomer();
                    Runnable r = new Runnable() {
                        @Override
                        public void run(){
                            //this will wait 1 sec till dataload value
                            LoginMethodToImplement("customer");
                        }
                    };
                    Handler h = new Handler();
                    h.postDelayed(r, 2000); // <-- the "1000" is the delay time in miliseconds.
                }
            }
        });
//register button code
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user_mode=((RadioButton)(findViewById(rg1.getCheckedRadioButtonId()))).getText().toString();
          //if it is seller registration
               if(user_mode.equals("seller")){
                   Intent Go_to_Regisrt_page =new Intent(MainActivity.this,Register.class);
                   Bundle bundle=new Bundle();
                   bundle.putString("usermode",user_mode);
                   Go_to_Regisrt_page.putExtras(bundle);
                   startActivity(Go_to_Regisrt_page);

               }//if it is customer registration
               else {
                Intent Go_to_customer_registration=new Intent(MainActivity.this,CustomerRegistration.class);
                Bundle bundle=new Bundle();
                bundle.putString("usermode",user_mode);
                Go_to_customer_registration.putExtras(bundle);
                   startActivity(Go_to_customer_registration);


               }


            }
        });

//Checking user mode for login and registration
    }

    private void SellerDataBaseInfoCheck() {
        String Username_data="+91"+username.getText().toString();
        String Password_data=password.getText().toString();

        Amplify.DataStore.query(
                Seller.class,
                Where.matches(Seller.USERNAME.eq(Username_data).and(Seller.PASSWORD.eq(Password_data))),
                items -> {
                    while (items.hasNext()) {
                        Seller item = items.next();

                        SellerID=item.getUsername();
                        LoginSucces=true;

                        Log.i("Amplify", "Id " + item.getName() +LoginSuccesValue.get(0));

                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );
    }

    private void CheckDataBaseInfoForCustomer() {

        String Username_data="+91"+username.getText().toString();
        String Password_data=password.getText().toString();
        //check data in database


        Amplify.DataStore.query(
                Customer.class,
                items -> {
                    while (items.hasNext()) {
                        Customer item = items.next();
                        if(Username_data.equals(item.getUsername()) && Password_data.equals(item.getPassword())){
                            CustomerID=item.getUsername();
                            LoginSucces=true;
                        }
                        Log.i("Amplify", "Id " + item.getName());
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );
    }

    private void LoginMethodToImplement(String usertype) {
if(usertype.equals("seller")){
    if(LoginSucces){
        //to know current user in app
        SharedPreferences.Editor editor = getSharedPreferences("capstone20", MODE_PRIVATE).edit();
        editor.putString("SellerID", SellerID);
        editor.apply();

        reduceLoginActivity();
        Intent Go_to_main_page =new Intent(MainActivity.this,SellerPage.class);
        startActivity(Go_to_main_page);


    }
    else {
        Toast.makeText(getApplicationContext(),"Wrong username or password",Toast.LENGTH_SHORT).show();
    }
}else{
    //customer login
    if(LoginSucces){
        SharedPreferences.Editor editor = getSharedPreferences("capstone20", MODE_PRIVATE).edit();
        //to know current user in app
        editor.putString("CustomerID", CustomerID);
        editor.apply();

        reduceLoginActivity();
        Intent Go_to_customer_page =new Intent(MainActivity.this,MainPage.class);
        startActivity(Go_to_customer_page);


    }
    else {
        Toast.makeText(getApplicationContext(),"Wrong username or password",Toast.LENGTH_SHORT).show();
    }


}


    }

    void reduceLoginActivity(){
        String prevStarted = "yes";
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(prevStarted, Boolean.TRUE);
        editor.apply();

    }

}

