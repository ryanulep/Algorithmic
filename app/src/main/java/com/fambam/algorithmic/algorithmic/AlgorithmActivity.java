package com.fambam.algorithmic.algorithmic;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Guthrie on 11/1/2017.
 */

public class AlgorithmActivity extends AppCompatActivity {
    private int[] imageIds;
    private Algorithm algorithm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics);

        //Get the drawable identifiers from the intent
        String algoKey = getString(R.string.algo_key);
        String drawKey = getString(R.string.drawables);
        String orderKey = getString(R.string.ordering);
        Intent callingIntent = getIntent();
        this.algorithm = callingIntent.getParcelableExtra(algoKey);
        int[] drawableIds = callingIntent.getIntArrayExtra(drawKey);
        int[] ordering = callingIntent.getIntArrayExtra(orderKey);

        //Construct the ImageViews from passed in drawableIds and add them to the ConstraintView
        ConstraintLayout baseLayout = findViewById(R.id.graphics_layout);

        this.imageIds = new int[drawableIds.length];

        for (int i = 0; i < this.imageIds.length; i++) {
            this.imageIds[i] = 100+i;
            ImageView image = new ImageView(this);
            image.setId(this.imageIds[i]);
            image.setImageResource(drawableIds[i]);
            baseLayout.addView(image);
        }

        ConstraintSet set = new ConstraintSet();
        set.clone(baseLayout);
        ConstraintSet initSet = this.algorithm.initialize(set, imageIds, ordering);
        initSet.applyTo(baseLayout);
    }

    public void swap(View view) {
        ConstraintLayout baseLayout = findViewById(R.id.graphics_layout);
        ConstraintSet set = new ConstraintSet();
        set.clone(baseLayout);

        if (this.algorithm.hasNext()) {
            set = this.algorithm.next(set);
        }
        TransitionManager.beginDelayedTransition(baseLayout);
        set.applyTo(baseLayout);
    }
}
