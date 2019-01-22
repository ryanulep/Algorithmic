package com.fambam.algorithmic.algorithmic;

import static android.text.TextUtils.isEmpty;
import static android.widget.Toast.makeText;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Objects;

public class UserLogin extends AppCompatActivity {

  private EditText email;
  private EditText password;
  private Button loginButton;
  private Button newUserButton;
  private FirebaseAuth mAuth;
  private AuthStateListener mAuthListener;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_login);

    email = findViewById(R.id.editTextEmail);
    password = findViewById(R.id.editTextPassword);
    loginButton = findViewById(R.id.buttonLogin);
    newUserButton = findViewById(R.id.buttonNewUser);

    mAuth = FirebaseAuth.getInstance();

    mAuthListener =
        new AuthStateListener() {
          @Override
          public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            if (firebaseAuth.getCurrentUser() != null) {
              // If CurrentUser is not null, then there must be a user logged in
              startActivity(new Intent(UserLogin.this, MainMenu.class));
              finish();
            }
          }
        };

    loginButton.setOnClickListener(
        new OnClickListener() {
          @Override
          public void onClick(View view) {
            loginButtonPressed();
          }
        });
    newUserButton.setOnClickListener(
        new OnClickListener() {
          @Override
          public void onClick(View view) {
            newUserButtonPressed();
          }
        });
  }

  @Override
  public void onStart() {
    super.onStart();
    mAuth.addAuthStateListener(mAuthListener);
  }

  private void loginButtonPressed() {
    String emailStr = email.getText().toString();
    String passwordStr = password.getText().toString();

    if (isEmpty(emailStr) || isEmpty(passwordStr)) {
      makeText(UserLogin.this, "Please enter a valid email and password", Toast.LENGTH_SHORT)
          .show();
    } else {
      mAuth
          .signInWithEmailAndPassword(emailStr, passwordStr)
          .addOnCompleteListener(
              new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                  if (!task.isSuccessful()) {
                    makeText(UserLogin.this, "Error with Login", Toast.LENGTH_SHORT).show();
                  }
                }
              });
    }
  }

  private void newUserButtonPressed() {
    String emailStr = email.getText().toString();
    String passwordStr = password.getText().toString();

    if (isEmpty(emailStr) || isEmpty(passwordStr)) {
      makeText(UserLogin.this, "Please enter a valid email and password", Toast.LENGTH_SHORT)
          .show();
    } else {
      mAuth
          .createUserWithEmailAndPassword(emailStr, passwordStr)
          .addOnCompleteListener(
              this,
              new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                  if (task.isSuccessful()) {

                    makeText(UserLogin.this, "Authentication SUCCESS", Toast.LENGTH_SHORT).show();

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    String UID =
                        Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser())
                            .getUid();
                    final DatabaseReference dRef = database.getReference(UID);

                    dRef.child("flags").setValue("0000000000000000");
                    dRef.child("b_quiz_score").setValue(0);
                    dRef.child("b_trys").setValue(0);
                    dRef.child("s_quiz_score").setValue(0);
                    dRef.child("s_trys").setValue(0);
                    dRef.child("i_quiz_score").setValue(0);
                    dRef.child("i_trys").setValue(0);
                    dRef.child("ls_quiz_score").setValue(0);
                    dRef.child("ls_trys").setValue(0);

                    startActivity(new Intent(UserLogin.this, MainMenu.class));
                    finish();
                  } else {
                    makeText(UserLogin.this, "Authentication FAILED", Toast.LENGTH_SHORT).show();
                  }
                }
              });
    }
  }
}
