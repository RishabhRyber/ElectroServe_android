package com.example.smartseller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginCustomer extends AppCompatActivity implements View.OnClickListener {

    EditText phoneemail,password;
    Button login;
    TextView signUp,loginAsSeller;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    String acType="";   //To identify Which Type of account is to be used

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_customer);
        loginAsSeller=findViewById(R.id.loginAsSellerTxtView);
        signUp=findViewById(R.id.registerAsCustomerTxtView);
        phoneemail=findViewById(R.id.enterphoneemailid);
        password=findViewById(R.id.enterpass);
        login=findViewById(R.id.loginid);
        login.setOnClickListener(this);
        loginAsSeller.setOnClickListener(this);
        signUp.setOnClickListener(this);

        databaseReference= FirebaseDatabase.getInstance().getReference("acType");

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),register_customer2.class));
        }
    }

    public void onClick(View view){
        if (view == login){
            LoginCustomerBtnPressed();
        }
        if(view == loginAsSeller){
            finish();
            startActivity(new Intent(this,Login.class));
        }
        if (view==signUp){
            finish();
            startActivity(new Intent(this,register_customer.class));
        }

    }


    public void LoginCustomerBtnPressed(){
        String emailStr = phoneemail.getText().toString().trim();
        String pwdStr= password.getText().toString().trim();

        firebaseAuth.signInWithEmailAndPassword(emailStr,pwdStr).addOnCompleteListener(
                this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //AccountVerified
                            FirebaseUser user=firebaseAuth.getCurrentUser();
                            DatabaseReference mostafa =  databaseReference.child(user.getUid());

                            mostafa.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    acType = dataSnapshot.getValue(String.class);
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_LONG);
                                    return;

                                }
                            });
                            if (!acType.equals("customer")){
                                firebaseAuth.signOut();
                                Log.i("demo","Passwordc khula" + acType);
                                Toast.makeText(getApplicationContext(),"Please Signin as Seller",Toast.LENGTH_LONG);
                                return;
                            }
                            else {
                                finish();
                                startActivity(new Intent(getApplicationContext(),identify_code_customer.class));
                            }

                            return;
                        }else{
                            Log.i("demo","Passwordc Nahin khula" + acType);
                            return;
                        }


                    }
                }
        );

    }
}
