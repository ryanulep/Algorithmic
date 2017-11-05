package com.fambam.algorithmic.algorithmic;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.constraint.ConstraintSet;

/**
 * Created by Guthrie on 11/4/2017.
 */

public abstract class Algorithm {
    int[] imageIds;
    abstract ConstraintSet initialize(ConstraintSet baseSet, int[] imageIds, int[] ordering);
    abstract ConstraintSet next(ConstraintSet currentSet);
    abstract boolean hasNext();
}
