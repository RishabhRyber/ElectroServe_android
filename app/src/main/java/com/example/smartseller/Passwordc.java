package com.example.smartseller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Passwordc extends AppCompatActivity {
    EditText createpass,confirmpass,emailidpass;
    Button finishid;
    CheckBox tickbox;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordc);
        mAuth= FirebaseAuth.getInstance();
        emailidpass=findViewById(R.id.emailpass);
        createpass=findViewById(R.id.crepassid);
        confirmpass=findViewById(R.id.confpassid);
        tickbox=findViewById(R.id.tickid);
      finishid=findViewById(R.id.finishid);
finishid.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      CreateNewAccount();  
    }
});


    }

    private void CreateNewAccount() {
        String email=emailidpass.getText().toString();
        String passwordcre=createpass.getText().toString();
        String passwordconf=confirmpass.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please write your email...", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(passwordcre)){
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(passwordconf)){
            Toast.makeText(this, "Please confirm your password...", Toast.LENGTH_SHORT).show();
        }else if(!passwordcre.equals(passwordconf)){
            Toast.makeText(this, "Confirm your password", Toast.LENGTH_SHORT).show();
        }else{
mAuth.createUserWithEmailAndPassword(email,passwordcre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
if (task.isSuccessful()){
    SendUserToLogin();
    Toast.makeText(Passwordc.this, "you are authenticated", Toast.LENGTH_SHORT).show();
}
else{
    String message=task.getException().getMessage();
    Toast.makeText(Passwordc.this, "Error occured"+message, Toast.LENGTH_SHORT).show();
}
    }
});
        }

    }

    private void SendUserToLogin() {
    Intent intent=new Intent(Passwordc.this,Login.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(intent);
    finish();
    }
}
