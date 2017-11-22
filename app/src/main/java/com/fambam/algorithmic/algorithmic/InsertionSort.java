package com.fambam.algorithmic.algorithmic;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.constraint.ConstraintSet;
import android.view.View;

/**
 * Created by Guthrie on 11/11/2017.
 */

public class InsertionSort extends ArrayAlgorithm implements Parcelable {
    private int i_index;
    private int j_index;
    private int i_image;
    private int j_image;
    private int k_image;
    private boolean is_sorted;
    private boolean is_swap_phase;
    private boolean has_swapped;

    public InsertionSort() {
        super();
        this.setID(3);
    }

    public void initialize(View parent, ConstraintSet baseSet,
                           int[] imageIds, int[] dataIds, int[] data) {
        super.initialize(parent, baseSet, imageIds, dataIds, data);

        reset(baseSet);
    }

    @Override
    void reset(ConstraintSet set) {
        super.reset(set);
        i_image = getImageIdAt(0);
        j_image = getImageIdAt(1);
        k_image = getImageIdAt(2);
        is_sorted = false;
        is_swap_phase = true;
        has_swapped = false;
    }

    void resetIndices() {
        i_index = 1;
        j_index = 1;
    }

    public void next(ConstraintSet set) {
        AlgorithmState state = this.getState();
        this.states.push(state);
        if (is_swap_phase) {
            int k_index = j_index - 1;
            if (getDataAt(j_index) < getDataAt(k_index)) {
                swapData(j_index, k_index);
                swapDataIds(j_index, k_index);
                this.buildStructure(set);
                has_swapped = true;
            }
            is_swap_phase = false;
        } else {
            --j_index;
            if (j_index == 0 || !has_swapped) {
                ++i_index;
                if (i_index == size) {
                    --i_index;
                    is_sorted = true;
                } else {
                    is_swap_phase = true;
                }
                j_index = i_index;
            } else {
                is_swap_phase = true;
                has_swapped = false;
            }
        }

        updateSelectors(set);
        updateHighlights();
    }

    public boolean hasNext() {
        return !(is_sorted);
    }

    public boolean isSearchingAlgorithm() {
        return false;
    }

    void updateSelectors(ConstraintSet set) {
        updateIndex(set, i_image, getDataIdAt(i_index));
        int target = i_image;
        if (i_index != j_index) {
            target = getDataIdAt(j_index);
        }
        updateIndex(set, j_image, target);
        updateIndex(set, k_image, getDataIdAt(j_index - 1));
    }

    void updateHighlights() {
        int [] newHighLights;
        newHighLights = this.getHighlights(new int[] {j_index, j_index - 1});
        this.applyHighlightDifference(this.highlights, newHighLights);
        this.highlights = newHighLights;
    }

    public void loadState(AlgorithmState state) {
        i_index = state.selectors[0];
        j_index = state.selectors[1];
        is_sorted = state.flags[0];
        is_swap_phase = state.flags[1];
        has_swapped = state.flags[2];
        this.data = state.data;
        this.dataIds = state.dataIds;
        this.highlights = state.highlights;
    }

    public AlgorithmState getState() {
        int[] selectors = new int[] {i_index, j_index};
        boolean[] flags = new boolean[] {is_sorted, is_swap_phase, has_swapped};
        return new AlgorithmState(selectors, this.highlights.clone(), this.data.clone(),
                                  this.dataIds.clone(), flags);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeIntArray(imageIds);
        out.writeIntArray(dataIds);
        out.writeIntArray(data);
        out.writeIntArray(new int[] {size, i_index, j_index, i_image, j_image, k_image,
                (is_sorted ? 1 : 0), (is_swap_phase ? 1 : 0), (has_swapped ? 1 : 0), algoID});
    }

    public static final Parcelable.Creator<InsertionSort> CREATOR =
            new Parcelable.Creator<InsertionSort>() {
                public InsertionSort createFromParcel(Parcel in) {
                    return new InsertionSort(in);
                }

                public InsertionSort[] newArray(int size) {
                    return new InsertionSort[size];
                }
            };

    private InsertionSort(Parcel in) {
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
        is_swap_phase = (other[7] == 1);
        has_swapped = (other[8] == 1);
        algoID = other[9];
    }
}
