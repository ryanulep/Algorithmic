package com.fambam.algorithmic.algorithmic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class AlgorithmSummary extends AppCompatActivity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm_summary);

        image = findViewById(R.id.simmaryImageView);

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
        }
        else if (subject.equals("selection")){
            image.setImageResource(R.drawable.cs_summary);
        }
        else if (subject.equals("insertion")){
            image.setImageResource(R.drawable.cs_summary);
        }
        else if (subject.equals("ls")) {
            image.setImageResource(R.drawable.cs_summary);
        }
    }
}
