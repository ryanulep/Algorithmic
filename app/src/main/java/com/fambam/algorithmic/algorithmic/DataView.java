package com.fambam.algorithmic.algorithmic;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;

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
