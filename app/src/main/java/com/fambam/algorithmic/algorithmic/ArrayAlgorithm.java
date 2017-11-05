package com.fambam.algorithmic.algorithmic;

import android.support.constraint.ConstraintSet;

/**
 * Created by Guthrie on 11/4/2017.
 */

public abstract class ArrayAlgorithm implements Algorithm {
    int[] imageIds;

    public ConstraintSet initialize(ConstraintSet baseSet, int[] imageIds) {
        this.imageIds = imageIds;

        for (int i = 0; i < this.imageIds.length; i++) {
            this.imageIds[i] = i+100;
            baseSet.constrainWidth(imageIds[i], 100);
            baseSet.constrainHeight(imageIds[i], 100);
        }

        baseSet.createHorizontalChain(
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT,
                this.imageIds,
                null,
                ConstraintSet.CHAIN_SPREAD
        );

        return baseSet;
    }
    public abstract ConstraintSet next();

}
