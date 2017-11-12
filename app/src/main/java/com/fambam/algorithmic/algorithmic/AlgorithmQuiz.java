package com.fambam.algorithmic.algorithmic;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AlgorithmQuiz extends AppCompatActivity {

    private Button tButton1;
    private Button tButton2;
    private Button tButton3;
    private Button tButton4;

    //FirebaseDatabase database = FirebaseDatabase.getInstance();
    //String userUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    //DatabaseReference dRef = database.getReference(userUID);

    String subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm_quiz);

        TextView questionText = (TextView) findViewById(R.id.questionTextView);
        String subject;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            subject = extras.getString("subject");
        }
        else {
            subject = "extras were NULL";
        }
        if (subject.equals("bubble")){
            questionText.setText(getResources().getString(R.string.bubble_sort_question_1));
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
                ans1ButtonPressed();
            }
        });
        tButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tButton1.setBackgroundColor(0xFFFFAAAA);
                tButton2.setBackgroundColor(0xFFFF0000);
                tButton3.setBackgroundColor(0xFFFFAAAA);
                tButton4.setBackgroundColor(0xFFFFAAAA);
                ans2ButtonPressed();
            }
        });
        tButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tButton1.setBackgroundColor(0xFFFFAAAA);
                tButton2.setBackgroundColor(0xFFFFAAAA);
                tButton3.setBackgroundColor(0xFFFF0000);
                tButton4.setBackgroundColor(0xFFFFAAAA);
                ans2ButtonPressed();
            }
        });
        tButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tButton1.setBackgroundColor(0xFFFFAAAA);
                tButton2.setBackgroundColor(0xFFFFAAAA);
                tButton3.setBackgroundColor(0xFFFFAAAA);
                tButton4.setBackgroundColor(0xFFFF0000);
                ans2ButtonPressed();
            }
        });
    }

    void ans1ButtonPressed(){
        //dRef.child("child1").setValue("Muh Vahlyews");
    }
    void ans2ButtonPressed(){
        //dRef.child("child1").setValue("Muh Vahlyews");
    }

}
