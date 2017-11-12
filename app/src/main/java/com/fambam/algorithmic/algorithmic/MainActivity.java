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
        int[] data = new int[] {3, 4, 6, 7, 1, 2, 1};
        Algorithm algorithm = new BubbleSort();
        UpdateOrdering ordering = new UpdateOrdering(new int[] {0,0,1});
        startAlgorithm(algorithm, data, drawables, ordering);
    }

    public void toSelectionSort(View view) {
        int[] drawables = new int[] {
                R.drawable.examplei,
                R.drawable.examplej
        };
        int[] data = new int[] {5,8,9,44,5,6,3,5};
        Algorithm algorithm = new SelectionSort();
        UpdateOrdering ordering = new UpdateOrdering(new int[] {1});
        startAlgorithm(algorithm, data, drawables, ordering);
    }

    public void startAlgorithm(Algorithm algorithm, int[] data, int[] drawables,
                               UpdateOrdering ordering) {
        String algoKey = getString(R.string.algo_key);
        String drawKey = getString(R.string.drawables);
        String dataKey = getString(R.string.data);
        String orderKey = getString(R.string.ordering);
        Intent intent = new Intent(this, AlgorithmActivity.class);
        intent.putExtra(drawKey, drawables);
        intent.putExtra(algoKey, (Parcelable) algorithm);
        intent.putExtra(dataKey, data);
        intent.putExtra(orderKey, ordering);
        startActivity(intent);
    }
}
