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

        // Retrieve values from bundle and check if successfully delivered
        Bundle bundle = getArguments();
        if (bundle != null) {
            assets = bundle.getParcelable(assetKey);
        }
        explanations = new ArrayList<>();
        indexExplanations = 0;
        read();
        explain();

        return view;
    }

    private void read() {
        String line = "";
        try {
            InputStream is = getActivity().getAssets().open(assets.getExplanationFilename());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            txt = new String(buffer);

            while ((line = br.readLine()) != null) {
                explanations.add(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void explain() {
        if (explanations != null) {
            int size = explanations.size();
            if (indexExplanations < size) {
                txtExplanation.setText(explanations.get(indexExplanations++));
            }
        }
    }
}
