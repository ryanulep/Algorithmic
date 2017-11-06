package com.fambam.algorithmic.algorithmic;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.constraint.ConstraintSet;

public class BubbleSort extends ArrayAlgorithm implements Parcelable {
    private int size;
    private int i_index;
    private int j_index;
    private int optimizer;
    private boolean is_sorted;

    public BubbleSort() {
        super();
    }

    @Override
    public ConstraintSet initialize(ConstraintSet baseSet, int[] imageIds, int[] ordering) {
        ConstraintSet initSet = super.initialize(baseSet, imageIds, ordering);
        size = this.ordering.length;
        i_index = 0;
        j_index = 0;
        optimizer = 0;
        return initSet;
    }
    
    public ConstraintSet next(ConstraintSet set) {
        is_sorted = true;
        boolean swapped = false;
        //if (i_index == ordering.length) { return; }
        if (j_index == ordering.length-1) {
            i_index++;
            j_index = 0;
        }
        // Swap conditions
        if (ordering[j_index] > ordering[j_index+1]) {
            int temp = ordering[j_index];
            ordering[j_index] = ordering[j_index+1];
            ordering[j_index+1] = temp;
            temp = imageIds[j_index];
            imageIds[j_index] = imageIds[j_index+1];
            imageIds[j_index+1] = temp;
            is_sorted = false;
            swapped = true;
            optimizer = 0;
        }
        // If BubbleSort has not swapped anything in length-1 attempts,
        // then it is has finished.
        //if (optimizer == ordering.length-1 && is_sorted) { return; }
        optimizer++;
        j_index++;

        if (swapped) {
            set.createHorizontalChain(
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.LEFT,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.RIGHT,
                    this.imageIds,
                    null,
                    ConstraintSet.CHAIN_SPREAD
            );
        }
        return set;
    }
    
    public boolean hasNext() {
        return !(i_index == ordering.length || (optimizer == ordering.length-1 && is_sorted));
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeIntArray(imageIds);
        out.writeIntArray(ordering);
        out.writeInt(size);
        out.writeInt(i_index);
        out.writeInt(j_index);
        out.writeInt(optimizer);
        out.writeInt((is_sorted ? 1 : 0));
    }

    public static final Parcelable.Creator<BubbleSort> CREATOR =
            new Parcelable.Creator<BubbleSort>() {
        public BubbleSort createFromParcel(Parcel in) {
            return new BubbleSort(in);
        }

        public BubbleSort[] newArray(int size) {
            return new BubbleSort[size];
        }
    };

    private BubbleSort(Parcel in) {
        imageIds = in.createIntArray();
        ordering = in.createIntArray();
        size = in.readInt();
        i_index = in.readInt();
        j_index = in.readInt();
        optimizer = in.readInt();
        is_sorted = (in.readInt() == 1);
    }

}