package com.example.iotremote;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_password,et_email,confirmemail,confirmpassword;
    private Button btn_sign_up,btn_sign_in_now;
    private FirebaseAuth mAuth;
    @Override
    protected  void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        mAuth = FirebaseAuth.getInstance();
        confirmemail = findViewById(R.id.et_email);
        confirmpassword = findViewById(R.id.et_password_2);
        et_email = findViewById(R.id.et_4);
        et_password = findViewById(R.id.tv_5);
        btn_sign_up = findViewById(R.id.btn_sign_up);
        btn_sign_in_now = findViewById(R.id.btn_sign_in_now);
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }

        });
        btn_sign_in_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jmptologin = new Intent (RegisterActivity.this,LoginActivity.class);
            }

        });

    }

    private void register() {
        String username,pass,confirmusername,confirmpass;
        confirmusername = confirmemail.getText().toString();
        confirmpass = confirmpassword.getText().toString();
        username = et_email.getText().toString();
        pass = et_password.getText().toString();
        if(TextUtils.isEmpty(username)){
            Toast.makeText(this, "Email is required!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Password cannot be Empty !",Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(username,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                Toast.makeText(getApplicationContext(), "Account Create Successful",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (RegisterActivity.this,LoginActivity.class);
                startActivity(intent);}

            else
            {
                Toast.makeText(getApplicationContext(), "Failed to create Account",Toast.LENGTH_SHORT).show();

            }}
        });
    }
}
