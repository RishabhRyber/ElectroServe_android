package com.example.smartseller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class Maintenance extends AppCompatActivity {
    Spinner category,maintenanaceparts;
    String[] categories={"Television","Refrigerator"};
    String[] partsmaint={"Cathode Ray Tube","Light valve","Logic Board","Capacitors","Screen,Output", "Speaker"};
    String[] partsmaint1={"All parts","Insulation","Outer cabinet and door","Inner cabinet","Bulb","Evaporator","Thermostat","Condenser","Refrigerant/Cooling fluid","Expansive valve/capillary","Defrost system","Switch","Crisper"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);
        category=findViewById(R.id.category);
        maintenanaceparts=findViewById(R.id.maintpart);
//spinner
        ArrayAdapter<String> myAdapter2=new ArrayAdapter<String>(Maintenance.this, android.R.layout.simple_spinner_item,categories);
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(myAdapter2);

//spinner
        ArrayAdapter<String>myAdapter3=new ArrayAdapter<String>(Maintenance.this,android.R.layout.simple_spinner_item,partsmaint);
        myAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maintenanaceparts.setAdapter(myAdapter3);
    }
}
