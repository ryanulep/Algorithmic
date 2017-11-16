package com.fambam.algorithmic.algorithmic;

import android.app.Activity;
import android.support.constraint.ConstraintSet;
import android.view.View;

import java.util.LinkedList;

/**
 * Created by Guthrie on 11/4/2017.
 */

public abstract class Algorithm {
    View parent;
    int[] imageIds;
    int[] dataIds;
    int[] data;
    int[] highlights;
    LinkedList<UIUpdate> updates = new LinkedList<>();
    abstract void initialize(View parent, ConstraintSet baseSet,
                             int[] imageIds, int[] dataIds, int[] data);
    abstract void next(ConstraintSet currentSet);
    abstract boolean hasNext();

    final void applyUpdates() {
        while(!updates.isEmpty()) {
            updates.getLast().apply();
            updates.removeLast();
        }
    }
}
