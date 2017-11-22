package com.fambam.algorithmic.algorithmic;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlgorithmFragment extends Fragment {
    private Algorithm algorithm;
    private TextView tv_searching;
    private int[] dataIds;
    private int[] imageIds;
    private View view;

    public AlgorithmFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onActivityCreated(savedInstanceState);

        // A default view must be instantiated on creation of a Fragment
        view = inflater.inflate(R.layout.fragment_algorithm, container, false);

        // Keys to retrieve values
        String algoKey = getString(R.string.algo_key);
        String drawKey = getString(R.string.drawables);
        String dataKey = getString(R.string.data);
        int[] drawableIds = null;
        int[] data = null;
        Bundle bundle = getArguments();

        // Retrieve values from bundle if successfully delivered
        if (bundle != null) {
            algorithm = bundle.getParcelable(algoKey);
            drawableIds = bundle.getIntArray(drawKey);
            data = bundle.getIntArray(dataKey);
        }

        // If sorting algorithm, use the TextView inside fragment to display info. Else, empty
        if (this.algorithm.isSearchingAlgorithm()) {
            tv_searching = view.findViewById(R.id.tv_search);
            tv_searching.setText("Search: 6");
        }

        // Construct the ImageViews from passed in drawableIds and add them to the ConstraintView
        ConstraintLayout baseLayout = view.findViewById(R.id.algorithm_fragment_layout);

        imageIds = new int[drawableIds.length];
        dataIds = new int[data.length];

        for (int i = 0; i < data.length; ++i) {
            dataIds[i] = 200+i;
            DataView dView = new DataView(getActivity());
            dView.setId(dataIds[i]);
            dView.setText(Integer.toString(data[i]));
            dView.setLayoutParams(new ConstraintLayout.LayoutParams(120, 120));
            dView.setGravity(Gravity.CENTER);
            baseLayout.addView(dView);
        }

        for (int i = 0; i < imageIds.length; i++) {
            imageIds[i] = 100+i;
            DataView pointer = new DataView(getActivity());
            pointer.setId(imageIds[i]);
            pointer.setText(Character.toString((char) drawableIds[i]));
            pointer.setBackgroundColor(Color.WHITE);
            pointer.setTextColor(Color.BLACK);
            pointer.setGravity(Gravity.CENTER);
            baseLayout.addView(pointer);
        }

        ConstraintSet set = new ConstraintSet();
        set.clone(baseLayout);
        this.algorithm.initialize(view, set, imageIds, dataIds, data);
        int x = this.algorithm.getID();
        set.applyTo(baseLayout);

        return view;
    }

    public void swap() {
        ConstraintLayout baseLayout = getActivity().findViewById(R.id.algorithm_fragment_layout);
        ConstraintSet set = new ConstraintSet();
        set.clone(baseLayout);

        if (this.algorithm.hasNext()) {
            this.algorithm.next(set);
        }
        TransitionManager.beginDelayedTransition(baseLayout);
        set.applyTo(baseLayout);
        algorithm.applyUpdates();
    }

    public void back() {
        if (algorithm.hasBack()) {
            ConstraintLayout baseLayout = getActivity().findViewById(R.id.algorithm_fragment_layout);
            ConstraintSet set = new ConstraintSet();
            set.clone(baseLayout);
            algorithm.back(set);
            TransitionManager.beginDelayedTransition(baseLayout);
            set.applyTo(baseLayout);
            algorithm.applyUpdates();
        }
    }

    // Clears all the dynamic content
    public void clear() {
        ConstraintLayout baseLayout = getActivity().findViewById(R.id.algorithm_fragment_layout);
        baseLayout.removeAllViews();
    }

    public View reset() {
        int[] data = null;
        int[] drawableIds = null;
        String dataKey = getString(R.string.data);
        String drawKey = getString(R.string.drawables);
        Bundle bundle = getArguments();

        clear();

        // Retrieve values from bundle if successfully delivered
        if (bundle != null) {
            data = bundle.getIntArray(dataKey);
            drawableIds = bundle.getIntArray(drawKey);
        }

        // Construct the ImageViews from passed in drawableIds and add them to the ConstraintView
        ConstraintLayout baseLayout = view.findViewById(R.id.algorithm_fragment_layout);

        for (int i = 0; i < data.length; ++i) {
            dataIds[i] = 200+i;
            DataView dView = new DataView(getActivity());
            dView.setId(dataIds[i]);
            dView.setText(Integer.toString(data[i]));
            dView.setLayoutParams(new ConstraintLayout.LayoutParams(120, 120));
            dView.setGravity(Gravity.CENTER);
            baseLayout.addView(dView);
        }

        for (int i = 0; i < imageIds.length; i++) {
            imageIds[i] = 100+i;
            DataView pointer = new DataView(getActivity());
            pointer.setId(imageIds[i]);
            pointer.setText(Character.toString((char) drawableIds[i]));
            pointer.setBackgroundColor(Color.WHITE);
            pointer.setTextColor(Color.BLACK);
            pointer.setGravity(Gravity.CENTER);
            baseLayout.addView(pointer);
        }

        ConstraintSet set = new ConstraintSet();
        set.clone(baseLayout);
        this.algorithm.initialize(view, set, imageIds, dataIds, data);
        set.applyTo(baseLayout);

        return view;

    }

    public View addView() {
        int[] data = null;
        String dataKey = getString(R.string.data);
        Bundle bundle = getArguments();
        if (bundle != null) {
            data = bundle.getIntArray(dataKey);
        }

        clear();
        dataIds = new int[data.length];

        // TODO start dynamically populating layout one element at a time

        // Construct the ImageViews from passed in drawableIds and add them to the ConstraintView
        ConstraintLayout baseLayout = view.findViewById(R.id.algorithm_fragment_layout);

        for (int i = 0; i < data.length; ++i) {
            dataIds[i] = 200+i;
            DataView dView = new DataView(getActivity());
            dView.setId(dataIds[i]);
            dView.setText(Integer.toString(data[i]));
            dView.setLayoutParams(new ConstraintLayout.LayoutParams(120, 120));
            dView.setGravity(Gravity.CENTER);
            baseLayout.addView(dView);
        }

        ConstraintSet set = new ConstraintSet();
        set.clone(baseLayout);
        this.algorithm.setAlgorithmInfo(data, dataIds);
        this.algorithm.createUserArray(set);
        set.applyTo(baseLayout);

        return view;
    }
}
