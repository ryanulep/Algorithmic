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
    String flags;

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

                flags = dataSnapshot.child(UID).getValue(userData.class).getFlags();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Could not read value
                // User logger??
            }
        });


        if (subject.equals("bubble")){
            image.setImageResource(R.drawable.cs_summary);

//            StringBuilder flagUpdate = new StringBuilder(flags);
//            flagUpdate.setCharAt(0, '1');
//            dRef.child("flags").setValue(flagUpdate);
        }
        else if (subject.equals("selection")){
            image.setImageResource(R.drawable.cs_summary);

//            StringBuilder flagUpdate = new StringBuilder(flags);
//            flagUpdate.setCharAt(4, '1');
//            dRef.child("flags").setValue(flagUpdate);
        }
        else if (subject.equals("insertion")){
            image.setImageResource(R.drawable.cs_summary);

//            StringBuilder flagUpdate = new StringBuilder(flags);
//            flagUpdate.setCharAt(8, '1');
//            dRef.child("flags").setValue(flagUpdate);
        }
        else if (subject.equals("ls")) {
            image.setImageResource(R.drawable.cs_summary);

//            StringBuilder flagUpdate = new StringBuilder(flags);
//            flagUpdate.setCharAt(12, '1');
//            dRef.child("flags").setValue(flagUpdate);
        }
    }
}

