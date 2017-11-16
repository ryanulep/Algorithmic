package com.fambam.algorithmic.algorithmic;

/**
 * Created by Guthrie on 11/15/2017.
 */

public class AlgorithmState {
    public int[] selectors;
    public int[] highlights;
    public boolean[] flags;
    public int[] data;
    public int[] dataIds;

    public AlgorithmState(int[] selectors, int[] highlights, int[] data,
                          int[] dataIds, boolean[] flags) {
        this.selectors = selectors;
        this.highlights = highlights;
        this.flags = flags;
        this.data = data;
        this.dataIds = dataIds;
    }
}
