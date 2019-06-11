package com.example.tusha.sprintjobs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity  extends AppCompatActivity implements View.OnClickListener{
    EditText username,password;
    TextView textViewlogin;
    private FirebaseAuth firebaseAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = (EditText)findViewById(R.id.usernamesignup) ;
        password = (EditText)findViewById(R.id.passwordsignup) ;
        firebaseAuth = FirebaseAuth.getInstance();
        findViewById(R.id.textViewsignup).setOnClickListener(this);

        if (firebaseAuth.getCurrentUser()!= null){
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }

        findViewById(R.id.signup).setOnClickListener(this);
    }


    private void addString(){

        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();



        if (TextUtils.isEmpty(username.getText())){
            Toast.makeText(this,"you should enter a username",Toast.LENGTH_LONG).show();
            username.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password.getText())){
            Toast.makeText(this,"you should enter a password",Toast.LENGTH_LONG).show();
            password.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(user).matches()){
            Toast.makeText(this,"Please enter a valid email",Toast.LENGTH_LONG).show();
            username.requestFocus();
            return;
        }
        if(pass.length()<6){
            Toast.makeText(this,"minimum length of password should be 6",Toast.LENGTH_LONG).show();
            password.requestFocus();
            return;
        }
        if(pass.length()>20){
            Toast.makeText(this,"maximum length of password should be 20",Toast.LENGTH_LONG).show();
            password.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(user,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"user added",Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"some error occurred",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signup:
                addString();
                break;

            case R.id.textViewsignup:
                finish();
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }
    }
}
