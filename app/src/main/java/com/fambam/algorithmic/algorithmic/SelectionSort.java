package com.fambam.algorithmic.algorithmic;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.constraint.ConstraintSet;
import android.view.View;

public class SelectionSort extends ArrayAlgorithm implements Parcelable {

  private int i_index;
  private int j_index;
  private int i_image;
  private int j_image;
  private int min_index;
  private boolean is_sorted;
  private boolean is_swap_phase;
  private boolean swapped;
  private boolean has_min_changed;

  public SelectionSort() {
    super();
    this.setID(2);
  }

  public void initialize(
      View parent, ConstraintSet baseSet, int[] imageIds, int[] dataIds, int[] data) {
    super.initialize(parent, baseSet, imageIds, dataIds, data);
    reset(baseSet);
  }

  @Override
  void reset(ConstraintSet set) {
    super.reset(set);
    is_sorted = false;
    is_swap_phase = false;
    swapped = false;
    has_min_changed = false;
  }

  void resetIndices() {
    i_image = getImageIdAt(0);
    j_image = getImageIdAt(1);
    i_index = 0;
    j_index = 1;
    min_index = 0;
  }

  public void next(ConstraintSet set) {
    AlgorithmState state = this.getState();
    this.states.push(state);
    if (is_swap_phase && has_min_changed) {
      swapData(i_index, min_index);
      swapDataIds(i_index, min_index);
      is_swap_phase = false;
      min_index = i_index;
      swapped = true;
      has_min_changed = false;
      this.buildStructure(set);
    } else if (is_swap_phase && !has_min_changed) {
      i_index++;
      is_sorted = i_index == dataIds.length - 1;
      if (hasNext()) {
        j_index = i_index + 1;
        min_index = i_index;
      }
      is_swap_phase = false;
    } else if (swapped) {
      i_index++;
      is_sorted = i_index == dataIds.length - 1;
      if (hasNext()) {
        j_index = i_index + 1;
        min_index = i_index;
      }
      swapped = false;
      if (is_sorted) {
        deselectAll();
        return;
      }
    } else {
      if (getDataAt(j_index) < getDataAt(min_index)) {
        min_index = j_index;
        has_min_changed = true;
      }
      is_swap_phase = j_index == dataIds.length - 1;

      if (!is_swap_phase) {
        j_index++;
      }
    }
    this.updateSelectors(set);
    this.updateHighlights();
  }

  public boolean hasNext() {
    return !is_sorted;
  }

  public boolean isSearchingAlgorithm() {
    return false;
  }

  void updateSelectors(ConstraintSet currentSet) {
    updateIndex(currentSet, i_image, getDataIdAt(i_index));
    if (is_sorted) {
      updateIndex(currentSet, j_image, i_image);
      this.deselectAll();
      return;
    } else {
      updateIndex(currentSet, j_image, getDataIdAt(j_index));
    }
  }

  void updateHighlights() {
    int[] newHighlights = this.getHighlights(new int[]{min_index});
    this.applyHighlightDifference(this.highlights, newHighlights);
    this.highlights = newHighlights;
  }

  public void loadState(AlgorithmState state) {
    i_index = state.selectors[0];
    j_index = state.selectors[1];
    min_index = state.selectors[2];
    is_sorted = state.flags[0];
    is_swap_phase = state.flags[1];
    swapped = state.flags[2];
    has_min_changed = state.flags[3];
    this.data = state.data;
    this.dataIds = state.dataIds;
    this.highlights = state.highlights;
  }

  public AlgorithmState getState() {
    int[] selectors = new int[]{i_index, j_index, min_index};
    boolean[] flags = new boolean[]{is_sorted, is_swap_phase, swapped, has_min_changed};
    return new AlgorithmState(
        selectors, this.highlights.clone(), this.data.clone(), this.dataIds.clone(), flags);
  }

  public int describeContents() {
    return 0;
  }

  public void writeToParcel(Parcel out, int flags) {
    out.writeIntArray(imageIds);
    out.writeIntArray(data);
    out.writeInt(size);
    out.writeInt(i_index);
    out.writeInt(j_index);
    out.writeInt(min_index);
    out.writeInt((is_sorted ? 1 : 0));
    out.writeInt(algoID);
  }

  public static final Creator<SelectionSort> CREATOR =
      new Creator<SelectionSort>() {
        public SelectionSort createFromParcel(Parcel in) {
          return new SelectionSort(in);
        }
        public SelectionSort[] newArray(int size) {
          return new SelectionSort[size];
        }
      };

  private SelectionSort(Parcel in) {
    imageIds = in.createIntArray();
    data = in.createIntArray();
    size = in.readInt();
    i_index = in.readInt();
    j_index = in.readInt();
    min_index = in.readInt();
    is_sorted = (in.readInt() == 1);
    algoID = in.readInt();
  }
}
