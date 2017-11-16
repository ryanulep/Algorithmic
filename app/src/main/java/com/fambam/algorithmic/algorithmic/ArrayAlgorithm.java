package com.fambam.algorithmic.algorithmic;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.constraint.ConstraintSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Guthrie on 11/4/2017.
 */

public abstract class ArrayAlgorithm extends Algorithm {
    public void initialize(View parent, ConstraintSet baseSet,
                           int[] imageIds, int[] dataIds, int[] data) {
        this.parent = parent;
        this.imageIds = imageIds;
        this.dataIds = dataIds;
        this.data = data;
        this.setSizeConstaints(baseSet, imageIds, 100, 100);
        this.setSizeConstaints(baseSet, dataIds, 100, 100);
        this.initializeDataViews();
        this.buildChain(baseSet);

    }
    public abstract void next(ConstraintSet currentSet);
    public abstract boolean hasNext();
    public abstract boolean isSortingAlgorithm();

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

    final void setDataIdAt(int dataIdLoc, int dataId) {
        this.dataIds[dataIdLoc] = dataId;
    }

    final int getDataAt(int dataLoc) {
        return this.data[dataLoc];
    }

    final void setDataAt(int dataLoc, int data) {
        this.data[dataLoc] = data;
    }

    final int getImageIdAt(int imageIdLoc) {
        return this.imageIds[imageIdLoc];
    }

    final void swapData(int dataLoc1, int dataLoc2) {
        int temp = this.getDataAt(dataLoc1);
        this.setDataAt(dataLoc1, this.getDataAt(dataLoc2));
        this.setDataAt(dataLoc2, temp);
    }

    final void swapDataIds(int dataIdLoc1, int dataIdLoc2) {
        int temp = this.getDataIdAt(dataIdLoc1);
        this.setDataIdAt(dataIdLoc1, this.getDataIdAt(dataIdLoc2));
        this.setDataIdAt(dataIdLoc2, temp);
    }

    final int getDataIdAt(int dataIdLoc) {
        return this.dataIds[dataIdLoc];
    }

    final void select(int dataIdLoc) {
        DataView dView = parent.findViewById(dataIdLoc);
        dView.getBackground();
        updates.addFirst(new UpdateTextViewBackground(Color.CYAN, dView));
    }

    final void select(int dataIdLoc, int colorTo) {
        DataView dView = parent.findViewById(dataIdLoc);
        dView.getBackground();
        updates.addFirst(new UpdateTextViewBackground(colorTo, dView));
    }

    final void deselect(int dataIdLoc) {
        DataView dView = parent.findViewById(dataIdLoc);
        updates.addFirst(new UpdateTextViewBackground(Color.WHITE, dView));
    }

    private final void initializeDataViews() {
        for (int dataId : dataIds) {
            DataView dView = parent.findViewById(dataId);
            dView.setTextColor(Color.BLACK);
            GradientDrawable gd = new GradientDrawable();
            gd.setColor(Color.WHITE);
            gd.setStroke(5, Color.BLACK);
            gd.setCornerRadius(4);
            dView.setBackground(gd);
        }
    }
}
