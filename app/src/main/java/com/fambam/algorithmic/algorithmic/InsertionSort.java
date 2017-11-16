package com.fambam.algorithmic.algorithmic;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.constraint.ConstraintSet;
import android.view.View;

/**
 * Created by Guthrie on 11/11/2017.
 */

public class InsertionSort extends ArrayAlgorithm implements Parcelable {
    private int size;
    private int i_index;
    private int j_index;
    private int i_image;
    private int j_image;
    private int k_image;
    private boolean is_sorted = false;
    private boolean is_swap_phase = false;
    private boolean update_i_phase = true;
    private boolean is_move_k_phase = false;
    private boolean has_swapped = false;

    public InsertionSort() {
        super();
    }

    @Override
    public void initialize(View parent, ConstraintSet baseSet,
                           int[] imageIds, int[] dataIds, int[] data) {
        super.initialize(parent, baseSet,imageIds, dataIds, data);
        i_index = 1;
        j_index = 1;
        i_image = getImageIdAt(0);
        j_image = getImageIdAt(1);
        k_image = getImageIdAt(2);
        size = data.length;
        updateIndices(baseSet);
        select(getDataIdAt(i_index));
        applyUpdates();
        update_i_phase = false;
        is_move_k_phase = true;
    }

    public void next(ConstraintSet set) {
        if (is_swap_phase) {
            int k_index = j_index - 1;
            if (getDataAt(j_index) < getDataAt(k_index)) {
                swapData(j_index, k_index);
                swapDataIds(j_index, k_index);
                buildChain(set);
                has_swapped = true;
            }
            is_swap_phase = false;
        } else if (is_move_k_phase) {
            is_swap_phase = true;
            is_move_k_phase = false;
            select(getDataIdAt(j_index - 1));
        } else {
            deselect(getDataIdAt(j_index));
            --j_index;
            if (j_index == 0 || !has_swapped) {
                ++i_index;
                deselect(getDataIdAt(j_index));
                if (i_index == size) {
                    --i_index;
                    is_sorted = true;
                } else {
                    select(getDataIdAt(i_index));
                    is_move_k_phase = true;
                }
                j_index = i_index;
                update_i_phase = true;
            } else {
                select(getDataIdAt(j_index-1));
                is_swap_phase = true;
                has_swapped = false;
            }
        }
        updateIndices(set);
        update_i_phase = false;
    }

    public boolean hasNext() {
        return !(is_sorted);
    }

    public boolean isSortingAlgorithm() {
        return false;
    }

    private void updateIndices(ConstraintSet set) {
        updateIndex(set, i_image, getDataIdAt(i_index));
        if (update_i_phase) {
            updateIndex(set, j_image, i_image);
            updateIndex(set, k_image, j_image);
        } else {
            int target = i_image;
            if (i_index != j_index) {
                target = getDataIdAt(j_index);
            }
            updateIndex(set, j_image, target);
            updateIndex(set, k_image, getDataIdAt(j_index - 1));
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeIntArray(imageIds);
        out.writeIntArray(dataIds);
        out.writeIntArray(data);
        out.writeIntArray(new int[] {size, i_index, j_index, i_image, j_image, k_image,
                (is_sorted ? 1 : 0), (update_i_phase ? 1 : 0), (is_swap_phase ? 1 : 0)});
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
        update_i_phase = (other[7] == 1);
        is_swap_phase = (other[8] == 1);
    }
}
