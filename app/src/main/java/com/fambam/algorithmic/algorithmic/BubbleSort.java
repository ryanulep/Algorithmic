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
    private boolean is_sorted = false;
    private boolean has_swapped = false;
    private boolean is_swap_phase = true;

    public BubbleSort() {
        super();
    }

    @Override
    public ConstraintSet initialize(ConstraintSet baseSet, int[] imageIds, int[] ordering) {
        ConstraintSet initSet = super.initialize(baseSet, imageIds, ordering);
        i_image = imageIds[ordering.length];
        j_image = imageIds[ordering.length + 1];
        k_image = imageIds[ordering.length + 2];
        i_index = 0;
        j_index = 0;
        this.updateIndices(initSet);
        size = this.ordering.length;

        return initSet;
    }
    
    public ConstraintSet next(ConstraintSet set) {
        // Swap conditions
        if (is_swap_phase) {
            if (ordering[j_index] > ordering[j_index+1]) {
                int temp = ordering[j_index];
                ordering[j_index] = ordering[j_index + 1];
                ordering[j_index + 1] = temp;
                temp = imageIds[j_index];
                imageIds[j_index] = imageIds[j_index + 1];
                imageIds[j_index + 1] = temp;
                has_swapped = true;
                this.buildChain(set);
            }
        }
        else {
            j_index++;
            int i_actual = this.size - i_index - 1;
            if (j_index == i_actual) {
                if (!has_swapped) {
                    is_sorted = true;
                    return set;
                } else {
                    i_index++;
                    j_index = 0;
                    has_swapped = false;
                }
            }
        }
        this.updateIndices(set);
        is_swap_phase = !is_swap_phase;
        return set;
    }
    
    public boolean hasNext() {
        return !(is_sorted);
    }

    private ConstraintSet updateIndices(ConstraintSet set) {
        int target;
        int i_actual = imageIds.length - i_index - 1;
        this.updateIndex(set, j_image, imageIds[j_index]);
        this.updateIndex(set, k_image, imageIds[j_index + 1]);
        if (j_index + 1 == i_actual) {
            target = k_image;
        }
        else {
            target = imageIds[i_actual];
        }
        set = this.updateIndex(set, i_image, target);
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
        is_sorted = (in.readInt() == 1);
    }

}