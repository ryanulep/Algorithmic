package com.fambam.algorithmic.algorithmic;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
    private static final int RANDOMSIZE = 8;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    DatabaseReference dRef = database.getReference();
    private String flags = "0000000000000000";
    private String flagsUpdate = "0000000000000000";
    int score;
    int trys;

    int finishedColor = 0xAAAAFFFF;
    int defaultColor = 0xFFFFFFFF;

    private static final int[] bIDs = {
            R.id.bubbleSummaryB,
            R.id.bubbleExplanationB,
            R.id.bubbleSimulateB,
            R.id.bubbleQuizB,
            R.id.selectionSummaryB,
            R.id.selectionExplanationB,
            R.id.selectionSimulateB,
            R.id.selectionQuizB,
            R.id.insertionSummaryB,
            R.id.insertionExplanationB,
            R.id.insertionSimulateB,
            R.id.insertionQuizB,
            R.id.lsSummaryB,
            R.id.lsExplanationB,
            R.id.lsSimulateB,
            R.id.lsQuizB,
    };


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


        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    flags = dataSnapshot.child(UID).getValue(userData.class).getFlags();
                    score = dataSnapshot.child(UID).getValue(userData.class).getQuiz_score();
                    trys = dataSnapshot.child(UID).getValue(userData.class).getTrys();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                /*
                try {
                    score = dataSnapshot.child(UID).getValue(userData.class).getQuiz_score();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    trys = dataSnapshot.child(UID).getValue(userData.class).getTrys();
                } catch (Exception e) {
                    e.printStackTrace();
                }
*/
                for(int i = 0; i < 16; i++){
                    char flag = flags.charAt(i);
                    if(flag == '1'){
                        Button currentButton = findViewById(bIDs[i]);
                        currentButton.setTextColor(finishedColor);
                    }
                    else{
                        Button currentButton = findViewById(bIDs[i]);
                        currentButton.setTextColor(defaultColor);
                    }
                }

                String spacing = new String();
                //Andorid 7.0 - bubbleQuiz.getWidth() - 1175
                for(int i = 0; i < 66; i++){
                        spacing += " ";
                }
                bubbleQuiz.setText("Quiz"+spacing+score+"/"+trys);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Could not read value
                // User logger??
            }
        });

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

                flagsUpdate = '1'+flags.substring(1, 16);
                dRef.child(UID).child("flags").setValue(flagsUpdate);

                i = new Intent(getApplicationContext(), AlgorithmSummary.class);
                i.putExtra("subject", "bubble");
                startActivity(i);
                break;

            case R.id.selectionSummaryB:

                flagsUpdate = flags.substring(0,4)+'1'+flags.substring(5, 16);
                dRef.child(UID).child("flags").setValue(flagsUpdate);

                i = new Intent(getApplicationContext(), AlgorithmSummary.class);
                i.putExtra("subject", "selection");
                startActivity(i);
                break;

            case R.id.insertionSummaryB:

                flagsUpdate = flags.substring(0,8)+'1'+flags.substring(9, 16);
                dRef.child(UID).child("flags").setValue(flagsUpdate);

                i = new Intent(getApplicationContext(), AlgorithmSummary.class);
                i.putExtra("subject", "insertion");
                startActivity(i);
                break;

            case R.id.lsSummaryB:

                flagsUpdate = flags.substring(0,12)+'1'+flags.substring(13, 16);
                dRef.child(UID).child("flags").setValue(flagsUpdate);

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
                ordering = new UpdateOrdering(new int[] {   1,1,1,1,0,2,2,1,0,2,0,
                                                            2,0,0,1,1,0,0,0,0,0,0,
                                                            0,0,0,0,0,0,0,0,0,0,0,
                                                            0,0,0,0,0,0,0,0,0,0,0,
                                                            0,0,1,1});
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
                ordering = new UpdateOrdering(new int[] {1,1,1,2,0,0,0,0,2,2,1,1});
                algoAsset = new AlgorithmAssets("LinearSearch.txt",
                                                "", "", "");
                startAlgorithm(algorithm, data, drawables, ordering, algoAsset);
                break;

            /* -------------------- Simulation button conditions -------------------- */
            case R.id.bubbleSimulateB:
                algorithm = new BubbleSort();
                data = getRandomArray();
                drawables = new int[] {'i','j','k'};
                startSimulation(algorithm, data, drawables);
                break;

            case R.id.selectionSimulateB:
                algorithm = new SelectionSort();
                data = getRandomArray();
                drawables = new int[] {'i','j'};
                startSimulation(algorithm, data, drawables);
                break;

            case R.id.insertionSimulateB:
                algorithm = new InsertionSort();
                data = getRandomArray();
                drawables = new int[] {'i','j','k'};
                startSimulation(algorithm, data, drawables);
                break;

            case R.id.lsSimulateB:
                algorithm = new LinearSearch();
                data = getRandomArray();
                drawables = new int[] {'i'};
                startSimulation(algorithm, data, drawables);
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

    private void startSimulation(Algorithm algorithm, int[] data, int[] drawables) {
        String algoKey = getString(R.string.algo_key);
        String drawKey = getString(R.string.drawables);
        String dataKey = getString(R.string.data);
        Intent intent = new Intent(this, SimulateActivity.class);
        intent.putExtra(algoKey, (Parcelable) algorithm);
        intent.putExtra(drawKey, drawables);
        intent.putExtra(dataKey, data);
        startActivity(intent);
    }

    private int[] getRandomArray() {
        ArrayList<Integer> n = new ArrayList<>();
        int[] data = new int[RANDOMSIZE];
        int i = 0;
        while (n.size() < RANDOMSIZE) {
            int r = new Random().nextInt(10);
            if (!n.contains(r)) {
                n.add(r);
                data[i++] = r;
            }
        }
        return data;
    }

}

