package com.example.smartseller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Repairing extends AppCompatActivity {
    Spinner Repairingcateg,repairingparts;
    String[] categories={"Television","Refrigerator"};
    String[] partsmaint={"Cathode Ray Tube","Light valve","Logic Board","Capacitors","Screen,Output", "Speaker"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repairing);
        Repairingcateg=findViewById(R.id.repairdrop);
        repairingparts=findViewById(R.id.partsrepid);
        ArrayAdapter<String> myAdapter2=new ArrayAdapter<String>(Repairing.this, android.R.layout.simple_spinner_item,categories);
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Repairingcateg.setAdapter(myAdapter2);
        ArrayAdapter<String>myAdapter3=new ArrayAdapter<String>(Repairing.this,android.R.layout.simple_spinner_item,partsmaint);
        myAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        repairingparts.setAdapter(myAdapter3);
    }
}
