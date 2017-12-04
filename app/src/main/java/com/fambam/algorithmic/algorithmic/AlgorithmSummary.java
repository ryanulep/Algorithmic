package com.fambam.algorithmic.algorithmic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ImageView;

public class AlgorithmSummary extends AppCompatActivity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm_summary);

        WebView wv = (WebView) findViewById(R.id.summary_webview);
        wv.getSettings().setBuiltInZoomControls(true);

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
            String imageUrl =  "file:///android_asset/bubble_sort_summary_pres_v2.png";
            wv.setInitialScale(135);
            wv.loadUrl(imageUrl);
            //image.setImageResource(R.drawable.bubble_sort_summary_pres_v2);
        }
        else if (subject.equals("selection")){
            image.setImageResource(R.drawable.selection_summary);
        }
        else if (subject.equals("insertion")){
            image.setImageResource(R.drawable.insertion_summary);
        }
        else if (subject.equals("ls")) {
            image.setImageResource(R.drawable.ls_summary);
        }
    }
}

