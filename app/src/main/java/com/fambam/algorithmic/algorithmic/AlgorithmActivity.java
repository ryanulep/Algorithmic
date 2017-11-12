package com.fambam.algorithmic.algorithmic;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AlgorithmActivity extends AppCompatActivity {
    private Algorithm algorithm;
    private AlgorithmFragment algoFragment;
    private ExplanationFragment explainFragment;
    private UpdateOrdering updateOrdering;
    private static final int ALGO = 0;
    private static final int EXPLAIN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics);

        //Get the drawable identifiers from the intent
        String algoKey = getString(R.string.algo_key);
        String drawKey = getString(R.string.drawables);
        String dataKey = getString(R.string.data);
        String orderKey = getString(R.string.ordering);
        Intent callingIntent = getIntent();
        this.algorithm = callingIntent.getParcelableExtra(algoKey);
        int[] drawableIds = callingIntent.getIntArrayExtra(drawKey);
        int[] data = callingIntent.getIntArrayExtra(dataKey);
        this.updateOrdering = callingIntent.getParcelableExtra(orderKey);

        // Passing data to fragment through the Bundle() class
        Bundle bundle = new Bundle();
        bundle.putParcelable(algoKey, (Parcelable) this.algorithm);
        bundle.putIntArray(drawKey, drawableIds);
        bundle.putIntArray(dataKey, data);
        algoFragment = new AlgorithmFragment();
        explainFragment = new ExplanationFragment();
        algoFragment.setArguments(bundle);

        // Adding fragment to activity
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.algorithm_fragment, algoFragment);
        ft.add(R.id.explanation_fragment, explainFragment);
        ft.commit();
    }

    public void swap(View view) {
        int option = updateOrdering.next();
        if (option == ALGO) {
            algoFragment.swap();
        }
        else if (option == EXPLAIN) {
            explainFragment.explain();
        }
    }
}
