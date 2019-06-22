package com.example.smartseller;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class identify_code_customer extends AppCompatActivity implements  View.OnClickListener{
    EditText codeVal;
    String code;
    Button scanBtn,submitBtn;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_code_customer);
        scanBtn = (Button)findViewById(R.id.scanBtn);
        submitBtn=findViewById(R.id.submitBtn);
        codeVal = (EditText)findViewById(R.id.productID);
        activity = this;
        submitBtn.setOnClickListener(this);

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator= new IntentIntegrator(identify_code_customer.this);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                intentIntegrator.setCameraId(0);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.setPrompt("Scanning");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setBarcodeImageEnabled(true);
                intentIntegrator.initiateScan();
            }
            });

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,Login.class));
        }
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("productCode").child(firebaseUser.getUid());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                code = dataSnapshot.getValue(String.class);
                Log.i("info","Product details available" +code);

                if(code==null) {
                    Toast.makeText(getApplicationContext(), "Good Start", Toast.LENGTH_SHORT).show();
                }
                else if(code.equals("lol")){
                    Toast.makeText(getApplicationContext(), "You don't own a IOT device", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    Intent intent = new Intent(getApplicationContext(),switchStatus.class);
                    intent.putExtra("deviceID",code);
                    finish();
                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error occoured",Toast.LENGTH_LONG).show();
                return;
            }
        });

    }

    @Override
    public void onClick(View view){
        if (view ==submitBtn){
            submitCode(codeVal.getText().toString().trim());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        codeVal.setText(result.getContents());
    }


    void submitCode(final String scannedCode){
        code = scannedCode;
        databaseReference.setValue(code).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                    sendSwitchStatus(code);
            }
        });
    }


//
//    void submitCode(String scannedCode){
//        code=scannedCode;
//        databaseReference.setValue(code).addOnCompleteListener(
//                this, new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()){
////                            sendSwitchStatus(code);
//                            DeviceStatus deviceStatus = new DeviceStatus();
//                            deviceStatus.deviceID = code;
//                            FirebaseDatabase.getInstance().getReference("DeviceStatus").child(code).setValue(deviceStatus).addOnCompleteListener( getApplicationContext(), new OnCompleteListener<Void>(){
//
//
//                                public void onComplete(@Nullable Task<void> task){
//                                    if (subTask.isSuccessful()) {
//                                        Toast.makeText(getApplicationContext(), "Product ID Successfully Registered", Toast.LENGTH_LONG).show();
//                                        finish();
//                                        startActivity(new Intent(getApplicationContext(), switchStatus.class));
//                                    }
//                                }
//
//
//                            });
//
//                        }
//                    }
//                }
//        );
//    }


    void sendSwitchStatus(String code){
        DeviceStatus deviceStatus = new DeviceStatus();
        deviceStatus.deviceID = code;
        FirebaseDatabase.getInstance().getReference("DeviceStatus").child(code).setValue(deviceStatus).addOnCompleteListener(
                this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.i("test","nvjjj");
                            Toast.makeText(getApplicationContext(), "Product ID Successfully Entered", Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), switchStatus.class));
                        }
                        else
                            Log.i("test","avjjj");
                    }
                }
        );
    }

}
