package com.fambam.algorithmic.algorithmic;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.drawable.GradientDrawable;
import android.widget.TextView;

/**
 * Created by Guthrie on 11/10/2017.
 */

public class UpdateTextViewBackground implements UIUpdate {
    int colorTo;
    int colorFrom;
    DataView dView;

    UpdateTextViewBackground(int colorTo, DataView dView) {
        super();
        this.colorFrom = dView.getBackgroundColor();
        this.colorTo = colorTo;
        this.dView = dView;
        this.dView.setBackColor(colorTo);
    }

    public void apply() {
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(),
                colorFrom, colorTo);
        colorAnimation.setDuration(500); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                GradientDrawable gd = (GradientDrawable) dView.getBackground();
                gd.setColor((int) animator.getAnimatedValue());
            }
        });
        colorAnimation.start();
    }
}
