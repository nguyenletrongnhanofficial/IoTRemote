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

import com.example.iotremote.assetdetail_database.DatabaseHandler;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
    DatabaseReference dtbasereference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://iotz-ce511-default-rtdb.firebaseio.com/");
    @Override
    protected  void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();final EditText email = findViewById(R.id.et_email);
       final EditText confirmpassword = findViewById(R.id.et_password_2);
       final EditText username = findViewById(R.id.et_4);
       final EditText password = findViewById(R.id.tv_5);
       final Button btn_sign_up = findViewById(R.id.btn_sign_up);
       final Button btn_sign_in_now = findViewById(R.id.btn_sign_in_now);
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usernametxt = username.getText().toString();
                final String emailtxt = email.getText().toString();
                final String passwordtxt = password.getText().toString();
                final String confirmpasswordtxt = confirmpassword.getText().toString();

                if(usernametxt.isEmpty() || emailtxt.isEmpty() || passwordtxt.isEmpty() || confirmpasswordtxt.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                else if (!passwordtxt.equals(confirmpasswordtxt)){
                    Toast.makeText(RegisterActivity.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                }
                else {
                    dtbasereference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(usernametxt)){
                                Toast.makeText(RegisterActivity.this, "Username is already registered", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                dtbasereference.child("users").child(usernametxt).child("email").setValue(emailtxt);
                                dtbasereference.child("users").child(usernametxt).child("password").setValue(passwordtxt);
                                Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



                }
            }

        });
        btn_sign_in_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });

    }


}
