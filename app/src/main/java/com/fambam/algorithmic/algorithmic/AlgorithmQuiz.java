package com.fambam.algorithmic.algorithmic;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class AlgorithmQuiz extends AppCompatActivity {

    private Button tButton1;
    private Button tButton2;
    private Button tButton3;
    private Button tButton4;

    private TextView questionText;
    //FirebaseDatabase database = FirebaseDatabase.getInstance();
    //String userUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    //DatabaseReference dRef = database.getReference(userUID);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm_quiz);

        questionText = findViewById(R.id.questionTextView);
        String subject;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            subject = extras.getString("subject" );
        }
        else {
            subject = "extras were NULL";
        }

        int randIndex = new Random().nextInt(3);
        if (subject.equals("bubble")){
            int[] qList = {R.string.B_Q1_qText, R.string.B_Q2_qText, R.string.B_Q3_qText};
            int randQuestion = qList[randIndex];
            questionText.setText(getResources().getString(randQuestion));
            int[] aList = {R.string.B_Q1_Ans, R.string.B_Q2_Wrong1, R.string.B_Q2_Wrong2, R.string.B_Q2_Wrong3};
            //Vector<Integer> = new ArrayList<Integer>(3);

            randIndex = new Random().nextInt(3);
            tButton1.setText(aList[randIndex]);
            randIndex = new Random().nextInt(2);
            tButton1.setText(aList[randIndex]);
            randIndex = new Random().nextInt(1);
            tButton1.setText(aList[randIndex]);

        }
        else if (subject.equals("selection")){
            int[] qList = {R.string.S_Q1_qText, R.string.S_Q2_qText, R.string.S_Q3_qText};
            int randQuestion = qList[randIndex];
            questionText.setText(getResources().getString(randQuestion));
        }
        else if (subject.equals("insertion")){
            int[] qList = {R.string.I_Q1_qText, R.string.I_Q2_qText, R.string.I_Q3_qText};
            int randQuestion = qList[randIndex];
            questionText.setText(getResources().getString(randQuestion));
        }
        else if (subject.equals("ls")){
            int[] qList = {R.string.LS_Q1_qText, R.string.LS_Q2_qText, R.string.LS_Q3_qText};
            int randQuestion = qList[randIndex];
            questionText.setText(getResources().getString(randQuestion));
        }

        tButton1 = findViewById(R.id.tButton1);
        tButton2 = findViewById(R.id.tButton2);
        tButton3 = findViewById(R.id.tButton3);
        tButton4 = findViewById(R.id.tButton4);

        tButton1.setBackgroundColor(0xFFFFAAAA);
        tButton2.setBackgroundColor(0xFFFFAAAA);
        tButton3.setBackgroundColor(0xFFFFAAAA);
        tButton4.setBackgroundColor(0xFFFFAAAA);

        //mAuth = FirebaseAuth.getInstance();

        tButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tButton1.setBackgroundColor(0xFFFF0000);
                tButton2.setBackgroundColor(0xFFFFAAAA);
                tButton3.setBackgroundColor(0xFFFFAAAA);
                tButton4.setBackgroundColor(0xFFFFAAAA);
                tB1Pressed();
            }
        });
        tButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tButton1.setBackgroundColor(0xFFFFAAAA);
                tButton2.setBackgroundColor(0xFFFF0000);
                tButton3.setBackgroundColor(0xFFFFAAAA);
                tButton4.setBackgroundColor(0xFFFFAAAA);
                tB2Pressed();
            }
        });
        tButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tButton1.setBackgroundColor(0xFFFFAAAA);
                tButton2.setBackgroundColor(0xFFFFAAAA);
                tButton3.setBackgroundColor(0xFFFF0000);
                tButton4.setBackgroundColor(0xFFFFAAAA);
                tB3Pressed();
            }
        });
        tButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tButton1.setBackgroundColor(0xFFFFAAAA);
                tButton2.setBackgroundColor(0xFFFFAAAA);
                tButton3.setBackgroundColor(0xFFFFAAAA);
                tButton4.setBackgroundColor(0xFFFF0000);
                tB4Pressed();
            }
        });
    }

    private void tB1Pressed() {


        //dRef.child("child1").setValue("Muh Vahlyews");
    }
    private void tB2Pressed() {

    }
    private void tB3Pressed() {

    }
    private void tB4Pressed() {

    }


}
