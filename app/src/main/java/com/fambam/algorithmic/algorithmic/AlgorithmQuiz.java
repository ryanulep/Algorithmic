package com.fambam.algorithmic.algorithmic;

import android.content.res.Resources;
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
        String currentAns;
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
        }

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
        //if( tButton1.getText().equals(R.string.B_Q1_Ans));

        //dRef.child("child1").setValue("Muh Vahlyews");
    }
    private void tB2Pressed() {

    }
    private void tB3Pressed() {

    }
    private void tB4Pressed() {

    }


}
