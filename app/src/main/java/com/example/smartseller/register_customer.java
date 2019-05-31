package com.example.smartseller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class register_customer extends AppCompatActivity {

    EditText regphoneemail;
    Button submitreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);
        regphoneemail = findViewById(R.id.phoneemailreg);
        submitreg = findViewById(R.id.submitreg);
        submitreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=regphoneemail.getText().toString().trim();
                if(number.isEmpty()|| number.length()<10) {
                    regphoneemail.setError("Number is required");
                    regphoneemail.requestFocus();
                    return;
                }
                Intent intent=new Intent(getApplicationContext(),Verify.class);
                intent.putExtra("phonenumber",number);
                startActivity(intent);
            }
        });
    }
}
