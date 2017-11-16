package com.fambam.algorithmic.algorithmic;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.constraint.ConstraintSet;
import android.view.View;

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
    public void initialize(View parent, ConstraintSet baseSet,
                           int[] imageIds, int[] dataIds, int[] data) {
        super.initialize(parent, baseSet, imageIds, dataIds, data);
        i_image = getImageIdAt(0);
        j_image = getImageIdAt(1);
        k_image = getImageIdAt(2);
        i_index = 0;
        j_index = 0;
        size = data.length;
        updateIndices(baseSet);
        //select(getDataIdAt(j_index));
        //select(getDataIdAt(j_index + 1));
        applyUpdates();
    }
    
    public void next(ConstraintSet set) {
        // Swap conditions
        if (is_swap_phase) {
            int k_index = j_index + 1;
            if (getDataAt(j_index) > getDataAt(k_index)) {
                swapData(j_index, k_index);
                swapDataIds(j_index, k_index);
                has_swapped = true;
                buildChain(set);
            }
        }
        else {
            j_index++;
            int i_actual = size - i_index - 1;
            if (j_index == i_actual) {
                if (!has_swapped) {
                    is_sorted = true;
                    this.deselectAll();
                    return;
                }
                else {
                    i_index++;
                    j_index = 0;
                    has_swapped = false;
                }
            }
        }
        updateIndices(set);
        is_swap_phase = !is_swap_phase;
    }
    
    public boolean hasNext() {
        return !(is_sorted);
    }

    public boolean isSortingAlgorithm() {
        return false;
    }

    private void updateIndices(ConstraintSet set) {
        int[] newHighlights = this.getHighlights(new int[] {j_index, j_index + 1});
        this.applyHighlightDifference(newHighlights);
        int target;
        int i_actual = dataIds.length - i_index - 1;
        int k_index = j_index + 1;
        updateIndex(set, j_image, getDataIdAt(j_index));
        updateIndex(set, k_image, getDataIdAt(k_index));
        if (k_index == i_actual) {
            target = k_image;
        }
        else {
            target = getDataIdAt(i_actual);
        }
        updateIndex(set, i_image, target);
        this.highlights = newHighlights;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeIntArray(imageIds);
        out.writeIntArray(dataIds);
        out.writeIntArray(data);
        out.writeIntArray(new int[] {size, i_index, j_index, i_image, j_image, k_image,
        (is_sorted ? 1 : 0), (has_swapped ? 1 : 0), (is_swap_phase ? 1 : 0)});
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
        dataIds = in.createIntArray();
        data = in.createIntArray();
        int[] other = in.createIntArray();
        size = other[0];
        i_index = other[1];
        j_index = other[2];
        i_image = other[3];
        j_image = other[4];
        k_image = other[5];
        is_sorted = (other[6] == 1);
        has_swapped = (other[7] == 1);
        is_swap_phase = (other[8] == 1);
    }
}