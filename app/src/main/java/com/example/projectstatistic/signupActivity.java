package com.example.projectstatistic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectstatistic.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signupActivity extends AppCompatActivity {

    private EditText edtUsername, edtPwd;
    private Button btnSignIn;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setTitle("Sign Up");

        edtUsername = findViewById(R.id.idEdtEmail);
        edtPwd = findViewById(R.id.idEdtPwd);
        btnSignIn = findViewById(R.id.idBtnSignIn);
        mAuth = FirebaseAuth.getInstance();

        btnSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email = edtUsername.getText().toString();
                String password = edtPwd.getText().toString();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(signupActivity.this, "Please fill the field", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(signupActivity.this,"Sign Up Success",Toast.LENGTH_SHORT).show();
                                String usernameFromEmail = task.getResult().getUser().getEmail().split("@")[0];
                                String userID = task.getResult().getUser().getUid();
                                mDatabase.child("Users").child(userID).setValue(new User(usernameFromEmail,email)).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Intent i = new Intent(signupActivity.this,MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                });
                            } else {
                                Toast.makeText(signupActivity.this,"Sign Up Failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}