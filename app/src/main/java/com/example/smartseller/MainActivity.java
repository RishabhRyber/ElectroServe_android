package com.example.smartseller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SellerClass sellerClass;
    EditText sellername,workname,contactNumber,address,pannumber,gstnumber,adharnumber,areaservicing;
    CheckBox homeservyes,homeservno,wareyes,wareno;
    Button finish;
    Spinner waredropdown;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,Login.class));
        }
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("sellerProfile").child(firebaseUser.getUid());

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sellerClass = dataSnapshot.getValue(SellerClass.class);
                Log.i("info","seller details available");
                if(sellerClass!=null){
                    finish();
                    startActivity(new Intent(getApplicationContext(),Details.class));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error occoured",Toast.LENGTH_LONG).show();
                return;
            }
        });


        sellername=findViewById(R.id.nameid);
        workname=findViewById(R.id.workid);
        contactNumber=findViewById(R.id.contactNumber);
        address=findViewById(R.id.addressInput);
        pannumber=findViewById(R.id.pannumberid);
        gstnumber=findViewById(R.id.gstnumberid);
        adharnumber=findViewById(R.id.adharnumberid);
        areaservicing=findViewById(R.id.areaservicingid);
        homeservyes=findViewById(R.id.homeyesid);
        homeservno=findViewById(R.id.homenoid);
        wareyes=findViewById(R.id.wareyesid);
        wareno=findViewById(R.id.wareyesno);
        finish=findViewById(R.id.submitid);
        waredropdown=findViewById(R.id.category);
        finish.setOnClickListener(this);

    }


    public void submitDetails(){
        sellerClass = new SellerClass();
        sellerClass.name=sellername.getText().toString().trim();
        sellerClass.nameWorkCenter=workname.getText().toString().trim();
        sellerClass.address=address.getText().toString().trim();
        sellerClass.areOfService=areaservicing.getText().toString().trim();
        sellerClass.panCard=pannumber.getText().toString().trim();
        sellerClass.aadharNumber = adharnumber.getText().toString().trim();
        if(homeservyes.isChecked())
            sellerClass.homeService = true;
        else
            sellerClass.homeService = false;

        sellerClass.GSTNumber = gstnumber.getText().toString().trim();

        if(wareyes.isChecked())
            sellerClass.offerServiceAtCompanyWarehouse=true;
        else
            sellerClass.offerServiceAtCompanyWarehouse=false;

        sellerClass.contactNumber=contactNumber.getText().toString().trim();
        sellerClass.email=firebaseUser.getEmail();

        databaseReference.setValue(sellerClass).addOnCompleteListener(
                this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Successfully Registred",Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(),Details.class));
                        }
                    }
                }
        );
    }

    public void onClick(View view){
        if (view==finish){
            submitDetails();
        }
    }
}