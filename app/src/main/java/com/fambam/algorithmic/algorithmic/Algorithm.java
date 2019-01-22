package com.fambam.algorithmic.algorithmic;

import android.graphics.Color;
import android.support.constraint.ConstraintSet;
import android.view.View;
import java.util.LinkedList;

public abstract class Algorithm {

  View parent;
  int algoID;
  int[] imageIds;
  int[] dataIds;
  int[] data;
  int[] highlights;
  LinkedList<UIUpdate> updates = new LinkedList<>();
  LinkedList<AlgorithmState> states = new LinkedList<>();

  void initialize(View parent, ConstraintSet set, int[] imageIds, int[] dataIds, int[] data) {
    this.parent = parent;
    this.imageIds = imageIds;
    this.dataIds = dataIds;
    this.data = data;
    resetHighlights();
    this.highlights = new int[data.length];
  }

  abstract void next(ConstraintSet currentSet);

  abstract boolean hasNext();

  abstract void loadState(AlgorithmState state);

  abstract void buildStructure(ConstraintSet currentSet);

  abstract void updateSelectors(ConstraintSet currentSet);

  abstract void updateHighlights();

  abstract void setAlgorithmInfo(int[] data, int[] dataIds);

  abstract void createUserArray(ConstraintSet currentSet);

  abstract AlgorithmState getState();

  abstract void reset(ConstraintSet set);

  final void back(ConstraintSet currentSet) {
    AlgorithmState state = states.pop();
    int[] oldHighlights = this.highlights.clone();
    this.loadState(state);
    this.buildStructure(currentSet);
    this.applyHighlightDifference(oldHighlights, this.highlights);
    this.updateSelectors(currentSet);
  }

  final boolean hasBack() {
    return !states.isEmpty();
  }

  abstract boolean isSearchingAlgorithm();

  final void applyUpdates() {
    while (!updates.isEmpty()) {
      updates.getLast().apply();
      updates.removeLast();
    }
  }

  final int getDataIdAt(int dataIdLoc) {
    return this.dataIds[dataIdLoc];
  }

  final void setDataIdAt(int dataIdLoc, int dataId) {
    this.dataIds[dataIdLoc] = dataId;
  }

  final int getDataAt(int dataLoc) {
    return this.data[dataLoc];
  }

  final void setDataAt(int dataLoc, int data) {
    this.data[dataLoc] = data;
  }

  final int getImageIdAt(int imageIdLoc) {
    return this.imageIds[imageIdLoc];
  }

  final void setSelectorIds(int[] selectors) {
    this.imageIds = selectors.clone();
  }

  final void resetHighlights() {
    this.highlights = new int[data.length];
  }

  final int[] getHighlights(int[] selections) {
    int[] newHighlights = new int[this.highlights.length];
    for (int i : selections) {
      newHighlights[i] = 1;
    }
    return newHighlights;
  }

  final void applyHighlightDifference(int[] oldHighlights, int[] newHighlights) {
    for (int i = 0; i < newHighlights.length; ++i) {
      if (oldHighlights[i] != newHighlights[i]) {
        if (oldHighlights[i] == 0) {
          select(getDataIdAt(i));
        } else {
          deselect(getDataIdAt(i));
        }
      }
    }
  }

  final void deselectAll() {
    applyHighlightDifference(this.highlights, new int[this.highlights.length]);
    resetHighlights();
  }

  final void select(int dataIdLoc) {
    DataView dView = parent.findViewById(dataIdLoc);
    dView.getBackground();
    updates.addFirst(new UpdateTextViewBackground(Color.CYAN, dView));
  }

  final void select(int dataIdLoc, int colorTo) {
    DataView dView = parent.findViewById(dataIdLoc);
    dView.getBackground();
    updates.addFirst(new UpdateTextViewBackground(colorTo, dView));
  }

  final void deselect(int dataIdLoc) {
    DataView dView = parent.findViewById(dataIdLoc);
    updates.addFirst(new UpdateTextViewBackground(Color.WHITE, dView));
  }

  final int getID() {
    return algoID;
  }

  final void setID(int algoID) {
    this.algoID = algoID;
  }
}
