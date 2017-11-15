package com.fambam.algorithmic.algorithmic;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;

/**
 * Created by Guthrie on 11/13/2017.
 */

public class DataView extends AppCompatTextView {

    private int backGroundColor = Color.WHITE;

    public DataView(Context context) {
        super(context);
    }

    public void setBackColor(int resId) {
        this.backGroundColor = resId;
    }

    public int getBackgroundColor() {
        return this.backGroundColor;
    }
}
