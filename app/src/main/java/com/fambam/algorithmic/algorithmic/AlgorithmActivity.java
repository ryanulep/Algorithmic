package com.fambam.algorithmic.algorithmic;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by Guthrie on 11/1/2017.
 */

public class AlgorithmActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics);

        //Get the drawable identifiers from the intent
        String key = getString(R.string.bubble_sort);
        Intent callingIntent = getIntent();
        int[] drawableIds = callingIntent.getIntArrayExtra(key);

        //TODO: Where is the image?
        //Construct the ImageViews from passed in drawableIds and add them to the ConstraintView
        ConstraintLayout thisLayout = findViewById(R.id.graphics_layout);
        ConstraintSet set = new ConstraintSet(); //TODO: what is this?
        set.clone(thisLayout);

        ImageView[] images = new ImageView[drawableIds.length];
        int[] imageIds = new int[images.length];

        for (int i = 0; i < images.length; i++) {
            imageIds[i] = i+100;
            ImageView image = new ImageView(this);
            image.setId(imageIds[i]);
            image.setImageResource(drawableIds[i]);
            thisLayout.addView(image);
            images[i] = image;
            set.constrainWidth(image.getId(), 100);
            set.constrainHeight(image.getId(), 100);
        }

        set.createHorizontalChain(
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT,
                imageIds,
                null,
                ConstraintSet.CHAIN_SPREAD
        );
        set.applyTo(thisLayout);
    }
}
