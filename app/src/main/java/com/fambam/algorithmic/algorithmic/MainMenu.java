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

    private Button bubbleSummary;
    private Button bubbleExplain;
    private Button bubbleSimulate;
    private Button bubbleQuiz;
    private Button selectionSummary;
    private Button selectionExplain;
    private Button selectionSimulate;
    private Button selectionQuiz;
    private Button insertionSummary;
    private Button insertionExplain;
    private Button insertionSimulate;
    private Button insertionQuiz;
    private Button lsSummary;
    private Button lsExplain;
    private Button lsSimulate;
    private Button lsQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bubbleSummary = findViewById(R.id.bubbleSummaryB);
        bubbleExplain = findViewById(R.id.bubbleExplanationB);
        bubbleSimulate = findViewById(R.id.bubbleSimulateB);
        bubbleQuiz = findViewById(R.id.bubbleQuizB);
        selectionSummary = findViewById(R.id.selectionSummaryB);
        selectionExplain = findViewById(R.id.selectionExplanationB);
        selectionSimulate = findViewById(R.id.selectionSimulateB);
        selectionQuiz = findViewById(R.id.selectionQuizB);
        insertionSummary = findViewById(R.id.insertionSummaryB);
        insertionExplain = findViewById(R.id.insertionExplanationB);
        insertionSimulate = findViewById(R.id.insertionSimulateB);
        insertionQuiz = findViewById(R.id.insertionQuizB);
        lsSummary = findViewById(R.id.lsSummaryB);
        lsExplain = findViewById(R.id.lsExplanationB);
        lsSimulate = findViewById(R.id.lsSimulateB);
        lsQuiz = findViewById(R.id.lsQuizB);

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

        bubbleSummary.setOnClickListener(this);
        bubbleExplain.setOnClickListener(this);
        bubbleSimulate.setOnClickListener(this);
        bubbleQuiz.setOnClickListener(this);
        selectionSummary.setOnClickListener(this);
        selectionExplain.setOnClickListener(this);
        selectionSimulate.setOnClickListener(this);
        selectionQuiz.setOnClickListener(this);
        insertionSummary.setOnClickListener(this);
        insertionExplain.setOnClickListener(this);
        insertionSimulate.setOnClickListener(this);
        insertionQuiz.setOnClickListener(this);
        lsSummary.setOnClickListener(this);
        lsExplain.setOnClickListener(this);
        lsSimulate.setOnClickListener(this);
        lsQuiz.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(), AlgorithmQuiz.class);
        switch (v.getId()) {
            case R.id.bubbleQuizB:
                i.putExtra("subject", "bubble");
                startActivity(i);
                break;
            case R.id.selectionQuizB:
                i.putExtra("subject", "selection");
                startActivity(i);
                break;
            case R.id.insertionQuizB:
                i.putExtra("subject", "insertion");
                startActivity(i);
                break;
            case R.id.lsQuizB:
                i.putExtra("subject", "ls");
                startActivity(i);
                break;
            default:
                break;
        }
    }

}

