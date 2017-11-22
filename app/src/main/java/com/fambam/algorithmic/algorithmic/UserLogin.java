package com.fambam.algorithmic.algorithmic;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserLogin extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button loginButton;
    private Button newUserButton;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);
        newUserButton = findViewById(R.id.buttonNewUser);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() != null){
                    // If CurrentUser is not null, then there must be a user logged in
                    startActivity(new Intent(UserLogin.this, MainMenu.class));
                    finish();
                }
            }
        };

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButtonPressed();
            }
        });
        newUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newUserButtonPressed();
            }
        });

    }

    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void loginButtonPressed(){
        String emailStr = email.getText().toString();
        String passwordStr = password.getText().toString();

        if(TextUtils.isEmpty(emailStr) || TextUtils.isEmpty(passwordStr)){
            Toast.makeText(UserLogin.this, "Please enter a valid email and password", Toast.LENGTH_SHORT).show();
        }
        else {
            mAuth.signInWithEmailAndPassword(emailStr, passwordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {

                        Toast.makeText(UserLogin.this, "Error with Login", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void newUserButtonPressed(){
        String emailStr = email.getText().toString();
        String passwordStr = password.getText().toString();

        if(TextUtils.isEmpty(emailStr) || TextUtils.isEmpty(passwordStr)){
            Toast.makeText(UserLogin.this, "Please enter a valid email and password", Toast.LENGTH_SHORT).show();
        }
        else {
            mAuth.createUserWithEmailAndPassword(emailStr, passwordStr)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(UserLogin.this, "Authentication SUCCESS",
                                        Toast.LENGTH_SHORT).show();

                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                final DatabaseReference dRef = database.getReference(UID);

                                dRef.child("flags").setValue("0000000000000000");
                                dRef.child("quiz_score").setValue(0);

                                startActivity(new Intent(UserLogin.this, MainMenu.class));
                                finish();
                            } else {

                                Toast.makeText(UserLogin.this, "Authentication FAILED",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}


