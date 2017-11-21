package com.fambam.algorithmic.algorithmic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AlgorithmSummary extends AppCompatActivity {

    private ImageView image;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    DatabaseReference dRef = database.getReference(UID);
    String flags = "0000000000000000";

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

        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    flags = dataSnapshot.child(UID).getValue(userData.class).getFlags();
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


        if (subject.equals("bubble")){
            image.setImageResource(R.drawable.cs_summary);

            String flagsUpdate = '1'+flags.substring(1, 16);
            dRef.child("flags").setValue(flagsUpdate);
        }
        else if (subject.equals("selection")){
            image.setImageResource(R.drawable.cs_summary);

            String flagsUpdate = flags.substring(0,4)+'1'+flags.substring(5, 16);
            dRef.child("flags").setValue(flagsUpdate);
        }
        else if (subject.equals("insertion")){
            image.setImageResource(R.drawable.cs_summary);

            String flagsUpdate = flags.substring(0,8)+'1'+flags.substring(9, 16);
            dRef.child("flags").setValue(flagsUpdate);
        }
        else if (subject.equals("ls")) {
            image.setImageResource(R.drawable.cs_summary);

            String flagsUpdate = flags.substring(0,12)+'1'+flags.substring(13, 16);
            dRef.child("flags").setValue(flagsUpdate);
        }
    }
}

