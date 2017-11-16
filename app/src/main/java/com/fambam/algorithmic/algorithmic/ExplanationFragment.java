package com.fambam.algorithmic.algorithmic;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExplanationFragment extends Fragment {
    private AlgorithmAssets assets;
    private TextView txtExplanation;
    private ArrayList<String> explanations;
    private int indexExplanations;

    public ExplanationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String assetKey = getString(R.string.asset);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explanation, container, false);
        txtExplanation = view.findViewById(R.id.tv_explanation);
        explanations = new ArrayList<>();
        indexExplanations = -1;     // Set to -1 because explain()

        // Retrieve values from bundle and check if successfully delivered
        Bundle bundle = getArguments();
        if (bundle != null) {
            assets = bundle.getParcelable(assetKey);
        }
        read();
        explain();

        return view;
    }

    private void read() {
        String line = "";
        try {
            InputStream is = getActivity().getAssets().open(assets.getExplanationFilename());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                explanations.add(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void explain() {
        if (explanations != null && explanations.size() > 0) {
            int size = explanations.size();
            if (indexExplanations < size-1 && indexExplanations >= -1) {
                txtExplanation.setText(explanations.get(++indexExplanations));
            }
            else {
                txtExplanation.setText(explanations.get(explanations.size() - 1));
            }
        }
    }

    public void back() {
        if (explanations != null && explanations.size() > 0) {
            if (indexExplanations > 0) {
                txtExplanation.setText(explanations.get(--indexExplanations));
            }
            else {
                txtExplanation.setText(explanations.get(0));
            }
        }
    }
}
