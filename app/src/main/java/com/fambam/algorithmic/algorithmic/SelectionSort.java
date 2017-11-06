package com.fambam.algorithmic.algorithmic;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.constraint.ConstraintSet;

public class SelectionSort extends ArrayAlgorithm implements Parcelable {
    private int size;
    private int i_index;
    private int j_index;
    private int min_index;
    private boolean is_sorted;

    public SelectionSort() { super(); }

    @Override
    public ConstraintSet initialize(ConstraintSet baseSet, int[] imageIds, int[] ordering) {
        ConstraintSet initSet = super.initialize(baseSet, imageIds, ordering);
        size = this.ordering.length;
        i_index = 0;
        j_index = 0;
        min_index = 0;
        return initSet;
    }

    public ConstraintSet next(ConstraintSet set) {
        boolean swapped = false;
        // Condition for outer loop to end if encased in loop.
        // if (i_index == imageIds.length) { return; }

        // Swap at the end of each inner loop once the smallest value's
        // index is found.
        if (j_index == ordering.length) {
            int temp = ordering[i_index];
            ordering[i_index] = ordering[min_index];
            ordering[min_index] = temp;

            temp = imageIds[j_index];
            imageIds[i_index] = imageIds[min_index];
            ordering[min_index] = temp;
            swapped = true;

            // Simulating terminating inner loop conditions.
            j_index = i_index+1;
            i_index++;
            min_index = i_index;
        }

        else if (ordering[j_index] < ordering[min_index]) {
            min_index = j_index;
        }
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
        return !(i_index == imageIds.length);
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
        out.writeInt(min_index);
        out.writeInt((is_sorted ? 1 : 0));
    }

    public static final Parcelable.Creator<SelectionSort> CREATOR =
            new Parcelable.Creator<SelectionSort>() {
        public SelectionSort createFromParcel(Parcel in) {
            return new SelectionSort(in);
        }

        public SelectionSort[] newArray(int size) {
            return new SelectionSort[size];
        }
    };

    private SelectionSort(Parcel in) {
        imageIds = in.createIntArray();
        ordering = in.createIntArray();
        size = in.readInt();
        i_index = in.readInt();
        j_index = in.readInt();
        min_index = in.readInt();
        is_sorted = (in.readInt() == 1);
    }

    /*
    public void Next2() {
        // Condition for outer loop to end if encased in loop.
        if (i_index == imageIds.length || isSorted()) { return; }        

        // Swap at the end of each inner loop once the smallest value's
        // index is found.
        if (j_index == imageIds.length) { 
            int temp = imageIds[i_index];
            imageIds[i_index] = imageIds[min_index];
            imageIds[min_index] = temp;

            // Simulating terminating inner loop conditions.
            j_index = i_index+1;
            i_index++;
            min_index = i_index;
        }

        else if (imageIds[j_index] < imageIds[min_index]) {
            min_index = j_index;
        }

        j_index++;
    }

    private boolean isSorted() {
        is_sorted = true;
        for (int i = 0; i < size-1; i++) {
            if (imageIds[i] > imageIds[i+1]) {
                is_sorted = false;
            }
        }
        return is_sorted;
    }
    */
}
