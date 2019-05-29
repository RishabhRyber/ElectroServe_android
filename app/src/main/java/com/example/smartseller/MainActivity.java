package com.example.smartseller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    EditText sellername,workname,address,pannumber,gstnumber,adharnumber,areaservicing;
    CheckBox homeservyes,homeservno,wareyes,wareno;
    Button finish;
    Spinner waredropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sellername=findViewById(R.id.nameid);
        workname=findViewById(R.id.workid);
        address=findViewById(R.id.addressid);
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
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Details.class);
                startActivity(intent);
            }
        });

    }
}