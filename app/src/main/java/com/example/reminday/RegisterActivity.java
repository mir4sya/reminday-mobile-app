package com.example.reminday;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth nAuth;
    private EditText emailReg, passwordReg;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nAuth = FirebaseAuth.getInstance();
        emailReg = findViewById(R.id.emailReg);
        passwordReg = findViewById(R.id.passwordReg);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

    }

    private void Register() {
        String user = emailReg.getText().toString().trim();
        String pass = passwordReg.getText().toString().trim();
        if(user.isEmpty())
        {
            emailReg.setError("Email can not be empty");
        }
        if(pass.isEmpty())
        {
            passwordReg.setError("Password can not be empty");
        }
        else
        {
            nAuth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful())
                   {
                       Toast.makeText(RegisterActivity.this, "Register successfull", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                   }
                   else
                   {
                       Toast.makeText(RegisterActivity.this, "Register failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                   }
                }
            });
        }
    }
}