package com.fambam.algorithmic.algorithmic;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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

  FirebaseDatabase database = FirebaseDatabase.getInstance();
  String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
  DatabaseReference dRef = database.getReference();
  int score1;
  int trys1;
  int score2;
  int trys2;
  int score3;
  int trys3;
  int score4;
  int trys4;
  int randIndex;
  int buttAns = 0;
  String subject;

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

    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      subject = extras.getString("subject");
    } else {
      subject = "extras were NULL";
    }

    Resources res = getResources();
    randIndex = new Random().nextInt(5);
    if (subject.equals("bubble")) {
      String[][] qList = {
          res.getStringArray(R.array.b_q1),
          res.getStringArray(R.array.b_q2),
          res.getStringArray(R.array.b_q3),
          res.getStringArray(R.array.b_q4),
          res.getStringArray(R.array.b_q5)
      };
      randIndex = new Random().nextInt(5);
      String[] currentQ = qList[randIndex];
      questionText.setText(currentQ[0]);
      ArrayList<String> ansList = new ArrayList<>();
      for (int i = 4; i > 0; i--) {
        ansList.add(currentQ[i]);
      }
      for (int i = 4; i > 0; i--) {
        randIndex = new Random().nextInt(i);
        bList.get(i - 1).setText(ansList.get(randIndex));
        ansList.remove(randIndex);
      }
      currentAns = currentQ[1];
    } else if (subject.equals("selection")) {
      String[][] qList = {
          res.getStringArray(R.array.s_q1),
          res.getStringArray(R.array.s_q2),
          res.getStringArray(R.array.s_q3),
          res.getStringArray(R.array.s_q4),
          res.getStringArray(R.array.s_q5)
      };
      randIndex = new Random().nextInt(5);
      String[] currentQ = qList[randIndex];
      questionText.setText(currentQ[0]);
      ArrayList<String> ansList = new ArrayList<>();
      for (int i = 4; i > 0; i--) {
        ansList.add(currentQ[i]);
      }
      for (int i = 4; i > 0; i--) {
        randIndex = new Random().nextInt(i);
        bList.get(i - 1).setText(ansList.get(randIndex));
        ansList.remove(randIndex);
      }
      currentAns = currentQ[1];
    } else if (subject.equals("insertion")) {
      String[][] qList = {
          res.getStringArray(R.array.i_q1),
          res.getStringArray(R.array.i_q2),
          res.getStringArray(R.array.i_q3),
          res.getStringArray(R.array.i_q4),
          res.getStringArray(R.array.i_q5)
      };
      randIndex = new Random().nextInt(5);
      String[] currentQ = qList[randIndex];
      questionText.setText(currentQ[0]);
      ArrayList<String> ansList = new ArrayList<>();
      for (int i = 4; i > 0; i--) {
        ansList.add(currentQ[i]);
      }
      for (int i = 4; i > 0; i--) {
        randIndex = new Random().nextInt(i);
        bList.get(i - 1).setText(ansList.get(randIndex));
        ansList.remove(randIndex);
      }
      currentAns = currentQ[1];
    } else if (subject.equals("ls")) {
      String[][] qList = {
          res.getStringArray(R.array.ls_q1),
          res.getStringArray(R.array.ls_q2),
          res.getStringArray(R.array.ls_q3),
          res.getStringArray(R.array.ls_q4),
          res.getStringArray(R.array.ls_q5)
      };
      randIndex = new Random().nextInt(5);
      String[] currentQ = qList[randIndex];
      questionText.setText(currentQ[0]);
      ArrayList<String> ansList = new ArrayList<>();
      for (int i = 4; i > 0; i--) {
        ansList.add(currentQ[i]);
      }
      for (int i = 4; i > 0; i--) {
        randIndex = new Random().nextInt(i);
        bList.get(i - 1).setText(ansList.get(randIndex));
        ansList.remove(randIndex);
      }
      currentAns = currentQ[1];
    }

    tButton1.setBackgroundResource(R.drawable.rounded_quiz_button_default);
    tButton2.setBackgroundResource(R.drawable.rounded_quiz_button_default);
    tButton3.setBackgroundResource(R.drawable.rounded_quiz_button_default);
    tButton4.setBackgroundResource(R.drawable.rounded_quiz_button_default);

    tButton1.setOnClickListener(
        new OnClickListener() {
          @Override
          public void onClick(View view) {
            tButton1.setBackgroundResource(R.drawable.rounded_quiz_button_wrong);
            tButton2.setBackgroundResource(R.drawable.rounded_quiz_button_default);
            tButton3.setBackgroundResource(R.drawable.rounded_quiz_button_default);
            tButton4.setBackgroundResource(R.drawable.rounded_quiz_button_default);
            genericPressed(1);
          }
        });
    tButton2.setOnClickListener(
        new OnClickListener() {
          @Override
          public void onClick(View view) {
            tButton1.setBackgroundResource(R.drawable.rounded_quiz_button_default);
            tButton2.setBackgroundResource(R.drawable.rounded_quiz_button_wrong);
            tButton3.setBackgroundResource(R.drawable.rounded_quiz_button_default);
            tButton4.setBackgroundResource(R.drawable.rounded_quiz_button_default);
            genericPressed(2);
          }
        });
    tButton3.setOnClickListener(
        new OnClickListener() {
          @Override
          public void onClick(View view) {
            tButton1.setBackgroundResource(R.drawable.rounded_quiz_button_default);
            tButton2.setBackgroundResource(R.drawable.rounded_quiz_button_default);
            tButton3.setBackgroundResource(R.drawable.rounded_quiz_button_wrong);
            tButton4.setBackgroundResource(R.drawable.rounded_quiz_button_default);
            genericPressed(3);
          }
        });
    tButton4.setOnClickListener(
        new OnClickListener() {
          @Override
          public void onClick(View view) {
            tButton1.setBackgroundResource(R.drawable.rounded_quiz_button_default);
            tButton2.setBackgroundResource(R.drawable.rounded_quiz_button_default);
            tButton3.setBackgroundResource(R.drawable.rounded_quiz_button_default);
            tButton4.setBackgroundResource(R.drawable.rounded_quiz_button_wrong);
            genericPressed(4);
          }
        });

    dRef.addValueEventListener(
        new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {

            try {
              score1 = dataSnapshot.child(UID).getValue(userData.class).getB_quiz_score();
              trys1 = dataSnapshot.child(UID).getValue(userData.class).getB_trys();
              score2 = dataSnapshot.child(UID).getValue(userData.class).getS_quiz_score();
              trys2 = dataSnapshot.child(UID).getValue(userData.class).getS_trys();
              score3 = dataSnapshot.child(UID).getValue(userData.class).getI_quiz_score();
              trys3 = dataSnapshot.child(UID).getValue(userData.class).getI_trys();
              score4 = dataSnapshot.child(UID).getValue(userData.class).getLs_quiz_score();
              trys4 = dataSnapshot.child(UID).getValue(userData.class).getLs_trys();
            } catch (Exception e) {
              e.printStackTrace();
            }
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {
            // Could not read value
            // User logger??
          }
        });
  }

  private void genericPressed(int butt) {
    if (tButton1.getText().equals(currentAns)) {
      buttAns = 1;
    } else if (tButton2.getText().equals(currentAns)) {
      buttAns = 2;
    } else if (tButton3.getText().equals(currentAns)) {
      buttAns = 3;
    } else if (tButton4.getText().equals(currentAns)) {
      buttAns = 4;
    }

    if (butt == buttAns) {
      switch (buttAns) {
        case 1:
          tButton1.setBackgroundResource(R.drawable.rounded_quiz_button_right);
          createExitButton(butt);
          break;
        case 2:
          tButton2.setBackgroundResource(R.drawable.rounded_quiz_button_right);
          createExitButton(butt);
          break;
        case 3:
          tButton3.setBackgroundResource(R.drawable.rounded_quiz_button_right);
          createExitButton(butt);
          break;
        case 4:
          tButton4.setBackgroundResource(R.drawable.rounded_quiz_button_right);
          createExitButton(butt);
          break;
      }
    } else {
      correctText.setText("Try again");
      switch (subject) {
        case "bubble":
          trys1 += 1;
          dRef.child(UID).child("b_trys").setValue(trys1);
          break;
        case "selection":
          trys2 += 1;
          dRef.child(UID).child("s_trys").setValue(trys2);
          break;
        case "insertion":
          trys3 += 1;
          dRef.child(UID).child("i_trys").setValue(trys3);
          break;
        case "ls":
          trys4 += 1;
          dRef.child(UID).child("ls_trys").setValue(trys4);
          break;
      }
    }
  }

  private void createExitButton(int butt) {
    correctText.setText("CORRECT!");

    switch (subject) {
      case "bubble":
        trys1 += 1;
        dRef.child(UID).child("b_trys").setValue(trys1);
        score1 += 1;
        dRef.child(UID).child("b_quiz_score").setValue(score1);
        break;
      case "selection":
        trys2 += 1;
        dRef.child(UID).child("s_trys").setValue(trys2);
        score2 += 1;
        dRef.child(UID).child("s_quiz_score").setValue(score2);
        break;
      case "insertion":
        trys3 += 1;
        dRef.child(UID).child("i_trys").setValue(trys3);
        score3 += 1;
        dRef.child(UID).child("i_quiz_score").setValue(score3);
        break;
      case "ls":
        trys4 += 1;
        dRef.child(UID).child("ls_trys").setValue(trys4);
        score4 += 1;
        dRef.child(UID).child("ls_quiz_score").setValue(score4);
        break;
    }

    tButton1.setEnabled(false);
    tButton2.setEnabled(false);
    tButton3.setEnabled(false);
    tButton4.setEnabled(false);

    ImageButton exitB = new ImageButton(this);
    //    exitB.setId(1);
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
    exitB.setOnClickListener(
        new OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
          }
        });
  }
}
