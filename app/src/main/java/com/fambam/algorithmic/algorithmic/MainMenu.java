package com.fambam.algorithmic.algorithmic;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
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

    private int[] drawables;
    private int[] data;
    private Algorithm algorithm;
    private UpdateOrdering ordering;
    private AlgorithmAssets algoAsset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
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

        FloatingActionButton fab = findViewById(R.id.fab);
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
                                // Nothing Here
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
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
        Intent i;
        switch (v.getId()) {
            /* -------------------- Quiz button conditions -------------------- */
            case R.id.bubbleSummaryB:
                i = new Intent(getApplicationContext(), AlgorithmSummary.class);
                i.putExtra("subject", "bubble");
                startActivity(i);
                break;
            case R.id.selectionSummaryB:
                i = new Intent(getApplicationContext(), AlgorithmSummary.class);
                i.putExtra("subject", "selection");
                startActivity(i);
                break;
            case R.id.insertionSummaryB:
                i = new Intent(getApplicationContext(), AlgorithmSummary.class);
                i.putExtra("subject", "insertion");
                startActivity(i);
                break;
            case R.id.lsSummaryB:
                i = new Intent(getApplicationContext(), AlgorithmSummary.class);
                i.putExtra("subject", "ls");
                startActivity(i);
                break;

            /* -------------------- Quiz button conditions -------------------- */
            case R.id.bubbleQuizB:
                i = new Intent(getApplicationContext(), AlgorithmQuiz.class);
                i.putExtra("subject", "bubble");
                startActivity(i);
                break;
            case R.id.selectionQuizB:
                i = new Intent(getApplicationContext(), AlgorithmQuiz.class);
                i.putExtra("subject", "selection");
                startActivity(i);
                break;
            case R.id.insertionQuizB:
                i = new Intent(getApplicationContext(), AlgorithmQuiz.class);
                i.putExtra("subject", "insertion");
                startActivity(i);
                break;
            case R.id.lsQuizB:
                i = new Intent(getApplicationContext(), AlgorithmQuiz.class);
                i.putExtra("subject", "ls");
                startActivity(i);
                break;

            /* -------------------- Explanation button conditions -------------------- */
            case R.id.bubbleExplanationB:
                drawables = new int[] {'i','j','k'};
                data = new int[] {3,4,6,7,1,2,1};
                algorithm = new BubbleSort();
                algoAsset = new AlgorithmAssets("BubbleSort.txt",
                                                                "", "", "");
                ordering = new UpdateOrdering(new int[] {   1,1,1,0,1,0,1,0,1,0,
                                                            1,0,1,0,1,0,0,1,0,0,
                                                            0,1,0,1,0,0,0,0,0,0,
                                                            0,0,0,0,0,0,0,0,0,0,
                                                            0,0,0,0,0,0,0,0,0,0,
                                                            0,0,0,1,0,1 });
                startAlgorithm(algorithm, data, drawables, ordering, algoAsset);
                break;

            case R.id.selectionExplanationB:
                drawables = new int[] {'i','j'};
                data = new int[] {3,5,1,6,2,4,7,8};
                algorithm = new SelectionSort();
                algoAsset = new AlgorithmAssets("SelectionSort.txt",
                                                "", "", "");
                ordering = new UpdateOrdering(new int[] {   1,1,1,0,1,0,1,0,1,0,1,
                                                            0,0,1,0,0,0,1,1,0,0,0,
                                                            0,0,0,0,0,0,0,0,0,0,0,
                                                            0,0,0,0,0,0,0,0,0,0,0,
                                                            0,0,0,0,0,1,1});
                startAlgorithm(algorithm, data, drawables, ordering, algoAsset);
                break;

            case R.id.insertionExplanationB:
                drawables = new int[] {'i','j','k'};
                data = new int[] {1,2,6,5,7,8,4,4};
                algorithm = new InsertionSort();
                ordering = new UpdateOrdering(new int[] {   0});
                algoAsset = new AlgorithmAssets("InsertionSort.txt",
                                                "", "", "");
                startAlgorithm(algorithm, data, drawables, ordering, algoAsset);
                break;

            case R.id.lsExplanationB:
                drawables = new int[] {'i'};
                data = new int[] {3,8,2,1,4,6,5,7};
                algorithm = new LinearSearch();
                ordering = new UpdateOrdering(new int[] {   1,1,1,2,0,0,0,0,2,2,1,1});
                algoAsset = new AlgorithmAssets("LinearSearch.txt",
                                                "", "", "");
                startAlgorithm(algorithm, data, drawables, ordering, algoAsset);
                break;

            /* -------------------- Other button conditions -------------------- */
            default:
                break;
        }
    }

    private void startAlgorithm(Algorithm algorithm, int[] data, int[] drawables,
                               UpdateOrdering ordering, AlgorithmAssets asset) {
        String algoKey = getString(R.string.algo_key);
        String drawKey = getString(R.string.drawables);
        String dataKey = getString(R.string.data);
        String orderKey = getString(R.string.ordering);
        String assetKey = getString(R.string.asset);
        Intent intent = new Intent(this, AlgorithmActivity.class);
        intent.putExtra(drawKey, drawables);
        intent.putExtra(algoKey, (Parcelable) algorithm);
        intent.putExtra(dataKey, data);
        intent.putExtra(orderKey, ordering);
        intent.putExtra(assetKey, asset);
        startActivity(intent);
    }
}

