package com.example.smartseller;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
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

public class switchStatus extends AppCompatActivity implements View.OnClickListener{

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    String devideID;
    Button submitBtn;
    EditText hoursInput,mintInput;
    Boolean USBStatus,switchStatus;
    DeviceStatus deviceStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_status);
        hoursInput = findViewById(R.id.hrsInput);
        mintInput = findViewById(R.id.hrsInput);
        submitBtn = findViewById(R.id.submitDeviceBtn);
        devideID = getIntent().getStringExtra("deviceID");
        if(devideID==null) {
            Toast.makeText(this, "Null ID passed from above", Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,loginCustomer.class));
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("DeviceStatus").child(devideID);
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                deviceStatus = dataSnapshot.getValue(DeviceStatus.class);
                Log.i("info","Product status available");
                TextView tempTxt = findViewById(R.id.deviceIDTxtView);
                tempTxt.setText(deviceStatus.deviceID);
                Button tempBtn = findViewById(R.id.submitDeviceBtn);
                tempBtn.setText("Turn ON");
                tempTxt=findViewById(R.id.deviceStatusTxtView);
                tempTxt.setText("OFF");
                if(deviceStatus.State) {
                    tempBtn.setText("Turn OFF");
                    tempTxt.setText("ON");
                }
                if(deviceStatus.USBstatus){
                    tempTxt=findViewById(R.id.USBStatusTxtView);
                    tempTxt.setText("ON");
                }else{
                    tempTxt=findViewById(R.id.USBStatusTxtView);
                    tempTxt.setText("OFF");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error occoured",Toast.LENGTH_LONG).show();
                return;
            }
        });
        submitBtn.setOnClickListener(this);
    }


    public void onClick(View view){
        if (view==submitBtn)
            updateInfo();
    }

    void updateInfo(){
        int timeMS=0;
        int temp;
        temp=Integer.parseInt(mintInput.getText().toString().trim());
        timeMS+=temp*60;
        temp=Integer.parseInt(hoursInput.getText().toString().trim());
        timeMS+=temp*60*60;
        timeMS*=1000;

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                submit();
            }
        }, timeMS);
    }

    public void submit(){
        CheckBox usbstatus = findViewById(R.id.usbStatusCheckBox);
        if(usbstatus.isChecked())
            deviceStatus.USBstatus=true;
        else deviceStatus.USBstatus=false;
        deviceStatus.State=!deviceStatus.State;
        FirebaseDatabase.getInstance().getReference("DeviceStatus").child(deviceStatus.deviceID).setValue(deviceStatus).addOnCompleteListener(
                this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.i("test","nvjjj");
                            Toast.makeText(getApplicationContext(), "Product ID Successfully Entered", Toast.LENGTH_LONG).show();
                            finish();
//                            startActivity(new Intent(getApplicationContext(), switchStatus.class));
                        }
                        else
                            Log.i("test","avjjj");
                    }
                }
        );
    }
}
