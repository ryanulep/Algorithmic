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

    public LinearSearch() {
        super();
        this.setID(4);
    }

    public void initialize(View parent, ConstraintSet baseSet,
                           int[] imageIds, int[] dataIds, int[] data) {
        super.initialize(parent, baseSet, imageIds, dataIds, data);
        reset(baseSet);
    }

    @Override
    void reset(ConstraintSet set) {
        super.reset(set);
        locating = 6;
        is_found = false;
    }

    void resetIndices() {
        i_image = getImageIdAt(0);
        i_index = 0;
    }

    public void next(ConstraintSet set) {
        AlgorithmState state = this.getState();
        this.states.push(state);
        if (hasNext()) {
            if (getDataAt(i_index) == locating) {
                is_found = true;
                select(getDataIdAt(i_index));
            }
            else {
                i_index++;
                if (i_index > size) {
                    --i_index;
                }
            }
        }
        else if (i_index == dataIds.length - 1) {
            // Do nothing
        }
        updateSelectors(set);
        updateHighlights();
    }

    public boolean hasNext() {
        return !is_found;
    }

    void updateSelectors(ConstraintSet currentSet) {
        updateIndex(currentSet, i_image, getDataIdAt(i_index));
    }

    void updateHighlights() {
        int[] newHighlights = this.getHighlights(new int[] {i_index});
        this.applyHighlightDifference(this.highlights, newHighlights);
        this.highlights = newHighlights;
    }

    public void loadState(AlgorithmState state) {
        this.i_index = state.selectors[0];
        this.is_found = state.flags[0];
        this.data = state.data;
        this.dataIds = state.dataIds;
        this.highlights = state.highlights;
    }

    public AlgorithmState getState() {
        int[] selectors = new int[] {i_index};
        boolean[] flags = new boolean[] {is_found};
        return new AlgorithmState(selectors, this.highlights.clone(), this.data.clone(),
                this.dataIds.clone(), flags);
    }

    public boolean isSearchingAlgorithm() {
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
        out.writeInt(algoID);
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
        algoID = in.readInt();
    }
}
