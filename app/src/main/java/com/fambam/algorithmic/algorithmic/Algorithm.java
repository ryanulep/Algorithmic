package com.fambam.algorithmic.algorithmic;

import android.app.Activity;
import android.graphics.Color;
import android.support.constraint.ConstraintSet;
import android.view.View;

import java.util.LinkedList;

/**
 * Created by Guthrie on 11/4/2017.
 */

public abstract class Algorithm {
    View parent;
    int[] imageIds;
    int[] dataIds;
    int[] data;
    int[] highlights;
    LinkedList<UIUpdate> updates = new LinkedList<>();
    LinkedList<AlgorithmState> states = new LinkedList<>();

    abstract void initialize(View parent, ConstraintSet baseSet,
                             int[] imageIds, int[] dataIds, int[] data);
    abstract void next(ConstraintSet currentSet);
    abstract boolean hasNext();
    abstract void loadState(AlgorithmState state);
    abstract void buildStructure(ConstraintSet currentSet);
    abstract void updateSelectors(ConstraintSet currentSet);
    abstract void updateHighlights();
    abstract AlgorithmState getState();

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
    abstract boolean isSortingAlgorithm();

    final void applyUpdates() {
        while(!updates.isEmpty()) {
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
}
