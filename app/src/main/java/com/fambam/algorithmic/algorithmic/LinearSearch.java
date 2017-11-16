package com.fambam.algorithmic.algorithmic;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.constraint.ConstraintSet;
import android.view.View;

public class LinearSearch extends ArrayAlgorithm implements Parcelable {
    private int size;
    private int i_index;
    private int i_image;
    private int locating;
    private boolean is_found;

    public LinearSearch() { super(); }

    public void initialize(View parent, ConstraintSet baseSet,
                           int[] imageIds, int[] dataIds, int[] data) {
        super.initialize(parent, baseSet, imageIds, dataIds, data);
        i_image = imageIds[0];
        i_index = 0;
        size = this.data.length;
        is_found = false;
        locating = 6;
        updateIndicies(baseSet);
        applyUpdates();
    }

    public void next(ConstraintSet set) {
        if (hasNext()) {
            if (getDataAt(i_index) == locating) {
                is_found = true;
                select(getDataIdAt(i_index));
            }
            else {
                i_index++;
            }
        }
        else if (i_index == dataIds.length - 1) {
            // Do nothing
        }
        updateIndicies(set);
    }

    public boolean hasNext() {
        return !is_found;
    }

    private void updateIndicies(ConstraintSet currentSet) {
        updateIndex(currentSet, i_image, getDataIdAt(i_index));
    }

    public boolean isSortingAlgorithm() {
        return true;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeIntArray(imageIds);
        out.writeIntArray(dataIds);
        out.writeIntArray(data);
        out.writeInt(size);
        out.writeInt(i_index);
        out.writeInt(locating);
        out.writeInt((is_found ? 1 : 0));
    }

    public static final Parcelable.Creator<LinearSearch> CREATOR =
            new Parcelable.Creator<LinearSearch>() {
                public LinearSearch createFromParcel(Parcel in) {
                    return new LinearSearch(in);
                }

                public LinearSearch[] newArray(int size) {
                    return new LinearSearch[size];
                }
            };

    private LinearSearch(Parcel in) {
        imageIds = in.createIntArray();
        dataIds = in.createIntArray();
        data = in.createIntArray();
        size = in.readInt();
        i_index = in.readInt();
        locating = in.readInt();
        is_found = (in.readInt() == 1);
    }
}
