package com.fambam.algorithmic.algorithmic;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.constraint.ConstraintSet;
import android.view.View;

public abstract class ArrayAlgorithm extends Algorithm {

  int size;

  public void initialize(
      View parent, ConstraintSet baseSet, int[] imageIds, int[] dataIds, int[] data) {
    super.initialize(parent, baseSet, imageIds, dataIds, data);
    this.setSizeConstaints(baseSet, imageIds, 120, 120);
    this.setSizeConstaints(baseSet, dataIds, 120, 120);
    this.initializeDataViews();
    this.buildStructure(baseSet);
  }

  public abstract void next(ConstraintSet currentSet);

  public abstract boolean hasNext();

  abstract void updateSelectors(ConstraintSet currentSet);

  public abstract void loadState(AlgorithmState state);

  public abstract AlgorithmState getState();

  abstract void updateHighlights();

  public abstract boolean isSearchingAlgorithm();

  abstract void resetIndices();

  void reset(ConstraintSet set) {
    states.clear();
    resetHighlights();
    resetIndices();
    size = data.length;
    updateSelectors(set);
    updateHighlights();
    applyUpdates();
  }

  final void setAlgorithmInfo(int[] data, int[] dataIds) {
    this.data = data.clone();
    this.dataIds = dataIds.clone();
  }

  final void createUserArray(ConstraintSet baseSet) {
    this.setSizeConstaints(baseSet, dataIds, 120, 120);
    this.initializeDataViews();
    this.buildStructure(baseSet);
  }

  final void buildStructure(ConstraintSet currentSet) {
    if (dataIds.length < 2) {
      currentSet.centerHorizontally(dataIds[0], ConstraintSet.PARENT_ID);
    } else {
      currentSet.createHorizontalChain(
          ConstraintSet.PARENT_ID,
          ConstraintSet.LEFT,
          ConstraintSet.PARENT_ID,
          ConstraintSet.RIGHT,
          this.dataIds,
          null,
          ConstraintSet.CHAIN_SPREAD);
    }
  }

  final void updateIndex(ConstraintSet currentSet, int indexId, int targetId) {
    currentSet.clear(indexId);
    currentSet.constrainWidth(indexId, 120);
    currentSet.constrainHeight(indexId, 120);
    currentSet.connect(indexId, ConstraintSet.BOTTOM, targetId, ConstraintSet.TOP);
    currentSet.connect(indexId, ConstraintSet.LEFT, targetId, ConstraintSet.LEFT);
  }

  final void setSizeConstaints(ConstraintSet currentSet, int[] ids, int width, int height) {
    for (int id : ids) {
      currentSet.constrainWidth(id, width);
      currentSet.constrainHeight(id, height);
      currentSet.centerVertically(id, ConstraintSet.PARENT_ID);
    }
  }

  final void swapData(int dataLoc1, int dataLoc2) {
    int temp = this.getDataAt(dataLoc1);
    this.setDataAt(dataLoc1, this.getDataAt(dataLoc2));
    this.setDataAt(dataLoc2, temp);
  }

  final void swapDataIds(int dataIdLoc1, int dataIdLoc2) {
    int temp = this.getDataIdAt(dataIdLoc1);
    this.setDataIdAt(dataIdLoc1, this.getDataIdAt(dataIdLoc2));
    this.setDataIdAt(dataIdLoc2, temp);
  }

  private final void initializeDataViews() {
    for (int dataId : dataIds) {
      DataView dView = parent.findViewById(dataId);
      dView.setTextColor(Color.BLACK);
      GradientDrawable gd = new GradientDrawable();
      gd.setColor(Color.WHITE);
      gd.setStroke(5, Color.BLACK);
      gd.setCornerRadius(4);
      dView.setBackground(gd);
    }
  }
}
