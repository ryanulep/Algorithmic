package com.fambam.algorithmic.algorithmic;

import android.support.constraint.ConstraintSet;

/**
 * Created by Guthrie on 11/4/2017.
 */

public abstract class ArrayAlgorithm extends Algorithm {
    public void initialize(ConstraintSet baseSet, int[] imageIds, int[] dataIds, int[] data) {
        this.imageIds = imageIds;
        this.dataIds = dataIds;
        this.data = data;
        this.setSizeConstaints(baseSet, imageIds, 100, 100);
        this.setSizeConstaints(baseSet, dataIds, 100, 100);
        this.buildChain(baseSet);

    }
    public abstract void next(ConstraintSet currentSet);
    public abstract boolean hasNext();

    final void buildChain(ConstraintSet currentSet) {
        currentSet.createHorizontalChain(
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT,
                this.dataIds,
                null,
                ConstraintSet.CHAIN_SPREAD);
    }

    final void updateIndex(ConstraintSet currentSet, int indexId, int targetId) {
        currentSet.clear(indexId);
        currentSet.constrainWidth(indexId, 100);
        currentSet.constrainHeight(indexId, 100);
        currentSet.connect(indexId, ConstraintSet.BOTTOM, targetId, ConstraintSet.TOP);
        currentSet.connect(indexId, ConstraintSet.LEFT, targetId, ConstraintSet.LEFT);
    }

    final void setSizeConstaints(ConstraintSet currentSet, int[] ids, int width, int height) {
        for (int id : ids) {
            currentSet.constrainWidth(id, width);
            currentSet.constrainHeight(id, height);
            currentSet.centerVertically(id, ConstraintSet.PARENT_ID);
        }
    }
}
