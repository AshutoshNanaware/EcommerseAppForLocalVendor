package com.ashu.capstone20;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Inventory;
import com.amplifyframework.datastore.generated.model.Product;
import com.amplifyframework.datastore.generated.model.ProductCategory;
import com.amplifyframework.datastore.generated.model.Shop;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AddItem extends AppCompatActivity {

    String productImgRefFirebase;
    Boolean Image_flag=false;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    Button btn_add,btn_main_page,getBtn_addImg;
    ImageView ProductImage;
    Button temp1;
    EditText ed_name,ed_prise,ed_desc,ed_quantity;
    Spinner category_spinner,item_spinner;
    ArrayList<String> catagory=new ArrayList<String>(10);
    int temp;
    Uri imageUri;
    String SellerID;
    String catID;
    String[] value={"a","b","c"};
    ArrayAdapter  arrayAdapter_1,arrayAdapter_2;
    String[] objects;
    ArrayList<String> t=new ArrayList<>(10);
    String ShopID;
    String ProductID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        t.clear();
        GetCategory();
        getBtn_addImg=findViewById(R.id.addImg);
        ProductImage=findViewById(R.id.product_img);
        btn_add=findViewById(R.id.button4);
        btn_main_page=findViewById(R.id.button5);
        ed_name=findViewById(R.id.ProductName);
        ed_desc=findViewById(R.id.ProductDescreption);
        ed_prise=findViewById(R.id.ProductPrise);
        ed_quantity=findViewById(R.id.ProductQuantity);

        item_spinner=findViewById(R.id.spinner2);



    t.add("None");








        arrayAdapter_2=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,t);
        arrayAdapter_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        item_spinner.setAdapter(arrayAdapter_2);
        item_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(getApplicationContext()," "+t.get(position),Toast.LENGTH_SHORT).show();
                catID=t.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getBtn_addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
btn_main_page.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {



    }
});
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(catID.equals("None")){
                    Toast.makeText(getApplicationContext(),"Please Select Category",Toast.LENGTH_SHORT).show();
                }else if(ed_name.getText().toString().equals("")){
                ed_name.setError("can be empty");
                }else if(ed_quantity.getText().toString().equals("")){
                    ed_quantity.setError("can be empty");
                }else if(ed_prise.getText().toString().equals("")){
                    ed_prise.setError("can be empty");
                }
                else if(! Image_flag){
                    Toast.makeText(getApplicationContext(),"Please select Image",Toast.LENGTH_SHORT).show();


                }
                else{

                    SharedPreferences prefs = getSharedPreferences("capstone20", MODE_PRIVATE);
                    SellerID = prefs.getString("SellerID", "none");//"No name defined
                    uploadImage();

                    getSellerID(SellerID);

                }

            }
        });


    }

    private void getSellerID(String sellerID) {
        Amplify.DataStore.query(
                Shop.class,
                items -> {
                    while (items.hasNext()) {
                        Shop item = items.next();
                        if(SellerID.equals(item.getUsername())){

                            ShopID=item.getId();
                        }
                        Log.i("Amplify", "Id " + item.getId());
                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );

    }
    private void AddProductToINventory(){

        String prise=ed_prise.getText().toString();
        String quantity=ed_quantity.getText().toString();
        Double Double_quantity=Double.parseDouble(quantity);
        Double Double_Prise=Double.parseDouble(prise);
        Inventory item = Inventory.builder()
                .productid(ProductID)
                .quantity(Double_quantity)
                .productprise(Double_Prise)
                .build();
        Amplify.DataStore.save(
                item,
                success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
                error -> Log.e("Amplify", "Could not save item to DataStore", error)
        );

    }
    // get y=the image from device
    private void selectImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);

    }

    private void AddDataToDatabase() {


        //NOTE sellerID in databas is actual shop table id and not seller account table id
        // TO change sellerid insted of shopid u can get sellertable id inted of shop table id
        String name=ed_name.getText().toString();
        String des=ed_desc.getText().toString();
        String prise=ed_prise.getText().toString();
        String quantity=ed_quantity.getText().toString();

        Product item = Product.builder()
                .name(name)
                .details(des)
                .shopid(SellerID)
                .quantity(quantity)
                .categoryid(catID)
                .prise(prise)
                .productimg(productImgRefFirebase)
                .sellershopid(ShopID)
                .build();
        Amplify.DataStore.save(
                item,
                success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
                error -> Log.e("Amplify", "Could not save item to DataStore", error)

        );
        ProductID=item.getId();

    }


    private void GetCategory() {

        Amplify.DataStore.query(
                ProductCategory.class,

                items -> {
                    while (items.hasNext()) {
                        ProductCategory item = items.next();
                        Log.i("Amplify", "Id " + item.getProductId());
                        t.add(item.getProductId());

                    }
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );

    // objects = catagory.toArray();


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && data != null && data.getData() != null){

            imageUri = data.getData();
            ProductImage.setImageURI(imageUri);
            Image_flag=true;

        }else{

        }
    }
    private void uploadImage() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading File....");
        progressDialog.show();


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
        Date now = new Date();
        String fileName = formatter.format(now);
        storageReference = FirebaseStorage.getInstance().getReference("images/"+fileName);


        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                //image to store in database refrance in string format
                                productImgRefFirebase= uri.toString();

                                ProductImage.setImageURI(null);
                                progressDialog.dismiss();
                               // Toast.makeText(getApplicationContext(),"Successfully Uploaded"+uri,Toast.LENGTH_SHORT).show();
                                if (progressDialog.isShowing())
                                    progressDialog.dismiss();
                                Runnable a=new Runnable() {
                                    @Override
                                    public void run() {

                                        //send data to database
                                        AddDataToDatabase();

                                        AddProductToINventory();

                                        ed_name.setText("");
                                        ed_desc.setText("");
                                        ed_prise.setText("");
                                        ed_quantity.setText("");
                                        Toast.makeText(getApplicationContext(),"Uploded",Toast.LENGTH_SHORT).show();
                                    }
                                };

                                Handler h=new Handler();


                                h.postDelayed(a,1000);


                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Failed to Upload",Toast.LENGTH_SHORT).show();


            }
        });

    }


}