package com.example.smartseller;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class Seller extends AppCompatActivity {
    EditText regphoneemail;
    Button submitreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);
        finish();
        startActivity(new Intent(this,loginCustomer.class));

    }

}
