package com.fambam.algorithmic.algorithmic;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.constraint.ConstraintSet;

public class BubbleSort extends ArrayAlgorithm implements Parcelable {
    private int size;
    private int i_index;
    private int j_index;
    private int i_image;
    private int j_image;
    private int k_image;
    private int optimizer;
    private boolean is_sorted;

    public BubbleSort() {
        super();
    }

    @Override
    public ConstraintSet initialize(ConstraintSet baseSet, int[] imageIds, int[] ordering) {
        ConstraintSet initSet = super.initialize(baseSet, imageIds, ordering);
        i_image = imageIds[ordering.length];
        j_image = imageIds[ordering.length + 1];
        k_image = imageIds[ordering.length + 2];
        initSet = this.updateIndex(initSet, i_image, imageIds[0]);
        initSet = this.updateIndex(initSet, j_image, i_image);
        initSet = this.updateIndex(initSet, k_image, imageIds[1]);
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

        if (j_index == ordering.length-1) {
            i_index++;
            j_index = 0;
        }

        if (swapped) {
            this.buildChain(set);
        }
        set = this.updateIndices(set);
        return set;
    }
    
    public boolean hasNext() {
        return !(i_index == ordering.length || (optimizer == ordering.length-1 && is_sorted));
    }

    private ConstraintSet updateIndices(ConstraintSet set) {
        int target;
        this.updateIndex(set, i_image, imageIds[i_index]);
        if (j_index == i_index) {
            target = i_image;
        }
        else {
            target = imageIds[j_index];
        }
        set = this.updateIndex(set, j_image, target);
        if (j_index + 1 == i_index) {
            target = i_image;
        }
        else {
            target = imageIds[j_index + 1];
        }
        set = this.updateIndex(set, k_image, target);
        return set;
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