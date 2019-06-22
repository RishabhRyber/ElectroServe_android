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

public class register_customer2 extends AppCompatActivity implements View.OnClickListener {

    EditText nameInput,contactNumberInput,aadharInput,addressInput;
    Button submitBtn;
    CheckBox IOTOwnerYes,IOTOwnerNo;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference,secDatabaseReference;
    CustomerClass customerClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_customer2);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,loginCustomer.class));
        }
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("customerProfile").child(firebaseUser.getUid());

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                customerClass = dataSnapshot.getValue(CustomerClass.class);
                if(customerClass!=null){
                    finish();
                    startActivity(new Intent(getApplicationContext(),identify_code_customer.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error occoured",Toast.LENGTH_LONG).show();
                return;
            }
        });

        nameInput=findViewById(R.id.nameInput);
        contactNumberInput=findViewById(R.id.contactNumberInput);
        aadharInput=findViewById(R.id.addressInput);
        addressInput=findViewById(R.id.aadharInput);
        submitBtn=findViewById(R.id.submitBtn);
        IOTOwnerYes=findViewById(R.id.checkedTextViewYes);
        IOTOwnerNo=findViewById(R.id.checkedTextViewNo);

        IOTOwnerNo.setOnClickListener(this);
        IOTOwnerYes.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

    }

    void registerCustomer(){
        final CustomerClass tempCustomer =new CustomerClass();
        tempCustomer.name=nameInput.getText().toString().trim();
        tempCustomer.aadharNumber=aadharInput.getText().toString().trim();
        tempCustomer.address=addressInput.getText().toString().trim();
        tempCustomer.email=firebaseUser.getEmail();
        tempCustomer.contactNumber=contactNumberInput.getText().toString().trim();
        if(IOTOwnerYes.isChecked()){
            tempCustomer.usingIOTDevice=true;
        }else
            tempCustomer.usingIOTDevice=false;

        databaseReference.setValue(tempCustomer).addOnCompleteListener(
                this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Successfully Registred",Toast.LENGTH_LONG).show();
                            //To ensure IOT device info is not asked if user don't use it
                            if(!tempCustomer.usingIOTDevice) {
                               setFakeIOTData();
                            }
                            finish();
                            startActivity(new Intent(getApplicationContext(),identify_code_customer.class));
                        }
                    }
                }
        );
    }


    public void setFakeIOTData(){
        secDatabaseReference = FirebaseDatabase.getInstance().getReference().child("productCode").child(firebaseUser.getUid());
        secDatabaseReference.setValue("lol").addOnCompleteListener(
                this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(),identify_code_customer.class));
                        }else {
                            setFakeIOTData();
                        }
                    }
                }
        );
    }


    public void onClick(View view){
        if (view==submitBtn){
            registerCustomer();
        }
        else if (view==IOTOwnerYes && IOTOwnerYes.isChecked())
            IOTOwnerNo.setChecked(false);
        else if (view == IOTOwnerNo &&IOTOwnerNo.isChecked() )
            IOTOwnerYes.setChecked(false);
    }

}
