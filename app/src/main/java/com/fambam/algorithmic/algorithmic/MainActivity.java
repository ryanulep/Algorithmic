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

    public void toBubbleSort(View view) {
        int[] drawables = new int[] {
                R.drawable.examplei,
                R.drawable.examplej,
                R.drawable.examplek};
        int[] data = new int[] {3,4,6,7,1,2,1};
        Algorithm algorithm = new BubbleSort();
        startAlgorithm(algorithm, data, drawables);
    }

    public void toSelectionSort(View view) {
        int[] drawables = new int[] {
                R.drawable.examplei,
                R.drawable.examplej
        };
        int[] data = new int[] {3,5,1,6,2,4,7,8};
        Algorithm algorithm = new SelectionSort();
        startAlgorithm(algorithm, data, drawables);
    }

    public void startAlgorithm(Algorithm algorithm, int[] data, int[] drawables) {
        String algoKey = getString(R.string.algo_key);
        String drawKey = getString(R.string.drawables);
        String dataKey = getString(R.string.data);
        Intent intent = new Intent(this, AlgorithmActivity.class);
        intent.putExtra(drawKey, drawables);
        intent.putExtra(algoKey, (Parcelable) algorithm);
        intent.putExtra(dataKey, data);
        startActivity(intent);
    }
}
