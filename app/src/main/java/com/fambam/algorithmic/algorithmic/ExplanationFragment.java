package com.fambam.algorithmic.algorithmic;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExplanationFragment extends Fragment {
    private AlgorithmAssets assets;
    private TextView txtExplanation;
    private String txt;

    public ExplanationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explanation, container, false);
        txtExplanation = view.findViewById(R.id.tv_explanation);
        read();
        return view;
    }

    private void read() {
        txt = "";
        try {
            InputStream is = getActivity().getAssets().open("BubbleSort.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            txt = new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void explain() {
        txtExplanation.setText(txt);
    }
}