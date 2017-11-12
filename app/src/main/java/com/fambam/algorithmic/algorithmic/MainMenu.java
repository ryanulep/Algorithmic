package com.fambam.algorithmic.algorithmic;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {

    private Button a1SummaryButton;
    private Button a1QuizButton;
    private Button a1Simulate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        a1SummaryButton = findViewById(R.id.bubbleSummary);
        a1QuizButton = findViewById(R.id.bubbleQuiz);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(MainMenu.this
                            , android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(MainMenu.this
                    );
                }
                builder.setMessage("Would you like to sign out?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(MainMenu.this, UserLogin.class));
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
            }
        });

        Button a1Simulate = (Button) findViewById(R.id.bubbleSimulate);
        a1Simulate.setOnClickListener(this); // calling onClick() method

        a1SummaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a1SummaryButtonPressed();
            }
        });
        a1QuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a1QuizButtonPressed();
            }
        });
    }

    @Override
    public void onClick(View v) {
        // default method for handling onClick Events..
        switch (v.getId()) {

            //Intent i = new Intent(getApplicationContext(), NewActivity.class);
            //i.putExtra("key","value");
            //startActivity(i);

            case R.id.bubbleSimulate:
                Intent i = new Intent(getApplicationContext(), AlgorithmQuiz.class);
                i.putExtra("subject", "bubble");
                startActivity(i);
                //startActivity(new Intent(MainMenu.this, AlgorithmQuiz.class));
                break;

            default:
                break;
        }
    }


    private void a1SummaryButtonPressed() {
        startActivity(new Intent(MainMenu.this, AlgorithmSummary.class));
    }
    private void a1QuizButtonPressed() {
        startActivity(new Intent(MainMenu.this, AlgorithmQuiz.class));
    }
}

