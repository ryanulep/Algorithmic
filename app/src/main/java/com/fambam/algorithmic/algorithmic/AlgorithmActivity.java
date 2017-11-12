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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics);

        //Get the drawable identifiers from the intent
        String algoKey = getString(R.string.algo_key);
        String drawKey = getString(R.string.drawables);
        String dataKey = getString(R.string.data);
        Intent callingIntent = getIntent();
        this.algorithm = callingIntent.getParcelableExtra(algoKey);
        int[] drawableIds = callingIntent.getIntArrayExtra(drawKey);
        int[] data = callingIntent.getIntArrayExtra(dataKey);

        // Passing data to fragment through the Bundle() class
        Bundle bundle = new Bundle();
        bundle.putParcelable(algoKey, (Parcelable) this.algorithm);
        bundle.putIntArray(drawKey, drawableIds);
        bundle.putIntArray(dataKey, data);
        AlgorithmFragment algorithmFragment = new AlgorithmFragment();
        ExplanationFragment explanationFragment = new ExplanationFragment();
        algorithmFragment.setArguments(bundle);

        // Adding fragment to activity
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.algorithm_fragment, algorithmFragment);
        ft.add(R.id.explanation_fragment, explanationFragment);
        ft.commit();
    }

    public void swap(View view) {
        // Getting reference to fragment in order to call its method
        FragmentManager fm = getFragmentManager();
        AlgorithmFragment af = (AlgorithmFragment)fm.findFragmentById(R.id.algorithm_fragment);
        af.swap();

        ExplanationFragment ef = (ExplanationFragment)fm.findFragmentById(R.id.explanation_fragment);
        ef.explain();
    }
}
