package com.example.smartseller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Verify extends AppCompatActivity {
    private String Verificationid;
    private EditText editText;
private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
mAuth=FirebaseAuth.getInstance();
editText=findViewById(R.id.codeenter);
        String phonenumber=getIntent().getStringExtra("phonenumber");
        sendVerificationCode(phonenumber);
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
String code=editText.getText().toString().trim();
if(code.isEmpty()||code.length()<6){
    editText.setError("Entercode...");
    editText.requestFocus();
    return;
}
verifycode(code);
            }
        });
    }
    private void verifycode(String code){
     PhoneAuthCredential credential=PhoneAuthProvider.getCredential(Verificationid,code);
     signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful()){
            Intent intent=new Intent(Verify.this,Passwordc.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }else{
            Toast.makeText(Verify.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
        }
    }
});
    }

    private void sendVerificationCode(String number){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(

                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        );
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            Verificationid=s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
String code=phoneAuthCredential.getSmsCode();
if(code!=null){
    editText.setText(code);
    verifycode(code);
}
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(Verify.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    };
}
