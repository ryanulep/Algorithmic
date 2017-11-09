package com.fambam.algorithmic.algorithmic;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Guthrie on 11/1/2017.
 */

public class AlgorithmActivity extends AppCompatActivity {
    private Algorithm algorithm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics);

        //Get the drawable identifiers from the intent
        String algoKey = getString(R.string.algo_key);
        String drawKey = getString(R.string.drawables);
        String dataKey = getString(R.string.data);
        Intent callingIntent = getIntent();
        this.algorithm = callingIntent.getParcelableExtra(algoKey);
        int[] drawableIds = callingIntent.getIntArrayExtra(drawKey);
        int[] data = callingIntent.getIntArrayExtra(dataKey);

        //Construct the ImageViews from passed in drawableIds and add them to the ConstraintView
        ConstraintLayout baseLayout = findViewById(R.id.graphics_layout);

        int[] imageIds = new int[drawableIds.length];
        int[] dataIds = new int[data.length];

        for (int i = 0; i < data.length; ++i) {
            dataIds[i] = 200+i;
            TextView tView = new TextView(this);
            tView.setId(dataIds[i]);
            tView.setText(Integer.toString(data[i]));
            tView.setLayoutParams(new ConstraintLayout.LayoutParams(120, 120));
            tView.setGravity(Gravity.CENTER);
            tView.setBackgroundColor(Color.WHITE);
            baseLayout.addView(tView);
        }

        for (int i = 0; i < imageIds.length; i++) {
            imageIds[i] = 100+i;
            ImageView image = new ImageView(this);
            image.setId(imageIds[i]);
            image.setImageResource(drawableIds[i]);
            baseLayout.addView(image);
        }

        ConstraintSet set = new ConstraintSet();
        set.clone(baseLayout);
        this.algorithm.initialize(set, imageIds, dataIds, data);
        set.applyTo(baseLayout);
    }

    public void swap(View view) {
        ConstraintLayout baseLayout = findViewById(R.id.graphics_layout);
        ConstraintSet set = new ConstraintSet();
        set.clone(baseLayout);

        if (this.algorithm.hasNext()) {
            this.algorithm.next(set);
        }
        TransitionManager.beginDelayedTransition(baseLayout);
        set.applyTo(baseLayout);
    }
}
