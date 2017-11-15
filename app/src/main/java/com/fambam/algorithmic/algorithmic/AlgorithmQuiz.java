package com.fambam.algorithmic.algorithmic;

import android.content.Intent;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class AlgorithmQuiz extends AppCompatActivity {

    private Button tButton1;
    private Button tButton2;
    private Button tButton3;
    private Button tButton4;

    private TextView questionText;
    private TextView correctText;

    private String currentAns;
    // Colors for buttons
    int defaultC = 0xFFC5CAE9;
    int selectedIncorrectC = 0xFF00BCD4;
    int selectedCorrectC = 0xFF76FF03;

    //FirebaseDatabase database = FirebaseDatabase.getInstance();
    //String userUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    //DatabaseReference dRef = database.getReference(userUID);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm_quiz);

        ArrayList<Button> bList = new ArrayList<>();
        tButton1 = findViewById(R.id.tButton1);
        tButton2 = findViewById(R.id.tButton2);
        tButton3 = findViewById(R.id.tButton3);
        tButton4 = findViewById(R.id.tButton4);
        bList.add(tButton1);
        bList.add(tButton2);
        bList.add(tButton3);
        bList.add(tButton4);

        questionText = findViewById(R.id.questionTextView);
        correctText = findViewById(R.id.correctTextView);
        String subject;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            subject = extras.getString("subject" );
        }
        else {
            subject = "extras were NULL";
        }

        Resources res = getResources();
        int randIndex = new Random().nextInt(3);
        if (subject.equals("bubble")){
            String[][] qList = {res.getStringArray(R.array.b_q1),res.getStringArray(R.array.b_q2),res.getStringArray(R.array.b_q3)};
            String[] currentQ = qList[randIndex];
            questionText.setText(currentQ[0]);
            ArrayList<String> ansList = new ArrayList<>();
            for(int i = 4; i > 0; i--){
                ansList.add(currentQ[i]);
            }
            for(int i = 4; i > 0; i--){
                randIndex = new Random().nextInt(i);
                bList.get(i-1).setText(ansList.get(randIndex));
                ansList.remove(randIndex);
            }
            currentAns = currentQ[1];
        }
        else if (subject.equals("selection")){
            String[][] qList = {res.getStringArray(R.array.s_q1),res.getStringArray(R.array.s_q2),res.getStringArray(R.array.s_q3)};
            String[] currentQ = qList[randIndex];
            questionText.setText(currentQ[0]);
            ArrayList<String> ansList = new ArrayList<>();
            for(int i = 4; i > 0; i--){
                ansList.add(currentQ[i]);
            }
            for(int i = 4; i > 0; i--){
                randIndex = new Random().nextInt(i);
                bList.get(i-1).setText(ansList.get(randIndex));
                ansList.remove(randIndex);
            }
            currentAns = currentQ[1];
        }
        else if (subject.equals("insertion")){
            String[][] qList = {res.getStringArray(R.array.i_q1),res.getStringArray(R.array.i_q2),res.getStringArray(R.array.i_q3)};
            String[] currentQ = qList[randIndex];
            questionText.setText(currentQ[0]);
            ArrayList<String> ansList = new ArrayList<>();
            for(int i = 4; i > 0; i--){
                ansList.add(currentQ[i]);
            }
            for(int i = 4; i > 0; i--) {
                randIndex = new Random().nextInt(i);
                bList.get(i - 1).setText(ansList.get(randIndex));
                ansList.remove(randIndex);
            }
            currentAns = currentQ[1];
        }
        else if (subject.equals("ls")){
            String[][] qList = {res.getStringArray(R.array.ls_q1),res.getStringArray(R.array.ls_q2),res.getStringArray(R.array.ls_q3)};
            String[] currentQ = qList[randIndex];
            questionText.setText(currentQ[0]);
            ArrayList<String> ansList = new ArrayList<>();
            for(int i = 4; i > 0; i--){
                ansList.add(currentQ[i]);
            }
            for(int i = 4; i > 0; i--){
                randIndex = new Random().nextInt(i);
                bList.get(i-1).setText(ansList.get(randIndex));
                ansList.remove(randIndex);
            }
            currentAns = currentQ[1];
        }

        tButton1.setBackgroundColor(defaultC);
        tButton2.setBackgroundColor(defaultC);
        tButton3.setBackgroundColor(defaultC);
        tButton4.setBackgroundColor(defaultC);

        //mAuth = FirebaseAuth.getInstance();

        tButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tButton1.setBackgroundColor(selectedIncorrectC);
                tButton2.setBackgroundColor(defaultC);
                tButton3.setBackgroundColor(defaultC);
                tButton4.setBackgroundColor(defaultC);
                tB1Pressed();
            }
        });
        tButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tButton1.setBackgroundColor(defaultC);
                tButton2.setBackgroundColor(selectedIncorrectC);
                tButton3.setBackgroundColor(defaultC);
                tButton4.setBackgroundColor(defaultC);
                tB2Pressed();
            }
        });
        tButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tButton1.setBackgroundColor(defaultC);
                tButton2.setBackgroundColor(defaultC);
                tButton3.setBackgroundColor(selectedIncorrectC);
                tButton4.setBackgroundColor(defaultC);
                tB3Pressed();
            }
        });
        tButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tButton1.setBackgroundColor(defaultC);
                tButton2.setBackgroundColor(defaultC);
                tButton3.setBackgroundColor(defaultC);
                tButton4.setBackgroundColor(selectedIncorrectC);
                tB4Pressed();
            }
        });

    }

    private void tB1Pressed() {
        if (tButton1.getText().equals(currentAns)){
            tButton1.setBackgroundColor(selectedCorrectC);
            createExitButton();
        }
        //dRef.child("child1").setValue("Muh Vahlyews");
    }
    private void tB2Pressed() {
        if (tButton2.getText().equals(currentAns)){
            tButton2.setBackgroundColor(selectedCorrectC);
            createExitButton();
        }
    }
    private void tB3Pressed() {
        if (tButton3.getText().equals(currentAns)){
            tButton3.setBackgroundColor(selectedCorrectC);
            createExitButton();
        }
    }
    private void tB4Pressed() {
        if (tButton4.getText().equals(currentAns)){
            tButton4.setBackgroundColor(selectedCorrectC);
            createExitButton();
        }
    }
    private void createExitButton(){
        correctText.setText("CORRECT!");

        ImageButton exitB = new ImageButton(this);
        exitB.setId(1);
        exitB.setImageResource(R.drawable.right_arrow);
        ConstraintLayout currentLayout = this.findViewById(R.id.quiz);
        currentLayout.addView(exitB);
        ConstraintSet exitBC = new ConstraintSet();
        exitBC.clone(currentLayout);
        exitBC.centerVertically(1, ConstraintSet.PARENT_ID);
        exitBC.connect(1, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
        exitBC.constrainWidth(1, 200);
        exitBC.constrainHeight(1, 400);
        exitBC.applyTo(currentLayout);
        exitB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }

}
