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

import com.example.iotremote.MainActivity;
import com.example.iotremote.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ForgetPassActivity extends AppCompatActivity {
    DatabaseReference dtbasereference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://iotz-ce511-default-rtdb.firebaseio.com/");
    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createnewpassword);
        final Button acceptbtn = findViewById(R.id.acceptbutton);
        final EditText username = findViewById(R.id.username);
        final EditText newpass = findViewById(R.id.newpass);
        final EditText connewpass = findViewById(R.id.conpass);
        acceptbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usernametxt = username.getText().toString();
                final String newpasstxt = newpass.getText().toString();
                final String connewpasstxt = connewpass.getText().toString();
                if (usernametxt.isEmpty() || newpasstxt.isEmpty() || connewpasstxt.isEmpty()){
                    Toast.makeText(ForgetPassActivity.this, "Please fill all fields ", Toast.LENGTH_SHORT).show();
                }
                else {
                    dtbasereference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.hasChild(usernametxt)) {
                                    if (!connewpasstxt.equals(newpasstxt)) {
                                        Toast.makeText(ForgetPassActivity.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        final String getPass = snapshot.child(usernametxt).child("password").getValue(String.class);
                                        if (getPass.equals(newpasstxt)) {
                                            Toast.makeText(ForgetPassActivity.this, "Password already set", Toast.LENGTH_SHORT).show();
                                        } else {
                                            dtbasereference.child("users").child(usernametxt).child("password").setValue(newpasstxt);
                                            Toast.makeText(ForgetPassActivity.this, "Password has changed", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(ForgetPassActivity.this, LoginActivity.class));
                                            finish();

                                        }
                                    }
                                }
                                else {
                                    Toast.makeText(ForgetPassActivity.this, "Username does not exist", Toast.LENGTH_SHORT).show();
                                }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });
    }
}
