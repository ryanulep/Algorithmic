package com.fambam.algorithmic.algorithmic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AlgorithmSummary extends AppCompatActivity {

    private ImageView image;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String userUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    DatabaseReference dRef = database.getReference(userUID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm_summary);

        image = findViewById(R.id.summaryImageView);

        String subject;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            subject = extras.getString("subject" );
        }
        else {
            subject = "extras were NULL";
        }

        if (subject.equals("bubble")){
            image.setImageResource(R.drawable.cs_summary);
            dRef.child("bSummary").setValue("1");
        }
        else if (subject.equals("selection")){
            image.setImageResource(R.drawable.selection_summary);
            dRef.child("sSummary").setValue("1");
        }
        else if (subject.equals("insertion")){
            image.setImageResource(R.drawable.insertion_summary);
            dRef.child("iSummary").setValue("1");
        }
        else if (subject.equals("ls")) {
            image.setImageResource(R.drawable.ls_summary);
            dRef.child("lsSummary").setValue("1");
        }
    }
}

