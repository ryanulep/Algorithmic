package com.fambam.algorithmic.algorithmic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Guthrie on 11/1/2017.
 */

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toGraphics(View view) {
        int[] drawables = new int[] {
                R.drawable.example1,
                R.drawable.example,
                R.drawable.example1,
                R.drawable.example};
        Algorithm algorithm = new BubbleSort();
        int[] ordering = new int[] {2, 1, 2, 1};
        String algoKey = getString(R.string.algo_key);
        String drawKey = getString(R.string.drawables);
        String orderKey = getString(R.string.ordering);
        Intent intent = new Intent(this, AlgorithmActivity.class);
        intent.putExtra(drawKey, drawables);
        intent.putExtra(algoKey, (Parcelable) algorithm);
        intent.putExtra(orderKey, ordering);
        startActivity(intent);
    }
}
