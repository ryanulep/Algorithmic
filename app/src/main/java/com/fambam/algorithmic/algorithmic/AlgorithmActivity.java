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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics);

        //Get the drawable identifiers from the intent
        String algoKey = getString(R.string.algo_key);
        String drawKey = getString(R.string.drawables);
        Intent callingIntent = getIntent();
        Algorithm algorithm = callingIntent.getParcelableExtra(algoKey);
        int[] drawableIds = callingIntent.getIntArrayExtra(drawKey);

        //Construct the ImageViews from passed in drawableIds and add them to the ConstraintView
        ConstraintLayout baseLayout = findViewById(R.id.graphics_layout);

        this.imageIds = new int[drawableIds.length];

        for (int i = 0; i < this.imageIds.length; i++) {
            this.imageIds[i] = i+100;
            ImageView image = new ImageView(this);
            image.setId(this.imageIds[i]);
            image.setImageResource(drawableIds[i]);
            baseLayout.addView(image);
        }

        ConstraintSet set = new ConstraintSet();
        set.clone(baseLayout);
        ConstraintSet initSet = algorithm.initialize(set, imageIds);
        initSet.applyTo(baseLayout);
    }

    public void swap(View view) {
        ConstraintLayout baseLayout = findViewById(R.id.graphics_layout);
        ConstraintSet set = new ConstraintSet();
        set.clone(baseLayout);

        int temp = this.imageIds[0];
        this.imageIds[0] = this.imageIds[1];
        this.imageIds[1] = temp;

        TransitionManager.beginDelayedTransition(baseLayout);
        set.createHorizontalChain(
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT,
                this.imageIds,
                null,
                ConstraintSet.CHAIN_SPREAD
        );

        set.applyTo(baseLayout);
    }
}
