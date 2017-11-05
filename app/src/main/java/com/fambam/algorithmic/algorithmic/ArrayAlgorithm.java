package com.fambam.algorithmic.algorithmic;

import android.support.constraint.ConstraintSet;

/**
 * Created by Guthrie on 11/4/2017.
 */

public abstract class ArrayAlgorithm extends Algorithm {
    int[] ordering;
    public ConstraintSet initialize(ConstraintSet baseSet, int[] imageIds, int[] ordering) {
        this.imageIds = imageIds;
        this.ordering = ordering;

        for (int i = 0; i < this.imageIds.length; i++) {
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
    public abstract ConstraintSet next(ConstraintSet currentSet);
    public abstract boolean hasNext();
}
