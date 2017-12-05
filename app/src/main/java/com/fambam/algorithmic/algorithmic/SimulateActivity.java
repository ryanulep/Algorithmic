package com.fambam.algorithmic.algorithmic;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;

public class SimulateActivity extends AppCompatActivity {
    private Algorithm algorithm;
    private AlgorithmFragment algoFragment;
    private Button nextButton;
    private Button backButton;
    private Button newButton;
    private Button removeButton;
    private Button addButton;
    private Button resetButton;
    private EditText editText;
    private boolean doneFlag;
    private ArrayList<Integer> resetData = new ArrayList<>();
    private ArrayList<Integer> resetImageIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation);

        nextButton = findViewById(R.id.btn_next_sim);
        backButton = findViewById(R.id.btn_back_sim);
        newButton = findViewById(R.id.btn_new_sim);
        addButton = findViewById(R.id.btn_add_sim);
        removeButton = findViewById(R.id.btn_remove_sim);
        resetButton = findViewById(R.id.btn_reset_sim);
        editText = findViewById(R.id.user_input);

        // Disabling remove and add buttons on creation
        addButton.setEnabled(false);
        removeButton.setEnabled(false);
        backButton.setEnabled(false);

        // Set default text for next button
        nextButton.setText("Next");

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        // Get the drawable identifiers from the intent
        String algoKey = getString(R.string.algo_key);
        String drawKey = getString(R.string.drawables);
        String dataKey = getString(R.string.data);
        Intent callingIntent = getIntent();
        this.algorithm = callingIntent.getParcelableExtra(algoKey);
        int[] drawableIds = callingIntent.getIntArrayExtra(drawKey);
        int[] data = callingIntent.getIntArrayExtra(dataKey);
        resetData = fillResetInfo(data);
        resetImageIds = fillResetInfo(drawableIds);

        // Passing data to algorithm fragment through the Bundle() class
        Bundle algorithmBundle = new Bundle();
        algorithmBundle.putParcelable(algoKey, (Parcelable) this.algorithm);
        algorithmBundle.putIntArray(drawKey, drawableIds);
        algorithmBundle.putIntArray(dataKey, data);
        algoFragment = new AlgorithmFragment();
        algoFragment.setArguments(algorithmBundle);

        // Adding fragments to activity
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.simulation_fragment, algoFragment);
        ft.commit();

        // Next button functionality
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!doneFlag) {
                    boolean hasNext = algoFragment.swap();
                    if (!hasNext) {
                        nextButton.setEnabled(false);
                    }
                    backButton.setEnabled(true);
                } else {
                    View view = getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    algoFragment.startNew();
                    nextButton.setText("Next");
                    addButton.setEnabled(false);
                    removeButton.setEnabled(false);
                    resetButton.setEnabled(true);
                    doneFlag = false;
                }
            }
        });

        // Back button functionality
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hasBack = algoFragment.back();
                nextButton.setEnabled(true);
                if (!hasBack) {
                    backButton.setEnabled(false);
                }
            }
        });

        // New button functionality
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                algoFragment.clear();
                editText.setText("");
                editText.setHint("Enter value then press ADD to build custom array");
                nextButton.setText("Done");
                nextButton.setEnabled(false);
                backButton.setEnabled(false);
                resetButton.setEnabled(false);
                removeButton.setEnabled(false);
                addButton.setEnabled(true);
                doneFlag = true;
                resetData.clear();
            }
        });

        // Reset button functionality
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dataKey = getString(R.string.data);
                String drawKey = getString(R.string.drawables);
                int[] data = new int[resetData.size()];
                int[] drawableIds = new int[resetImageIds.size()];

                for (int i = 0; i < resetData.size(); i++)
                    data[i] = resetData.get(i);

                for (int i = 0; i < resetImageIds.size(); i++)
                    drawableIds[i] = resetImageIds.get(i);

                Bundle algorithmBundle = algoFragment.getArguments();
                algorithmBundle.putIntArray(dataKey, data);
                algorithmBundle.putIntArray(drawKey, drawableIds);
                algoFragment.reset();
                nextButton.setEnabled(true);
                backButton.setEnabled(false);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dataKey = getString(R.string.data);
                Bundle algorithmBundle = algoFragment.getArguments();

                if(!TextUtils.isEmpty(editText.getText().toString()) && resetData.size() < 8) {
                    String str = editText.getText().toString();
                    editText.setText("");
                    resetData.add(Integer.valueOf(str));

                    int[] data = new int[resetData.size()];
                    for (int i = 0; i < resetData.size(); i++)
                        data[i] = resetData.get(i);

                    algorithmBundle.putIntArray(dataKey, data);
                    algoFragment.updateViews();
                }
                else if (resetData.size()==8) {
                    editText.setText("");
                    editText.setHint("Max entries for array is 8");
                }

                nextButton.setEnabled(isReadyToSimulate());
                removeButton.setEnabled(resetData.size() > 0);

            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dataKey = getString(R.string.data);
                Bundle algorithmBundle = algoFragment.getArguments();
                resetData.remove(resetData.size() - 1);
                int[] data = new int[resetData.size()];
                for (int i = 0; i < resetData.size(); i++)
                    data[i] = resetData.get(i);
                algorithmBundle.putIntArray(dataKey, data);
                algoFragment.updateViews();
                nextButton.setEnabled(isReadyToSimulate());
                removeButton.setEnabled(resetData.size() > 0);
            }
        });
    }

    // Ready to simulate once there are at least 2 values in array
    private boolean isReadyToSimulate() {
        return resetData.size() > 1;
    }

    private ArrayList<Integer> fillResetInfo(int[] data) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            temp.add(data[i]);
        }
        return temp;
    }
}
