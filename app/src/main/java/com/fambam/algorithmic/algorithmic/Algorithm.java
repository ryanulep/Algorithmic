package com.fambam.algorithmic.algorithmic;

import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Guthrie on 11/4/2017.
 */

public abstract class Algorithm {
    AppCompatActivity parent;
    int[] imageIds;
    int[] dataIds;
    int[] data;
    abstract void initialize(AppCompatActivity parent, ConstraintSet baseSet,
                             int[] imageIds, int[] dataIds, int[] data);
    abstract void next(ConstraintSet currentSet);
    abstract boolean hasNext();
}
