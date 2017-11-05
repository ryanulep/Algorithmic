package com.fambam.algorithmic.algorithmic;

import android.os.Parcelable;
import android.support.constraint.ConstraintSet;

/**
 * Created by Guthrie on 11/4/2017.
 */

public interface Algorithm extends Parcelable {
    ConstraintSet initialize(ConstraintSet baseSet, int[] imageIds);
    ConstraintSet next();
}
