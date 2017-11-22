package com.fambam.algorithmic.algorithmic;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
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
                if (isReadyToSimulate()) {
                    nextButton.setText("Next");
                    algoFragment.swap();
                }
            }
        });

        // Back button functionality
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                algoFragment.back();
            }
        });

        // New button functionality
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                algoFragment.clear();
                nextButton.setText("Done");
                nextButton.setEnabled(false);
                addButton.setEnabled(true);
                removeButton.setEnabled(true);
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

                Bundle algorithmBundle = new Bundle();
                algorithmBundle.putIntArray(dataKey, data);
                algorithmBundle.putIntArray(drawKey, drawableIds);
                algoFragment.setArguments(algorithmBundle);
                algoFragment.reset();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dataKey = getString(R.string.data);
                Bundle algorithmBundle = new Bundle();

                if(!TextUtils.isEmpty(editText.getText().toString()) && resetData.size() < 8) {
                    String str = editText.getText().toString();
                    editText.setText("");
                    resetData.add(Integer.valueOf(str));

                    int[] data = new int[resetData.size()];
                    for (int i = 0; i < resetData.size(); i++)
                        data[i] = resetData.get(i);

                    algorithmBundle.putIntArray(dataKey, data);
                    algoFragment.setArguments(algorithmBundle);
                    algoFragment.addView();
                }
                else if (TextUtils.isEmpty(editText.getText().toString()) || resetData.size()==8) {
                    // Do nothing
                }
                else {
                    editText.setHint("Max entries for array is 8");
                }
                if (isReadyToSimulate()) {
                    nextButton.setEnabled(true);
                }
            }
        });
    }

    // Ready to simulate once there are at least 2 values in array
    private boolean isReadyToSimulate() {
        if (resetData.size() > 1)
            return true;
        else
            return false;
    }

    private ArrayList<Integer> fillResetInfo(int[] data) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            temp.add(data[i]);
        }
        return temp;
    }
}
