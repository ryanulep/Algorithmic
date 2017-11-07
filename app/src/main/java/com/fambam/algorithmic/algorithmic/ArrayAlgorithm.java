package com.fambam.algorithmic.algorithmic;

import android.support.constraint.ConstraintSet;

/**
 * Created by Guthrie on 11/4/2017.
 */

public abstract class ArrayAlgorithm extends Algorithm {
    int[] ordering;
    public ConstraintSet initialize(ConstraintSet baseSet, int[] imageIds, int[] ordering) {
        this.imageIds = new int[ordering.length];
        this.ordering = ordering;

        for (int i = 0; i < this.ordering.length; i++) {
            this.imageIds[i] = imageIds[i];
            baseSet.constrainWidth(this.imageIds[i], 100);
            baseSet.constrainHeight(this.imageIds[i], 100);
            baseSet.centerVertically(this.imageIds[i], ConstraintSet.PARENT_ID);
        }

        return this.buildChain(baseSet);
    }
    public abstract ConstraintSet next(ConstraintSet currentSet);
    public abstract boolean hasNext();

    final ConstraintSet buildChain(ConstraintSet currentSet) {
        currentSet.createHorizontalChain(
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT,
                this.imageIds,
                null,
                ConstraintSet.CHAIN_SPREAD);
        return currentSet;
    }

    final ConstraintSet updateIndex(ConstraintSet currentSet, int indexId, int targetId) {
        currentSet.clear(indexId);
        currentSet.constrainWidth(indexId, 100);
        currentSet.constrainHeight(indexId, 100);
        currentSet.connect(indexId, ConstraintSet.BOTTOM, targetId, ConstraintSet.TOP);
        currentSet.connect(indexId, ConstraintSet.LEFT, targetId, ConstraintSet.LEFT);
        return currentSet;
    }
}
